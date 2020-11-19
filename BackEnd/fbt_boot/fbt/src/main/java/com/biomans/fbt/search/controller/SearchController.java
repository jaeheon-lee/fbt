package com.biomans.fbt.search.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.search.service.SearchService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private MatchScheduleService matchScheduleService;
	
	//M001|S001|S002: 일정 등록 + 등록된 일정 출력 + 매치 등록
	@PostMapping("/search")
	public ResponseEntity addSearch1(@RequestBody Search search) throws SQLException {
		try {
			//S001
			MatchSchedule matchSchedule = search.getMatchSchedule();
			if(matchSchedule.getMatchScheduleId() == 0) {
				int teamId = search.getTeamGiver().getTeamId();
				matchSchedule.getHomeTeam().setTeamId(teamId);
				if(matchSchedule.getAwayTeam().getTeamId() == 0) matchSchedule.setAwayTeam(null);
				System.out.println(matchSchedule);
				matchScheduleService.addMatchSchedule(matchSchedule);
				//S002
				int matchScheduleId = matchScheduleService.showLatestMatchScheduleIdById(teamId);
				search.getMatchSchedule().setMatchScheduleId(matchScheduleId);
			}
			//M001
			searchService.addSearch(search);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//M002-1
	@GetMapping("/search/1/{teamId}")
	public ResponseEntity addSearch1(@PathVariable int teamId) throws SQLException {
		try {
			List<Search> list = searchService.showSearchByTeam(teamId);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//M009
	@DeleteMapping("/search/{searchId}")
	public ResponseEntity deleteSearch(@PathVariable int searchId) throws SQLException {
		try {
			searchService.deleteSearch(searchId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//M010
	@PutMapping("/search/1/{searchId}")
	public ResponseEntity renewSearch(@PathVariable int searchId) throws SQLException {
		try {
			searchService.renewSearch(searchId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
