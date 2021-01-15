package com.biomans.fbt.matchschedule.service.impl;

import java.math.MathContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.Entry;
import com.biomans.fbt.domain.MatchResult;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.TeamScore;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.employ.dao.EmployDAO;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.util.AvgScore;
import com.biomans.fbt.util.MatchResultCollection;
import com.biomans.fbt.util.ScoreDescInput;
import com.biomans.fbt.util.SearchKey;
import com.biomans.fbt.votematch.dao.VoteMatchDAO;

@Service
public class MatchScheduleServiceImpl implements MatchScheduleService {
	@Autowired
	private MatchScheduleDAO matchScheduleDAO;
	
	@Autowired
	private VoteMatchDAO voteMatchDAO;
	
	@Autowired
	private EmployDAO employDAO;
	
	//FS01
	@Override
	public MatchSchedule showMatchScheduleByMatchScheduleId(int matchScheduleId) throws SQLException {
		return matchScheduleDAO.showMatchScheduleByMatchScheduleId(matchScheduleId);
	}
	
	//FS02
	@Override
	public List<MatchSchedule> showMatchSchduleByTeamPeriod(SearchKey searchKey) throws SQLException {
		return matchScheduleDAO.showMatchSchduleByTeamPeriod(searchKey);
	}
	
	// FV16
	@Override
	public void confirmMatchSchedule(HashMap<String, Integer> searchCon) throws SQLException {
		matchScheduleDAO.confirmMatchSchedule(searchCon);
	}
	
	//FS07
	@Override
	@Transactional
	public MatchSchedule showMatchScheduleResultByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		MatchSchedule matchSchedule = matchScheduleDAO.showMatchScheduleResult(searchCon);
		
