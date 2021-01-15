package com.biomans.fbt.assignment.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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

import com.biomans.fbt.assignment.service.AssignmentService;
import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.AssignmentReservation;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.notice.service.NoticeService;
import com.biomans.fbt.util.Filter;
import com.biomans.fbt.util.NoticeFactor;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class AssignmentController {
	@Autowired
	private AssignmentService assignmentService;
	
	@Autowired
	private MatchScheduleService matchScheduleService;
	
	@Autowired
	private NoticeService noticeService;
	
	//FA01
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
	
	//FA04, FA10
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
	
	//FA07, FA12, FA13
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
	
	//FA02
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
	
	//FA03
	@PostMapping("/assignment-reservation")
	public ResponseEntity doApplyAssignment(@RequestBody Assignment assign) throws SQLException {
		try {
			AssignmentReservation assignmentRes = assign.getAssignmentReservations().get(0);
			
			assignmentService.doApplyAssignment(assignmentRes);
			
			//2. 알림 보낸다
			//2-1. 알림 보낼 때 필요한 정보를 정리한다.
			NoticeFactor nf = new NoticeFactor();
			nf.setType("applyAssign");
			nf.setTeamName(assignmentRes.getTeamTaker().getTeamName());
			nf.setAssign(assign);;
			nf.setAssignRes(assignmentRes);
			//2-2. 알림을 보낸다.
			noticeService.addNoticeByCase(nf);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FA08, FA09
	@PutMapping("/assignment-reservation/1")
	public ResponseEntity updateResStatus(@RequestBody Assignment assignment) throws SQLException {
		try {
			Boolean isUpdated = false;
			
			try {
				assignmentService.updateResStatus(assignment);
				isUpdated = true;
			} catch(Exception e) {
				isUpdated = false;
				System.out.println("수정에 실패");
			}
			
			if(isUpdated == true) {
				AssignmentReservation assignmentRes = assignment.getAssignmentReservations().get(0);
				//2. 알림 보낸다
				//2-1. 알림 보낼 때 필요한 정보를 정리한다.
				NoticeFactor nf = new NoticeFactor();
				String type = ""; 
				int status = assignmentRes.getReservationStatus();
				if(status == 1) type = "acceptAssign";
				else if(status == -1) type = "refuseAssign";
				else type = "completeAssign";
				nf.setType(type);
				nf.setTeamName(assignment.getTeamGiver().getTeamName());
				nf.setAssign(assignment);
				nf.setAssignRes(assignmentRes);
				//2-2. 알림을 보낸다.
				noticeService.addNoticeByCase(nf);
				//2-3. 양도 확정이면 실패된 팀들에게 알림을 보낸다
				if(type.equals("completeAssign")) {
					Assignment a = assignmentService.getAssignmentById(assignment.getAssignmentId());
					List<AssignmentReservation> ars = a.getAssignmentReservations();
					for(AssignmentReservation ar : ars) {
						// 확정하기로한 팀이면 넘어간다.
						if(assignmentRes.getTeamTaker().getTeamId() == ar.getTeamTaker().getTeamId()) continue;
						NoticeFactor nf2 = new NoticeFactor();
						String type2 = "failAssign"; 
						nf2.setType(type2);
						nf2.setTeamName(assignment.getTeamGiver().getTeamName());
						nf2.setAssign(assignment);
						nf2.setAssignRes(ar);
						noticeService.addNoticeByCase(nf2);
					}
				}
			}
			
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FA05, FA11
	@DeleteMapping("/assignment/{assignmentId}")
	public ResponseEntity deleteSearch(@PathVariable int assignmentId) throws SQLException {
		try {
			assignmentService.deleteAssignment(assignmentId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FA06
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
	
	//FA14
	@DeleteMapping("/assign-reservation/{assignmentId}/{teamId}")
	public ResponseEntity deleteSeachRes(@PathVariable int assignmentId,
			@PathVariable int teamId,
			@RequestParam(value="teamName") String teamName,
			@RequestParam(value="msgTeamTakerId") int msgTeamTakerId,
			@RequestParam(value="msgTaker") String msgTaker) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("assignmentId", assignmentId);
			searchCon.put("teamTakerId", teamId);
			assignmentService.deleteAssignRes(searchCon);
			
			Notice notice = new Notice();
			Team teamGiver = new Team();
			teamGiver.setTeamId(teamId);
			Team teamTaker = new Team();
			teamTaker.setTeamId(msgTeamTakerId);
			List<User> takerUsers = new ArrayList<User>();
			User takerUser = new User();
			String email = msgTaker.split("-")[1];
			takerUser.setEmail(email);
			takerUsers.add(takerUser);
			String content = teamName + "에서 ";
			content += "양도취소을 했습니다.";
			String pageName = "apply-cancelAssignApply";
			
			notice.setGiverTeam(teamGiver);
			notice.setTakerTeam(teamTaker);
			notice.setTakerUsers(takerUsers);
			notice.setContent(content);
			notice.setPageName(pageName);
			//2-2. 알림을 보낸다.
			noticeService.addNotice(notice);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FA06
	@PutMapping("/assignment/2")
	public ResponseEntity renewAssign(@RequestBody Assignment assignment) throws SQLException {
		try {
			assignmentService.updateAssignment(assignment);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

}
