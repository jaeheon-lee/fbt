package com.biomans.fbt;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;

@SpringBootTest
class CommonTest {
	
	final String ns = "SearchMapper.";
	
	@Autowired
	private SqlSession sqlSession;

	@Test
	void contextLoads() {
		List<Search> list = sqlSession.selectList(ns+"showSearchByTeam", 1);
		System.out.println(list.size());
	}

}
