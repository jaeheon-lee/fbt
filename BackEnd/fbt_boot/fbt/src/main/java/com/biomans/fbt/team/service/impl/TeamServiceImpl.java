package com.biomans.fbt.team.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.TeamScore;
import com.biomans.fbt.team.dao.TeamDAO;
import com.biomans.fbt.team.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService{
	@Autowired
	private TeamDAO teamDAO;
	
	//T001
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
