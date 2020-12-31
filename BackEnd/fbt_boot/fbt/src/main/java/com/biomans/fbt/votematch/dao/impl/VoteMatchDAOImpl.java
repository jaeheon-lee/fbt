package com.biomans.fbt.votematch.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biomans.fbt.domain.Notice;
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
	public VoteMatch showVoteMatchInfoByScheduleId(HashMap<String, Integer> searchCon) throws SQLException {
		return sqlSession.selectOne(ns+"showVoteMatchInfoByScheduleId", searchCon);
	}
	//V001-3
	@Override
	public VoteMatch showVoteMatchInfoById(int voteMatchId) throws SQLException {
		return sqlSession.selectOne(ns+"showVoteMatchInfoById", voteMatchId);
	}
	
	//V002-1
	@Override
	public VoteMatch showVoteMatchNumByVote(int voteMatchId) throws SQLException {
		return sqlSession.selectOne(ns+"showVoteMatchNumByVote", voteMatchId);
	}
	//V002-2
	@Override
	public VoteMatch showVoteMatchNumByScheduleId(HashMap<String, Integer> searchCon) throws SQLException {
		return sqlSession.selectOne(ns+"showVoteMatchNumByScheduleId", searchCon);
	}
	//V003-1
	@Override
	public List<VoteMatchResult> showVoteMatchResultByVote(int voteMatchId) throws SQLException {
		return sqlSession.selectList(ns+"showVoteMatchResultByVote", voteMatchId);
	}
	//V003-2
	@Override
	public ArrayList<VoteMatchResult> showVoteMatchResultByScheduleId(HashMap<String, Integer> searchCon) throws SQLException {
		ArrayList<VoteMatchResult> voteMatchResults = new ArrayList<VoteMatchResult>();
		voteMatchResults.addAll(sqlSession.selectList(ns+"showVoteMatchResultByScheduleId", searchCon));
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
	//V007
	@Override
	public void addAttendance(VoteMatchResult voteMatchResult) throws SQLException {
		sqlSession.insert(ns+"addAttendance", voteMatchResult);
		
	}
	//V008
	@Override
	public void updateVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException {
		sqlSession.update(ns+"updateAttendace", voteMatchResult);
	}
	//V009
	public String checkBySearch(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectOne(ns+"checkBySearch", searchCon);
	}
	//V010
	@Override
	public void updateVoteMatch(VoteMatch voteMatch) throws SQLException {
		sqlSession.update(ns+"updateVoteMatch", voteMatch);
		
	}
	//V011
	@Override
	public void updateVoteMatchSetting(VoteMatchSetting voteMatchSetting) throws SQLException {
		sqlSession.update(ns+"updateVoteMatchSetting",voteMatchSetting);
	}
	//V012
	@Override
	public List<User> searchFriend(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"searchFriend", searchCon);
	}
	
	//V014-1
	@Override
	public void deleteVoteMatchByVoteMatchId(int voteMatchId) throws SQLException {
		sqlSession.delete(ns+"deleteVoteMatchByVoteMatchId", voteMatchId);
	}
	
	//V014-1
	@Override
	public void deleteVoteMatchByMatchScheduleId(HashMap<String, Integer> searchCon) throws SQLException {
		sqlSession.delete(ns+"deleteVoteMatchByMatchScheduleId", searchCon);
	}
	
	//V015
	@Override
	public VoteMatch showVoteMatchBasicByScheduleTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return sqlSession.selectOne(ns+"showVoteMatchBasicByScheduleTeam", searchCon);
	}
	
	//V016
	@Override
	public List<VoteMatch> loadEndedVoteMatch(HashMap<String, Integer> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"loadEndedVoteMatch", searchCon);
	}
	

	@Override
	public void deleteVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException {
		sqlSession.delete(ns+"deleteVoteMatchResult", voteMatchResult);
		
	}
	

	
	

	
	
}
