package com.biomans.fbt.teammember.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.teammember.dao.TeamMemberDAO;

@Repository
public class TeamMemberDAOImpl implements TeamMemberDAO {
	final String ns = "TeamMemberMapper.";
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<TeamMember> showMembersByTeamId(int teamId) throws SQLException {
		return sqlSession.selectList(ns+"showMembersByTeamId", teamId);
	}

	@Override
	public List<TeamMember> showMembersByTeamIdName(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showMembersByTeamIdName", searchCon);
	}
	
	//TM002-3
	public TeamMember showMemberById(String teamMemberId) throws SQLException {
		return sqlSession.selectOne(ns+"showMemberById", teamMemberId);
	}
	
}
