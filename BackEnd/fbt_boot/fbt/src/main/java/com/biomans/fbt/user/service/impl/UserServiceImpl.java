package com.biomans.fbt.user.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.user.dao.UserDAO;
import com.biomans.fbt.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	//FU01
	@Override
	public void addUser(User user) throws SQLException {
		userDAO.addUser(user);
	}
	
	//FS13
	@Override
	public List<EmpScore> showEmpScore(HashMap<String, String> searchCon) throws SQLException {
		return userDAO.showEmpScore(searchCon);
	}
	
	//U002
	@Override
	public String checkDuple(String email) throws SQLException {
		return userDAO.checkDuple(email);
	}
	
	//U003-1
	@Override
	public User LoginByApi(HashMap<String, String> searchCon) throws SQLException {
		User user = userDAO.LoginByApi(searchCon);
		return user;
	}
	
	//U003-2
	@Override
	public User LoginByEmail(HashMap<String, String> searchCon) throws SQLException {
		User user = userDAO.LoginByEmail(searchCon);
		return user;
	}
	
	//U004
	@Override
	public User showUserInfo(String email) throws SQLException {
		return userDAO.showUserInfo(email);
	}
}
