package com.biomans.fbt.teammember.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.biomans.fbt.domain.Employ;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.teammember.service.TeamMemberService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class TeamMemberController {
	@Autowired
	TeamMemberService teamMemberService;
	//TM002-1
	@GetMapping("/team-member/2/{teamId}")
	public ResponseEntity showMembersByTeamIdName(@PathVariable int teamId) throws SQLException {
		try {
			System.out.println(teamId);
			List<TeamMember> list = teamMemberService.showMembersByTeamId(teamId);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//TM002-2
	@GetMapping("/team-member/1/{teamId}/{name}")
	public ResponseEntity showMembersByTeamIdName(@PathVariable String teamId,
			@PathVariable String name) throws SQLException {
		try {
			HashMap<String, String> searchCon = new HashMap<String, String>();
			searchCon.put("teamId", teamId);
			searchCon.put("name", name);
			List<TeamMember> list = teamMemberService.showMembersByTeamIdName(searchCon);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
