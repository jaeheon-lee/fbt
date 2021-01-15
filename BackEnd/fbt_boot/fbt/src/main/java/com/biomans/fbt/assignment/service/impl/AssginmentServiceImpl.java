package com.biomans.fbt.assignment.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biomans.fbt.assignment.dao.AssignmentDAO;
import com.biomans.fbt.assignment.service.AssignmentService;
import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.AssignmentReservation;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.team.dao.TeamDAO;
import com.biomans.fbt.util.Filter;
import com.biomans.fbt.votematch.dao.VoteMatchDAO;

@Service
public class AssginmentServiceImpl implements AssignmentService{
	@Autowired
	private AssignmentDAO assignmentDAO;
	
	@Autowired
	private MatchScheduleDAO matchScheduleDAO;
	
	@Autowired
	private VoteMatchDAO voteMatchDAO;
	
	@Autowired
	private TeamDAO teamDAO;
	
	//FA01
	@Override
	public void addAssignment(Assignment assignment) throws SQLException {
		assignmentDAO.addAssignment(assignment);
		
	}
	
	//FA04, FA10
	@Override
	public List<Assignment> showRegisteredAssignByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return assignmentDAO.showRegisteredAssignByTeam(searchCon);
	}
	
	//FA07, FA12, FA13
	@Override
	public List<Assignment> showRegisteredAssignAppliedByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		//양도글을 먼저 받아온다
		List<Assignment> assignments = assignmentDAO.showRegisteredAssignAppliedByTeam(searchCon);
		// 신청 팀과 글쓴이 연락처에 대한 정보를 받아온다
		if(assignments.size() > 0) {
			for(Assignment assignment : assignments) {
				List<AssignmentReservation> assignmentReservations = assignment.getAssignmentReservations();
				for(AssignmentReservation assignmentRes : assignmentReservations) {
					// 신청 팀 정보 받기
					int teamTakerId = assignmentRes.getTeamTaker().getTeamId();
					Team teamTaker = teamDAO.showTeamBasicInfo(teamTakerId);
					if(teamTaker != null) assignmentRes.setTeamTaker(teamTaker);
				}
			}
		}
		return assignments;
		
	}
	
	//FA02
	@Override
	public List<Assignment> searchAssignmentByFilter(Filter filter) throws SQLException {
		return assignmentDAO.searchAssignmentByFilter(filter);
	}
	
	//FA04
	@Override
	public void doApplyAssignment(AssignmentReservation assignmentRes) throws SQLException {
		assignmentDAO.doApplyAssignment(assignmentRes);
	}
	
	//FA08, FA09
	@Override
	@Transactional
	public void updateResStatus(Assignment assignment) throws SQLException {
		AssignmentReservation assignRes = assignment.getAssignmentReservations().get(0);
		// 수락이면 
		if(assignRes.getReservationStatus() == 2) {
			//기존 투표를 지운다
			int matchScheduleId = assignment.getMatchSchedule().getMatchScheduleId();
			int teamGiverId = assignment.getTeamGiver().getTeamId();
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("matchScheduleId", matchScheduleId);
			searchCon.put("teamId", teamGiverId);
			VoteMatch v = voteMatchDAO.showVoteMatchBasicByScheduleTeam(searchCon);
			int voteMatchId = 0;
			if(v != null) voteMatchId = v.getVoteMatchId();
			if(voteMatchId != 0) voteMatchDAO.deleteVoteMatchByVoteMatchId(voteMatchId);
			
			// 홈팀을 바꾼다
			HashMap<String, Integer> con = new HashMap<String, Integer>();
			con.put("takerTeamId", assignRes.getTeamTaker().getTeamId());
			con.put("matchScheduleId", matchScheduleId);
			matchScheduleDAO.changeHomeTeam(con);
			
			// 홈팀이 된 팀의 투표를 마감시킨다.
//			v.setVoteStatus(1);
//			voteMatchDAO.updateVoteMatch(v);
			
			//나머지 팀을 실패로 한다
			con.put("assignmentId", assignment.getAssignmentId());
			System.out.println(con);
			assignmentDAO.failAssign(con);
		}
		assignmentDAO.updateResStatus(assignRes);
	}
	
	//FA08, FA09
	public Assignment getAssignmentById(int assignmentId) throws SQLException {
		return assignmentDAO.getAssignmentById(assignmentId);
	}
	
	//FA05, FA11
	@Override
	public void deleteAssignment(int assignmentId) throws SQLException {
		assignmentDAO.deleteAssignment(assignmentId);
		
	}
	
	//FA06
	@Override
	public void renewAssign(int assignmentId) throws SQLException {
		assignmentDAO.renewAssign(assignmentId);
		
	}
	
	//FA14
	@Override
	public void deleteAssignRes(HashMap<String, Integer> searchCon) throws SQLException {
		assignmentDAO.deleteAssignRes(searchCon);
	}
	
	//FA15
	public void updateAssignment(Assignment assignment) throws SQLException {
		assignmentDAO.updateAssignment(assignment);
	}

}
