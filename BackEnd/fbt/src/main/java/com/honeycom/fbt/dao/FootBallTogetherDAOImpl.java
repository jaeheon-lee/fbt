package com.honeycom.fbt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.honeycom.fbt.dto.Employment;
import com.honeycom.fbt.dto.TeamMember;
import com.honeycom.fbt.dto.User;
import com.honeycom.fbt.dto.Vote;
import com.honeycom.fbt.dto.VoteResult;

@Repository
public class FootBallTogetherDAOImpl implements FootBallTogetherDAO_LJH{
	
	@Autowired
	private SqlSession sqlSession;
	private final String NS = "sql.footBAllTogether.mapper." ;
	
	@Override
	public User selectUser(User user) {
		return sqlSession.selectOne(NS+"selectUser", user);
	}

	@Override
	public List<Employment> selectEmployments(Employment employment) {
		return sqlSession.selectList(NS+"selectEmployments", employment);
	}

	@Override
	public List<TeamMember> selectTeamMembers(TeamMember teamMember) {
		return sqlSession.selectList(NS+"selectTeamMembers", teamMember);
	}

	@Override
	public List<Vote> selectVotes(Vote vote) {
		return sqlSession.selectList(NS+"selectVotes", vote);
	}

	@Override
	public int insertVote(Vote vote) {
		return sqlSession.insert(NS+"insertVote", vote);
	}

	@Override
	public boolean insertVoteResult(VoteResult voteResult) {
		return sqlSession.insert(NS+"insertVoteResult", voteResult) == 1? true : false;
	}

	@Override
	public List<VoteResult> selectVoteResults(VoteResult voteResult) {
		return sqlSession.selectList(NS+"selectVoteResults", voteResult);
	}

	@Override
	public boolean updateVoteResult(VoteResult voteResult) {
		return sqlSession.update(NS+"updateVoteResult", voteResult)==1?true:false;
	}
}
