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

@Repository
public class TeamDAOImpl implements TeamDAO{
	final String ns = "TeamMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	//T001
	@Override
	public Team showTeamInfo(int teamId) throws SQLException {
		return sqlSession.selectOne(ns+"showOtherTeamInfo", teamId);
	}
	
	//T002
	public List<MatchSchedule> showMatchRecordByTeam(int teamId) throws SQLException {
		return sqlSession.selectList(ns+"showMatchRecordByTeam", teamId);
	}
	
	//V006: 팀 검색
	@Override
	public List<Team> searchTeams(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"searchTeams", searchCon);
	}
	
	//TNN :
	@Override
	public Team showTeamInfoForAutoWrite(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectOne(ns+"showTeamInfoForAutoWrite", searchCon);
	}
	
}
