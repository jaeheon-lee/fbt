package com.biomans.fbt.matchschedule.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.ls.LSInput;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.Entry;
import com.biomans.fbt.domain.MatchResult;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.TeamScore;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.util.MatchResultCollection;
import com.biomans.fbt.util.SearchKey;

@Service
public class MatchScheduleServiceImpl implements MatchScheduleService {
	@Autowired
	private MatchScheduleDAO matchScheduleDAO;
	
	//FS01
	@Override
	public MatchSchedule showMatchScheduleByMatchScheduleId(int matchScheduleId) throws SQLException {
		return matchScheduleDAO.showMatchScheduleByMatchScheduleId(matchScheduleId);
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
	
	// S00N: 일정 삭제
	@Override
	public void deleteMatchSchedule(int matchScheduleId) throws SQLException {
		matchScheduleDAO.deleteMatchSchedule(matchScheduleId);
	}

	@Override
	public List<MatchSchedule> showMatchSchduleByTeamPeriod(SearchKey searchKey) throws SQLException {
		return matchScheduleDAO.showMatchSchduleByTeamPeriod(searchKey);
	}
	
	// S005-2: 등록된 개인 경기 일정 출력
	public List<MatchSchedule> showMatchSchduleByUserPeriod(HashMap<String, String> searchCon) throws SQLException {
		List<MatchSchedule> totalUserSchedule = new ArrayList<MatchSchedule>();
		List<MatchSchedule> teamSchedules = matchScheduleDAO.showMatchSchduleByUserPeriod(searchCon);
		// 팀일정이 하나라도 있으면 개인 일정에 담는다
		if(teamSchedules != null) {
			totalUserSchedule.addAll(teamSchedules);
		}
		String email = searchCon.get("email");
		List<MatchSchedule> empSchedules = matchScheduleDAO.showMatchScheduleByEmployPeriod(email);
		// 용병 일정이 하나라도 있으면 개인 일정에 담는다
		if(empSchedules != null) {
			totalUserSchedule.addAll(empSchedules );
		}
		return totalUserSchedule;
	}

	
	
	// S007
	@Override
	public List<TeamMember> showAttendVotedMember(String voteMatchId) throws SQLException {
		return matchScheduleDAO.showAttendVotedMember(voteMatchId);
	}
	
	// S008
	@Override
	public HashMap<String, List<User>> showAttendFriendEmploy(HashMap<String, String> searchCon) throws SQLException {
		HashMap<String, List<User>> friendEmployMap = new HashMap<String, List<User>>();
		// 1. 참여하기로 한 지인들을 불러온다
		String voteMatchId = searchCon.get("voteMatchId");
		List<User> friendList = matchScheduleDAO.showAttendVotedFriend(voteMatchId);
		// 2. 참여하기로 한 용병들을 불러온다
		int matchScheduleId = Integer.valueOf(searchCon.get("matchScheduleId"));
		List<User> employList = matchScheduleDAO.showAcceptedEmploy(matchScheduleId);
		// 3. 합친다.
		if(friendList.size() > 0) friendEmployMap.put("friendList", friendList);
		if(employList.size() > 0) friendEmployMap.put("employList", employList);
		
		return friendEmployMap;
	}
	
	//S009
	@Override
	@Transactional
	public void addMatchResultCollection(MatchResultCollection matchResultCollection) throws SQLException {
		// 0. 클래스 분할
		List<Entry> entries = matchResultCollection.getEntries();
		List<EmpScore> empScores = matchResultCollection.getEmpScores();
		TeamScore teamScore = matchResultCollection.getTeamScore();
		MatchResult matchResult = matchResultCollection.getMatchResult();
		// 1. 미정인데 상대팀의 변화가 있는 경우, 확인하고 없으면 입력, 있으면 패스
		int matchScheduleId = matchResult.getMatchSchedule().getMatchScheduleId();
		int awayTeamOfDB = matchScheduleDAO.checkAwayTeam(matchScheduleId);
		int awayTeamIdOfFront = teamScore.getTeamTaker().getTeamId();
		if(awayTeamOfDB != awayTeamIdOfFront) {
			HashMap<String, String> searchCon = new HashMap<String, String>();
			searchCon.put("takerTeamId", awayTeamIdOfFront+"");
			searchCon.put("matchScheduleId", matchScheduleId+"");
			matchScheduleDAO.addAwayTeam(searchCon);
		}
		// 2. 승패 입력
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
		// 3. 각각 입력(for문 필요하면 사용)
		for(Entry entry : entries) {
			matchScheduleDAO.addEntry(entry);
		}
		for(EmpScore empScore : empScores) {
			matchScheduleDAO.addEmpScore(empScore);
		}
		matchScheduleDAO.addMatchResult(matchResult);
		matchScheduleDAO.addTeamScore(teamScore);
	}
	
	//S010
	// S010
	public MatchSchedule showMatchScheduleResult(int matchScheduleId) throws SQLException {
		MatchSchedule matchSchedule = matchScheduleDAO.showMatchScheduleResult(matchScheduleId);
		List<TeamScore> teamScores = matchSchedule.getTeamScores();
		for(TeamScore ts : teamScores) {
			//1. 평가당 매너 평균 구하기
			double sumManner = ts.getMannerArrangement() + ts.getMannerBodyFight() +ts.getMannerContact()
				+ ts.getMannerPayment() + ts.getMannerPromise() + ts.getMannerReferee() + ts.getMannerRule()
				+ ts.getMannerSlang() + ts.getMannerSmoking() + ts.getMannerTackle() + ts.getMannerUniform();
			ts.setAvgManner(Math.round(sumManner/11*10)/10.0);
			//2. 평가당 경기력 평균 구하기
			double sumAbility = ts.getDefence() + ts.getForward() + ts.getMiddle();
			ts.setAvgAbility(Math.round(sumAbility/3*10)/10.0);
		}
		matchSchedule.setTeamScores(teamScores);
		return matchSchedule;
	}
	
	// S011
	public void addTeamScore(TeamScore teamScore) throws SQLException {
		matchScheduleDAO.addTeamScore(teamScore);
	}

}
