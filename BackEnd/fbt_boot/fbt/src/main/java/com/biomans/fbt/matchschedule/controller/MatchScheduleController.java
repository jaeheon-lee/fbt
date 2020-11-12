package com.biomans.fbt.matchschedule.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class MatchScheduleController {
	@Autowired
	private MatchScheduleService matchScheduleService;
	
	//S001 | S002: 일정 등록과 방금 등록된 일정 ID 가져오기
	@PostMapping("/match-schedule")
	public ResponseEntity searchTeams(@RequestBody MatchSchedule matchSchedule) throws SQLException {
		try {
			matchScheduleService.addMatchSchedule(matchSchedule);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
