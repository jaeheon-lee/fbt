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

@SpringBootTest
class VoteMatchTest {
	
	final String ns = "VoteMatchMapper.";
	
	@Autowired
	private SqlSession sqlSession;

	@Test
	void contextLoads() {
		VoteMatch vm = new VoteMatch();
		vm.setContents("늦지 마시오");
		vm.setDueDate("2020-09-14 21:00");
		vm.setVoteStatus(1);
		vm.setWriter("왕십리FC 구단주");
		Team team = new Team();
		team.setTeamId(2);
		vm.setTeam(team);
		MatchSchedule ms = new MatchSchedule();
		ms.setMatchScheduleId(2);
		vm.setMatchSchedule(ms);
		
//		sqlSession.insert(ns+"addVoteMatch", vm);
		
		System.out.println(sqlSession.selectList(ns+"showVoteMatchInfoByTeam", 2));
		
		System.out.println(sqlSession.selectList(ns+"showVoteMatchStatusByTeam", 2));
	}

}
