package com.biomans.fbt.team.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;

public interface TeamDAO {
	//T001
	public Team showTeamInfo(int teamId) throws SQLException;
	//T002
	public List<MatchSchedule> showMatchRecordByTeam(int teamId) throws SQLException;
	//T006: 팀 검색
	public List<Team> searchTeams(HashMap<String, String> searchCon) throws SQLException;
	//TNN :
	public Team showTeamInfoForAutoWrite(HashMap<String, String> searchCon) throws SQLException;
}
