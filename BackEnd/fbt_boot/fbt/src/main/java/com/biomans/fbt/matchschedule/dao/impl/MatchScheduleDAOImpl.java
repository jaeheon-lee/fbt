package com.biomans.fbt.matchschedule.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.Entry;
import com.biomans.fbt.domain.MatchResult;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.TeamScore;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.util.SearchKey;

@Repository
public class MatchScheduleDAOImpl implements MatchScheduleDAO{
	final String ns = "MatchScheduleMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	// S001
	@Override
	public MatchSchedule showMatchScheduleByMatchScheduleId(int matchScheduleId) throws SQLException {
		return sqlSession.selectOne(ns+"showMatchScheduleById", matchScheduleId);
	}
	// S002
	@Override
	public int showLatestMatchScheduleIdById(int teamId) throws SQLException {
		return sqlSession.selectOne(ns+"showLatestMatchScheduleIdById", teamId);
		
	}
	//S003
	@Override
	public void addMatchSchedule(MatchSchedule matchSchedule) throws SQLException {
		sqlSession.insert(ns+"addMatchSchedule", matchSchedule);
		
	}
	
	
	
	

	
	//일정 수정
	@Override
	public void updateMatchSchedule(MatchSchedule matchSchedule) throws SQLException {
		sqlSession.update(ns+"updateMatchSchedule", matchSchedule);
	}
	
	// S00N: 일정 삭제
	@Override
	public void deleteMatchSchedule(int matchScheduleId) throws SQLException {
		sqlSession.delete(ns+"deleteMatchSchedule", matchScheduleId);
	}
	
	// S005-1: 등록된 팀 경기 일정 출력 
	@Override
	public List<MatchSchedule> showMatchSchduleByTeamPeriod(SearchKey searchKey) throws SQLException {
		return sqlSession.selectList(ns+"showMatchSchduleByTeamPeriod", searchKey);
	}
	
	// S005-2: 등록된 개인 경기 일정 출력
	@Override
	public List<MatchSchedule> showMatchSchduleByUserPeriod(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showMatchSchduleByUserPeriod", searchCon);
	}
	@Override
	public List<MatchSchedule> showMatchScheduleByEmployPeriod(String email) throws SQLException {
		return sqlSession.selectList(ns+"showMatchScheduleByEmployPeriod", email);
	}
	
	
	
	// S007
	@Override
	public List<TeamMember> showAttendVotedMember(String voteMatchId) throws SQLException {
		return sqlSession.selectList(ns+"showAttendVotedMember", voteMatchId);
	}
	
	// S008
	@Override
	public List<User> showAttendVotedFriend(String voteMatchId) throws SQLException {
		return sqlSession.selectList(ns+"showAttendVotedFriend", voteMatchId);
	}
	@Override
	public List<User> showAcceptedEmploy(int matchScheduleId) throws SQLException {
		return sqlSession.selectList(ns+"showAcceptedEmploy", matchScheduleId);
	}
	
	// S009
	@Override
	public void addEntry(Entry entry) throws SQLException {
		sqlSession.insert(ns+"addEntry", entry);
	}
	@Override
	public void addMatchResult(MatchResult matchResult) throws SQLException {
		sqlSession.insert(ns+"addMatchResult", matchResult);
	}
	@Override
	public void addEmpScore(EmpScore empScore) throws SQLException {
		sqlSession.insert(ns+"addEmpScore", empScore);
	}
	@Override
	public void addTeamScore(TeamScore teamScore) throws SQLException {
		sqlSession.insert(ns+"addTeamScore", teamScore);
	}
	@Override
	public int checkAwayTeam(int matchScheduleId) throws SQLException {
		return sqlSession.selectOne(ns+"checkAwayTeam", matchScheduleId);
	}
	
	//S010
	// S010
	public MatchSchedule showMatchScheduleResult(int matchScheduleId) throws SQLException {
		return sqlSession.selectOne(ns+"showMatchScheduleResult", matchScheduleId);
	}
	
	@Override
	// A006
	public void changeHomeTeam(HashMap<String, Integer> con) throws SQLException {
		sqlSession.update(ns+"changeHomeTeam", con);
	}
	
	
	
	
}