		// 받은 팀 평가 점수 삽입. 단, 자신 팀이 받은 것만 걸러낸다.
		List<TeamScore> teamScores = matchSchedule.getTeamScores();
		List<TeamScore> selectedTeamScores = new ArrayList<TeamScore>();
		int teamId = searchCon.get("teamId");
		if(teamScores.size() > 0) {
			for(TeamScore ts : teamScores) {
				//0. 자신이 받은 것만
				int teamTakerId = ts.getTeamTaker().getTeamId();
				if(teamId != teamTakerId) continue;
				//1. 평가당 매너 평균 구하기
				double sumManner = ts.getMannerArrangement() + ts.getMannerBodyFight() +ts.getMannerContact()
					+ ts.getMannerPayment() + ts.getMannerPromise() + ts.getMannerReferee() + ts.getMannerRule()
					+ ts.getMannerSlang() + ts.getMannerSmoking() + ts.getMannerTackle() + ts.getMannerUniform();
				ts.setAvgManner(Math.round(sumManner/11*10)/10.0);
				//2. 평가당 경기력 평균 구하기
				double sumAbility = ts.getDefence() + ts.getForward() + ts.getMiddle();
				ts.setAvgAbility(Math.round(sumAbility/3*10)/10.0);
				selectedTeamScores.add(ts);
			}
			matchSchedule.setTeamScores(selectedTeamScores);
		}
		List<Entry> entries = matchSchedule.getEntries();
		List<Entry> selectedEntries = new ArrayList<Entry>();
		if(entries.size() > 0) {
			for(Entry entry : entries) {
				// 자신의 팀 엔트리만
				int entryTeamId = entry.getTeam().getTeamId();
				if(teamId != entryTeamId) continue;
				selectedEntries.add(entry);
			}
			matchSchedule.setEntries(selectedEntries);
		}
		return matchSchedule;
	}
	
	//FS08
	@Override
	@Transactional
	public void addMatchResultCollection(MatchResultCollection matchResultCollection) throws SQLException {
		// 0. 클래스 분할
		List<Entry> entries = matchResultCollection.getEntries();
		List<EmpScore> empScores = matchResultCollection.getEmpScores();
		TeamScore teamScore = matchResultCollection.getTeamScore();
		MatchResult matchResult = matchResultCollection.getMatchResult();
		
		// 부여하는 팀의 팀 평균 매너, 실력 수정
		int teamTakerId = teamScore.getTeamTaker().getTeamId();
		if(teamTakerId != 0) {
			Team teamScoreDesc = matchScheduleDAO.getTeamScoreDesc(teamTakerId);
			double teamAbility = teamScoreDesc.getTeamAbility();
			double teamManner= teamScoreDesc.getTeamManner();
			int totalRatedNum = teamScoreDesc.getTotalRatedNum();
			
			double sumTeamAbility = teamAbility * totalRatedNum;
			double sumTeamManner = teamManner * totalRatedNum;
			sumTeamAbility += teamScore.getForward() + teamScore.getMiddle() + teamScore.getDefence();
			sumTeamManner += teamScore.getMannerArrangement() + teamScore.getMannerBodyFight() +teamScore.getMannerContact()
			+ teamScore.getMannerPayment() + teamScore.getMannerPromise() + teamScore.getMannerReferee() + teamScore.getMannerRule()
			+ teamScore.getMannerSlang() + teamScore.getMannerSmoking() + teamScore.getMannerTackle() + teamScore.getMannerUniform();
			
			double newTeamAbility = sumTeamAbility / (totalRatedNum + 3);
			double newTeamManner = sumTeamManner / (totalRatedNum + 11);
			
			ScoreDescInput sdi = new ScoreDescInput();
			sdi.setTeamId(teamTakerId);
			sdi.setTeamAbility(newTeamAbility);
			sdi.setTeamManner(newTeamManner);
			matchScheduleDAO.updateTeamScoreDesc(sdi);
		}
		// 부여하는 용병의 매너, 실력 수정
		if(empScores.size() > 0) {
			for(EmpScore es : empScores) {
				String takerEmail = es.getUser().getEmail();
				User empScoreDesc = matchScheduleDAO.getEmpScoreDesc(takerEmail);
				double empAbility = empScoreDesc.getEmpAbility();
				double empManner = empScoreDesc.getEmpManner();
				int totalRatedNum = empScoreDesc.getTotalRatedNum();
				
				double sumEmpAbility = empAbility * totalRatedNum;
				double sumEmpManner = empManner * totalRatedNum;
				sumEmpAbility += es.getForward() + es.getMiddle() + es.getDefence();
				sumEmpManner += es.getMannerArrangement() + es.getMannerBodyFight() +es.getMannerContact()
				+ es.getMannerPayment() + es.getMannerPromise() + es.getMannerReferee() + es.getMannerRule()
				+ es.getMannerSlang() + es.getMannerSmoking() + es.getMannerTackle() + es.getMannerUniform();
				
				double newTeamAbility = sumEmpAbility / (totalRatedNum + 3);
				double newTeamManner = sumEmpManner / (totalRatedNum + 11);
				
				ScoreDescInput sdi = new ScoreDescInput();
				sdi.setEmail(takerEmail);
				sdi.setEmpAbility(newTeamAbility);
				sdi.setEmpManner(newTeamManner);
				matchScheduleDAO.updateEmpScoreDesc(sdi);
				
			}
		}
		
		// 2. 승패 입력
		if(matchResult != null) {
			int homeScore = matchResult.getHomeScore();
			int awayScore = matchResult.getAwayScore();
			if (homeScore > awayScore) {
				matchResult.setHomeResult(1);
				matchResult.setAwayResult(-1);
			} else if (homeScore < awayScore) {
				matchResult.setHomeResult(-1);
				matchResult.setAwayResult(1);
			} else {
				matchResult.setHomeResult(0);
				matchResult.setAwayResult(0);
			}
			matchScheduleDAO.addMatchResult(matchResult);
		}
		
		// 3. 각각 입력(for문 필요하면 사용)
		if(entries.size()>0) {
			for(Entry entry : entries) {
				matchScheduleDAO.addEntry(entry);
			}
		}
		if(empScores.size() > 0) {
			for(EmpScore empScore : empScores) {
				matchScheduleDAO.addEmpScore(empScore);
			}
		}
		if(teamScore != null) matchScheduleDAO.addTeamScore(teamScore);
		
	}
	
	// FS09
	@Override
	public List<TeamMember> showAttendVotedMember(HashMap<String, Integer> searchCon) throws SQLException {
		return matchScheduleDAO.showAttendVotedMember(searchCon);
	}
	
	// FS10
	@Override
	@Transactional
	public HashMap<String, List<User>> showAttendFriendEmploy(HashMap<String, Integer> searchCon) throws SQLException {
		HashMap<String, List<User>> friendEmployMap = new HashMap<String, List<User>>();
		// 1. 참여하기로 한 지인들을 불러온다
		List<User> friendList = matchScheduleDAO.showAttendVotedFriend(searchCon);
		// 2. 참여하기로 한 용병들을 불러온다
		int matchScheduleId = searchCon.get("matchScheduleId");
		List<User> employList = matchScheduleDAO.showAcceptedEmploy(matchScheduleId);
		// 3. 합친다.
		if(friendList.size() > 0) friendEmployMap.put("friendList", friendList);
		if(employList.size() > 0) friendEmployMap.put("employList", employList);
		
		return friendEmployMap;
	}
	
	//FS11
	@Override
	@Transactional
	public MatchResultCollection showMatchResultCollection(HashMap<String, Integer> searchCon) throws SQLException {
		MatchResultCollection mrc = new MatchResultCollection();
		MatchSchedule matchSchedule = matchScheduleDAO.showMatchScheduleResult(searchCon);
		//matchResult는 무조건 담긴다
		mrc.setMatchResult(matchSchedule.getMatchResult());
		
		// 해당 경기에 부여한 타팀 점수가 있으면 담는다
		List<TeamScore> teamScores = matchSchedule.getTeamScores();
		if(teamScores.size() > 0) {
			for(TeamScore ts : teamScores) {
				//0. 자신의 것을 제외하고 있으면 담는다 + 용병이 준 점수도 제외
				int teamId = searchCon.get("teamId");
				int teamTakerId = ts.getTeamTaker().getTeamId();
				if(teamId == teamTakerId || ts.getUser() != null) continue;
				if(ts != null) mrc.setTeamScore(ts);
			}
		}
		
		//해당경기 용병/지인 점수가 있으면 담는다
		List<Entry> entries = matchSchedule.getEntries();

		for(Entry entry : entries) {
			//용병/지인만 본다
			if(entry.getUser() != null) {
				// 해당 경기의 요청한 팀이 부여한 용병지인의 용병 점수를 뽑는다
				String email = entry.getUser().getEmail();
				HashMap<String, String> con = new HashMap<String, String>();
				con.put("email", email);
				con.put("teamId", searchCon.get("teamId")+"");
				con.put("matchScheduleId", searchCon.get("matchScheduleId")+"");
				EmpScore empScore = matchScheduleDAO.getEmpScoreByScheduleIdEmail(con);
				entry.setEmpScore(empScore);
			}
		}
		mrc.setEntries(entries);
		
		return mrc;
	}
	
	//FS11
	@Override
	@Transactional
	public void updateMatchResult(MatchResultCollection matchResultCollection) throws SQLException {
		// 0. 클래스 분할
		List<Entry> entries = matchResultCollection.getEntries();
		List<EmpScore> empScores = matchResultCollection.getEmpScores();
		TeamScore teamScore = matchResultCollection.getTeamScore();
		MatchResult matchResult = matchResultCollection.getMatchResult();
		
		// 1. 승패 입력
		if(matchResult != null) {
			int homeScore = matchResult.getHomeScore();
			int awayScore = matchResult.getAwayScore();
			if (homeScore > awayScore) {
				matchResult.setHomeResult(1);
				matchResult.setAwayResult(-1);
			} else if (homeScore < awayScore) {
				matchResult.setHomeResult(-1);
				matchResult.setAwayResult(1);
			} else {
				matchResult.setHomeResult(0);
				matchResult.setAwayResult(0);
			}
			matchScheduleDAO.updateMatchResult(matchResult);
		}
		
		// 2. 각각 입력(for문 필요하면 사용)
		if(entries.size() > 0) {
			for(Entry entry : entries) {
				matchScheduleDAO.updateEntry(entry);
			}
		}
		if(empScores.size() > 0) {
			for(EmpScore empScore : empScores) {
				matchScheduleDAO.updateEmpScore(empScore);
			}
		}
		if(teamScore != null) matchScheduleDAO.updateTeamScore(teamScore);
		
		// 3. 평균 다시 계산
		// teamScore
		if(teamScore != null) {
			int takerTeamId = teamScore.getTeamTaker().getTeamId();
			AvgScore as = matchScheduleDAO.getAvgTeamScore(takerTeamId);
			double teamAbility = (as.getForward() + as.getMiddle() + as.getDefence()) / 3;
			double teamManner =  (as.getMannerArrangement() + as.getMannerBodyFight() +as.getMannerContact()
			+ as.getMannerPayment() + as.getMannerPromise() + as.getMannerReferee() + as.getMannerRule()
			+ as.getMannerSlang() + as.getMannerSmoking() + as.getMannerTackle() + as.getMannerUniform()) / 11;
			ScoreDescInput sdi = new ScoreDescInput();
			sdi.setTeamId(takerTeamId);
			sdi.setTeamAbility(teamAbility);
			sdi.setTeamManner(teamManner);
			matchScheduleDAO.updateTeamScoreDesc(sdi);
		}
		// empScore
		if(empScores.size() > 0) {
			for(EmpScore empScore : empScores) {
				String email = empScore.getUser().getEmail();
				AvgScore as = matchScheduleDAO.getAvgEmpScore(email);
				double empAbility = (as.getForward() + as.getMiddle() + as.getDefence())/3;
				double empManner =  (as.getMannerArrangement() + as.getMannerBodyFight() +as.getMannerContact()
				+ as.getMannerPayment() + as.getMannerPromise() + as.getMannerReferee() + as.getMannerRule()
				+ as.getMannerSlang() + as.getMannerSmoking() + as.getMannerTackle() + as.getMannerUniform())/11;
				ScoreDescInput sdi = new ScoreDescInput();
				sdi.setEmail(email);
				sdi.setEmpAbility(empAbility);
				sdi.setEmpManner(empManner);
				matchScheduleDAO.updateEmpScoreDesc(sdi);
			}
		}
	}
	
	// FS12
	public List<MatchSchedule> showMatchSchduleByUserPeriod(HashMap<String, String> searchCon) throws SQLException {
		List<MatchSchedule> totalUserSchedule = new ArrayList<MatchSchedule>();
		List<MatchSchedule> teamSchedules = matchScheduleDAO.showMatchSchduleByUserPeriod(searchCon);
		// 팀일정이 하나라도 있으면 개인 일정에 담는다
		if(teamSchedules.size() > 0) {
			totalUserSchedule.addAll(teamSchedules);
		}
		String email = searchCon.get("email");
		List<MatchSchedule> empSchedules = matchScheduleDAO.showMatchScheduleByEmployPeriod(searchCon);
		// 용병 일정이 하나라도 있으면 개인 일정에 담는다
		if(empSchedules.size() > 0) {
			totalUserSchedule.addAll(empSchedules );
		}
		// 지인 일정이 하나라도 있으면 개인 일정에 넣는다
		List<MatchSchedule> friendSchedules = matchScheduleDAO.showMatchScheduleByFriendPeriod(searchCon);
		if(friendSchedules.size() > 0) {
			totalUserSchedule.addAll(friendSchedules);
		}
		return totalUserSchedule;
	}
	
	//FS14
	@Override
	public MatchSchedule showMatchScheduleResultByUser(HashMap<String, Integer> searchCon, String email) throws SQLException {
		MatchSchedule matchSchedule = matchScheduleDAO.showMatchScheduleResult(searchCon);
		//1. 자신의 속한 팀의 teamId를 먼저 찾는다
		List<Entry> entries = matchSchedule.getEntries();
		int teamId = 0;
		for(Entry entry : entries) {
			String entryEmail = "";
			if(entry.getUser() != null) entryEmail = entry.getUser().getEmail();
			if(email.equals(entryEmail)) {
				teamId = entry.getTeam().getTeamId();
				break;
			}
		}
		//2. 자신의 속한 팀의 entry만 가져온다
		List<Entry> selectedEntries = new ArrayList<Entry>();
		for(Entry entry : entries) {
			int entryTeamId = entry.getTeam().getTeamId();
			if(teamId != entryTeamId) continue;
			selectedEntries.add(entry);
		}
		matchSchedule.setEntries(selectedEntries);
		
		return matchSchedule;
	}
	
	// FS15
	@Override
	public void addTeamScore(TeamScore teamScore) throws SQLException {
		matchScheduleDAO.addTeamScore(teamScore);
	}
	
	//FS16
	@Override
	public void updateTeamScore(TeamScore teamScore) throws SQLException {
		matchScheduleDAO.updateTeamScore(teamScore);
	}
	
	//FS17
	@Override
	public List<MatchSchedule> showFutureSchedule(int teamId) throws SQLException {
		return matchScheduleDAO.showFutureSchedule(teamId);
	}
	
	//FS18
	@Override
	public void updateMatchSchedule(MatchSchedule matchSchedule) throws SQLException {
		matchScheduleDAO.updateMatchSchedule(matchSchedule);
	}
	
	//FS19: 일정 삭제
	@Override
	public void deleteMatchSchedule(int matchScheduleId) throws SQLException {
		matchScheduleDAO.deleteMatchSchedule(matchScheduleId);
	}
	
	//S001: 일정 등록
	@Override
	public void addMatchSchedule(MatchSchedule matchSchedule) throws SQLException {
		matchScheduleDAO.addMatchSchedule(matchSchedule);
		
	}
	
	// S002: 방금 동록한 일정 ID 출력
	@Override
	public int showLatestMatchScheduleIdById(int teamId) throws SQLException {
		return matchScheduleDAO.showLatestMatchScheduleIdById(teamId);
	}
	
	

	
	
	

	
	
	
	
	//S009
	
	
	

}
