package com.biomans.fbt.team.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.Team;

public interface TeamDAO {
	//T006: 팀 검색
	public List<Team> searchTeams(HashMap<String, String> searchCon) throws SQLException;
	//TNN :
	public Team showTeamInfoForAutoWrite(HashMap<String, String> searchCon) throws SQLException;
}
