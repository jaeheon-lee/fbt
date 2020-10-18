package com.biomans.fbt;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
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
		vms.setType(0);
		vms.setCancelNumber(11);
		vms.setIsFirst(1);
		vms.setWaiting(1);
		vms.setFriendEmp(1);
		vm.setVoteMatchSetting(vms);

		
//		sqlSession.insert(ns+"addVoteMatch", vm);
//		sqlSession.insert(ns+"addVoteMatchSetting", vm);
		
//		System.out.println(sqlSession.selectList(ns+"showVoteMatchInfoByTeam", 2));
//		
//		System.out.println(sqlSession.selectList(ns+"showVoteMatchStatusByTeam", 2));
		List<VoteMatch> list = sqlSession.selectList(ns+"showVoteMatchInfoByTeam", 1);
		int votedNum = 0;
		int attendNum = 0;
		int abscentNum = 0;
		int friendNum = 0;
		
		for(VoteMatch voteMatch : list) {
			ArrayList<VoteMatchResult> vmrlist = voteMatch.getVoteMatchResults();
			for(VoteMatchResult vmr : vmrlist) {
				if(vmr.getUser() != null) friendNum++;
				if(vmr.getAttendance()==1) attendNum++;
				if(vmr.getAttendance()==0) abscentNum++;
			}
			voteMatch.setVotedNum(vmrlist.size());
//			voteMatch.set
		}
		System.out.println("votedNum : "+ list.get(0).getVotedNum());
		System.out.println("attendNum : "+attendNum);
		System.out.println("abscentNum : "+attendNum);
		System.out.println("friendNum : "+friendNum);
//		List<VoteMatch> list2 = sqlSession.selectList(ns+"showVoteMatchStatusByTeam", 1);
		for(VoteMatch vm2 : list) System.out.println(vm2);
//		for(VoteMatch vmr : list2) System.out.println(vmr);
	}

}
