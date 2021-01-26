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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.TeamScore;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.teammember.service.TeamMemberService;
import com.biomans.fbt.util.MatchResultCollection;
import com.biomans.fbt.util.NoticeFactor;
import com.biomans.fbt.util.SearchKey;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class MatchScheduleController {
	@Autowired
	private MatchScheduleService matchScheduleService;
	
	@Autowired
	private TeamMemberService teamMemberService;
	
	//M01-1
	@GetMapping("/match-schedule/2/{matchScheduleId}")
	public ResponseEntity showMatchScheduleByMatchScheduleId(@PathVariable int matchScheduleId) throws SQLException {
		try {
			MatchSchedule matchSchedule = matchScheduleService.showMatchScheduleByMatchScheduleId(matchScheduleId);
			return new ResponseEntity(matchSchedule,HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FS02
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
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FV16
	@PutMapping("/match-schedule/1/{matchScheduleId}/{homeTeamId}")
	public ResponseEntity confirmMatchSchedule(@PathVariable int matchScheduleId,
			@PathVariable int homeTeamId) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("matchScheduleId", matchScheduleId);
			searchCon.put("teamId", homeTeamId);
			matchScheduleService.confirmMatchSchedule(searchCon);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FS07
	@GetMapping("/match-schedule/5/{matchScheduleId}/{teamId}")
	public ResponseEntity showMatchScheduleResult(@PathVariable int matchScheduleId,
			@PathVariable int teamId) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("matchScheduleId", matchScheduleId);
			searchCon.put("teamId", teamId);
			MatchSchedule matchSchedule = matchScheduleService.showMatchScheduleResultByTeam(searchCon);
			return new ResponseEntity(matchSchedule,HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FS08
	@PostMapping("/match-schedule/2")
	public ResponseEntity addMatchResultCollection(@RequestBody MatchResultCollection matchResultCollection) throws SQLException {
		try {
			matchScheduleService.addMatchResultCollection(matchResultCollection);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FS09
	@GetMapping("/match-schedule/3/{matchScheduleId}/{teamId}")
	public ResponseEntity showAttendVotedMember(@PathVariable int matchScheduleId,
			@PathVariable int teamId) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("matchScheduleId", matchScheduleId);
			searchCon.put("teamId", teamId);
			List<TeamMember> list = matchScheduleService.showAttendVotedMember(searchCon);
			return new ResponseEntity(list,HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FS10
	@GetMapping("/match-schedule/4/{matchScheduleId}/{teamId}")
	public ResponseEntity showAttendFriendEmploy(@PathVariable int matchScheduleId,
			@PathVariable int teamId) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("matchScheduleId", matchScheduleId);
			searchCon.put("teamId", teamId);
			HashMap<String, List<User>> friendEmployMap = matchScheduleService.showAttendFriendEmploy(searchCon);
			return new ResponseEntity(friendEmployMap,HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	//FS11
	@GetMapping("/match-schedule/7/{matchScheduleId}/{teamId}")
	public ResponseEntity showMatchResultCollection(@PathVariable int matchScheduleId,
			@PathVariable int teamId) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("matchScheduleId", matchScheduleId);
			searchCon.put("teamId", teamId);
			MatchResultCollection mrc = matchScheduleService.showMatchResultCollection(searchCon);
			return new ResponseEntity(mrc,HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	// FS11
	@PutMapping("/match-schedule/2")
	public ResponseEntity confirmMatchSchedule(@RequestBody MatchResultCollection matchResultCollection) throws SQLException {
		try {
			matchScheduleService.updateMatchResult(matchResultCollection);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FS12
	@GetMapping("/match-schedule/6/{email}")
	public ResponseEntity showMatchSchduleByTeamPeriod(@PathVariable String email,
			@RequestParam(value="startTime") String startTime,
			@RequestParam(value="endTime") String endTime) throws SQLException {
		try {
			HashMap<String, String> searchCon = new HashMap<String, String>();
			searchCon.put("email", email);
			searchCon.put("startTime", startTime);
			searchCon.put("endTime", endTime);
			List<MatchSchedule> list = matchScheduleService.showMatchSchduleByUserPeriod(searchCon);
			return new ResponseEntity(list,HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FS14
	@GetMapping("/match-schedule/8/{matchScheduleId}/{email}")
	public ResponseEntity showMatchScheduleResultByUser(@PathVariable String email,
			@PathVariable int matchScheduleId) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("matchScheduleId", matchScheduleId);
			MatchSchedule matchSchedule = matchScheduleService.showMatchScheduleResultByUser(searchCon, email);
			return new ResponseEntity(matchSchedule,HttpStatus.OK);
		}catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FS15
	@PostMapping("/match-schedule/3")
	public ResponseEntity addTeamScore(@RequestBody TeamScore teamScore) throws SQLException {
		try {
			teamScore.setTeamGiver(null);
			matchScheduleService.addTeamScore(teamScore);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FS16
	@PutMapping("/match-schedule/3")
	public ResponseEntity updateTeamScore(@RequestBody TeamScore teamScore) throws SQLException {
		try {
			matchScheduleService.updateTeamScore(teamScore);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FS17
	@GetMapping("/match-schedule/9/{teamId}")
	public ResponseEntity showFutureSchedule(@PathVariable int teamId) throws SQLException {
		try {
			List<MatchSchedule> list = matchScheduleService.showFutureSchedule(teamId);
			return new ResponseEntity(list,HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FS18
	@PutMapping("/match-schedule/4")
	public ResponseEntity updateMatchSchedule(@RequestBody MatchSchedule matchSchedule,
			@RequestParam(value="updatingTeamId") int updatingTeamId) throws SQLException {
		try {
			matchScheduleService.updateMatchSchedule(matchSchedule);
			
			// 자기 팀에게 알림을 보낸다
			Notice notice = new Notice();
//			notice.set
			
			
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	// V06-1: 일정 삭제
	@DeleteMapping("/match-schedule/{matchScheduleId}")
	public ResponseEntity deleteMatchSchedule(@PathVariable int matchScheduleId) throws SQLException {
		try {
			matchScheduleService.deleteMatchSchedule(matchScheduleId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
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
	
	

}
