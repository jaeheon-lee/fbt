package com.biomans.fbt.search.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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
import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.notice.service.NoticeService;
import com.biomans.fbt.search.service.SearchService;
import com.biomans.fbt.teammember.service.TeamMemberService;
import com.biomans.fbt.util.Filter;
import com.biomans.fbt.util.NoticeFactor;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class SearchController {
	@Autowired
	private SearchService searchService;
	public static HashMap<Integer, Future<Integer>> searchId;
	public static HashMap<Integer, Integer> search;
	
	@Autowired
	private MatchScheduleService matchScheduleService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired 
	private TeamMemberService teamMemberService;
	
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
	public ResponseEntity doApplySearch(@RequestBody Search search,
			@RequestParam(value="teamName") String teamName) throws SQLException {
		try {
			searchService.doApplySearch(search);
			
			//2. 알림 보낸다
			//2-1. 알림 보낼 때 필요한 정보를 정리한다.
			NoticeFactor nf = new NoticeFactor();
			nf.setType("applySearch");
			nf.setTeamName(teamName);
			nf.setSearch(search);
			nf.setSearchRes(search.getSearchReservations().get(0));
			//2-2. 알림을 보낸다.
			noticeService.addNoticeByCase(nf);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FM04, FM12
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
	
	//FM05, FM13
	@DeleteMapping("/search/{searchId}")
	public ResponseEntity deleteSearch(@PathVariable int searchId) throws SQLException {
		try {
			searchService.deleteSearch(searchId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FM06
	@PutMapping("/search/1/{searchId}")
	public ResponseEntity renewSearch(@PathVariable int searchId) throws SQLException {
		try {
			searchService.renewSearch(searchId);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FM07, FM10, FM16
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
	
	
	//FM08, FM09, FM11
	@PutMapping("/search-reservation/1")
	public ResponseEntity updateResStatus(@RequestBody Search search,
			@RequestParam(value="teamName") String teamName) throws SQLException {
		try {
			SearchReservation searchRes = search.getSearchReservations().get(0);
			searchService.updateResStatus(search, searchRes);
			
			//2. 알림 보낸다
			//2-1. 알림 보낼 때 필요한 정보를 정리한다.
			NoticeFactor nf = new NoticeFactor();
			String type = "";
			int status = searchRes.getReservationStatus();
			if(status == 1) type = "acceptSearch";
			else if(status == -1) type = "refuseSearch";
			nf.setType(type);
			nf.setTeamName(teamName);
			nf.setSearch(search);
			nf.setSearchRes(searchRes);
			//2-2. 알림을 보낸다.
			noticeService.addNoticeByCase(nf);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FM08
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
	
	//FM15
	@PutMapping("/search/2")
	public ResponseEntity completeSearch(@RequestBody Search search,
			@RequestParam(value="teamName") String teamName) throws SQLException {
		try {
			searchService.completeSearch(search);
			//2. 알림 보낸다
			//2-1. 알림 보낼 때 필요한 정보를 정리한다.
			SearchReservation searchRes = new SearchReservation();
			for(SearchReservation sr : search.getSearchReservations()) {
				if(sr.getReservationStatus() == 2) {
					searchRes = sr;
					break;
				}
			}
			NoticeFactor nf = new NoticeFactor();
			String type = "completeSearch";
			int status = searchRes.getReservationStatus();
			nf.setType(type);
			nf.setTeamName(teamName);
			nf.setSearch(search);
			nf.setSearchRes(searchRes);
			//2-2. 알림을 보낸다.
			noticeService.addNoticeByCase(nf);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	//FM17
	@DeleteMapping("/search-reservation/{searchId}/{teamId}")
	public ResponseEntity deleteSeachRes(@PathVariable int searchId,
			@PathVariable int teamId,
			@RequestParam(value="teamName") String teamName,
			@RequestParam(value="msgTeamTakerId") int msgTeamTakerId,
			@RequestParam(value="msgTaker") String msgTaker) throws SQLException {
		try {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("searchId", searchId);
			searchCon.put("teamIdTaker", teamId);
			System.out.println(searchCon);
			searchService.deleteSeachRes(searchCon);
			
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
			content += "인원파악취소을 했습니다.";
			String pageName = "search-cancelSearchApply";
			
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
	
	//FM18
	@PutMapping("/search/3")
	public ResponseEntity updateSearch(@RequestBody Search search) throws SQLException {
		try {
			searchService.updateSearch(search);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
