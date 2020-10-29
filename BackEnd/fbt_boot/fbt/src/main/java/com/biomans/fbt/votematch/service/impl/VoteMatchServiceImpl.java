package com.biomans.fbt.votematch.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biomans.fbt.domain.Invite;
import com.biomans.fbt.domain.Team;
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
	public List<VoteMatch> showVoteMatchInfoByTeam(int teamId) throws SQLException {
		List<VoteMatch> voteMatchList = voteMatchDAO.showVoteMatchInfoByTeam(teamId);
		List<VoteMatch> numList = voteMatchDAO.showVoteMatchNumByVote(teamId);
		for(VoteMatch voteMatch1 : voteMatchList) {
//			투표 인원 정보 넣기
			for(VoteMatch voteMatch2 : numList) {
				if(voteMatch1.getVoteMatchId().equals(voteMatch2.getVoteMatchId())) {
					voteMatch1.setVotedNum(voteMatch2.getVotedNum());
					voteMatch1.setAttendNum(voteMatch2.getAttendNum());
					voteMatch1.setAbscentNum(voteMatch2.getAbscentNum());
					voteMatch1.setFriendNum(voteMatch2.getFriendNum());
					voteMatch1.setTotalAttend(voteMatch2.getAttendNum()+voteMatch2.getFriendNum());
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

}
