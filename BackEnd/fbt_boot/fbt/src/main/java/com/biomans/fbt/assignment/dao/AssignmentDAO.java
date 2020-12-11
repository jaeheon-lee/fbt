package com.biomans.fbt.assignment.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.AssignmentReservation;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.util.Filter;

public interface AssignmentDAO {
	//A001
	public void addAssignment(Assignment assignment) throws SQLException;
	//A002-1
	public List<Assignment> showRegisteredAssignByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//A002-2
	public List<Assignment> showRegisteredAssignAppliedByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//A003
	public List<Assignment> searchAssignmentByFilter(Filter filter) throws SQLException;
	//A004
	public void doApplyAssignment(AssignmentReservation assignmentRes) throws SQLException;
	//A005
	public void updateResStatus(AssignmentReservation assignmentRes) throws SQLException;
	//A006
	public void deleteAssignment(int assignmentId) throws SQLException;
	//A007
	public void renewAssign(int assignmentId) throws SQLException;
	//A008
	public void deleteAssignRes(HashMap<String, Integer> searchCon) throws SQLException;
}
