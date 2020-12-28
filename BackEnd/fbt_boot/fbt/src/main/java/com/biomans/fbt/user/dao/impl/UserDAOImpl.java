package com.biomans.fbt.user.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.user.dao.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO{
	final String ns = "UserMapper.";
	
	@Autowired
	SqlSession sqlSession;
	
	//U001
	@Override
	public void addUser(User user) throws SQLException {
		sqlSession.insert(ns+"addUser", user);
	}
	
	//U002
	@Override
	public String checkDuple(String email) throws SQLException {
		return sqlSession.selectOne(ns+"checkDuple", email);
	}
	
	//U003-1
	@Override
	public User LoginByApi(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectOne(ns+"LoginByApi", searchCon);
	}
	
	//U003-2
	@Override
	public User LoginByEmail(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectOne(ns+"LoginByEmail", searchCon);
	}
	
	//U004
	@Override
	public User showUserInfo(String email) throws SQLException {
		return sqlSession.selectOne(ns+"showUserInfo", email);
	}
	
	//U005
	@Override
	public List<EmpScore> showEmpScore(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showEmpScore", searchCon);
	}
	
	//U006
	@Override
	public List<Team> showBelongedTeam(String email) throws SQLException {
		return sqlSession.selectList(ns+"showBelongedTeam", email);
	}
	
	//U007
	@Override
	public String getPass(String email) throws SQLException {
		return sqlSession.selectOne(ns+"getPass", email);
	}
	
	//U008
	@Override
	public void updateUser(User user) throws SQLException {
		sqlSession.update(ns+"updateUser", user);
	}
}
