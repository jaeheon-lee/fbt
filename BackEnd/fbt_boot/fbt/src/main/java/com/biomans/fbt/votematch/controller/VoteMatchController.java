package com.biomans.fbt.votematch.controller;

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

import com.biomans.fbt.domain.Invite;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.domain.VoteMatchSetting;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.votematch.service.VoteMatchService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class VoteMatchController {
	@Autowired
	private VoteMatchService voteMatchService;
	
	@Autowired
	private MatchScheduleService matchScheduleService;
	
	//V001, V012
	@GetMapping("/vote-match/1/{teamId}")
	public ResponseEntity showVoteMatchInfoByTeam(@PathVariable int teamId,
			@RequestParam(value="voteStatus") int voteStauts) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("teamId", teamId);
			searchCon.put("voteStatus", voteStauts);
			List<VoteMatch> list = voteMatchService.showVoteMatchInfoByTeam(searchCon);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//V002, V003
	@PostMapping("/vote-match-result")
	public ResponseEntity addAttendance(@RequestBody VoteMatchResult voteMatchResult) throws SQLException {
		try {
			List<VoteMatchResult> remain = voteMatchService.showVoteMatchResultByVote(voteMatchResult.getVoteMatchId());
			for(VoteMatchResult vmr : remain) {
				// 이미 투표했다면
				if(vmr.getTeamMember().getTeamMemberId().equals(voteMatchResult.getTeamMember().getTeamMemberId())) {
					
					// 대기인데 또 대기 건다면 하지말도록 유도
					if(vmr.getAttendance() == voteMatchResult.getAttendance()) {
						return new ResponseEntity(2, HttpStatus.OK);
					}else { // 다른 것인데 다른 것으로 바꾸면 수정. 단, 이미 참석눌렀을 경우, 앞단에서 거름
						voteMatchService.updateVoteMatchResult(voteMatchResult);
						return new ResponseEntity(1, HttpStatus.OK);
					}
				}
			}
			// 투표를 하지 않은 경우, 생성
			voteMatchService.addAttendance(voteMatchResult);
			return new ResponseEntity(0, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//V004 : 지인 초대하기
	@PostMapping("/vote-match/invite")
	public ResponseEntity inviteFriend(@RequestBody Invite invite) throws SQLException{
		try {
			voteMatchService.inviteFriend(invite);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//V005 | V006 | S001 | S002: 투표 등록하기, 투표 설정 등록하기, 일정 등록
	@PostMapping("/vote-match")
	public ResponseEntity addVoteMatchAndSetting(@RequestBody VoteMatch voteMatch) throws SQLException{
		try {
			//S001
			MatchSchedule matchSchedule = voteMatch.getMatchSchedule();
			if(matchSchedule.getAwayTeam().getTeamId() == 0) matchSchedule.setAwayTeam(null);
			matchScheduleService.addMatchSchedule(matchSchedule);
			//S002
			int teamId = voteMatch.getTeam().getTeamId();
			int matchScheduleId = matchScheduleService.showLatestMatchScheduleIdById(teamId);
			
			//V005 | V006
			// voteMatchId 설정
			String voteMatchId = String.valueOf(teamId) + "-" + String.valueOf(matchScheduleId);
			voteMatch.getMatchSchedule().setMatchScheduleId(matchScheduleId);
			voteMatch.setVoteMatchId(voteMatchId);
			voteMatch.getVoteMatchSetting().setVoteMatchId(voteMatchId);
			// 로직 실행
			voteMatchService.addVoteMatchAndSetting(voteMatch);
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
	
	//V011: 투표 결과 삭제(지인 투표 불참 시)
	@DeleteMapping("/vote-match-result")
	public ResponseEntity deleteVoteMatchResult(@RequestBody VoteMatchResult voteMatchResult) throws SQLException {
		try {
			voteMatchService.deleteVoteMatchResult(voteMatchResult);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//V013: 지인 검색하기
	@GetMapping("/user/1")
	public ResponseEntity searchFriend(@RequestParam(value="email") String email, 
			@RequestParam(value="teamId") String teamId) throws SQLException {
		try {
			HashMap<String, String> searchCon = new HashMap<String, String>();
			searchCon.put("email", email);
			searchCon.put("teamId", teamId);
			return new ResponseEntity(voteMatchService.searchFriend(searchCon), HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//V014, V015
	@GetMapping("vote-match/2/{matchScheduleId}")
	public ResponseEntity showVoteMatchInfoByScheduleId(@PathVariable int matchScheduleId) throws SQLException {
		try {
			VoteMatch voteMatch = voteMatchService.showVoteMatchInfoByScheduleId(matchScheduleId);
			System.out.println(voteMatch);
			return new ResponseEntity(voteMatch, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
}
