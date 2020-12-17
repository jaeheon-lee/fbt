package com.biomans.fbt.team.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.util.TeamFilter;

public interface TeamService {
	//FT01
	public Team showTeamInfo(HashMap<String, String> con) throws SQLException;
	//FT02
	public void addTeam(Team team, MultipartFile file, String path, TeamMember teamMember) throws SQLException;
	//FT03
	public String checkDupleTeamName(String teamName) throws SQLException;
	//T006: 팀 검색
	public List<Team> searchTeams(HashMap<String, String> searchCon) throws SQLException;
	//T006-1: 팀 필터 검색
	public List<Team> searchTeamsByFilter(TeamFilter teamFilter) throws SQLException;
	//TNN :
	public Team showTeamInfoForAutoWrite(HashMap<String, String> searchCon) throws SQLException;
}
