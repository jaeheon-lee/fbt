package com.biomans.fbt.user.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.User;

public interface UserService {
	//FU01
	public void addUser(User user) throws SQLException;
	//FU02
	public List<EmpScore> showEmpScore(HashMap<String, String> searchCon) throws SQLException;
	//U002
	public String checkDuple(String email) throws SQLException;
	//U003-1
	public User LoginByApi(HashMap<String, String> searchCon) throws SQLException;
	//U003-2
	public User LoginByEmail(HashMap<String, String> searchCon) throws SQLException;
	//U004
	public User showUserInfo(String email) throws SQLException;
	
}
