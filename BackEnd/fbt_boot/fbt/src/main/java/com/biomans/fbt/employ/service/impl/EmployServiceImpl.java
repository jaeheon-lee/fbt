package com.biomans.fbt.employ.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomans.fbt.domain.Employ;
import com.biomans.fbt.domain.EmployResult;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.employ.dao.EmployDAO;
import com.biomans.fbt.employ.service.EmployService;
import com.biomans.fbt.util.Filter;

@Service
public class EmployServiceImpl implements EmployService {
	@Autowired
	private EmployDAO employDAO;
	
	//FE01
	@Override
	public void addEmploy(Employ employ) throws SQLException {
		employDAO.addEmploy(employ);
		
	}
	
	//FE04, FE09, FE10
	@Override
	public List<Employ> showRegisteredEmployByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		List<Employ> selectedList = new ArrayList<Employ>();
		int status = searchCon.get("status");
		List<Employ> list = employDAO.showRegisteredEmployByTeam(searchCon);
		for(Employ emp : list) {
			int currentNum = countEmployDesc(emp);
			int reqNumber = emp.getReqNumber();
			emp.setCurrentNum(currentNum); // 현재인원 추가
			if(status == 0) { // 용병찾기 중 창에서 요청
				selectedList.add(emp);
			}else if (status == 1) { // 용병찾기실패 창에서 요청
				if(currentNum < reqNumber) selectedList.add(emp); // 요구 용병수보다 현재원이 작다면
			}else { // 용병찾기완료 창에서 요청
				if(currentNum > reqNumber) selectedList.add(emp); // 요구 용병수보다 현재원이 크다면
			}
		}
		return selectedList;
	}
	
	//E002-2
	@Override
	public List<Employ> showAppliedEmployByUser(HashMap<String, String> searchCon) throws SQLException {
		List<Employ> list = employDAO.showAppliedEmployByUser(searchCon);
		// 모집 현황 삽입
		for(Employ emp : list) {
			emp.setCurrentNum(countEmployDesc(emp));
		}
		return list;
	}

	//FE02
	@Override
	public List<Employ> searchEmployByFilter(Filter filter) throws SQLException {
		List<Employ> list = employDAO.searchEmployByFilter(filter);
		// 모집 현황 삽입
		for(Employ emp : list) {
			emp.setCurrentNum(countEmployDesc(emp));
		}
		return list;
	}
	
	@Override
	public int countEmployDesc(Employ employ) throws SQLException {
		List<EmployResult> list = employ.getEmployResults();
		System.out.println(list);
		return list.size();
	}
	
	
	//FE03
	@Override
	public void doApplyEmploy(EmployResult employRes) throws SQLException {
		employDAO.doApplyEmploy(employRes);
	}
	
	//FE07
	@Override
	public void updateResStatus(EmployResult employRes) throws SQLException {
		employDAO.updateResStatus(employRes);
	}
	
	//FE05
	@Override
	public void deleteEmploy(int employId) throws SQLException {
		employDAO.deleteEmploy(employId);
		
	}
	
	//FE06
	@Override
	public void renewEmploy(int employId) throws SQLException {
		employDAO.renewEmploy(employId);
		
	}
	
	//E013
	@Override
	public void deleteEmployRes(HashMap<String, String> searchCon) throws SQLException {
		employDAO.deleteEmployRes(searchCon);
	}
	
	//E014
	public Team findEmpTeam(int matchScheduleId) throws SQLException {
		return employDAO.findEmpTeam(matchScheduleId);
	}
}
