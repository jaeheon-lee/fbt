package com.biomans.fbt.matchschedule.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.util.SearchKey;

@Service
public class MatchScheduleServiceImpl implements MatchScheduleService {
	@Autowired
	private MatchScheduleDAO matchScheduleDAO;
	
	//S001: 일정 등록
	@Override
	public void addMatchSchedule(MatchSchedule matchSchedule) throws SQLException {
		matchScheduleDAO.addMatchSchedule(matchSchedule);
		
	}
	
	// S002: 방금 동록한 일정 ID 출력
	@Override
	public int showLatestMatchScheduleIdById(int teamId) throws SQLException {
		return matchScheduleDAO.showLatestMatchScheduleIdById(teamId);
	}
	
	// S00N: 일정 삭제
	@Override
	public void deleteMatchSchedule(int matchScheduleId) throws SQLException {
		matchScheduleDAO.deleteMatchSchedule(matchScheduleId);
	}

	@Override
	public List<MatchSchedule> showMatchSchduleByTeamPeriod(SearchKey searchKey) throws SQLException {
		return matchScheduleDAO.showMatchSchduleByTeamPeriod(searchKey);
	}

	@Override
	public MatchSchedule showMatchScheduleById(int matchScheduleId) throws SQLException {
		return matchScheduleDAO.showMatchScheduleById(matchScheduleId);
	}

}
