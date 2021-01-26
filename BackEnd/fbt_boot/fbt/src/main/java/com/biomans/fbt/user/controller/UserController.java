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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
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
	
	//FU02
	@GetMapping("/user/5/{email}/{matchScheduleId}")
	public ResponseEntity showEmpScore(@PathVariable String email,
			@PathVariable int matchScheduleId) throws SQLException {
		try {
			HashMap<String, String> searchCon = new HashMap<String, String>();
			searchCon.put("email", email);
			searchCon.put("matchScheduleId", matchScheduleId+"");
			List<EmpScore> empScores = userService.showEmpScore(searchCon);
			return new ResponseEntity(empScores, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FU03
	@GetMapping("/user/6/{email}")
	public ResponseEntity showBelongedTeam(@PathVariable String email) throws SQLException {
		try {
			List<Team> list = userService.showBelongedTeam(email);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FU04
	@GetMapping("/user/7/{email}")
	public ResponseEntity getPass(@PathVariable String email) throws SQLException {
		try {
			String pass = userService.getPass(email);
			return new ResponseEntity(pass, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FU05
	@PutMapping("/user/1")
	public ResponseEntity updateUser(@RequestBody User user) throws SQLException {
		try {
			System.out.println(user);
			userService.updateUser(user);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FU06
	@PostMapping("/user")
	public ResponseEntity addUser(@RequestBody User user) throws SQLException {
		try {
			System.out.println(user);
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
			System.out.println(searchCon);
			Session session = userService.LoginByApi(searchCon);
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
			Session session = userService.LoginByEmail(searchCon);
			return new ResponseEntity(session, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//V04-1
	@GetMapping("/user/1")
	public ResponseEntity searchFriend(@RequestParam(value="email") String email, 
			@RequestParam(value="teamId") String teamId) throws SQLException {
		try {
			HashMap<String, String> searchCon = new HashMap<String, String>();
			searchCon.put("email", email);
			searchCon.put("teamId", teamId);
			List<User> list = userService.searchFriend(searchCon);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
