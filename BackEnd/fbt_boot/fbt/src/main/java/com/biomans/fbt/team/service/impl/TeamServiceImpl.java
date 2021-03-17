package com.biomans.fbt.team.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.TeamScore;
import com.biomans.fbt.team.dao.TeamDAO;
import com.biomans.fbt.team.service.TeamService;
import com.biomans.fbt.teammember.dao.TeamMemberDAO;
import com.biomans.fbt.util.TeamFilter;

@Service
public class TeamServiceImpl implements TeamService{
	@Autowired
	private TeamDAO teamDAO;
	
	@Autowired
	private TeamMemberDAO teamMemberDAO;
	
	//FT01
	@Override
	public Team showTeamInfo(HashMap<String, String> con) throws SQLException {
		Team team = teamDAO.showTeamInfo(con);
		// 팀원의 총원을 구한다
		List<TeamMember> tmList = team.getTeamMembers();
		if(tmList.size() > 0) team.setTotalNum(tmList.size());
		
		// 연락처를 남길 대표자만 남긴다
		List<TeamMember> setList = new ArrayList<TeamMember>();
		String teamMemberId = con.get("teamMemberId");
		for(TeamMember member : tmList) {
			if(member.getTeamMemberId().equals(teamMemberId)) {
				setList.add(member);
				break;
			}else {
				continue;
			}
		}
		team.setTeamMembers(setList);
		
		// 최근 5경기 전적 삽입
		int teamId = Integer.valueOf(con.get("teamId"));
		List<MatchSchedule> matchSchedules = teamDAO.showMatchRecordByTeam(teamId);
		if (matchSchedules.size() > 0) {
			team.setMatchSchedules(matchSchedules);
		}
		return team;
	}

	@Override
	public List<Team> searchTeams(HashMap<String, String> searchCon) throws SQLException {
		return teamDAO.searchTeams(searchCon);
	}
	
	//FT02
	@Override
	@Transactional
	public void addTeam(Team team, MultipartFile file, String path, TeamMember teamMember) throws SQLException {
		String fileName = "";
		// 파일 업로드 & 저장
		if(file != null) {
			// 파일명 양식: teamName.확장자 
			String rawFileName = file.getOriginalFilename();
			String[] unit = rawFileName.split("\\.");
			String extension = unit[unit.length-1];
			fileName = team.getTeamName() + "." + extension;
			try {
				file.transferTo(new File(path+fileName)); //파일 생성
			} catch (IllegalStateException | IOException e) {
				System.out.println(e);
			}
		}
		// emblem 지정
		team.setEmblem(fileName);
		
		// 팀등록
		teamDAO.addTeam(team);
		// 등록한 팀명으로 teamId 불러오기, teamId랑 teamMemberId 설정
		int teamId = teamDAO.getTeamIdByTeamName(team.getTeamName());
		Team team2 = new Team();
		team2.setTeamId(teamId);
		teamMember.setTeam(team2);
		String teamMemberId = teamId + "-" + teamMember.getUser().getEmail();
		teamMember.setTeamMemberId(teamMemberId);
		//본인을 팀원, 구단주로 등록
		teamMemberDAO.addTeamMember(teamMember);
	}
	
	//FT03
	@Override
	public String checkDupleTeamName(String teamName) throws SQLException {
		return teamDAO.checkDupleTeamName(teamName);
	}
	
	//FT04
	@Override 
	public List<Team> searchTeamsByFilter(TeamFilter teamFilter) throws SQLException {
		List<Team> teams = teamDAO.searchTeamsByFilter(teamFilter);
		return teams;
	}
	
	//FT05
	@Override
	public Team showTeamInfo(int teamId) throws SQLException {
		return teamDAO.showTeamInfo(teamId);
	}
	
	//FT06
	@Override
	@Transactional
	public void updateTeamInfo(Team team, MultipartFile file, String beforeUrl, String path) throws SQLException {
		String fileName = "";
		//1. 이미지를 수정하는지 확인한다
		if(beforeUrl != null) {
			// 수정한다면 그리고 기존 url이 기본 이미지가 아니라면 기존 이미지를 삭제한다
			File img = new File(path + beforeUrl);
			if(img.exists() && !beforeUrl.equals("emptyFC.svg")) img.delete();
			// 기본이미지로 수정인지, 다른 이미지로 수정인지 확인한다
			if(file != null) { // 다른 이미지라면 파일명을 양식(teamName.확장자)대로 하고 새로운 파일을 생성한다
				String rawFileName = file.getOriginalFilename();
				int idx = rawFileName.indexOf(".");
				String extension = rawFileName.substring(idx, rawFileName.length());
				fileName = team.getTeamName() + extension;
				team.setEmblem(fileName);
				try {
					file.transferTo(new File(path+fileName)); //파일 생성
				} catch (IllegalStateException | IOException e) {
					System.out.println(e);
				}
			}
			// 기본이미지라면 null로 받아왔기 때문에 그냥 넘어간다
		}
		teamDAO.updateTeamInfo(team);
	}
	
	//FT07
	@Override
	public void deleteTeam(int teamId) throws SQLException {
		teamDAO.deleteTeam(teamId);
	}
	
	//TNN :
	@Override
	public Team showTeamInfoForAutoWrite(HashMap<String, String> searchCon) throws SQLException {
		Team team = teamDAO.showTeamInfoForAutoWrite(searchCon);
		List<TeamScore> teamScores = team.getTeamScores();
		double sumManner = 0.0;
		double sumAbility = 0.0;
		// 팀이 받은 전체 평가들의 합 산출
		for(TeamScore ts : teamScores) {
			sumManner += ts.getMannerArrangement() + ts.getMannerBodyFight() +ts.getMannerContact()
			+ ts.getMannerPayment() + ts.getMannerPromise() + ts.getMannerReferee() + ts.getMannerRule()
			+ ts.getMannerSlang() + ts.getMannerSmoking() + ts.getMannerTackle() + ts.getMannerUniform();
			
			sumAbility += ts.getDefence() + ts.getForward() + ts.getMiddle();
		}
		// 팀이 받은 전체 평균 산출
		double teamManner = sumManner / (11*teamScores.size());
		double teamAbility = sumAbility / (3*teamScores.size());
		
		// set
		team.setTeamManner(teamManner);
		team.setTeamAbility(teamAbility);
		return team;
	}
}
