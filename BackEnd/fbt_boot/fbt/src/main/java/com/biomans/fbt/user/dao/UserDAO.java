package com.biomans.fbt.user.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.Team;
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
	// U005
	public List<EmpScore> showEmpScore(HashMap<String, String> searchCon) throws SQLException;
	//U006
	public List<Team> showBelongedTeam(String email) throws SQLException;
	//U007
	public String getPass(String email) throws SQLException;
	//U008
	public void updateUser(User user) throws SQLException;
	//V04-1
	public List<User> searchFriend(HashMap<String, String> searchCon) throws SQLException;
}
