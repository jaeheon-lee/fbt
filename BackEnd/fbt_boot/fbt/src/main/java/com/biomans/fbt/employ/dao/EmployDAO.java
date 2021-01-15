package com.biomans.fbt.employ.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.Employ;
import com.biomans.fbt.domain.EmployResult;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.util.Filter;

public interface EmployDAO {
	
	//
	public List<EmployResult> getEmployResults(EmployResult employResult) throws SQLException;
	
	
	//E001
	public void addEmploy(Employ employ) throws SQLException;
	//E002-1
	public List<Employ> showRegisteredEmployByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//E002-2
	public List<Employ> showAppliedEmployByUser(HashMap<String, String> searchCon) throws SQLException;
	//E003
	public List<Employ> searchEmployByFilter(Filter filter) throws SQLException;
	//E004
	public void updateResStatus(EmployResult employRes) throws SQLException;
	//E005
	public void doApplyEmploy(EmployResult employRes) throws SQLException;
	//E006
	public void deleteEmploy(int employId) throws SQLException;
	//E007
	public void renewEmploy(int employId) throws SQLException;
	//E008
	public void deleteEmployRes(HashMap<String, String> searchCon) throws SQLException;
	//E009
	public Team findEmpTeam(int matchScheduleId) throws SQLException;
	//E010
	public Employ getEmployDesc(int employId) throws SQLException;
	//E011
	public void updateEmploy(Employ employ) throws SQLException;
}
