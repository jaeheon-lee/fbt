package com.biomans.fbt.assignment.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biomans.fbt.assignment.dao.AssignmentDAO;
import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.AssignmentReservation;
import com.biomans.fbt.util.Filter;

@Repository
public class AssignmentDAOImpl implements AssignmentDAO{
final String ns = "AssignmentMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	//A001
	@Override
	public void addAssignment(Assignment assignment) throws SQLException {
		sqlSession.insert(ns+"addAssignment", assignment);
		
	}
	
	//A002-1
	@Override
	public List<Assignment> showRegisteredAssignByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showRegisteredAssignByTeam", searchCon);
	}
	
	//A002-2
	@Override
	public List<Assignment> showRegisteredAssignAppliedByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showRegisteredAssignAppliedByTeam", searchCon);
	}
	
	//A002-3
	@Override
	public Assignment getAssignmentById(int assignmentId) throws SQLException {
		return sqlSession.selectOne(ns+"getAssignmentById", assignmentId);
	}

	//A003
	@Override
	public List<Assignment> searchAssignmentByFilter(Filter filter) throws SQLException {
		return sqlSession.selectList(ns+"searchAssignmentByFilter", filter);
	}
	
	//A004
	@Override
	public void updateResStatus(AssignmentReservation assignmentRes) throws SQLException {
		sqlSession.update(ns+"updateResStatus", assignmentRes);
	}
	
	//A005
	@Override
	public void doApplyAssignment(AssignmentReservation assignmentRes) throws SQLException {
		sqlSession.insert(ns + "doApplyAssignment", assignmentRes);
	}
	//A006
	@Override
	public void deleteAssignment(int assignmentId) throws SQLException {
		sqlSession.delete(ns+"deleteAssignment", assignmentId);
		
	}
	
	//A007
	@Override
	public void renewAssign(int assignmentId) throws SQLException {
		sqlSession.update(ns+"renewAssign", assignmentId);
		
	}
	
	//A008
	@Override
	public void deleteAssignRes(HashMap<String, Integer> searchCon) throws SQLException {
		sqlSession.delete(ns+"deleteAssignRes", searchCon);
	}

	//A009
	@Override
	public void updateAssignment(Assignment assignment) throws SQLException {
		sqlSession.update(ns+"updateAssignment", assignment);
	}
	
	//A011
	@Override
	public void failAssign(HashMap<String, Integer> searchCon) throws SQLException {
		sqlSession.update(ns+"failAssign", searchCon);
	}
	
}
