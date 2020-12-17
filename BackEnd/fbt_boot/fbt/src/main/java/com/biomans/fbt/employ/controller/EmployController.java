package com.biomans.fbt.employ.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.Employ;
import com.biomans.fbt.domain.EmployResult;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.employ.service.EmployService;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.util.Filter;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class EmployController {
	@Autowired
	private EmployService employService;
	
	@Autowired
	private MatchScheduleService matchScheduleService;
	
	//FE01
	@PostMapping("/employ")
	public ResponseEntity addEmploy(@RequestBody Employ employ) throws SQLException {
		try {
			//S001
			MatchSchedule matchSchedule = employ.getMatchSchedule();
			if(matchSchedule.getMatchScheduleId() == 0) {
				int teamId = employ.getTeamGiver().getTeamId();
				matchSchedule.getHomeTeam().setTeamId(teamId);
				if(matchSchedule.getAwayTeam().getTeamId() == 0) matchSchedule.setAwayTeam(null);
				matchScheduleService.addMatchSchedule(matchSchedule);
				//S002
				int matchScheduleId = matchScheduleService.showLatestMatchScheduleIdById(teamId);
				employ.getMatchSchedule().setMatchScheduleId(matchScheduleId);
			}
			//E001
			employService.addEmploy(employ);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FE04, FE09, FE10
	@GetMapping("/employ/1/{teamId}/{status}")
	public ResponseEntity showRegisteredEmployByTeam(@PathVariable int teamId,
			@PathVariable int status) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("teamIdGiver", teamId);
			searchCon.put("status", status);
			List<Employ> list = employService.showRegisteredEmployByTeam(searchCon);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FE11
	@GetMapping("/employ/2/{email}/{empResultStatus}")
	public ResponseEntity showAppliedEmployByUser(@PathVariable String email,
			@PathVariable String empResultStatus) throws SQLException {
		try {
			HashMap<String, String> searchCon = new HashMap<String, String>();
			searchCon.put("email", email);
			searchCon.put("empResultStatus", empResultStatus);
			List<Employ> list = employService.showAppliedEmployByUser(searchCon);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FE02
	@PostMapping("/employ/2")
	public ResponseEntity searchEmployByFilter(@RequestBody Filter filter) throws SQLException {
		try {
			List<Employ> list = employService.searchEmployByFilter(filter);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FE03
	@PostMapping("/employ-result")
	public ResponseEntity doApplyEmploy(@RequestBody EmployResult employRes) throws SQLException {
		try {
			employService.doApplyEmploy(employRes);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FE07, FE08
	@PutMapping("/employ-result/1")
	public ResponseEntity updateResStatus(@RequestBody Employ employ) throws SQLException {
		try {
			EmployResult employRes = employ.getEmployResults().get(0);
			employService.updateResStatus(employRes, employ);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//FE05
	@DeleteMapping("/employ/{employId}")
	public ResponseEntity deleteEmploy(@PathVariable int employId) throws SQLException {
		try {
			employService.deleteEmploy(employId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FE06
	@PutMapping("/employ/1/{employId}")
	public ResponseEntity renewEmploy(@PathVariable int employId) throws SQLException {
		try {
			employService.renewEmploy(employId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FE12, FE13
	@DeleteMapping("/employ-result/{employId}/{email}")
	public ResponseEntity deleteEmployRes(@PathVariable String employId, 
			@PathVariable String email) throws SQLException {
		try {
			HashMap<String, String> searchCon = new HashMap<String, String>();
			searchCon.put("email", email);
			searchCon.put("employId", employId);
			employService.deleteEmployRes(searchCon);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FE14
	@PutMapping("/employ/2")
	public ResponseEntity updateEmploy(@RequestBody Employ employ) throws SQLException {
		try {
			employService.updateEmploy(employ);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//E002-1
	@GetMapping("/employ/3/{matchScheduleId}")
	public ResponseEntity findEmpTeam(@PathVariable int matchScheduleId) throws SQLException {
		try {
			Team team = employService.findEmpTeam(matchScheduleId);
			return new ResponseEntity(team, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
