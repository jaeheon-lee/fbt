package com.biomans.fbt.teammember.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.TeamMember;

public interface TeamMemberService {
	//FTM01
	public void addTeamMember(TeamMember teamMember) throws SQLException;
	//TM002-1
	List<TeamMember> showMembersByTeamId(int teamId) throws SQLException;
	//TM002-2
	List<TeamMember> showMembersByTeamIdName(HashMap<String, String> searchCon) throws SQLException;
}
