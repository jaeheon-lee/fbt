package com.biomans.fbt.teammember.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.TeamMember;

public interface TeamMemberDAO {
	//TM001
	public void addTeamMember(TeamMember teamMember) throws SQLException;
	//TM002-1
	public List<TeamMember> showMembersByTeamId(int teamId) throws SQLException;
	//TM002-2
	public List<TeamMember> showMembersByTeamIdName(HashMap<String, String> searchCon) throws SQLException;
	//TM002-3
	public TeamMember showMemberById(String teamMemberId) throws SQLException;
}
