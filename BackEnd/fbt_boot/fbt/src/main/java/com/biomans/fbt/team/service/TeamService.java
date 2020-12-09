package com.biomans.fbt.team.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.Team;

public interface TeamService {
	//T001
	public Team showTeamInfo(int teamId) throws SQLException;
	//T006: 팀 검색
	public List<Team> searchTeams(HashMap<String, String> searchCon) throws SQLException;
	//TNN :
	public Team showTeamInfoForAutoWrite(HashMap<String, String> searchCon) throws SQLException;
}
