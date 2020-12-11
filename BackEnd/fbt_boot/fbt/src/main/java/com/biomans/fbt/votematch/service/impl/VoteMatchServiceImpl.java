package com.biomans.fbt.votematch.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biomans.fbt.domain.Invite;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.domain.VoteMatchSetting;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.search.dao.SearchDAO;
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
	
	//FV01, FV02, FS02
	@Override
	@Transactional
	public List<VoteMatch> showVoteMatchInfoByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		//1. 팀별 투표를 가져온다.
		List<VoteMatch> voteMatchList = voteMatchDAO.showVoteMatchInfoByTeam(searchCon);
		List<VoteMatch> numList = voteMatchDAO.showVoteMatchNumByVote(searchCon.get("teamId"));
		for(VoteMatch voteMatch1 : voteMatchList) {
			// 2. 투표 명단 요약 삽입
			for(VoteMatch voteMatch2 : numList) {
				if(voteMatch1.getVoteMatchId() == voteMatch2.getVoteMatchId()) {
					voteMatch1.setVotedNum(voteMatch2.getVotedNum());
					voteMatch1.setAttendNum(voteMatch2.getAttendNum());
					voteMatch1.setAbscentNum(voteMatch2.getAbscentNum());
					voteMatch1.setFriendNum(voteMatch2.getFriendNum());
					voteMatch1.setTotalAttend(voteMatch2.getAttendNum()+voteMatch2.getFriendNum());
				}
			}
			// 3. 투표 명단 삽입
			ArrayList<VoteMatchResult> voteMatchResults = 
					(ArrayList<VoteMatchResult>) voteMatchDAO.showVoteMatchResultByVote(voteMatch1.getVoteMatchId()); 
			voteMatch1.setVoteMatchResults(voteMatchResults);
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
	public VoteMatch showVoteMatchInfoByScheduleId(int matchScheduleId) throws SQLException {
		//1. 경기 일정 별 투표 정보를 가져온다
		VoteMatch voteMatch = voteMatchDAO.showVoteMatchInfoByScheduleId(matchScheduleId);
		VoteMatch num = voteMatchDAO.showVoteMatchNumByScheduleId(matchScheduleId);
		ArrayList<VoteMatchResult> voteMatchResults = voteMatchDAO.showVoteMatchResultByScheduleId(matchScheduleId);
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
	
	//FV04
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
	
	//FV05, FV06
	@Override
	@Transactional
	public void addAttendance(VoteMatchResult voteMatchResult, VoteMatch voteMatch) throws SQLException {
		// FV05
		voteMatchDAO.addAttendance(voteMatchResult);
		// FV06
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
				searchCon.put("searchId", attendance.getSearchId()+"");
				if(minNumber <= totalNumber && resStatus != -1) {
				// 넘겼다면
					// 해당 신청 매치 인원파악 완료
					searchDAO.completeSearch(searchCon);
					// 나머지 신청 매치 실패
					searchDAO.failSearch(searchCon);
				}
			}
		}
		
	}
	
	//FV05
	@Override
	public void updateVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException {
		voteMatchDAO.updateVoteMatchResult(voteMatchResult);
		
	}
	
	//FV07, FV14
	@Override
	@Transactional
	public void updateVoteMatch(VoteMatch voteMatch, int type) throws SQLException {
		voteMatchDAO.updateVoteMatch(voteMatch);
		//투표수정이면 일정 수정까지 같이 한다.
		if(type == 1) {
			MatchSchedule matchSchedule = voteMatch.getMatchSchedule();
			matchScheduleDAO.updateMatchSchedule(matchSchedule);
		}
		
	}
	
	//FV09
	@Override
	public void inviteFriend(Invite invite) throws SQLException {
		voteMatchDAO.inviteFriend(invite);
		
	}
	//FV09
	@Override
	public List<User> searchFriend(HashMap<String, String> searchCon) throws SQLException {
		return voteMatchDAO.searchFriend(searchCon);
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
	


	@Override
	public void deleteVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException {
		voteMatchDAO.deleteVoteMatchResult(voteMatchResult);
		
	}

	
}
