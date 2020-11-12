package com.biomans.fbt.matchschedule.dao;

import java.sql.SQLException;

import com.biomans.fbt.domain.MatchSchedule;

public interface MatchScheduleDAO {
	// S001: 일정 등록
	public void addMatchSchedule(MatchSchedule matchSchedule) throws SQLException;
	// S002: 방금 동록한 일정 ID 출력
	public int showLatestMatchScheduleIdById(int teamId) throws SQLException;
}
