package com.biomans.fbt;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.Employ;
import com.biomans.fbt.domain.Entry;
import com.biomans.fbt.domain.MatchResult;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.util.Attendance;
import com.biomans.fbt.util.Filter;

@SpringBootTest
class CommonTest {
	
	final String ns = "MatchScheduleMapper.";
	
	@Autowired
	private SqlSession sqlSession;

	@Test
	void contextLoads() throws ParseException {
		HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
		searchCon.put("matchScheduleId", 6);
		searchCon.put("teamId", 1);
//		MatchSchedule m = sqlSession.selectOne(ns+"showMatchScheduleById", 3);
//		System.out.println(m.getAwayTeam());
		MatchSchedule m = sqlSession.selectOne(ns+"showMatchScheduleResult", searchCon);
		System.out.println(m.getEntries());
		System.out.println(m.getMatchResult());
		System.out.println(m.getTeamScores());
	}

}
