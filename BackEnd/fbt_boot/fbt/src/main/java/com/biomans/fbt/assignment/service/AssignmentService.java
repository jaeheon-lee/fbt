package com.biomans.fbt.assignment.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.AssignmentReservation;
import com.biomans.fbt.util.Filter;

public interface AssignmentService {
	//FA01
	public void addAssignment(Assignment assignment) throws SQLException;
	//FA04, FA10
	public List<Assignment> showRegisteredAssignByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//FA07, FA12, FA13
	public List<Assignment> showRegisteredAssignAppliedByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//FA02
	public List<Assignment> searchAssignmentByFilter(Filter filter) throws SQLException;
	//FA03
	public void doApplyAssignment(AssignmentReservation assignmentRes) throws SQLException;
	//FA08, FA09
	public void updateResStatus(Assignment assignment) throws SQLException;
	//FA08, FA09
	public Assignment getAssignmentById(int assignmentId) throws SQLException;
	//FA05, FA11
	public void deleteAssignment(int assignmentId) throws SQLException;
	//FA06
	public void renewAssign(int assignmentId) throws SQLException;
	//FA14
	public void deleteAssignRes(HashMap<String, Integer> searchCon) throws SQLException;
	//FA15
	//A009
	public void updateAssignment(Assignment assignment) throws SQLException;
}
