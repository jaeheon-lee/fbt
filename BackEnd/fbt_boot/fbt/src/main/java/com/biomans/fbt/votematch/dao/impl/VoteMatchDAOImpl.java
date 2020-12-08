package com.biomans.fbt.votematch.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biomans.fbt.domain.Invite;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.domain.VoteMatchSetting;
import com.biomans.fbt.votematch.dao.VoteMatchDAO;

@Repository
public class VoteMatchDAOImpl implements VoteMatchDAO {
	final String ns = "VoteMatchMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	//V001-1
	@Override
	public List<VoteMatch> showVoteMatchInfoByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showVoteMatchInfoByTeam", searchCon);
	}
	//V001-2
	@Override
	public VoteMatch showVoteMatchInfoByScheduleId(int matchScheduleId) throws SQLException {
		return sqlSession.selectOne(ns+"showVoteMatchInfoByScheduleId", matchScheduleId);
	}
	//V002-1
	@Override
	public List<VoteMatch> showVoteMatchNumByVote(int teamId) throws SQLException {
		return sqlSession.selectList(ns+"showVoteMatchNumByTeam", teamId);
	}
	//V002-2
	@Override
	public VoteMatch showVoteMatchNumByScheduleId(int matchScheduleId) throws SQLException {
		return sqlSession.selectOne(ns+"showVoteMatchNumByScheduleId", matchScheduleId);
	}
	//V003-1
	@Override
	public List<VoteMatchResult> showVoteMatchResultByVote(int voteMatchId) throws SQLException {
		return sqlSession.selectList(ns+"showVoteMatchResultByVote", voteMatchId);
	}
	//V003-2
	@Override
	public ArrayList<VoteMatchResult> showVoteMatchResultByScheduleId(int matchScheduleId) throws SQLException {
		ArrayList<VoteMatchResult> voteMatchResults = new ArrayList<VoteMatchResult>();
		voteMatchResults.addAll(sqlSession.selectList(ns+"showVoteMatchResultByScheduleId", matchScheduleId));
		return voteMatchResults;
	}
	
	//V004
	@Override
	public void addVoteMatch(VoteMatch voteMatch) throws SQLException {
		sqlSession.insert(ns+"addVoteMatch", voteMatch);
		
	}
	//V005
	@Override
	public int getLatestVoteMatchIdByTeam(int teamId) throws SQLException {
		return sqlSession.selectOne(ns+"getLatestVoteMatchIdByTeam", teamId);
	}
	//V006
	@Override
	public void addVoteMatchSetting(VoteMatchSetting voteMatchSetting) throws SQLException {
		sqlSession.insert(ns+"addVoteMatchSetting", voteMatchSetting);
		
	}
	
	
	
	
	@Override
	public void updateVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException {
		sqlSession.update(ns+"updateAttendace", voteMatchResult);
	}
	
	@Override
	public void addAttendance(VoteMatchResult voteMatchResult) throws SQLException {
		sqlSession.insert(ns+"addAttendance", voteMatchResult);
		
	}
	
	// 상대방 찾기를 통한 투표인지 확인
	public String checkBySearch(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectOne(ns+"checkBySearch", searchCon);
	}

	@Override
	public void inviteFriend(Invite invite) throws SQLException {
		sqlSession.insert(ns+"inviteFriend", invite);
		
	}

	@Override
	public void endVoteMatch(VoteMatch voteMatch) throws SQLException {
		sqlSession.update(ns+"endVoteMatch", voteMatch);
		
	}

	@Override
	public void updateVoteMatch(VoteMatch voteMatch) throws SQLException {
		sqlSession.update(ns+"updateVoteMatch", voteMatch);
		
	}

	@Override
	public void deleteVoteMatch(String voteMatchId) throws SQLException {
		sqlSession.delete(ns+"deleteVoteMatch", voteMatchId);
		
	}

	@Override
	public void updateVoteMatchSetting(VoteMatchSetting voteMatchSetting) throws SQLException {
		sqlSession.update(ns+"updateVoteMatchSetting",voteMatchSetting);
	}

	@Override
	public void deleteVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException {
		sqlSession.delete(ns+"deleteVoteMatchResult", voteMatchResult);
		
	}
	

	@Override
	public List<User> searchFriend(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"searchFriend", searchCon);
	}

	

	
	
}
