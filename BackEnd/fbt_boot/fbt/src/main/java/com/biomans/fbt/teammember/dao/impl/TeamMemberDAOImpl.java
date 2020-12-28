package com.biomans.fbt.teammember.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.teammember.dao.TeamMemberDAO;

@Repository
public class TeamMemberDAOImpl implements TeamMemberDAO {
	final String ns = "TeamMemberMapper.";
	
	@Autowired
	SqlSession sqlSession;
	
	//TM001
	@Override
	public void addTeamMember(TeamMember teamMember) throws SQLException {
		sqlSession.insert(ns+"addTeamMember", teamMember);
	}

	@Override
	public List<TeamMember> showMembersByTeamId(int teamId) throws SQLException {
		return sqlSession.selectList(ns+"showMembersByTeamId", teamId);
	}

	@Override
	public List<TeamMember> showMembersByTeamIdName(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showMembersByTeamIdName", searchCon);
	}
	
	//TM002-3
	@Override
	public TeamMember showMemberById(String teamMemberId) throws SQLException {
		return sqlSession.selectOne(ns+"showMemberById", teamMemberId);
	}
	
	//TM003
	@Override
	public void changeMemberLever(TeamMember teamMember) throws SQLException {
		sqlSession.update(ns+"changeMemberLever", teamMember);
	}
	
	//TM004
	@Override
	public void deleteMember(String teamMemberId) throws SQLException {
		sqlSession.delete(ns+"deleteMember", teamMemberId);
	}
	
	//TM005
	@Override
	public List<TeamMember> showApplyMemberByTeam(int teamId) throws SQLException {
		return sqlSession.selectList(ns+"showApplyMemberByTeam", teamId);
	}
	
	//TM006
	@Override
	public void acceptApplyMember(String teamMemberId) throws SQLException {
		sqlSession.update(ns+"acceptApplyMember", teamMemberId);
	}
	
	//TM007
	@Override
	public void changeImg(User user) throws SQLException {
		sqlSession.update(ns+"changeImg", user);
	}

}
