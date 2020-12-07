package com.biomans.fbt.employ.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biomans.fbt.domain.Employ;
import com.biomans.fbt.domain.EmployResult;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.employ.dao.EmployDAO;
import com.biomans.fbt.util.Filter;

@Repository
public class EmployDAOImpl implements EmployDAO{
	final String ns = "EmployMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	//E001
	@Override
	public void addEmploy(Employ employ) throws SQLException {
		sqlSession.insert(ns+"addEmploy", employ);
		
	}
	
	//E002-1
	@Override
	public List<Employ> showRegisteredEmployByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showRegisteredEmployByTeam", searchCon);
	}
	
	//E002-2
	@Override
	public List<Employ> showAppliedEmployByUser(HashMap<String, String> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showAppliedEmployByUser", searchCon);
	}
	
	//E003
	@Override
	public List<Employ> searchEmployByFilter(Filter filter) throws SQLException {
		return sqlSession.selectList(ns+"searchEmployByFilter", filter);
	}
	
	//E005
	@Override
	public void doApplyEmploy(EmployResult employRes) throws SQLException {
		sqlSession.insert(ns+"doApplyEmploy", employRes);
	}
	
	//E006
	@Override
	public void updateResStatus(EmployResult employRes) throws SQLException {
		sqlSession.update(ns+"updateResStatus", employRes);
	}
	
	//E009
	@Override
	public void deleteEmploy(int employId) throws SQLException {
		sqlSession.delete(ns+"deleteEmploy", employId);
		
	}
	
	//E010
	@Override
	public void renewEmploy(int employId) throws SQLException {
		sqlSession.update(ns+"renewEmploy", employId);
		
	}
	
	//E013
	@Override
	public void deleteEmployRes(HashMap<String, String> searchCon) throws SQLException {
		sqlSession.delete(ns+"deleteEmployRes", searchCon);
	}
	
	//E014
	public Team findEmpTeam(int matchScheduleId) throws SQLException {
		return sqlSession.selectOne(ns+"findEmpTeam", matchScheduleId);
	}
	
	
}
