package com.biomans.fbt.assignment.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.AssignmentReservation;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.util.Filter;

public interface AssignmentService {
	//A001
	public void addAssignment(Assignment assignment) throws SQLException;
	//A002-1
	public List<Assignment> showRegisteredAssignByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//A002-2
	public List<Assignment> showRegisteredAssignAppliedByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//A003
	public List<Assignment> searchAssignmentByFilter(Filter filter) throws SQLException;
	//A005
	public void doApplyAssignment(AssignmentReservation assignmentRes) throws SQLException;
	//A006
	public void updateResStatus(Assignment assignment) throws SQLException;
	//A009
	public void deleteAssignment(int assignmentId) throws SQLException;
	//A010
	public void renewAssign(int assignmentId) throws SQLException;
	//A013
	public void deleteAssignRes(HashMap<String, Integer> searchCon) throws SQLException;
}
