package com.biomans.fbt.teammember.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.TeamMember;

public interface TeamMemberDAO {
	
	//TM002-1
	List<TeamMember> showMembersByTeamId(int teamId) throws SQLException;
	//TM002-2
	List<TeamMember> showMembersByTeamIdName(HashMap<String, String> searchCon) throws SQLException;
	//TM002-3
	TeamMember showMemberById(String teamMemberId) throws SQLException;
}
