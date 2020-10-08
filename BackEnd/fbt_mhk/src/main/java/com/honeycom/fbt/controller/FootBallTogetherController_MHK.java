package com.honeycom.fbt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.honeycom.fbt.dto.Assignment;
import com.honeycom.fbt.dto.Attendant;
import com.honeycom.fbt.dto.Employment;
import com.honeycom.fbt.dto.MatchInfo;
import com.honeycom.fbt.dto.ReviewFromTeam;
import com.honeycom.fbt.dto.MatchSchedule;
import com.honeycom.fbt.dto.Search;
import com.honeycom.fbt.dto.Team;
import com.honeycom.fbt.dto.TeamScore;
import com.honeycom.fbt.dto.TeamMember;
import com.honeycom.fbt.dto.User;
import com.honeycom.fbt.dto.Vote;
import com.honeycom.fbt.dto.VoteResult;
import com.honeycom.fbt.service.FootBallTogetherService_MHK;

@RestController
//@RequestMapping("fbt")
public class FootBallTogetherController_MHK {

	@Autowired
	FootBallTogetherService_MHK footBallTogetherService_MHK;

	// 모든 유저 불러오기
	@GetMapping("user")
	public ResponseEntity<List<User>> allUser() throws Exception {
		User user = new User();
		System.out.println(user);
		try {
			List<User> rUsers = footBallTogetherService_MHK.selectUsers(user);
			return new ResponseEntity<List<User>>(rUsers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
	}

	// 회원 가입
	@PostMapping("user")
	public ResponseEntity registerUser(@RequestBody User user) throws Exception {
		boolean result = footBallTogetherService_MHK.insertUser(user);

		if (result)
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);

	}

	// 회원 정보 수정
	@PutMapping("user")
	public ResponseEntity updateUserInfo(@RequestBody User user) throws Exception {
		boolean result = footBallTogetherService_MHK.updateUser(user);

		if (result)
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);

	}

