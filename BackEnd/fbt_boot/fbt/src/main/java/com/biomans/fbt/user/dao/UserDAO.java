package com.biomans.fbt.user.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.biomans.fbt.domain.User;

public interface UserDAO {
	//U001
	public void addUser(User user) throws SQLException;
	//U002
	public String checkDuple(String email) throws SQLException;
	//U003-1
	public User LoginByApi(HashMap<String, String> searchCon) throws SQLException;
	//U003-2
	public User LoginByEmail(HashMap<String, String> searchCon) throws SQLException;
	//U004
	public User showUserInfo(String email) throws SQLException;
}
