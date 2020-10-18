package com.biomans.fbt;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;

@SpringBootTest
class NoticeTest {
	
	final String ns = "NoticeMapper.";
	
	@Autowired
	private SqlSession sqlSession;

	@Test
	void contextLoads() {
		Notice notice = new Notice();
		notice.setContent("경기 투표가 열렸습니다. 참여해주세요");
		notice.setDate("2020-10-15 09:00");
		Team team = new Team();
		team.setTeamId(2);
		notice.setTeam(team);
		List<Integer> tmids = sqlSession.selectList("TeamMemberMapper.showAllMembers", 2);
		System.out.println(tmids);
		notice.setTeamMembers(tmids);
		
		
		
		sqlSession.insert(ns+"addNotice", notice);
	}

}
