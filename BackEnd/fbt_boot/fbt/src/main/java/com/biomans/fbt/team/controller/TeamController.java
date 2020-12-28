package com.biomans.fbt.team.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.team.service.TeamService;
import com.biomans.fbt.util.TeamFilter;
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
	
	//FT02
	@PostMapping("/team/1")
	public ResponseEntity addTeam(@RequestPart(value="file", required=false) MultipartFile file,
			@RequestPart(value="team") Team team,
			@RequestPart(value="teamMember") TeamMember teamMember,
			HttpServletRequest request) throws SQLException {
		try {
			String root = request.getSession().getServletContext().getRealPath("/").substring(0, 11);
			String path = root + "front contents\\fbt\\src\\assets\\image\\emblem\\";
			teamService.addTeam(team, file, path, teamMember);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FT03
	@GetMapping("/team/4/{teamName}")
	public ResponseEntity checkDupleTeamName(@PathVariable String teamName) throws SQLException {
		try {
			String searchedName = teamService.checkDupleTeamName(teamName);
			return new ResponseEntity(searchedName, HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FT04
	@PostMapping("/team/2")
	public ResponseEntity searchTeamsByFilter(@RequestBody TeamFilter teamFilter) throws SQLException {
		try {
			List<Team> teams = teamService.searchTeamsByFilter(teamFilter);
			return new ResponseEntity(teams, HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FT05
	@GetMapping("/team/5/{teamId}")
	public ResponseEntity showTeamInfo(@PathVariable int teamId) throws SQLException {
		try {
			Team team = teamService.showTeamInfo(teamId);
			return new ResponseEntity(team, HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FT06
	@PutMapping("/team/1/{beforeUrl}")
	public ResponseEntity addTeam(@RequestPart(value="file", required=false) MultipartFile file,
			@RequestPart(value="team") Team team,
			@PathVariable String beforeUrl,
			HttpServletRequest request) throws SQLException {
		try {
			String root = request.getSession().getServletContext().getRealPath("/").substring(0, 11);
			String path = root + "front contents\\fbt\\src\\assets\\image\\emblem\\";
			teamService.updateTeamInfo(team, file, beforeUrl, path);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FT07
	@DeleteMapping("/team/1/{teamId}")
	public ResponseEntity deleteTeam(@PathVariable int teamId) throws SQLException {
		try {
			teamService.deleteTeam(teamId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
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
