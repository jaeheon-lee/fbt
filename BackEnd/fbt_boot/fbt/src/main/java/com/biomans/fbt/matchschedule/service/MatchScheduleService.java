package com.biomans.fbt.matchschedule.service;

import java.sql.SQLException;
import java.util.List;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.util.SearchKey;

public interface MatchScheduleService {
	//S001: 일정 등록
	public void addMatchSchedule(MatchSchedule matchSchedule) throws SQLException;
	// S002: 방금 동록한 일정 ID 출력
	public int showLatestMatchScheduleIdById(int teamId) throws SQLException;
	// S00N: 일정 삭제 
	public void deleteMatchSchedule(int matchScheduleId) throws SQLException;
	// S005: 등록된 팀 경기 일정 출력 
	public List<MatchSchedule> showMatchSchduleByTeamPeriod(SearchKey searchKey) throws SQLException;
	// S006
	public MatchSchedule showMatchScheduleById(int matchScheduleId) throws SQLException;
}
