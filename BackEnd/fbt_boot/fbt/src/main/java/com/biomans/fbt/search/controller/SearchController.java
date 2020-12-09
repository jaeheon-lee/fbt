package com.biomans.fbt.search.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
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
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.search.service.SearchService;
import com.biomans.fbt.util.Filter;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class SearchController {
	@Autowired
	private SearchService searchService;
	public static HashMap<Integer, Future<Integer>> searchId;
	public static HashMap<Integer, Integer> search;
	
	@Autowired
	private MatchScheduleService matchScheduleService;
	
	//FM01
	@PostMapping("/search")
	public ResponseEntity addSearch1(@RequestBody Search search) throws SQLException {
		try {
			searchService.addSearch(search);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	//FM02
	@PostMapping("/search/2")
	public ResponseEntity searchMatchByFilter(@RequestBody Filter filter) throws SQLException {
		try {
			List<Search> list = searchService.searchMatchByFilter(filter);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	//FM03
	@PostMapping("/search-reservation")
	public ResponseEntity doApplySearch(@RequestBody SearchReservation searchRes) throws SQLException {
		try {
			searchService.doApplySearch(searchRes);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//M002-1
	@GetMapping("/search/1/{teamId}/{status}")
	public ResponseEntity showRegisteredSearchByTeam(@PathVariable int teamId,
			@PathVariable int status) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("teamIdGiver", teamId);
			searchCon.put("status", status);
			List<Search> list = searchService.showRegisteredSearchByTeam(searchCon);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//M002-2
	@GetMapping("/search/2/{teamId}/{reservationStatus}/{isApply}")
	public ResponseEntity showRegisteredSearchAppliedByTeam(@PathVariable int teamId,
			@PathVariable int reservationStatus,
			@PathVariable int isApply) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("isApply", isApply);
			if(isApply == 0) searchCon.put("teamIdGiver", teamId);
			else searchCon.put("teamIdTaker", teamId);
			searchCon.put("reservationStatus", reservationStatus);
			List<Search> list = searchService.showRegisteredSearchAppliedByTeam(searchCon);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	
	//M006
	@PutMapping("/search-reservation/1")
	public ResponseEntity updateResStatus(@RequestBody Search search) throws SQLException {
		try {
			SearchReservation searchRes = search.getSearchReservations().get(0);
			searchService.updateResStatus(search, searchRes);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/search-reservation/2")
	public ResponseEntity timerOn(@RequestBody Search search) throws SQLException {
		try {
			SearchReservation searchRes = search.getSearchReservations().get(0);
			searchService.timeSearchFailure(search, searchRes);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
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
	
	//M013
	@DeleteMapping("/search-reservation/{searchId}/{teamId}")
	public ResponseEntity deleteSeachRes(@PathVariable int searchId,
			@PathVariable int teamId) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("searchId", searchId);
			searchCon.put("teamIdTaker", teamId);
			searchService.deleteSeachRes(searchCon);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
