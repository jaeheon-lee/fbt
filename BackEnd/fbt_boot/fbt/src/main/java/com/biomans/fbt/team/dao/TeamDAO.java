package com.biomans.fbt.team.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.util.TeamFilter;

public interface TeamDAO {
	//T001
	public Team showTeamInfo(HashMap<String, String> con) throws SQLException;
	//T002
	public List<MatchSchedule> showMatchRecordByTeam(int teamId) throws SQLException;
	//T003
	public Team showTeamBasicInfo(int teamId) throws SQLException;
	//T004
	public void addTeam(Team team) throws SQLException;
	//T005
	public String checkDupleTeamName(String teamName) throws SQLException;
	//T006: 팀 검색
	public List<Team> searchTeams(HashMap<String, String> searchCon) throws SQLException;
	//T006-1: 팀 필터 검색
	public List<Team> searchTeamsByFilter(TeamFilter teamFilter) throws SQLException;
	//T007 :
	public Team showTeamInfoForAutoWrite(HashMap<String, String> searchCon) throws SQLException;
	//T008
	public int getTeamIdByTeamName(String teamName) throws SQLException;
}
