package com.biomans.fbt.teammember.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.teammember.dao.TeamMemberDAO;
import com.biomans.fbt.teammember.service.TeamMemberService;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {
	@Autowired
	private TeamMemberDAO teamMemberDAO;

	//FTM01
	@Override
	public void addTeamMember(TeamMember teamMember) throws SQLException {
		teamMemberDAO.addTeamMember(teamMember);
	}
	
	@Override
	public List<TeamMember> showMembersByTeamId(int teamId) throws SQLException {
		return teamMemberDAO.showMembersByTeamId(teamId);
	}

	@Override
	public List<TeamMember> showMembersByTeamIdName(HashMap<String, String> searchCon) throws SQLException {
		return teamMemberDAO.showMembersByTeamIdName(searchCon);
	} 
}
