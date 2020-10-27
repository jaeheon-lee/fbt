package com.biomans.fbt;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biomans.fbt.domain.Invite;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.domain.VoteMatchSetting;

@SpringBootTest
class VoteMatchTest {
	
	final String ns = "VoteMatchMapper.";
	
	@Autowired
	private SqlSession sqlSession;

	@Test
	void contextLoads() {
		// V005 & V006
		VoteMatch vm = new VoteMatch();
		String voteMatchId = "1"+"-"+"1";
		vm.setVoteMatchId(voteMatchId);
		vm.setContents("늦지 마시오");
		vm.setDueDate("2020-10-14 21:00");
		vm.setVoteStatus(0);
		vm.setWriter("왕십리FC 구단주");
		Team team = new Team();
		team.setTeamId(1);
		vm.setTeam(team);
		MatchSchedule ms = new MatchSchedule();
		ms.setMatchScheduleId(1);
		vm.setMatchSchedule(ms);
		VoteMatchSetting vms = new VoteMatchSetting();
		vms.setVoteMatchId("1-2");
		vms.setType(0);
		vms.setCancelNumber(11);
		vms.setIsFirst(1);
		vms.setWaiting(1);
		vms.setFriendEmp(1);
		vms.setSelfMinNumber(-1);
		vms.setSelfMaxNumber(-1);
		vms.setEmpDueDate("-1");
		vms.setEmpMinNumber(-1);
		vms.setAwayMinNumber(-1);
		vms.setAwayDueDate("-1");
		vms.setEmpCost(-1);
		vms.setAssignCost(-1);
		vms.setSearchCost(-1);
		vm.setVoteMatchSetting(vms);
		

//		sqlSession.insert(ns+"addVoteMatch", vm);
//		sqlSession.insert(ns+"addVoteMatchSetting", vms);
		
		//---------------------------------------//
		
		//V001
//		System.out.println(sqlSession.selectList(ns+"showVoteMatchInfoByTeam", 2));
//		
//		System.out.println(sqlSession.selectList(ns+"showVoteMatchStatusByTeam", 2));
		List<VoteMatch> voteMatchList = sqlSession.selectList(ns+"showVoteMatchInfoByTeam", 1);
		List<VoteMatch> numList = sqlSession.selectList(ns+"showVoteMatchNumByVote", 1);
		for(VoteMatch vm00 : voteMatchList) {
			for(VoteMatch vm01 : numList) {
				if(vm00.getVoteMatchId().equals(vm01.getVoteMatchId())) {
					vm00.setTotalNum(vm01.getTotalNum());
					vm00.setAttendNum(vm01.getAttendNum());
					vm00.setAbscentNum(vm01.getAbscentNum());
				}
			}
		}
		for(VoteMatch vm00 : voteMatchList) {
			System.out.println(vm00);
		}
		
		
		
		
		
//		for(VoteMatch voteMatch : list) {
//			int votedNum = 0;
//			int attendNum = 0;
//			int friendNum = 0;
//			ArrayList<VoteMatchResult> vmrlist = voteMatch.getVoteMatchResults();
//			for(VoteMatchResult vmr : vmrlist) {
//				if(vmr.getUser() != null) friendNum++;
//				if(vmr.getUser() == null) {
//					votedNum++;
//					if(vmr.getAttendance()==1) attendNum++;
//				}
//			}
//			voteMatch.setVotedNum(votedNum);
//			voteMatch.setFriendNum(friendNum);
//			System.out.println("frn : "+friendNum);
//			voteMatch.setAttendNum(attendNum);
//			voteMatch.setAbscentNum(votedNum-attendNum);
//			System.out.println(voteMatch.getVoteMatchResults());
//		}
//		System.out.println("votedNum : "+ list.get(0).getVotedNum());
//		System.out.println("attendNum : "+ list.get(0).getAttendNum());
//		System.out.println("abscentNum : "+ list.get(0).getAbscentNum());
//		System.out.println("friendNum : "+ list.get(0).getFriendNum());
//		List<VoteMatch> list2 = sqlSession.selectList(ns+"showVoteMatchStatusByTeam", 1);
		
		//V003 : 투표 입력
		
		//case1 : 팀원  투표
//		VoteMatch vm5 = new VoteMatch();
//		vm5.setVoteMatchId("1-1");
//		VoteMatchResult vmr2 = new VoteMatchResult();
//		TeamMember tm2 = new TeamMember();
//		tm2.setTeamMemberId(3);
//		vmr2.setTeamMember(tm2);
//		vmr2.setAttendance(1);
//		vm5.setVoteMatchResult(vmr2);
//		sqlSession.insert(ns+"addAttendance", vm5);
		
		//case2 : 지인 투표
//		VoteMatch vm6 = new VoteMatch();
//		vm6.setVoteMatchId("1-1");
//		VoteMatchResult vmr2 = new VoteMatchResult();
//		User u1 = new User();
//		u1.setEmail("bioman16@gmail.com");
//		vmr2.setUser(u1);
//		vmr2.setAttendance(1);
//		vm6.setVoteMatchResult(vmr2);
//		sqlSession.insert(ns+"addAttendance", vm6);
		
		//V004 : 지인 초청하기
//		Invite invite = new Invite();
//		TeamMember tm10 = new TeamMember();
//		tm10.setTeamMemberId(3);
//		User user10 = new User();
//		user10.setEmail("bioman16@gmail.com");
//		VoteMatch vm10 = new VoteMatch();
//		vm10.setVoteMatchId("1-1");
//		invite.setTeamMember(tm10);
//		invite.setUser(user10);
//		invite.setVoteMatch(vm10);
		
//		sqlSession.insert(ns+"inviteFriend", invite);
		
//		//V007 : 투표 마감하기
//		VoteMatch vm7 = new VoteMatch();
//		vm7.setVoteMatchId("1-1");
//		sqlSession.update(ns+"endVoteMatch", vm7);
		
		//V002 : 투표 수정
//		VoteMatch vm2 = new VoteMatch();
//		vm2.setVoteMatchId("1-1");
//		VoteMatchResult vmr2 = new VoteMatchResult();
//		vmr2.setAttendance(0);
//		TeamMember tm2 = new TeamMember();
//		tm2.setTeamMemberId(1);
////		vmr2.setTeamMember(tm2);
//		User u2 = new User();
//		u2.setEmail("bioman16@gmail.com");
//		vm2.setVoteMatchResult(vmr2);
//		sqlSession.update(ns+"updateVoteMatchResult", vm2);
		
		//V008 : 투표 내용 수정
//		VoteMatch vm8 = new VoteMatch();
//		vm8.setContents("절대 늦지 마시오");
//		vm8.setDueDate("2020-10-14 23:00");
//		vm8.setVoteMatchId("1-1");
//		sqlSession.update(ns+"updateVoteMatch", vm8);
		
		//V009 : 투표 삭제
//		sqlSession.delete(ns+"deleteVoteMatch", "1-1");
		
		//V010 : 투표 설정 수정
//		VoteMatchSetting vms10 = new VoteMatchSetting();
//		vms10.setType(0);
//		vms10.setCancelNumber(12);
//		vms10.setVoteMatchId("1-1");
//		vms10.setIsFirst(1);
//		vms10.setFriendEmp(1);
//		vms10.setAssignCost(10);
//		sqlSession.update(ns+"updateVoteMatchSetting",vms10);
		
		//V011 : 투표 결과 삭제
//		VoteMatchResult vmr11 = new VoteMatchResult();
//		vmr11.setVoteMatchId("1-1");
//		User u11 = new User();
//		u11.setEmail("bioman16@gmail.com");
//		vmr11.setUser(u11);
//		sqlSession.delete(ns+"deleteVoteMatchResult", vmr11);
		
	}
}
