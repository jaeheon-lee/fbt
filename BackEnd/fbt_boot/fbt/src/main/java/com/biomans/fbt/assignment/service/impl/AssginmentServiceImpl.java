package com.biomans.fbt.assignment.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomans.fbt.assignment.dao.AssignmentDAO;
import com.biomans.fbt.assignment.service.AssignmentService;
import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.AssignmentReservation;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
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

	@Override
	public void addAssignment(Assignment assignment) throws SQLException {
		assignmentDAO.addAssignment(assignment);
		
	}
	
	@Override
	public List<Assignment> showRegisteredAssignByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return assignmentDAO.showRegisteredAssignByTeam(searchCon);
	}

	@Override
	public List<Assignment> showRegisteredAssignAppliedByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return assignmentDAO.showRegisteredAssignAppliedByTeam(searchCon);
		
	}
	
	//A003
	@Override
	public List<Assignment> searchAssignmentByFilter(Filter filter) throws SQLException {
		return assignmentDAO.searchAssignmentByFilter(filter);
	}
	
	//A005
	@Override
	public void doApplyAssignment(AssignmentReservation assignmentRes) throws SQLException {
		assignmentDAO.doApplyAssignment(assignmentRes);
	}
	
	//A006
	@Override
	public void updateResStatus(Assignment assignment) throws SQLException {
		AssignmentReservation assignRes = assignment.getAssignmentReservations().get(0);
		// 수락이면 
		if(assignRes.getReservationStatus() == 1) {
			//기존 투표를 지운다
			int teamId = assignment.getTeamGiver().getTeamId();
			int matchScheduleId = assignment.getMatchSchedule().getMatchScheduleId();
			String voteMatchId = teamId + "-" + matchScheduleId;
			voteMatchDAO.deleteVoteMatch(voteMatchId);
			
			// 홈팀을 바꾼다
			HashMap<String, Integer> con = new HashMap<String, Integer>();
			con.put("takerTeamId", assignRes.getTeamTaker().getTeamId());
			con.put("matchScheduleId", matchScheduleId);
			matchScheduleDAO.changeHomeTeam(con);
		}
		assignmentDAO.updateResStatus(assignRes);
	}

	@Override
	public void deleteAssignment(int assignmentId) throws SQLException {
		assignmentDAO.deleteAssignment(assignmentId);
		
	}

	@Override
	public void renewAssign(int assignmentId) throws SQLException {
		assignmentDAO.renewAssign(assignmentId);
		
	}
	
	//A013
	@Override
	public void deleteAssignRes(HashMap<String, Integer> searchCon) throws SQLException {
		assignmentDAO.deleteAssignRes(searchCon);
	}

}
