package com.biomans.fbt.votematch.controller;

import java.sql.SQLException;
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

import com.biomans.fbt.domain.Invite;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.domain.VoteMatchSetting;
import com.biomans.fbt.votematch.service.VoteMatchService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class VoteMatchController {
	@Autowired
	private VoteMatchService voteMatchService;
	
	//V001
	@GetMapping("/vote-match/{teamId}")
	public ResponseEntity showVoteMatchInfoByTeam(@PathVariable int teamId) throws SQLException {
		try {
			List<VoteMatch> list = voteMatchService.showVoteMatchInfoByTeam(teamId);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//V002
	@PutMapping("/vote-match-result")
	public ResponseEntity updateVoteMatchResult(@RequestBody VoteMatchResult voteMatchResult) throws SQLException {
		try {
			voteMatchService.updateVoteMatchResult(voteMatchResult);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//V003
	@PostMapping("/vote-match-result")
	public ResponseEntity addAttendance(@RequestBody VoteMatchResult voteMatchResult) throws SQLException{
		try {
			voteMatchService.addAttendance(voteMatchResult);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//V004
	@PostMapping("/vote-match/invite")
	public ResponseEntity inviteFriend(@RequestBody Invite invite) throws SQLException{
		try {
			voteMatchService.inviteFriend(invite);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//V005 | V006
	@PostMapping("/vote-match")
	public ResponseEntity addVoteMatchAndSetting(@RequestBody VoteMatch voteMatch) throws SQLException{
		try {
			voteMatchService.addVoteMatchAndSetting(voteMatch);;
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//V007
	@PutMapping("/vote-match/1")
	public ResponseEntity endVoteMatch(@RequestBody VoteMatch voteMatch) throws SQLException {
		try {
			voteMatchService.endVoteMatch(voteMatch);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//V008
	@PutMapping("/vote-match/2")
	public ResponseEntity updateVoteMatch(@RequestBody VoteMatch voteMatch) throws SQLException {
		try {
			voteMatchService.updateVoteMatch(voteMatch);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//V009
	@DeleteMapping("/vote-match/{voteMatchId}")
	public ResponseEntity deleteVoteMatch(@PathVariable String voteMatchId) throws SQLException {
		try {
			voteMatchService.deleteVoteMatch(voteMatchId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//V010
	@PutMapping("/vote-match-setting")
	public ResponseEntity updateVoteMatchSetting(@RequestBody VoteMatchSetting voteMatchSetting) throws SQLException {
		try {
			voteMatchService.updateVoteMatchSetting(voteMatchSetting);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//V011
	@DeleteMapping("/vote-match-result")
	public ResponseEntity deleteVoteMatchResult(@RequestBody VoteMatchResult voteMatchResult) throws SQLException {
		try {
			System.out.println(voteMatchResult);
			voteMatchService.deleteVoteMatchResult(voteMatchResult);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
}