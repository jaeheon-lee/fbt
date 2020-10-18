package com.biomans.fbt;

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
		VoteMatchSetting vm = new VoteMatchSetting();
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
		vm.setType(0);
		vm.setCancelNumber(11);
		vm.setIsFirst(1);
		vm.setWaiting(1);
		vm.setFriendEmp(1);
		
//		sqlSession.insert(ns+"addVoteMatch", vm);
//		sqlSession.insert(ns+"addVoteMatchSetting", vm);
		
//		System.out.println(sqlSession.selectList(ns+"showVoteMatchInfoByTeam", 2));
//		
//		System.out.println(sqlSession.selectList(ns+"showVoteMatchStatusByTeam", 2));
		List<VoteMatch> list = sqlSession.selectList(ns+"showVoteMatchInfoByTeam", 1);
//		List<VoteMatch> list2 = sqlSession.selectList(ns+"showVoteMatchStatusByTeam", 1);
		for(VoteMatch vms : list) System.out.println(vms);
//		for(VoteMatch vmr : list2) System.out.println(vmr);
	}

}
