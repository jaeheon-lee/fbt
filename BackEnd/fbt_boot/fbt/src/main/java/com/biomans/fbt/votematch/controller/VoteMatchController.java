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

import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.domain.VoteMatchSetting;
import com.biomans.fbt.notice.dao.NoticeDAO;
import com.biomans.fbt.notice.service.NoticeService;
import com.biomans.fbt.teammember.dao.TeamMemberDAO;
import com.biomans.fbt.teammember.service.TeamMemberService;
import com.biomans.fbt.util.NoticeFactor;
import com.biomans.fbt.votematch.service.VoteMatchService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class VoteMatchController {
	@Autowired
	private VoteMatchService voteMatchService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired 
	private TeamMemberService teamMemberService;
	
	//FV01, FV02, FS02
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
			System.out.println(e);
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FV03
	@GetMapping("/vote-match/2/{matchScheduleId}/{teamId}")
	public ResponseEntity showVoteMatchInfoByScheduleId(@PathVariable int matchScheduleId,
			@PathVariable int teamId) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("matchScheduleId", matchScheduleId);
			searchCon.put("teamId", teamId);
			System.out.println(searchCon);
			VoteMatch voteMatch = voteMatchService.showVoteMatchInfoByScheduleId(searchCon);
			System.out.println("da : " + voteMatch);
			return new ResponseEntity(voteMatch, HttpStatus.OK);
		}catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FV04
	@PostMapping("/vote-match")
	public ResponseEntity addVoteMatchAndSetting(@RequestBody VoteMatch voteMatch,
			@RequestParam(value="teamName") String teamName) throws SQLException{
		try {
			//1. 투표 등록한다
			voteMatchService.addVoteMatchAndSetting(voteMatch);
			
			//2. 알림 보낸다
			//2-1. 알림 보낼 때 필요한 정보를 정리한다.
			NoticeFactor nf = new NoticeFactor();
			nf.setType("addVoteMatch");
			nf.setTeamName(teamName);
			nf.setVoteMatch(voteMatch);
			List<TeamMember> members = teamMemberService.showMembersByTeamId(voteMatch.getTeam().getTeamId());
			nf.setTeamMembers(members);
			//2-2. 알림을 보낸다.
			noticeService.addNoticeByCase(nf);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FV05
	@PostMapping("/vote-match-result")
	public ResponseEntity addAttendance(@RequestBody VoteMatch voteMatch,
			@RequestParam(value="teamName") String teamName) throws SQLException {
		try {			
			VoteMatchResult voteMatchResult = voteMatch.getVoteMatchResult();
			System.out.println(voteMatchResult);
			int searchId =  voteMatchService.addAttendance(voteMatchResult, voteMatch);
			if(searchId != 0 ) {
				NoticeFactor nf = new NoticeFactor();
				nf.setType("fillNumber");
				nf.setTeamName(teamName);
				nf.setVoteMatch(voteMatch);
				Search search = new Search();
				search.setSearchId(searchId);
				nf.setSearch(search);
				//2-2. 알림을 보낸다.
				noticeService.addNoticeByCase(nf);
			}
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	//FV05, FV06, FS05 ,FS06
	@PutMapping("/vote-match-result")
	public ResponseEntity updateVoteMatchResult(@RequestBody VoteMatchResult voteMatchResult) throws SQLException {
		try {
			voteMatchService.updateVoteMatchResult(voteMatchResult);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FV07
	@PutMapping("/vote-match/1")
	public ResponseEntity endVoteMatch(@RequestBody VoteMatch voteMatch,
			@RequestParam(value="teamName", required = false) String teamName) throws SQLException {
		try {
			//1. 투표를 마감한다.
			//1-1. 마감이라는 것을 표시
			int type = 0;
			//1-2. 마감하기
			voteMatchService.updateVoteMatch(voteMatch, type, teamName);
			
			//2. 알림 보낸다
			//2-1. 알림 보낼 때 필요한 정보를 정리한다.
			NoticeFactor nf = new NoticeFactor();
			nf.setType("endVoteMatch");
			nf.setTeamName(teamName);
			nf.setVoteMatch(voteMatch);
			List<TeamMember> members = teamMemberService.showMembersByTeamId(voteMatch.getTeam().getTeamId());
			nf.setTeamMembers(members);
			//2-2. 알림을 보낸다.
			noticeService.addNoticeByCase(nf);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FV09
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
	
	//FV10
	@PutMapping("/vote-match-setting")
	public ResponseEntity updateVoteMatchSetting(@RequestBody VoteMatchSetting voteMatchSetting) throws SQLException {
		try {
			voteMatchService.updateVoteMatchSetting(voteMatchSetting);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	//FV14
	@PutMapping("/vote-match/2")
	public ResponseEntity updateVoteMatch(@RequestBody VoteMatch voteMatch,
			@RequestParam(value="teamName", required=false) String teamName) throws SQLException {
		try {
			//1. 투표를 수정한다
			//1-1. 수정이라는 것을 표시
			int type = 1;
			//1-2. 수정한다.
			voteMatchService.updateVoteMatch(voteMatch, type, teamName);
			
			//2. 알림 보낸다
			//2-1. 알림 보낼 때 필요한 정보를 정리한다.
			NoticeFactor nf = new NoticeFactor();
			nf.setType("updateVoteMatch");
			nf.setTeamName(teamName);
			nf.setVoteMatch(voteMatch);
			List<TeamMember> members = teamMemberService.showMembersByTeamId(voteMatch.getTeam().getTeamId());
			nf.setTeamMembers(members);
			//2-2. 알림을 보낸다.
			noticeService.addNoticeByCase(nf);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FV15
	@GetMapping("vote-match/3/{teamId}/{type}")
	public ResponseEntity loadEndedVoteMatch(@PathVariable int teamId,
			@PathVariable int type) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("teamId", teamId);
			searchCon.put("type", type);
			List<VoteMatch> list = voteMatchService.loadEndedVoteMatch(searchCon);
			return new ResponseEntity(list, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//FV01, FV02, FS02
	@GetMapping("/vote-match/4/{voteMatchId}")
	public ResponseEntity showVoteMatchInfoByTeam(@PathVariable int voteMatchId) throws SQLException {
		try {
			VoteMatch voteMatch = voteMatchService.showVoteMatchInfoById(voteMatchId);
			return new ResponseEntity(voteMatch, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
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
}
