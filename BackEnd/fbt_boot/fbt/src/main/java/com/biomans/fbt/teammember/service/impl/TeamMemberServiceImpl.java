package com.biomans.fbt.teammember.service.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.teammember.dao.TeamMemberDAO;
import com.biomans.fbt.teammember.service.TeamMemberService;
import com.biomans.fbt.user.dao.UserDAO;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {
	@Autowired
	private TeamMemberDAO teamMemberDAO;
	
	@Autowired
	private UserDAO userDAO;

	//FTM01
	@Override
	public void addTeamMember(TeamMember teamMember) throws SQLException {
		teamMemberDAO.addTeamMember(teamMember);
	}
	
	//FTM02
	@Override
	public List<TeamMember> showMembersByTeamId(int teamId) throws SQLException {
		return teamMemberDAO.showMembersByTeamId(teamId);
	}

	@Override
	public List<TeamMember> showMembersByTeamIdName(HashMap<String, String> searchCon) throws SQLException {
		return teamMemberDAO.showMembersByTeamIdName(searchCon);
	} 
	
	//FTM03
	@Override
	public int changeMemberLever(List<TeamMember> teamMembers) throws SQLException {
		// 리스트를 변환자와 피변환자로 나눈다
		TeamMember changer = teamMembers.get(0);
		TeamMember changed = teamMembers.get(1);
		// 일단 피변환자의 등급을 바꾼다
		teamMemberDAO.changeMemberLever(changed);
		// 변환자가 매니저이고 매니저를 이양하지를 판단한다
		if(changer.getMemberLevel() == 2 && changed.getMemberLevel() == 2) {
			// 먼저 자신의 등급을 0으로 바꾼다
			changer.setMemberLevel(0);
			teamMemberDAO.changeMemberLever(changer);
			return 1;
		}
		return 0;
	}
	
	//FTM04, FTM07, FTM08
	@Override
	public void deleteMember(String teamMemberId) throws SQLException {
		//1. 삭제한다
		teamMemberDAO.deleteMember(teamMemberId);
	}
	
	//FTM05
	@Override
	public List<TeamMember> showApplyMemberByTeam(int teamId) throws SQLException {
		return teamMemberDAO.showApplyMemberByTeam(teamId);
	}
	
	//FTM06
	@Override
	public void acceptApplyMember(String teamMemberId) throws SQLException {
		teamMemberDAO.acceptApplyMember(teamMemberId);
	}
	
	//FTM07
	@Override
	public String changeImg(User user, MultipartFile file, String path) throws SQLException {
		// 1. 기존 이미지 삭제(있다면)
		if(user.getImage() != null) {
			File img = new File(path + user.getImage());
			if(img.exists()) img.delete();
		}
		//2. 새 이미지 업로드 | 각인
		String fileName = "";
		if(file != null) {
			String rawFileName = file.getOriginalFilename();
			String[] unit = rawFileName.split("\\.");
			String extension = unit[unit.length-1];
			fileName = user.getEmail() + "." + extension;
			user.setImage(fileName);
			try {
				file.transferTo(new File(path+fileName)); //파일 생성
			} catch (IllegalStateException | IOException e) {
				System.out.println(e);
			}
		}
		//3. 수정
		teamMemberDAO.changeImg(user);
		
		return fileName;
	}
	
}
