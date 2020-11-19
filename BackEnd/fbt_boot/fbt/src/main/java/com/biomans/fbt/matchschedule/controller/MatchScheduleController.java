package com.biomans.fbt.matchschedule.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.util.SearchKey;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class MatchScheduleController {
	@Autowired
	private MatchScheduleService matchScheduleService;
	
	//S001: 일정 등록
	@PostMapping("/match-schedule")
	public ResponseEntity searchTeams(@RequestBody MatchSchedule matchSchedule) throws SQLException {
		try {
			matchScheduleService.addMatchSchedule(matchSchedule);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	// S004: 일정 삭제
	@DeleteMapping("/match-schedule/{matchScheduleId}")
	public ResponseEntity deleteMatchSchedule(@PathVariable int matchScheduleId) throws SQLException {
		try {
			matchScheduleService.deleteMatchSchedule(matchScheduleId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//S005: 팀에 등록된 일정 기간 별로 불러오기
	@GetMapping("/match-schedule/{teamId}")
	public ResponseEntity showMatchSchduleByTeamPeriod(@PathVariable int teamId,
			@RequestParam(value="startTime") String startTime,
			@RequestParam(value="endTime") String endTime) throws SQLException {
		try {
			SearchKey searchKey = new SearchKey();
			searchKey.setTeamId(teamId);
			searchKey.setStartTime(startTime);
			searchKey.setEndTime(endTime);
			List<MatchSchedule> list = matchScheduleService.showMatchSchduleByTeamPeriod(searchKey);
			return new ResponseEntity(list,HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//S005: 팀에 등록된 일정 기간 별로 불러오기
	@GetMapping("/match-schedule/2/{matchScheduleId}")
	public ResponseEntity showMatchScheduleById(@PathVariable int matchScheduleId) throws SQLException {
		try {
			MatchSchedule matchSchedule = matchScheduleService.showMatchScheduleById(matchScheduleId);
			return new ResponseEntity(matchSchedule,HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
