package com.biomans.fbt.user.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.user.service.UserService;
import com.biomans.fbt.util.Session;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class UserController {
	@Autowired
	private UserService userService;
	
	//FU01
	@GetMapping("/user/4/{email}")
	public ResponseEntity showUserInfo(@PathVariable String email) throws SQLException {
		try {
			User user = userService.showUserInfo(email);
			return new ResponseEntity(user, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	//U001
	@PostMapping("/user")
	public ResponseEntity addUser(@RequestBody User user) throws SQLException {
		try {
			userService.addUser(user);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//U002
	@GetMapping("/user/1/{email}")
	public ResponseEntity showMembersByTeamIdName(@PathVariable String email) throws SQLException {
		try {
			return new ResponseEntity(userService.checkDuple(email), HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//U003-1
	@GetMapping("/user/2/{apiType}/{apiKey}")
	public ResponseEntity LoginByApi(@PathVariable String apiType, 
			@PathVariable String apiKey) throws SQLException {
		try {
			HashMap<String, String> searchCon = new HashMap<String, String>();
			searchCon.put("apiType", apiType);
			searchCon.put("apiKey", apiKey);
			User user = userService.LoginByApi(searchCon);
			Session session = new Session();
			if (user != null) session = makeSession(user);
			return new ResponseEntity(session, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//U003-2
	@GetMapping("/user/3/{email}/{pass}")
	public ResponseEntity LoginByEmail(@PathVariable String email, 
			@PathVariable String pass) throws SQLException {
		try {
			HashMap<String, String> searchCon = new HashMap<String, String>();
			searchCon.put("email", email);
			searchCon.put("pass", pass);
			User user = userService.LoginByEmail(searchCon);
			Session session = new Session();
			if (user != null) session = makeSession(user);
			System.out.println(session);
			return new ResponseEntity(session, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
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
}
