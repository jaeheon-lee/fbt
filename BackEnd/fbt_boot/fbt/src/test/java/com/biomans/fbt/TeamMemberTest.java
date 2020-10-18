package com.biomans.fbt;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.User;

@SpringBootTest
class TeamMemberTest {
	
	final String ns = "TeamMemberMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	void contextLoads() {
		TeamMember tm = new TeamMember();
		Team team = new Team();
		team.setTeamId(2);
		User user = new User();
		user.setEmail("biomans3@gmail.com");
		tm.setMemberLevel(2);
		tm.setRegStatus(1);
		tm.setNickName("왕십리FC 매니저");
		tm.setTeam(team);
		tm.setUser(user);
		
//		sqlSession.insert(ns+"addTeamMember", tm);
		List<Integer> list = sqlSession.selectList(ns+"showAllMembers", 2);
		System.out.println(list);
		
	}

}
