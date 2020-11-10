package com.biomans.fbt.team.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomans.fbt.domain.Team;
import com.biomans.fbt.team.dao.TeamDAO;
import com.biomans.fbt.team.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService{
	@Autowired
	private TeamDAO teamDAO;

	@Override
	public List<Team> searchTeams(HashMap<String, String> searchCon) throws SQLException {
		return teamDAO.searchTeams(searchCon);
	}
	
	
}
