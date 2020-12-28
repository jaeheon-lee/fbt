package com.biomans.fbt.teammember.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.User;

public interface TeamMemberService {
	//FTM01
	public void addTeamMember(TeamMember teamMember) throws SQLException;
	//FTM02
	List<TeamMember> showMembersByTeamId(int teamId) throws SQLException;
	//FTM03
	public int changeMemberLever(List<TeamMember> teamMembers) throws SQLException;
	//FTM04, FTM07, FTM08
	public void deleteMember(String teamMemberId) throws SQLException;
	//FTM05
	public List<TeamMember> showApplyMemberByTeam(int teamId) throws SQLException;
	//FTM06
	public void acceptApplyMember(String teamMemberId) throws SQLException;
	//FTM07
	public String changeImg(User user, MultipartFile file, String path) throws SQLException;
	
	//TM002-2
	List<TeamMember> showMembersByTeamIdName(HashMap<String, String> searchCon) throws SQLException;
}
