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
	public void updateResStatus(EmployResult employRes, Employ employ) throws SQLException {
		// 용병 신청 결과 상태 수정
		employDAO.updateResStatus(employRes);
		
		// 수락 시, 수락인원(지금 가져오는 것은 수락 전 숫자이므로 + 1)이 모집인원 이상인지 확인
		if(employRes.getEmpResultStatus() == 1) {
			int acceptNum = employ.getAcceptNum() + 1;
			int reqNumber = employ.getReqNumber();
			
			// 이상이면 경기 확정
			if(acceptNum >= reqNumber) {
				int matchScheduleId = employ.getMatchSchedule().getMatchScheduleId();
				HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
				searchCon.put("matchScheduleId", matchScheduleId);
				MatchSchedule ms = matchScheduleDAO.showMatchScheduleByMatchScheduleId(matchScheduleId);
				if(ms.getAwayTeam() == null) searchCon.put("awayTeamId", 0); 
				else searchCon.put("awayTeamId", -1); 
				matchScheduleDAO.confirmMatchSchedule(searchCon);
			}
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
