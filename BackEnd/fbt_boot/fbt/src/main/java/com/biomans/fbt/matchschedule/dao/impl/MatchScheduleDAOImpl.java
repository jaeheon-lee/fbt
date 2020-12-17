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
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.TeamScore;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.util.AvgScore;
import com.biomans.fbt.util.ScoreDescInput;
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
	//S004
	@Override
	public void addAwayTeam(HashMap<String, Integer> con) throws SQLException {
		sqlSession.update(ns+"addAwayTeam", con);
		
	}
	
	// S005-1: 월별 팀 경기 일정 출력
	@Override
	public List<MatchSchedule> showMatchSchduleByTeamPeriod(SearchKey searchKey) throws SQLException {
		return sqlSession.selectList(ns+"showMatchSchduleByTeamPeriod", searchKey);
	}
	
	// S005-2: 월별 개인 경기 일정 출력
	@Override
	public List<MatchSchedule> showMatchSchduleByUserPeriod(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showMatchSchduleByUserPeriod", searchCon);
	}
	@Override
	public List<MatchSchedule> showMatchScheduleByEmployPeriod(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showMatchScheduleByEmployPeriod", searchCon);
	}
	
	//S006 일정 수정
	@Override
	public void updateMatchSchedule(MatchSchedule matchSchedule) throws SQLException {
		sqlSession.update(ns+"updateMatchSchedule", matchSchedule);
	}
	
	// S007
	@Override
	public List<TeamMember> showAttendVotedMember(HashMap<String, Integer> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showAttendVotedMember", searchCon);
	}
	
	// S008
	@Override
	public List<User> showAttendVotedFriend(HashMap<String, Integer> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showAttendVotedFriend", searchCon);
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
	@Override
	public MatchSchedule showMatchScheduleResult(HashMap<String, Integer> searchCon) throws SQLException {
		return sqlSession.selectOne(ns+"showMatchScheduleResult", searchCon);
	}
	
	// S011
	@Override
	public void changeHomeTeam(HashMap<String, Integer> con) throws SQLException {
		sqlSession.update(ns+"changeHomeTeam", con);
	}

	// S012: 일정 삭제
	@Override
	public void deleteMatchSchedule(int matchScheduleId) throws SQLException {
		sqlSession.delete(ns+"deleteMatchSchedule", matchScheduleId);
	}
	
	// S013
	@Override
	public void confirmMatchSchedule(HashMap<String, Integer> searchCon) throws SQLException {
		sqlSession.update(ns+"confirmMatchSchedule", searchCon);
	}
	
	// S014
	@Override
	public Team getTeamScoreDesc(int teamId) throws SQLException {
		return sqlSession.selectOne(ns+"getTeamScoreDesc", teamId);
	}
	
	// S015
	@Override
	public void updateTeamScoreDesc(ScoreDescInput scoreDescInput) throws SQLException {
		sqlSession.update(ns+"updateTeamScoreDesc", scoreDescInput);
	}
	
	// S016
	@Override
	public User getEmpScoreDesc(String email) throws SQLException {
		return sqlSession.selectOne(ns+"getEmpScoreDesc", email);
	}
	
	// S017
	@Override
	public void updateEmpScoreDesc(ScoreDescInput scoreDescInput) throws SQLException {
		sqlSession.update(ns+"updateEmpScoreDesc", scoreDescInput);
	}
	
	// S018
	@Override
	public EmpScore getEmpScoreByScheduleIdEmail(HashMap<String, String> con) throws SQLException {
		return sqlSession.selectOne(ns+"getEmpScoreByScheduleIdEmail", con);
	}
	
	// S019
	@Override
	public void updateEntry(Entry entry) throws SQLException {
		sqlSession.update(ns+"updateEntry", entry);
	}
	@Override
	public void updateMatchResult(MatchResult matchResult) throws SQLException {
		sqlSession.update(ns+"updateMatchResult", matchResult);
	}
	@Override
	public void updateEmpScore(EmpScore empScore) throws SQLException {
		sqlSession.update(ns+"updateEmpScore", empScore);
	}
	@Override
	public void updateTeamScore(TeamScore teamScore) throws SQLException{
		sqlSession.update(ns+"updateTeamScore", teamScore);
	}
	
	// S020
	@Override
	public AvgScore getAvgTeamScore(int takerTeamId) throws SQLException {
		return sqlSession.selectOne(ns+"getAvgTeamScore", takerTeamId);
	}
	// S021
	@Override
	public AvgScore getAvgEmpScore(String email) throws SQLException {
		return sqlSession.selectOne(ns+"getAvgEmpScore", email);
	}
}
