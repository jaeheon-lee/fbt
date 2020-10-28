package com.biomans.fbt;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;

@SpringBootTest
class MatchScheduleTest {
	final String ns = "MatchScheduleMapper.";
	
	@Autowired
	private SqlSession sqlsession;
	
	@Test
	void contextLoads() {
		MatchSchedule matchSchedule = new MatchSchedule();
		Team team = new Team();
		team.setTeamId(1);
		matchSchedule.setHomeTeam(team);
		matchSchedule.setStartTime("2020-10-15 09:00");
		matchSchedule.setDuration(2);
		matchSchedule.setCost(10);
		matchSchedule.setWriter("왕십리FC 구단주");
		matchSchedule.setContent("늦지 마시오");
		matchSchedule.setStadiumAddress("서울특별시 서초구 방배동 599 방배 유수지");
		matchSchedule.setStadiumName("방배 유수지 축구장1");
		matchSchedule.setStadiumParking(1);
		matchSchedule.setStadiumShower(0);
		matchSchedule.setMatchType(11);
		matchSchedule.setStadiumType("흙");
		
		sqlsession.insert(ns+"addMatchSchedule", matchSchedule);
		
	}

}
