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
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.domain.VoteMatchSetting;
import com.biomans.fbt.votematch.dao.VoteMatchDAO;
import com.biomans.fbt.votematch.service.VoteMatchService;

@Service
public class VoteMatchServiceImpl implements VoteMatchService {
	@Autowired
	private VoteMatchDAO voteMatchDAO;

	@Override
	public List<VoteMatch> showVoteMatchInfoByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		List<VoteMatch> voteMatchList = voteMatchDAO.showVoteMatchInfoByTeam(searchCon);
		List<VoteMatch> numList = voteMatchDAO.showVoteMatchNumByVote(searchCon.get("teamId"));
		ArrayList<VoteMatchResult> voteMatchResults = voteMatchDAO.showVoteMatchResult(searchCon.get("teamId")); 
//		투표 인원 명단 정보 가공하기
		for(VoteMatchResult voteMatchResult : voteMatchResults) {
			if(voteMatchResult.getTeamMember().getUser().getEmail() == null) {
				voteMatchResult.getTeamMember().getUser().setEmail(voteMatchResult.getUser().getEmail() + " (지인)");
			}
		}
		for(VoteMatch voteMatch1 : voteMatchList) {
//			투표 인원 정보 넣기 & 투표 인원 명단 넣기
			for(VoteMatch voteMatch2 : numList) {
				if(voteMatch1.getVoteMatchId().equals(voteMatch2.getVoteMatchId())) {
					voteMatch1.setVotedNum(voteMatch2.getVotedNum());
					voteMatch1.setAttendNum(voteMatch2.getAttendNum());
					voteMatch1.setAbscentNum(voteMatch2.getAbscentNum());
					voteMatch1.setFriendNum(voteMatch2.getFriendNum());
					voteMatch1.setTotalAttend(voteMatch2.getAttendNum()+voteMatch2.getFriendNum());
					voteMatch1.setVoteMatchResults(voteMatchResults);
				}
			}
//			away팀이 미정인 경우 미정 결과 넣기
			if(voteMatch1.getMatchSchedule().getAwayTeam() == null) {
				Team awayTeam = new Team();
				awayTeam.setTeamName("미정");
				voteMatch1.getMatchSchedule().setAwayTeam(awayTeam);
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
	public void addVoteMatchAndSetting(VoteMatch voteMatch) throws SQLException {
		System.out.println(1);
		voteMatchDAO.addVoteMatch(voteMatch);
		System.out.println(2);
		voteMatchDAO.addVoteMatchSetting(voteMatch.getVoteMatchSetting());
		System.out.println(3);
		
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

}
