package com.biomans.fbt.team.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biomans.fbt.domain.Team;
import com.biomans.fbt.team.service.TeamService;
import com.biomans.fbt.votematch.service.VoteMatchService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class TeamController {
	@Autowired
	private TeamService teamService;
	
	//FT01
	@GetMapping("/team/3/{teamId}/{teamMemberId}")
	public ResponseEntity showTeamInfo(@PathVariable String teamId,
			@PathVariable String teamMemberId) throws SQLException {
		try {
			HashMap<String, String> con = new HashMap<String, String>();
			con.put("teamId", teamId);
			con.put("teamMemberId", teamMemberId);
			Team team = teamService.showTeamInfo(con);
			return new ResponseEntity(team, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//T006: 팀 검색
	@GetMapping("/team/{teamName}")
	public ResponseEntity searchTeams(@PathVariable String teamName,
			@RequestParam String homeTeamId) throws SQLException {
		try {
			HashMap<String, String> searchCon = new HashMap<String, String>();
			searchCon.put("teamName", teamName);
			searchCon.put("teamId", homeTeamId);
			List<Team> list = teamService.searchTeams(searchCon);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//TN
	@GetMapping("/team/2/{teamId}/{email}")
	public ResponseEntity showTeamInfoForAutoWrite(@PathVariable String teamId,
			@PathVariable String email) throws SQLException {
		try {
			HashMap<String, String> searchCon = new HashMap<String, String>();
			searchCon.put("email", email);
			searchCon.put("teamId", teamId);
			Team team = teamService.showTeamInfoForAutoWrite(searchCon);
			return new ResponseEntity(team, HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
