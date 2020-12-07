package com.biomans.fbt.votematch.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biomans.fbt.domain.Invite;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.domain.VoteMatchSetting;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.search.dao.SearchDAO;
import com.biomans.fbt.util.Attendance;
import com.biomans.fbt.util.VoteMatchRes;
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

	@Override
	public List<VoteMatch> showVoteMatchInfoByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		List<VoteMatch> voteMatchList = voteMatchDAO.showVoteMatchInfoByTeam(searchCon);
		List<VoteMatch> numList = voteMatchDAO.showVoteMatchNumByVote(searchCon.get("teamId"));
		for(VoteMatch voteMatch1 : voteMatchList) {
//			투표 인원 정보 넣기 & 투표 인원 명단 넣기
			for(VoteMatch voteMatch2 : numList) {
				if(voteMatch1.getVoteMatchId().equals(voteMatch2.getVoteMatchId())) {
					voteMatch1.setVotedNum(voteMatch2.getVotedNum());
					voteMatch1.setAttendNum(voteMatch2.getAttendNum());
					voteMatch1.setAbscentNum(voteMatch2.getAbscentNum());
					voteMatch1.setFriendNum(voteMatch2.getFriendNum());
					voteMatch1.setTotalAttend(voteMatch2.getAttendNum()+voteMatch2.getFriendNum());
				}
			}
			ArrayList<VoteMatchResult> voteMatchResults = 
					(ArrayList<VoteMatchResult>) voteMatchDAO.showVoteMatchResultByVote(voteMatch1.getVoteMatchId()); 
			voteMatch1.setVoteMatchResults(voteMatchResults);
			
//			투표 인원 명단 정보 가공하기
			for(VoteMatchResult voteMatchResult : voteMatchResults) {
				if(voteMatchResult.getTeamMember().getUser().getEmail() == null) {
					voteMatchResult.getTeamMember().getUser().setEmail(voteMatchResult.getUser().getEmail() + " (지인)");
				}
			}
		}
		return voteMatchList;
	}
	
	@Override
	public void updateVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException {
		voteMatchDAO.updateVoteMatchResult(voteMatchResult);
		
	}

	@Override
	public void addAttendance(VoteMatchResult voteMatchResult) throws SQLException {
		voteMatchDAO.addAttendance(voteMatchResult);
		
	}

	@Override
	public void inviteFriend(Invite invite) throws SQLException {
		voteMatchDAO.inviteFriend(invite);
		
	}

	@Override
	@Transactional
	public void addVoteMatchAndSetting(VoteMatchRes voteMatchRes) throws SQLException {
		VoteMatch voteMatch = voteMatchRes.getVoteMatch();
		SearchReservation searchRes = voteMatchRes.getSearchReservation();
		//S001
		MatchSchedule matchSchedule = voteMatch.getMatchSchedule();
		if(matchSchedule.getMatchScheduleId() == 0) { //신규 투표 생성이라면
			if(matchSchedule.getAwayTeam().getTeamId() == 0) matchSchedule.setAwayTeam(null);
			matchScheduleDAO.addMatchSchedule(matchSchedule);
			//S002
			int teamId = voteMatch.getTeam().getTeamId();
			int matchScheduleId = matchScheduleDAO.showLatestMatchScheduleIdById(teamId);
			// voteMatchId 설정
			String voteMatchId = String.valueOf(teamId) + "-" + String.valueOf(matchScheduleId);
			voteMatch.getMatchSchedule().setMatchScheduleId(matchScheduleId);
			voteMatch.setVoteMatchId(voteMatchId);
			voteMatch.getVoteMatchSetting().setVoteMatchId(voteMatchId);
		} else { // 매칭을 통한 투표 생성이라면
			// matchSchedule에서 상대팀으로 등록하지 않는다
			// voteMatchId 설정2
			int teamId = voteMatch.getMatchSchedule().getAwayTeam().getTeamId();
			int matchScheduleId = voteMatch.getMatchSchedule().getMatchScheduleId();
			String voteMatchId = String.valueOf(teamId) + "-" + String.valueOf(matchScheduleId);
			voteMatch.getTeam().setTeamId(teamId);
			voteMatch.setVoteMatchId(voteMatchId);
			voteMatch.getVoteMatchSetting().setVoteMatchId(voteMatchId);
			searchDAO.updateResStatus(searchRes);
		}
		voteMatchDAO.addVoteMatch(voteMatch);
		voteMatchDAO.addVoteMatchSetting(voteMatch.getVoteMatchSetting());
		
		
	}

	@Override
	public void endVoteMatch(VoteMatch voteMatch) throws SQLException {
		voteMatchDAO.endVoteMatch(voteMatch);
		
	}

	@Override
	public void updateVoteMatch(VoteMatch voteMatch) throws SQLException {
		voteMatchDAO.updateVoteMatch(voteMatch);
		
	}

	@Override
	public void deleteVoteMatch(String voteMatchId) throws SQLException {
		voteMatchDAO.deleteVoteMatch(voteMatchId);
		
	}

	@Override
	public void updateVoteMatchSetting(VoteMatchSetting voteMatchSetting) throws SQLException {
		voteMatchDAO.updateVoteMatchSetting(voteMatchSetting);
		
	}

	@Override
	public void deleteVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException {
		voteMatchDAO.deleteVoteMatchResult(voteMatchResult);
		
	}

	@Override
	public List<User> searchFriend(HashMap<String, String> searchCon) throws SQLException {
		return voteMatchDAO.searchFriend(searchCon);
	}

	@Override
	public VoteMatch showVoteMatchInfoByScheduleId(int matchScheduleId) throws SQLException {
		VoteMatch voteMatch = voteMatchDAO.showVoteMatchInfoByScheduleId(matchScheduleId);
		VoteMatch num = voteMatchDAO.showVoteMatchNumByScheduleId(matchScheduleId);
		ArrayList<VoteMatchResult> voteMatchResults = voteMatchDAO.showVoteMatchResultByScheduleId(matchScheduleId);
		// 각각의 정보를 삽입
		if(num != null) {
			voteMatch.setVotedNum(num.getVotedNum());
			voteMatch.setAttendNum(num.getAttendNum());
			voteMatch.setAbscentNum(num.getAbscentNum());
			voteMatch.setFriendNum(num.getFriendNum());
			voteMatch.setTotalAttend(num.getAttendNum()+num.getFriendNum());
		}
		
//		투표 인원 명단 정보 가공하기
		for(VoteMatchResult voteMatchResult : voteMatchResults) {
			if(voteMatchResult.getTeamMember().getUser().getEmail() == null) {
				voteMatchResult.getTeamMember().getUser().setEmail(voteMatchResult.getUser().getEmail() + " (지인)");
			}
		}
		voteMatch.setVoteMatchResults(voteMatchResults);
		return voteMatch;
	}

	@Override
	public List<VoteMatchResult> showVoteMatchResultByVote(String voteMatchId) throws SQLException {
		return voteMatchDAO.showVoteMatchResultByVote(voteMatchId);
	}
	
	//
	//
	public void checkMinNum(String voteMatchId) throws SQLException {
		String[] arr = voteMatchId.split("-");
		HashMap<String, String> con = new HashMap<String, String>();
		con.put("voteMatchId", voteMatchId);
		con.put("matchScheduleId", arr[1]);
		con.put("takerTeamId", arr[0]);
		Attendance attendance = searchDAO.checkMinNum(con);
		if(attendance != null) {
			int minNumber = attendance.getMinNumber();
			int totalNumber = attendance.getTotalFriend() + attendance.getTotalMember();
			int resStatus = attendance.getReservationStatus();
			con.put("searchId", attendance.getSearchId()+"");
			if(minNumber <= totalNumber && resStatus != -1) {
				// 해당 신청 매치 완료
				searchDAO.completeSearch(con);
				// 나머지 신청 매치 실패
				searchDAO.failSearch(con);
				// 일정에서 awayTeam으로 등록
				matchScheduleDAO.addAwayTeam(con);
			}
		}
		
	}

}
