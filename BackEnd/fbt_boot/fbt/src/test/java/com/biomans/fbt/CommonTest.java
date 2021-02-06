package com.biomans.fbt;

import java.sql.Date;
import java.sql.SQLException;
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
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.util.Attendance;
import com.biomans.fbt.util.Filter;

@SpringBootTest
class CommonTest {
	
	final String ns = "VoteMatchMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private MatchScheduleService matchScheduleService;

	@Test
	void contextLoads() throws ParseException {
		VoteMatch v = new VoteMatch();
		v.setMatchScheduleId(1);
		v.setTeamId(1);
		List<VoteMatch> vlist = sqlSession.selectList(ns+"showVoteMatchInfoByTeam", v);
		for(VoteMatch voteMatch : vlist) {
			System.out.println(voteMatch);
		}
	}

}
