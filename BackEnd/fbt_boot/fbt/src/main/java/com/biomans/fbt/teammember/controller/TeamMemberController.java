package com.biomans.fbt.teammember.controller;

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

import com.biomans.fbt.domain.Employ;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.teammember.service.TeamMemberService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class TeamMemberController {
	@Autowired
	TeamMemberService teamMemberService;
	
	//FTM01
	@PostMapping("/team-member/1")
	public ResponseEntity addTeamMember(@RequestBody TeamMember teamMember) throws SQLException {
		try {
			teamMemberService.addTeamMember(teamMember);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FTM02
	@GetMapping("/team-member/2/{teamId}")
	public ResponseEntity showMembersByTeamId(@PathVariable int teamId) throws SQLException {
		try {
			List<TeamMember> list = teamMemberService.showMembersByTeamId(teamId);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FTM03
	@PutMapping("/team-member/1")
	public ResponseEntity changeMemberLever(@RequestBody List<TeamMember> teamMembers) throws SQLException {
		try {
			int result = teamMemberService.changeMemberLever(teamMembers);
			return new ResponseEntity(result, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FTM04, FTM07, FTM08
	@DeleteMapping("/team-member/1/{teamMemberId}")
	public ResponseEntity deleteMember(@PathVariable String teamMemberId) throws SQLException {
		try {
			teamMemberService.deleteMember(teamMemberId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FTM05
	@GetMapping("/team-member/3/{teamId}")
	public ResponseEntity showApplyMemberByTeam(@PathVariable int teamId) throws SQLException {
		try {
			List<TeamMember> list = teamMemberService.showApplyMemberByTeam(teamId);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FTM06
	@PutMapping("/team-member/2/{teamMemberId}")
	public ResponseEntity acceptApplyMember(@PathVariable String teamMemberId) throws SQLException {
		try {
			teamMemberService.acceptApplyMember(teamMemberId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FTM07
	@PutMapping("/team-member/3")
	public ResponseEntity changeImg(@RequestPart(value="user", required=false) User user,
			@RequestPart(value="image", required=false) MultipartFile image,
			HttpServletRequest request) throws SQLException {
		try {
			String root = request.getSession().getServletContext().getRealPath("/"); // E:\kjy\apache-tomcat-8.5.61\webapps\fbt\
			root = root.substring(0, root.length() - 4);
			String path = root + "\\user\\";
			System.out.println(path);
			String imageName = teamMemberService.changeImg(user, image, path);
			return new ResponseEntity(imageName, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
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
