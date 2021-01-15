package com.biomans.fbt.employ.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.Employ;
import com.biomans.fbt.domain.EmployResult;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.util.Filter;

public interface EmployService {
	//FE01
	public void addEmploy(Employ employ) throws SQLException;
	//FE04, FE09, FE10
	public List<Employ> showRegisteredEmployByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//FE11
	public List<Employ> showAppliedEmployByUser(HashMap<String, String> searchCon) throws SQLException;
	//FE02
	public List<Employ> searchEmployByFilter(Filter filter) throws SQLException;
	//FE03
	public void doApplyEmploy(EmployResult employRes) throws SQLException;
	//FE07, FE08
	public void updateResStatus(Employ employ) throws SQLException;
	//FE05
	public void deleteEmploy(int employId) throws SQLException;
	//FE06
	public void renewEmploy(int employId) throws SQLException;
	//FE12, FE13
	public void deleteEmployRes(HashMap<String, String> searchCon) throws SQLException;
	//FE14
	public void updateEmploy(Employ employ) throws SQLException;
	//E014
	public Team findEmpTeam(int matchScheduleId) throws SQLException;
}
