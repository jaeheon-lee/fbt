package com.biomans.fbt.assignment.controller;

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

import com.biomans.fbt.assignment.service.AssignmentService;
import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.AssignmentReservation;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.util.Filter;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class AssignmentController {
	@Autowired
	private AssignmentService assignmentService;
	
	@Autowired
	private MatchScheduleService matchScheduleService;
	
	//A001
	@PostMapping("/assignment")
	public ResponseEntity addAssignment(@RequestBody Assignment assignment) throws SQLException {
		try {
			//S001
			MatchSchedule matchSchedule = assignment.getMatchSchedule();
			if(matchSchedule.getMatchScheduleId() == 0) {
				int teamId = assignment.getTeamGiver().getTeamId();
				matchSchedule.getHomeTeam().setTeamId(teamId);
				if(matchSchedule.getAwayTeam().getTeamId() == 0) matchSchedule.setAwayTeam(null);
				matchScheduleService.addMatchSchedule(matchSchedule);
				//S002
				int matchScheduleId = matchScheduleService.showLatestMatchScheduleIdById(teamId);
				assignment.getMatchSchedule().setMatchScheduleId(matchScheduleId);
			}
			//A001
			assignmentService.addAssignment(assignment);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//A002-1
	@GetMapping("/assignment/1/{teamId}/{status}")
	public ResponseEntity showRegisteredAssignByTeam(@PathVariable int teamId,
			@PathVariable int status) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("teamIdGiver", teamId);
			searchCon.put("status", status);
			List<Assignment> list = assignmentService.showRegisteredAssignByTeam(searchCon);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//A002-2
	@GetMapping("/assignment/2/{teamId}/{reservationStatus}/{isApply}")
	public ResponseEntity showRegisteredAssignAppliedByTeam(@PathVariable int teamId,
			@PathVariable int reservationStatus,
			@PathVariable int isApply) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("isApply", isApply);
			if(isApply == 0) searchCon.put("teamIdGiver", teamId);
			else searchCon.put("teadTakerId", teamId);
			searchCon.put("reservationStatus", reservationStatus);
			List<Assignment> list = assignmentService.showRegisteredAssignAppliedByTeam(searchCon);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//A003
	@PostMapping("/assignment/3")
	public ResponseEntity addAssignment(@RequestBody Filter filter) throws SQLException {
		try {
			List<Assignment> list = assignmentService.searchAssignmentByFilter(filter);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//A005
	@PostMapping("/assignment-reservation")
	public ResponseEntity doApplyAssignment(@RequestBody AssignmentReservation assignmentRes) throws SQLException {
		try {
			assignmentService.doApplyAssignment(assignmentRes);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//M006
	@PutMapping("/assignment-reservation/1")
	public ResponseEntity updateResStatus(@RequestBody Assignment assignment) throws SQLException {
		try {
			assignmentService.updateResStatus(assignment);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//A009
	@DeleteMapping("/assignment/{assignmentId}")
	public ResponseEntity deleteSearch(@PathVariable int assignmentId) throws SQLException {
		try {
			assignmentService.deleteAssignment(assignmentId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//A010
	@PutMapping("/assignment/1/{assignmentId}")
	public ResponseEntity renewAssign(@PathVariable int assignmentId) throws SQLException {
		try {
			assignmentService.renewAssign(assignmentId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//A013
	@DeleteMapping("/assign-reservation/{assignmentId}/{teamId}")
	public ResponseEntity deleteSeachRes(@PathVariable int assignmentId,
			@PathVariable int teamId) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("assignmentId", assignmentId);
			searchCon.put("teamTakerId", teamId);
			assignmentService.deleteAssignRes(searchCon);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

}
