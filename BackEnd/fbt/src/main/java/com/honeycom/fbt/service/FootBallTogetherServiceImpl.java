package com.honeycom.fbt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.honeycom.fbt.dao.FootBallTogetherDAO_LJH;
import com.honeycom.fbt.dto.Employment;
import com.honeycom.fbt.dto.TeamMember;
import com.honeycom.fbt.dto.User;
import com.honeycom.fbt.dto.Vote;
import com.honeycom.fbt.dto.VoteResult;

@Service
public class FootBallTogetherServiceImpl implements FootBallTogetherService_LJH {

	@Autowired
	private FootBallTogetherDAO_LJH footBallTogetherDAO_LJH;

	@Override
	public User selectUser(User user) {
		return footBallTogetherDAO_LJH.selectUser(user);
	}

	@Override
	public List<Employment> selectEmployments(Employment employment) {
		return footBallTogetherDAO_LJH.selectEmployments(employment);
	}

	@Override
	public List<TeamMember> selectTeamMembers(TeamMember teamMember) {
		return footBallTogetherDAO_LJH.selectTeamMembers(teamMember);
	}

	@Override
	public List<Vote> selectVotes(Vote vote) {
		return footBallTogetherDAO_LJH.selectVotes(vote);
	}

	@Transactional
	public int insertVote(Vote vote) {
		return footBallTogetherDAO_LJH.insertVote(vote);
	}

	@Transactional
	public boolean insertVoteResult(VoteResult voteResult) {
		return footBallTogetherDAO_LJH.insertVoteResult(voteResult);
	}

	@Override
	public List<VoteResult> selectVoteResults(VoteResult voteResult) {
		return footBallTogetherDAO_LJH.selectVoteResults(voteResult);
	}

	@Override
	public boolean updateVoteResult(VoteResult voteResult) {
		return footBallTogetherDAO_LJH.updateVoteResult(voteResult);
	}



}
