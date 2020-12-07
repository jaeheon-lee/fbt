package com.biomans.fbt.employ.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.Employ;
import com.biomans.fbt.domain.EmployResult;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.util.Filter;

public interface EmployDAO {
	//E001
	public void addEmploy(Employ employ) throws SQLException;
	//E002-1
	public List<Employ> showRegisteredEmployByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//E002-2
	public List<Employ> showAppliedEmployByUser(HashMap<String, String> searchCon) throws SQLException;
	//E003
	public List<Employ> searchEmployByFilter(Filter filter) throws SQLException;
	//E005
	public void doApplyEmploy(EmployResult employRes) throws SQLException;
	//E006
	public void updateResStatus(EmployResult employRes) throws SQLException;
	//E009
	public void deleteEmploy(int employId) throws SQLException;
	//E010
	public void renewEmploy(int employId) throws SQLException;
	//E013
	public void deleteEmployRes(HashMap<String, String> searchCon) throws SQLException;
	//E014
	public Team findEmpTeam(int matchScheduleId) throws SQLException;
}
