package com.biomans.fbt.user.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.util.Session;

public interface UserService {
	//FU01
	public void addUser(User user) throws SQLException;
	//FU02
	public List<EmpScore> showEmpScore(HashMap<String, String> searchCon) throws SQLException;
	//FU03
	public List<Team> showBelongedTeam(String email) throws SQLException;
	//FU04
	public String getPass(String email) throws SQLException;
	//FU05
	public void updateUser(User user) throws SQLException;
	//FU06
	public String checkDuple(String email) throws SQLException;
	//U003-1
	public Session LoginByApi(HashMap<String, String> searchCon) throws SQLException;
	//U003-2
	public Session LoginByEmail(HashMap<String, String> searchCon) throws SQLException;
	//U004
	public User showUserInfo(String email) throws SQLException;
	
}
