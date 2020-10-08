package com.honeycom.fbt.dao;

import java.util.List;

import com.honeycom.fbt.dto.Employment;
import com.honeycom.fbt.dto.MatchInfo;
import com.honeycom.fbt.dto.TeamMember;
import com.honeycom.fbt.dto.User;
import com.honeycom.fbt.dto.Vote;
import com.honeycom.fbt.dto.VoteResult;

public interface FootBallTogetherDAO_LJH {
	public User selectUser(User user);

	public List<Employment> selectEmployments(Employment employment);
	public List<TeamMember> selectTeamMembers(TeamMember teamMember);
	public List<Vote> selectVotes(Vote vote);
	public int insertVote(Vote vote);
	public boolean insertVoteResult(VoteResult voteResult);
	public List<VoteResult> selectVoteResults(VoteResult voteResult);
	public boolean updateVoteResult(VoteResult voteResult);
	public int insertMatchInfo(MatchInfo matchInfo);
}
