package com.biomans.fbt.votematch.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.votematch.service.VoteMatchService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class VoteMatchController {
	@Autowired
	private VoteMatchService voteMatchService;
	
	@GetMapping("/vote-match/{teamId}")
	public ResponseEntity showVoteMatchInfoByTeam(@PathVariable int teamId) throws SQLException {
		try {
			System.out.println(1);
			List<VoteMatch> list = voteMatchService.showVoteMatchInfoByTeam(teamId);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
