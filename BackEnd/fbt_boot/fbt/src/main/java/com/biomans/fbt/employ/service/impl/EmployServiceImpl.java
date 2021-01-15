package com.biomans.fbt.employ.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biomans.fbt.domain.Employ;
import com.biomans.fbt.domain.EmployResult;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.employ.dao.EmployDAO;
import com.biomans.fbt.employ.service.EmployService;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.util.Filter;

@Service
public class EmployServiceImpl implements EmployService {
	@Autowired
	private EmployDAO employDAO;
	
	@Autowired
	private MatchScheduleDAO matchScheduleDAO;
	
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
		List<Employ> employs = employDAO.showRegisteredEmployByTeam(searchCon);
		for(Employ emp : employs) {
			// 용병 모집 현황 삽입
			setEmployDesc(emp);
			
			// 단계별로 분류하기
			int acceptNum = emp.getAcceptNum();
			int reqNumber = emp.getReqNumber();
			if(status == 0) { // 용병찾기 중 창에서 요청
				if(acceptNum < reqNumber) selectedList.add(emp);
			}else if (status == 1) { // 용병찾기실패 창에서 요청
				if(acceptNum < reqNumber) selectedList.add(emp); // 요구 용병수보다 현재원이 작다면
			}else { // 용병찾기완료 창에서 요청
				if(acceptNum >= reqNumber) selectedList.add(emp); // 요구 용병수보다 현재원이 크다면
			}
		}
		return selectedList;
	}
	
	//FE11
	@Override
	public List<Employ> showAppliedEmployByUser(HashMap<String, String> searchCon) throws SQLException {
		List<Employ> list = employDAO.showAppliedEmployByUser(searchCon);
		// 모집 현황 삽입
		for(Employ emp : list) {
			setEmployDesc(emp);
		}
		return list;
	}

	//FE02
	@Override
	public List<Employ> searchEmployByFilter(Filter filter) throws SQLException {
		List<Employ> list = employDAO.searchEmployByFilter(filter);
		// 모집 현황 삽입
		for(Employ emp : list) {
			setEmployDesc(emp);
		}
		return list;
	}
	
	//FE02, FE04, FE09, FE10
	public void setEmployDesc(Employ emp) throws SQLException {
		Employ employDesc = employDAO.getEmployDesc(emp.getEmployId());
		if(employDesc != null) {
			emp.setCurrentNum(employDesc.getCurrentNum());
			emp.setAcceptNum(employDesc.getAcceptNum());
			emp.setRefuseNum(employDesc.getRefuseNum());
		}
	}
	
	//FE03
	@Override
	public void doApplyEmploy(EmployResult employRes) throws SQLException {
		employDAO.doApplyEmploy(employRes);
	}
	
	//FE07, FE08
	@Override
	@Transactional
	public void updateResStatus(Employ employ) throws SQLException {
		// 용병 신청 결과 상태 수정
		List<EmployResult> employRess = employ.getEmployResults();
		for(EmployResult employRes : employRess) {
			employDAO.updateResStatus(employRes);
		}
		// 만일 용병 확정이면 해당 일정을 확정한다. + 만일 상대팀이 없다면, 상대팀을 자기팀으로 
		EmployResult sampleRes = employ.getEmployResults().get(0);
		if(sampleRes.getEmpResultStatus() == 2) {
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("matchScheduleId", employ.getMatchSchedule().getMatchScheduleId());
			// 상대팀 있는지 체크
			if(employ.getMatchSchedule().getAwayTeam() == null) {
				// 상대팀을 자기로
				searchCon.put("teamId", employ.getMatchSchedule().getHomeTeam().getTeamId());
			}
			
			matchScheduleDAO.confirmMatchSchedule(searchCon);
		}
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
	
	//FE12, FE13
	@Override
	public void deleteEmployRes(HashMap<String, String> searchCon) throws SQLException {
		employDAO.deleteEmployRes(searchCon);
	}
	
	//FE14
	@Override
	public void updateEmploy(Employ employ) throws SQLException {
		employDAO.updateEmploy(employ);
	}
	
	//E014
	public Team findEmpTeam(int matchScheduleId) throws SQLException {
		return employDAO.findEmpTeam(matchScheduleId);
	}
}