	// 회원 탈퇴
	@DeleteMapping("user")
	public ResponseEntity deleteUser(@RequestBody User user) throws Exception {
		boolean result = footBallTogetherService_MHK.deleteUser(user);

		if (result)
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	// 팀 생성 -> (1)팀을 생성하는 쿼리 실행, (2) 팀을 생성한 아이디를 매니저로 바로 승급시킴 : 세션에 저장된 로그인 정보 필요
	@PostMapping("team")
	public ResponseEntity createTeam(@RequestBody Team team, HttpSession session) throws Exception {
		try {
			boolean resultTeam = footBallTogetherService_MHK.insertTeam(team);
			System.out.println("팀 생성됨 :: " + team);
			
			// 팀 생성한 유저를 팀멤버에 추가하고 매니저로 승급시키기 위해 이메일을 세션에서 가져오고
			// 팀 아이디는 useGeneratedKeys로 받아오기
			TeamMember tm = new TeamMember();
			tm.setEmail(((User)session.getAttribute("rUser")).getEmail());
			tm.setTeamId(team.getTeamId());
			
			boolean resultTeamMember = footBallTogetherService_MHK.insertTeamMember(tm);
			System.out.println("팀멤버 추가됨" + tm);
			
			return new ResponseEntity(HttpStatus.OK);				
		} catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	// 모든 팀 가져오기(DB insert 확인용으로 작성)
	@GetMapping("team")
	public ResponseEntity<List<Team>> allTeam() throws Exception {
		Team team = new Team();
		try {
			List<Team> list = footBallTogetherService_MHK.selectTeams(team);
			return new ResponseEntity<List<Team>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Team>>(HttpStatus.NO_CONTENT);
		}
	}

	// 팀 회원 추가 => 팀 회원 가입 요청 시 DB에 가입 대기 상태(default = 1)로 추가됨
	@PostMapping("teamMember")
	public ResponseEntity requestTeamMember(@RequestBody TeamMember tm) throws Exception {
		boolean result = footBallTogetherService_MHK.insertTeamMember(tm);

		if (result)
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	// 모든 팀회원 가져오기(DB insert 확인용으로 작성)
	@GetMapping("teamMember")
	public ResponseEntity<List<TeamMember>> allTeamMember() throws Exception {
		TeamMember teamMember = new TeamMember();
		try {
			List<TeamMember> list = footBallTogetherService_MHK.selectTeamMembers(teamMember);
			return new ResponseEntity<List<TeamMember>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<TeamMember>>(HttpStatus.NO_CONTENT);
		}
	}

	// 팀 회원 가입 수락 => 매니저가 가입 대기중인 유저의 가입 수락, 팀 회원으로 상태 변환(reg_stauts=2)
	@PutMapping("teamMember_allow")
	public ResponseEntity allowTeamMember(@RequestBody TeamMember tm) throws Exception {
		boolean result = footBallTogetherService_MHK.updateTeamMember_allow(tm);

		if (result)
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	// 팀 회원 가입 거절 => 매니저가 가입 대기 중인 유저의 가입 거절, 거절 시 DB에서 삭제
	@DeleteMapping("teamMember")
	public ResponseEntity rejectTeamMember(@RequestBody TeamMember tm) throws Exception {
		boolean result = footBallTogetherService_MHK.deleteTeamMember(tm);

		if (result)
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	// 팀 회원 탈퇴 => 탈퇴 또는 강제탈퇴 시 팀회원의 상태를 탈퇴로 변환(reg_status = 3), 삭제 아님
	@PutMapping("teamMember_quit")
	public ResponseEntity quitTeamMember(@RequestBody TeamMember tm) throws Exception {
		boolean result = footBallTogetherService_MHK.updateTeamMember_quit(tm);

		if (result)
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	// 부매니저 임명 => 매니저가 회원(reg_status=2)들을 대상으로 부매니저 권한을 설정(member_level = 2)
	@PutMapping("teamMember_appoint")
	public ResponseEntity appointViceManager(@RequestBody TeamMember tm) throws Exception {
		boolean result = footBallTogetherService_MHK.updateTeamMember_appoint(tm);

		if (result)
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	// 소속팀 중 하나 선택해서 해당 팀 홈 들어가기(매니저 & 일반 회원)
	// 팀 홈 접속 시 해당 팀과 관련된 모든 VO DB에서 가져오기
	@GetMapping("team/{teamId}")
	public ResponseEntity selectMyTeam(@PathVariable int teamId, HttpSession session) throws Exception {
		Team team = new Team();
		team.setTeamId(teamId);
		
		List<Team> teams = footBallTogetherService_MHK.selectTeams(team);
		
		// teamMannerScore
		TeamScore teamMannerScore = new TeamScore();
		teamMannerScore.setTeamId(teamId);
		teamMannerScore = footBallTogetherService_MHK.selectTeamMannerScore(teamMannerScore);
		
		// teamMember
		TeamMember teamMember = new TeamMember();
		List<TeamMember> teamMembers = footBallTogetherService_MHK.selectTeamMembers(teamMember);
		
		// vote
		Vote vote = new Vote();
		vote.setTeamId(teamId);
		List<Vote> votes = footBallTogetherService_MHK.selectVotes(vote);
		
		// matchSchedules
		MatchSchedule ms = new MatchSchedule();
		ms.setHomeTeamId(teamId);
		List<MatchSchedule> matchSchedules = footBallTogetherService_MHK.selectMatchSchedules(ms);
		
		// assignment
		Assignment assignment = new Assignment();
		assignment.setTeamIdGiver(teamId);
		List<Assignment> assignments = footBallTogetherService_MHK.selectAssignments(assignment);		
		
		// employment
		Employment emp = new Employment();
		emp.setTeamIdGiver(teamId);
		List<Employment> emps = footBallTogetherService_MHK.selectEmployments(emp); 
		
		// search
		Search search = new Search();
		search.setTeamIdGiver(teamId);
		List<Search> searchs = footBallTogetherService_MHK.selectSearchs(search);
		
		// matchInfo
		MatchInfo mi = new MatchInfo();
		mi.setHomeTeamId(teamId);
		List<MatchInfo> matchInfo = footBallTogetherService_MHK.selectMatchInfos(mi);
		
		// Team VO에 주입
		Team teamResult = teams.get(0);
		teamResult.setTeamScore(teamMannerScore);
		teamResult.setTeamMembers(teamMembers);
		teamResult.setVotes(votes);
		teamResult.setMatchSchedules(matchSchedules);
		teamResult.setAssignments(assignments);
		teamResult.setEmployements(emps);
		teamResult.setSearchs(searchs);
		teamResult.setMatchInfo(matchInfo);
		
		// session에 주입
		session.setAttribute("team", teamResult);
		
		//편의를 위해 teamId와 teamMemberId를 session에 추가
		session.setAttribute("teamId", teamId);
		session.setAttribute("teamMemberId", teamMembers.get(0).getTeamMemberId());
		
		if(teamResult != null) return new ResponseEntity(HttpStatus.OK);
		else return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	// 상대팀 매치 관련 기능	
	
	// 1. 팀 매칭을 원하는 경우 DB의 search 테이블에 해당 내용이 insert돼야 함 -> 이것도 upsert
	// 나의 팀 아이디와 match_info 아이디가 필수
	// 만약에 match_info가 없는 경우 match_info를 먼저 생성하고 이 기능이 실행됨
	@PutMapping("search")
	public ResponseEntity upsertSearch(@RequestBody MatchInfo matchInfo, HttpSession session) throws Exception {
		// match_info 등록 및 투표 후 상대팀 찾는 경우 -> match_info_id가 있음
		// search 메뉴에서 글을 등록하는 경우 -> match_info_id가 없음
		
		if(matchInfo.getMatchInfoId() == 0) {
			// 글 등록 먼저 해야 함
			boolean miResult = footBallTogetherService_MHK.insertMatchInfo(matchInfo);
			System.out.println(matchInfo.getMatchInfoId()); // 자동생성키가 들어왔는지 확인
		}
		
		// 이제는 둘다 match_info_id가 있는 상태이므로 search를 통해 upsert 수행
		int teamMemberId = (Integer) session.getAttribute("teamMemberId");
		
		Search search = new Search();
		search.setTeamIdGiver(matchInfo.getHomeTeamId());
		search.setTeamMemberIdGiver(teamMemberId);
		search.setMatchInfoId(matchInfo.getMatchInfoId());
		
		
		int searchResult = footBallTogetherService_MHK.upsertSearch(search);
		System.out.println(searchResult);
		
		if(searchResult==1) return new ResponseEntity(HttpStatus.OK);
		else return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	// 2. away팀 입장에서 search에 올라온 글 가져오기 (등록매치현황)
//	@GetMapping("search")
//	public ResponseEntity getSearchs() throws Exception {
//		Search search = new Search();
//		search.setSearchStatus(s);
//		
//		
//	}
//	
	
	
	
	
	// 9월1일 : 경기 후 상대 평가 관련 기능
	/*
	 * 평가 주체 : 실제 경기 참석자(attendant table에 등록됨)
	 * 평가 대상 : match_schedule table에서 상대 팀(away 혹은 home) <- session상 team_id로 상대 팀 구분
	 * 조건 : 경기 완료 여부, 경기 완료 후 7일 이내(7일 이후일 경우 평가대상), 이미 평가된 경기인지?
	 * UI 에서 경기 조회 클릭 시 경기 목록이 나타나고 이들 중 평가 대상 경기는 평가하기 배너가 경기 정보 옆에 나타남
	 * 		-> 평가하기 배너에 상대팀의 id가 포함되어 있다고 가정
	 * 평가하기를 클릭하면 10개 항목에 대해 별점(10점 만점)
	 */

	
	// 선택한 소속팀의 모든 매치 스케쥴 + 나의 참석여부 가져오기 (match_schedule의 attendants 주입)
	@GetMapping("matchHistory")
	public ResponseEntity getMyTeamMatchSchedules(HttpSession session) throws Exception {
		// 세션에 teamId와 
		int myTeamId = (Integer) session.getAttribute("teamId");
		int myTeamMemberId = (Integer) session.getAttribute("teamMemberId");
		
		// 선택한 소속팀의 모든 매치 스케쥴
		MatchSchedule ms = new MatchSchedule();
		ms.setHomeTeamId(myTeamId);
		List<MatchSchedule> msList = footBallTogetherService_MHK.getMyTeamMatchSchedules(ms);
		
		// 각 매치스케쥴에 나의 참석 여부 불러오기
		List<MatchSchedule> msListResult = new ArrayList<MatchSchedule>(); //컨트롤러가 리턴할 객체 생성

		for(MatchSchedule m : msList) {
			List<Attendant> atList = m.getAttendants();
			List<Attendant> atResult = new ArrayList<Attendant>();
			
			for(Attendant at : atList) {
				if(at.getTeamMemberId() == myTeamMemberId) {
					atResult.add(at);
				}
			}
			m.setAttendants(atResult);
			msListResult.add(m);
		}
		
		if(msListResult.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		else return new ResponseEntity(msListResult, HttpStatus.OK);
	}
	
	
	// 평가하기 배너 클릭 후 점수 입력 -> 평가 등록하기 클릭 -> match_review에 본인의 attendant id로 각 항목의 점수가 추가됨
	public ResponseEntity insertReview(HttpServletRequest request, @RequestBody ReviewFromTeam mr) throws Exception {
		// 나의 attendant id는 나의 team_member_id와 match_schedule id로 알 수 있음
		// attendant는 매니저 수동 지정, 매니저가 경기당일 확인 후 참석 여부 지정한 대상자만 attendant table에 추가가 됨
		// 따라서 평가하기 배너가 ui에 나타나려면 attendant table에 해당 경기의 match_schedule id와 내 팀멤버 아이디로 조회했을 때,
		// attendant 아이디가 있어야 함
		// attendant 아이디가 있다는 가정 하에 밑의 로직 진행
		
		// match_review 테이블에 저장
		boolean result1 = footBallTogetherService_MHK.insertReviewFromTeam(mr);
		
		// team_manner_score 테이블에는 그간의 매치 리뷰에 대한 평균값이 저장됨, 새롭게 추가된 점수도 업데이트해야 함
		// 필요한 파라미터 = 상대팀 teamId --> 앞에서 request로 matchSchedule을 쏴준다고 가정
		HttpSession session = request.getSession();
		int myTeamId = (Integer) session.getAttribute("teamId");
		MatchSchedule ms = (MatchSchedule) request.getAttribute("matchSchedule");
		
		// 우리 팀이 홈팀이었으면 상대편팀이 어웨이팀, 반대로 우리 팀이 홈팀이 아니면(어웨이팀이었으면) 상대팀이 홈팀이었으므로
		// 각 경우에 맞는 teamID 반환
		int counterTeamId = 0;
		if(ms.getHomeTeamId() == myTeamId) counterTeamId = ms.getAwayTeamId();
		else counterTeamId = ms.getHomeTeamId();
		
		
		// 만약 특정 teamId에 대해 team_manner_score에 기록이 없다면 = 팀에 대해 한번도 평가가 남겨져 있지 않으면, 
		// 위의 평가를 해당 팀의 team_manner_score에 바로 집어넣으면 됨
		TeamScore tms = new TeamScore();
		tms.setTeamId(counterTeamId);
		tms = footBallTogetherService_MHK.selectTeamMannerScore(tms);
		
		boolean result2 = false;
		if(tms == null) {
			tms.setMannerArrangement(mr.getMannerArrangement());
			tms.setMannerBodyFight(mr.getMannerBodyFight());
			tms.setMannerContact(mr.getMannerContact());
			tms.setMannerPayment(mr.getMannerPayment());
			tms.setMannerPromise(mr.getMannerPromise());
			tms.setMannerRule(mr.getMannerRule());
			tms.setMannerSlang(mr.getMannerSlang());
			tms.setMannerSmoking(mr.getMannerSmoking());
			tms.setMannerUniform(mr.getMannerUniform());
			tms.setMannerTackle(mr.getMannerTackle());
			tms.setMannerReferee(mr.getMannerReferee());
			tms.setCounter(1);
			                 
			result2 = footBallTogetherService_MHK.upsertReviewFromTeam(tms);
		}
		else {
			// 이미 평가된 값(평균값)이 하나라도 있는 경우 -> 평균필터 사용하여 새로운 데이터와 이전 평균값의 새로운 평균값 계산
			int denom = tms.getCounter() + 1;
			tms.setMannerArrangement((tms.getMannerArrangement() * ((denom-1)/denom)) + (mr.getMannerArrangement() * (1/denom)));
			tms.setMannerArrangement((tms.getMannerBodyFight() * ((denom-1)/denom)) + (mr.getMannerBodyFight() * (1/denom)));
			tms.setMannerArrangement((tms.getMannerContact() * ((denom-1)/denom)) + (mr.getMannerContact() * (1/denom)));
			tms.setMannerArrangement((tms.getMannerPayment() * ((denom-1)/denom)) + (mr.getMannerPayment() * (1/denom)));
			tms.setMannerArrangement((tms.getMannerPromise() * ((denom-1)/denom)) + (mr.getMannerPromise() * (1/denom)));
			tms.setMannerArrangement((tms.getMannerReferee() * ((denom-1)/denom)) + (mr.getMannerReferee() * (1/denom)));
			tms.setMannerArrangement((tms.getMannerRule() * ((denom-1)/denom)) + (mr.getMannerRule() * (1/denom)));
			tms.setMannerArrangement((tms.getMannerSlang() * ((denom-1)/denom)) + (mr.getMannerSlang() * (1/denom)));
			tms.setMannerArrangement((tms.getMannerSmoking() * ((denom-1)/denom)) + (mr.getMannerSmoking() * (1/denom)));
			tms.setMannerArrangement((tms.getMannerTackle() * ((denom-1)/denom)) + (mr.getMannerTackle() * (1/denom)));
			tms.setMannerArrangement((tms.getMannerUniform() * ((denom-1)/denom)) + (mr.getMannerUniform() * (1/denom)));
			
			result2 = footBallTogetherService_MHK.upsertReviewFromTeam(tms);
		}
		
		if(result1 && result2) return new ResponseEntity(HttpStatus.OK);
		else return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("user")
	public ResponseEntity<User> login(User user, HttpSession session) throws Exception {

		// user
		User rUser = footBallTogetherService_MHK.selectUser(user);

		// teamMember
		TeamMember teamMember = new TeamMember();
		teamMember.setEmail(user.getEmail());
		System.out.println(teamMember);
		List<TeamMember> teamMembers = footBallTogetherService_MHK.selectTeamMembers(teamMember);
		rUser.setTeamMembers(teamMembers);

		// employment
		Employment employment = new Employment();
		//employment.setEmail(user.getEmail());
		List<Employment> employments = footBallTogetherService_MHK.selectEmployments(employment);
		rUser.setEmployments(employments);

		if (rUser == null)
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		else {
			session.setAttribute("rUser", rUser);
			return new ResponseEntity<User>(rUser, HttpStatus.OK);
		}

	}

	// 3.경기참석 투표 - 3-1 투표개설
	// 투표를 개설한 후에 auto_increment 된 voteId를 가져옵니다. 자동으로 vote가 update 됩니다. 나머지 정보는
	// 가져오지 않아서(몰라서), 그냥 voteId로 vote 객체를 받아 옵니다.
	@PostMapping("vote")
	public ResponseEntity<Vote> createVote(@RequestBody Vote vote) throws Exception {
		footBallTogetherService_MHK.insertVote(vote);
		Vote vote2 = new Vote();
		vote2.setVoteId(vote.getVoteId());
		Vote rVote = footBallTogetherService_MHK.selectVotes(vote2).get(0);

		if (rVote.getVoteId() == 0)
			return new ResponseEntity<Vote>(HttpStatus.NO_CONTENT);
		else {
			return new ResponseEntity<Vote>(rVote, HttpStatus.OK);
		}
	}

	// 3.경기참석 투표 - 3-2 투표
	@PostMapping("voteResult")
	public ResponseEntity<List<VoteResult>> doVote(@RequestBody VoteResult voteResult) throws Exception {

		// 1. voteResult의 voteId와 teamMemeberId를 통해서 투표한 적이 있는지 없는지 먼저 확인한다.
		// rVoteResult값이 null이면 투표한적이 없으므로, 투표 결과를 insert 하면 됩니다.
		
		if (footBallTogetherService_MHK.selectVoteResults(voteResult).isEmpty()) {
			footBallTogetherService_MHK.insertVoteResult(voteResult);
		} // 투표를 했었으면, 투표 결과 수정하기를 합니다.
		else {
			footBallTogetherService_MHK.updateVoteResult(voteResult);
		}

		//2. 해당 voteId에 해당하는 모든 투표 결과를 받아 옵니다. 
		voteResult.setAttendance(0);
		voteResult.setTeamMemberId(0);
		List<VoteResult> voteResults = footBallTogetherService_MHK.selectVoteResults(voteResult);

		if (voteResults == null)
			return new ResponseEntity<List<VoteResult>>(HttpStatus.NO_CONTENT);
		else {
			return new ResponseEntity<List<VoteResult>>(voteResults, HttpStatus.OK);
		}

	}

	// 3. 경기참석 투표 - 3-4 지난투표보기, 현재 진행 중인 투표보기
	// voteStatus가 0이면 투표 다 가져오고, 1이면 현재 진행중인 투표들 가져오고, 2이면 완료된 투표 가져온다.
	@GetMapping("vote")
	public ResponseEntity<List<Vote>> getClosedVote(Vote vote) throws Exception {
		System.out.println(vote);
		List<Vote> rVotes = footBallTogetherService_MHK.selectVotes(vote);

		if (rVotes == null)
			return new ResponseEntity<List<Vote>>(HttpStatus.NO_CONTENT);
		else {
			return new ResponseEntity<List<Vote>>(rVotes, HttpStatus.OK);
		}
	}
	
	

}
