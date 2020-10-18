package com.biomans.fbt;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biomans.fbt.domain.Team;

@SpringBootTest
class TeamTest {
	
	final String ns = "TeamMapper.";
	
	@Autowired
	private SqlSession sqlSession;

	@Test
	void teamTest() {
		Team team = new Team();
		team.setTeamName("답십리FC");
		team.setTeamRegDate("2020-02-01");
		team.setEmblem("답십리FC2010141624.jpg");
		team.setArea("서울특별시 성동구");
		team.setStadiumInfo("없음");
		team.setUniformColor("파랑");
		team.setUniformType(1);
		team.setFoundingDate("2019-06-01");
//		team.setAccount("281-071164-01-013");
//		team.setBank("기업");
		
		sqlSession.insert(ns+"addTeam", team);
		
	}

}
