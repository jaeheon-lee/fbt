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
import com.biomans.fbt.util.Session;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	//FU06
	@Override
	public void addUser(User user) throws SQLException {
		userDAO.addUser(user);
	}
	
	//FU03
	@Override
	public List<Team> showBelongedTeam(String email) throws SQLException {
		return userDAO.showBelongedTeam(email);
	}
	
	//FU04
	@Override
	public String getPass(String email) throws SQLException {
		return userDAO.getPass(email);
	}
	
	//FU05
	@Override
	public void updateUser(User user) throws SQLException {
		userDAO.updateUser(user);
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
	public Session LoginByApi(HashMap<String, String> searchCon) throws SQLException {
		Session session = new Session();
		User user = userDAO.LoginByApi(searchCon);
		if(user != null) session = makeSession(user);
		return session;
	}
	
	//U003-2
	@Override
	public Session LoginByEmail(HashMap<String, String> searchCon) throws SQLException {
		Session session = new Session();
		User user = userDAO.LoginByEmail(searchCon);
		if(user != null) session = makeSession(user);
		return session;
	}
	
	public Session makeSession(User user) {
		Session session = new Session();
		session.setEmail(user.getEmail());
		session.setName(user.getName());
		session.setImage(user.getImage());
		List<TeamMember> list = user.getTeamMembers();
		List<HashMap<String, String>> teams = new ArrayList<HashMap<String, String>>();
		for (TeamMember tm : list) {
			HashMap<String, String> team = new HashMap<String, String>();
			team.put("teamId", tm.getTeam().getTeamId()+"");
			team.put("teamName", tm.getTeam().getTeamName());
			team.put("teamMemberId", tm.getTeamMemberId());
			team.put("nickName", tm.getNickName());
			team.put("memberLevel", tm.getMemberLevel()+"");
			teams.add(team);
		}
		session.setTeams(teams);
		return session;
	}
	
	//U004
	@Override
	public User showUserInfo(String email) throws SQLException {
		return userDAO.showUserInfo(email);
	}
}
