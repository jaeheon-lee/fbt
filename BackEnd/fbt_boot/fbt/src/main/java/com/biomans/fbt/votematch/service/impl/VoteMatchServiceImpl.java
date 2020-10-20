package com.biomans.fbt.votematch.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biomans.fbt.domain.Invite;
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
		List<VoteMatch> list = voteMatchDAO.showVoteMatchInfoByTeam(teamId);
		for(VoteMatch voteMatch : list) {
			int votedNum = 0;
			int attendNum = 0;
			int friendNum = 0;
			ArrayList<VoteMatchResult> vmrlist = voteMatch.getVoteMatchResults();
			for(VoteMatchResult vmr : vmrlist) {
				if(vmr.getUser() != null) friendNum++;
				if(vmr.getUser() == null) {
					votedNum++;
					if(vmr.getAttendance()==1) attendNum++;
				}
			}
			voteMatch.setVotedNum(votedNum);
			voteMatch.setFriendNum(friendNum);
			voteMatch.setAttendNum(attendNum);
			voteMatch.setAbscentNum(votedNum-attendNum);
		}
		
		return list;
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
