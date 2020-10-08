package com.honeycom.fbt.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.honeycom.fbt.dto.Employment;
import com.honeycom.fbt.dto.TeamMember;
import com.honeycom.fbt.dto.User;
import com.honeycom.fbt.dto.Vote;
import com.honeycom.fbt.dto.VoteResult;
import com.honeycom.fbt.service.FootBallTogetherService_LJH;

@RestController
//@RequestMapping("fbt")
//@Controller
public class FootBallTogetherController {

	@Autowired
	private FootBallTogetherService_LJH footBallTogetherService_LJH;

	@GetMapping("user")
	public ResponseEntity<User> login(User user, HttpSession session) throws Exception {

		// user
		User rUser = footBallTogetherService_LJH.selectUser(user);

		// teamMember
		TeamMember teamMember = new TeamMember();
		teamMember.setEmail(user.getEmail());
		System.out.println(teamMember);
		List<TeamMember> teamMembers = footBallTogetherService_LJH.selectTeamMembers(teamMember);
		rUser.setTeamMembers(teamMembers);

		// employment
		Employment employment = new Employment();
		employment.setEmail(user.getEmail());
		List<Employment> employments = footBallTogetherService_LJH.selectEmployments(employment);
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
	public ResponseEntity<Vote> createVote(@RequestBody Vote vote) {
		System.out.println(vote);
		footBallTogetherService_LJH.insertVote(vote);
		Vote vote2 = new Vote();
		vote2.setVoteId(vote.getVoteId());
		Vote rVote = footBallTogetherService_LJH.selectVotes(vote2).get(0);

		if (rVote.getVoteId() == 0)
			return new ResponseEntity<Vote>(HttpStatus.NO_CONTENT);
		else {
			return new ResponseEntity<Vote>(rVote, HttpStatus.OK);
		}
	}

	// 3.경기참석 투표 - 3-2 투표
	@PostMapping("voteResult")
	public ResponseEntity<List<VoteResult>> doVote(@RequestBody VoteResult voteResult) {

		// 1. voteResult의 voteId와 teamMemeberId를 통해서 투표한 적이 있는지 없는지 먼저 확인한다.
		// rVoteResult값이 null이면 투표한적이 없으므로, 투표 결과를 insert 하면 됩니다.
		
		if (footBallTogetherService_LJH.selectVoteResults(voteResult).isEmpty()) {
			footBallTogetherService_LJH.insertVoteResult(voteResult);
		} // 투표를 했었으면, 투표 결과 수정하기를 합니다.
		else {
			footBallTogetherService_LJH.updateVoteResult(voteResult);
		}

		//2. 해당 voteId에 해당하는 모든 투표 결과를 받아 옵니다. 
		voteResult.setAttendance(0);
		voteResult.setTeamMemberId(0);
		List<VoteResult> voteResults = footBallTogetherService_LJH.selectVoteResults(voteResult);

		if (voteResults == null)
			return new ResponseEntity<List<VoteResult>>(HttpStatus.NO_CONTENT);
		else {
			return new ResponseEntity<List<VoteResult>>(voteResults, HttpStatus.OK);
		}

	}

	// 3. 경기참석 투표 - 3-4 지난투표보기, 현재 진행 중인 투표보기
	// voteStatus가 0이면 투표 다 가져오고, 1이면 현재 진행중인 투표들 가져오고, 2이면 완료된 투표 가져온다.
	@GetMapping("vote")
	public ResponseEntity<List<Vote>> getClosedVote(Vote vote) {
		System.out.println(vote);
		List<Vote> rVotes = footBallTogetherService_LJH.selectVotes(vote);

		if (rVotes == null)
			return new ResponseEntity<List<Vote>>(HttpStatus.NO_CONTENT);
		else {
			return new ResponseEntity<List<Vote>>(rVotes, HttpStatus.OK);
		}
	}

}
