package com.biomans.fbt.matchschedule.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.util.SearchKey;

@Repository
public class MatchScheduleDAOImpl implements MatchScheduleDAO{
	final String ns = "MatchScheduleMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	//S001: 일정 등록
	@Override
	public void addMatchSchedule(MatchSchedule matchSchedule) throws SQLException {
		sqlSession.insert(ns+"addMatchSchedule", matchSchedule);
		
	}
	
	// S002: 방금 동록한 일정 ID 출력
	@Override
	public int showLatestMatchScheduleIdById(int teamId) throws SQLException {
		return sqlSession.selectOne(ns+"showLatestMatchScheduleIdById", teamId);
		
	}
	
	// S00N: 일정 삭제
	@Override
	public void deleteMatchSchedule(int matchScheduleId) throws SQLException {
		sqlSession.delete(ns+"deleteMatchSchedule", matchScheduleId);
	}
	
	// S005: 등록된 팀 경기 일정 출력 
	@Override
	public List<MatchSchedule> showMatchSchduleByTeamPeriod(SearchKey searchKey) throws SQLException {
		return sqlSession.selectList(ns+"showMatchSchduleByTeamPeriod", searchKey);
	}
	
	// S006
	@Override
	public MatchSchedule showMatchScheduleById(int matchScheduleId) throws SQLException {
		return sqlSession.selectOne(ns+"showMatchScheduleById", matchScheduleId);
	}
	
	
}
