package com.biomans.fbt.votematch.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.domain.VoteMatchSetting;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.notice.dao.NoticeDAO;
import com.biomans.fbt.search.dao.SearchDAO;
import com.biomans.fbt.teammember.dao.TeamMemberDAO;
import com.biomans.fbt.util.Attendance;
import com.biomans.fbt.votematch.dao.VoteMatchDAO;
import com.biomans.fbt.votematch.service.VoteMatchService;

@Service
public class VoteMatchServiceImpl implements VoteMatchService {
	@Autowired
	private VoteMatchDAO voteMatchDAO;
	
	@Autowired
	private MatchScheduleDAO matchScheduleDAO;
	
	@Autowired
	private SearchDAO searchDAO;
	

// ============================================================================================================ //	
	
	//V02-2, FS02
	@Override
	@Transactional
	public List<VoteMatch> showVoteMatchInfoByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		//1. 팀별 투표를 가져온다.
		List<VoteMatch> voteMatchList = voteMatchDAO.showVoteMatchInfoByTeam(searchCon);
		for(VoteMatch voteMatch1 : voteMatchList) {
			VoteMatch desc = voteMatchDAO.showVoteMatchNumByVote(voteMatch1.getVoteMatchId());
			// 2. 투표 명단 요약 삽입
			if(desc != null) {
				voteMatch1.setVotedNum(desc.getVotedNum());
				voteMatch1.setAttendNum(desc.getAttendNum());
				voteMatch1.setAbscentNum(desc.getAbscentNum());
				voteMatch1.setFriendNum(desc.getFriendNum());
				voteMatch1.setTotalAttend(desc.getAttendNum() + desc.getFriendNum());
			}
			
			// 3. 투표 명단 삽입
			ArrayList<VoteMatchResult> voteMatchResults = 
					(ArrayList<VoteMatchResult>) voteMatchDAO.showVoteMatchResultByVote(voteMatch1.getVoteMatchId()); 
			if(voteMatchResults.size() > 0) voteMatch1.setVoteMatchResults(voteMatchResults);
			// 4. 투표 명단 정보 가공: 지인이면 지인태그를 붙인다.
			for(VoteMatchResult voteMatchResult : voteMatchResults) {
				if(voteMatchResult.getUser() != null) {
					String email = voteMatchResult.getUser().getEmail();
					voteMatchResult.getUser().setEmail( email + " (지인)");
				}
			}
		}
		return voteMatchList;
	}
	
	//FV03
	@Override
	@Transactional
	public VoteMatch showVoteMatchInfoByScheduleId(HashMap<String, Integer> searchCon) throws SQLException {
		//1. 경기 일정 별 투표 정보를 가져온다
		VoteMatch voteMatch = voteMatchDAO.showVoteMatchInfoByScheduleId(searchCon);
		VoteMatch num = voteMatchDAO.showVoteMatchNumByScheduleId(searchCon);
		ArrayList<VoteMatchResult> voteMatchResults = voteMatchDAO.showVoteMatchResultByScheduleId(searchCon);
		// 2. 경기 일정별 투표 명단 요약 삽입
		if(num != null) {
			voteMatch.setVotedNum(num.getVotedNum());
			voteMatch.setAttendNum(num.getAttendNum());
			voteMatch.setAbscentNum(num.getAbscentNum());
			voteMatch.setFriendNum(num.getFriendNum());
			voteMatch.setTotalAttend(num.getAttendNum()+num.getFriendNum());
		}
		// 3. 경기 일정별 투표 명단 삽입
		if(voteMatchResults.size() > 0) voteMatch.setVoteMatchResults(voteMatchResults);
		// 4. 투표 명단 가공: 지인 추가
		for(VoteMatchResult voteMatchResult : voteMatchResults) {
			if(voteMatchResult.getUser() != null) {
				String email = voteMatchResult.getUser().getEmail();
				voteMatchResult.getUser().setEmail( email + " (지인)");
			}
		}
		// 5. 용병이 있다면, 용병 명단 담기
		
		return voteMatch;
	}
	
	//V01-6
	@Override
	@Transactional
	public void addVoteMatchAndSetting(VoteMatch voteMatch) throws SQLException {
		int teamId = 0;
		//S001
		MatchSchedule matchSchedule = voteMatch.getMatchSchedule();
		// 1. 일정등록한다
		if(matchSchedule.getAwayTeam().getTeamId() == 0) matchSchedule.setAwayTeam(null); // 상대팀이 미정인 경우, null 처리
		matchScheduleDAO.addMatchSchedule(matchSchedule);
		// 2. 갓 등록한 일정의 id를 가져와서 주입
		teamId = voteMatch.getTeam().getTeamId();
		int matchScheduleId = matchScheduleDAO.showLatestMatchScheduleIdById(teamId);
		voteMatch.getMatchSchedule().setMatchScheduleId(matchScheduleId);
		// 경기일정Id에 경기 투표 등록
		voteMatchDAO.addVoteMatch(voteMatch);
		// 가장 최근의 등록한, 해당 팀의 투표 아이디를 가져온다
		int voteMatchId = voteMatchDAO.getLatestVoteMatchIdByTeam(teamId);
		voteMatch.getVoteMatchSetting().setVoteMatchId(voteMatchId);
		// 경기투표Id에 경기투표설정 등록
		voteMatchDAO.addVoteMatchSetting(voteMatch.getVoteMatchSetting());
	}
	
	//V03-4
	@Override
	@Transactional
	public int addAttendance(VoteMatchResult voteMatchResult, VoteMatch voteMatch) throws SQLException {
		// FV05
		voteMatchDAO.addAttendance(voteMatchResult);
		int result = checkMinNum(voteMatch);
		return result;
	}
	//V03-5
	@Override
	public int checkMinNum(VoteMatch voteMatch) throws SQLException {
		// 만일 상대방 찾기를 통해 투표를 하는 거라면
		HashMap<String, String> searchCon = new HashMap<String, String>();
		searchCon.put("voteMatchId", voteMatch.getVoteMatchId()+"");
		searchCon.put("matchScheduleId", voteMatch.getMatchSchedule().getMatchScheduleId()+"");
		searchCon.put("takerTeamId", voteMatch.getTeam().getTeamId()+"");
		String searchId = voteMatchDAO.checkBySearch(searchCon);
		if(searchId != null) {
			Attendance attendance = searchDAO.checkMinNum(searchCon);
			// 맞다면 minNum을 넘기는 매번 확인
			if(attendance != null) {
				int minNumber = attendance.getMinNumber();
				int totalNumber = attendance.getTotalFriend() + attendance.getTotalMember();
				int resStatus = attendance.getReservationStatus();
				if(minNumber <= totalNumber && resStatus != -1) {
				// 넘겼다면
					// 해당 신청 매치 인원파악 완료
					SearchReservation searchRes = new SearchReservation();
					searchRes.setSearchId(attendance.getSearchId());
					searchRes.setReservationStatus(2);
					Team teamTaker = new Team();
					teamTaker.setTeamId(voteMatch.getTeam().getTeamId());
					searchRes.setTeamTaker(teamTaker);
					searchDAO.updateResStatus(searchRes);
					// 나머지 신청 매치 실패
					searchDAO.failSearch(searchCon);
					// 알림 보내기
					return attendance.getSearchId();
				}
			}
		}
		return 0;
	}
	
	//V03-6
	@Override
	public void updateVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException {
		voteMatchDAO.updateVoteMatchResult(voteMatchResult);
		
	}
	
	//V05-3, V06-1
	@Override
	@Transactional
	public void updateVoteMatch(VoteMatch voteMatch, int type, String teamName) throws SQLException {
		voteMatchDAO.updateVoteMatch(voteMatch);
		//투표수정이면 일정 수정까지 같이 한다.
		if(type == 1) {
			MatchSchedule matchSchedule = voteMatch.getMatchSchedule();
			System.out.println(matchSchedule);
			matchScheduleDAO.updateMatchSchedule(matchSchedule);
		}
		
	}
	
	//FV10
	@Override
	public void updateVoteMatchSetting(VoteMatchSetting voteMatchSetting) throws SQLException {
		voteMatchDAO.updateVoteMatchSetting(voteMatchSetting);
		
	}
	
	//FV15
	@Override
	//FV15
	public List<VoteMatch> loadEndedVoteMatch(HashMap<String, Integer> searchCon) throws SQLException {
		return voteMatchDAO.loadEndedVoteMatch(searchCon);
	}
	
	//FV17
	@Override
	@Transactional
	public VoteMatch showVoteMatchInfoById(int voteMatchId) throws SQLException {
		VoteMatch voteMatch = voteMatchDAO.showVoteMatchInfoById(voteMatchId);
		//1. 경기 일정 별 투표 정보를 가져온다
		HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
		searchCon.put("matchScheduleId", voteMatch.getMatchSchedule().getMatchScheduleId());
		searchCon.put("teamId", voteMatch.getTeam().getTeamId());
		VoteMatch num = voteMatchDAO.showVoteMatchNumByScheduleId(searchCon);
		ArrayList<VoteMatchResult> voteMatchResults = voteMatchDAO.showVoteMatchResultByScheduleId(searchCon);
		// 2. 경기 일정별 투표 명단 요약 삽입
		if(num != null) {
			voteMatch.setVotedNum(num.getVotedNum());
			voteMatch.setAttendNum(num.getAttendNum());
			voteMatch.setAbscentNum(num.getAbscentNum());
			voteMatch.setFriendNum(num.getFriendNum());
			voteMatch.setTotalAttend(num.getAttendNum()+num.getFriendNum());
		}
		// 3. 경기 일정별 투표 명단 삽입
		voteMatch.setVoteMatchResults(voteMatchResults);
		// 4. 투표 명단 가공: 지인 추가
		for(VoteMatchResult voteMatchResult : voteMatchResults) {
			if(voteMatchResult.getUser() != null) {
				String email = voteMatchResult.getUser().getEmail();
				voteMatchResult.getUser().setEmail( email + " (지인)");
			}
		}
		return voteMatch;
	}

	@Override
	public void deleteVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException {
		voteMatchDAO.deleteVoteMatchResult(voteMatchResult);
		
	}

	
}
