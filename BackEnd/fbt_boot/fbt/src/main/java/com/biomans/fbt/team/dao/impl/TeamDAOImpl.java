package com.biomans.fbt.team.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.team.dao.TeamDAO;
import com.biomans.fbt.util.TeamFilter;

@Repository
public class TeamDAOImpl implements TeamDAO{
	final String ns = "TeamMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	//T001
	@Override
	public Team showTeamInfo(HashMap<String, String> con) throws SQLException {
		return sqlSession.selectOne(ns+"showOtherTeamInfo", con);
	}
	
	//T002
	@Override
	public List<MatchSchedule> showMatchRecordByTeam(int teamId) throws SQLException {
		return sqlSession.selectList(ns+"showMatchRecordByTeam", teamId);
	}
	
	//T003
	@Override
	public Team showTeamBasicInfo(int teamId) throws SQLException {
		return sqlSession.selectOne(ns+"showTeamBasicInfo", teamId);
	}
	
	//T004
	@Override
	public void addTeam(Team team) throws SQLException {
		sqlSession.insert(ns+"addTeam", team);
	}
	
	//T005
	@Override
	public String checkDupleTeamName(String teamName) throws SQLException {
		return sqlSession.selectOne(ns+"checkDupleTeamName", teamName);
	}
	
	//V006: 팀 검색
	@Override
	public List<Team> searchTeams(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"searchTeams", searchCon);
	}
	
	//T006-1: 팀 필터 검색
	public List<Team> searchTeamsByFilter(TeamFilter teamFilter) throws SQLException {
		return sqlSession.selectList(ns+"searchTeamsByFilter", teamFilter);
	}
	
	//TNN :
	@Override
	public Team showTeamInfoForAutoWrite(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectOne(ns+"showTeamInfoForAutoWrite", searchCon);
	}
	
	//T008
	public int getTeamIdByTeamName(String teamName) throws SQLException {
		return sqlSession.selectOne(ns+"getTeamIdByTeamName", teamName);
	}
	
	//T009
	@Override
	public Team showTeamInfo(int teamId) throws SQLException {
		return sqlSession.selectOne(ns+"showTeamInfo", teamId);
	}
	
	//T010
	@Override
	public void updateTeamInfo(Team team) throws SQLException {
		sqlSession.update(ns+"updateTeamInfo", team);
	}
	
	//T011
	@Override
	public void deleteTeam(int teamId) throws SQLException {
		sqlSession.delete(ns+"deleteTeam", teamId);
	}
}
