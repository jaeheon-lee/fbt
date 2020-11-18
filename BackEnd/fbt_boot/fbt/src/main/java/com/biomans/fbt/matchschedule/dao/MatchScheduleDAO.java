package com.biomans.fbt.matchschedule.dao;

import java.sql.SQLException;
import java.util.List;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.util.SearchKey;

public interface MatchScheduleDAO {
	// S001: 일정 등록
	public void addMatchSchedule(MatchSchedule matchSchedule) throws SQLException;
	// S002: 방금 동록한 일정 ID 출력
	public int showLatestMatchScheduleIdById(int teamId) throws SQLException;
	// S005: 등록된 팀 경기 일정 출력 
	public List<MatchSchedule> showMatchSchduleByTeamPeriod(SearchKey searchKey) throws SQLException;
}
