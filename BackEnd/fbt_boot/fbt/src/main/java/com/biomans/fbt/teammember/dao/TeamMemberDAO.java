package com.biomans.fbt.teammember.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.User;

public interface TeamMemberDAO {
	//TM001
	public void addTeamMember(TeamMember teamMember) throws SQLException;
	//TM002-1
	public List<TeamMember> showMembersByTeamId(int teamId) throws SQLException;
	//TM002-2
	public List<TeamMember> showMembersByTeamIdName(HashMap<String, String> searchCon) throws SQLException;
	//TM002-3
	public TeamMember showMemberById(String teamMemberId) throws SQLException;
	//TM003
	public void changeMemberLever(TeamMember teamMember) throws SQLException;
	//TM004
	public void deleteMember(String teamMemberId) throws SQLException;
	//TM005
	public List<TeamMember> showApplyMemberByTeam(int teamId) throws SQLException;
	//TM006
	public void acceptApplyMember(String teamMemberId) throws SQLException;
	//TM007
	public void changeImg(User user) throws SQLException;
}
