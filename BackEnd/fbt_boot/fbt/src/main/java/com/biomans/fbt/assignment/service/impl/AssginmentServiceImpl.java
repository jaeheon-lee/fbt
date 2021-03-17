package com.biomans.fbt.assignment.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biomans.fbt.assignment.dao.AssignmentDAO;
import com.biomans.fbt.assignment.service.AssignmentService;
import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.AssignmentReservation;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchSetting;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.matchschedule.service.MatchScheduleService;
import com.biomans.fbt.team.dao.TeamDAO;
import com.biomans.fbt.util.Filter;
import com.biomans.fbt.votematch.dao.VoteMatchDAO;

@Service
public class AssginmentServiceImpl implements AssignmentService{
	@Autowired
	private AssignmentDAO assignmentDAO;
	
	@Autowired
	private MatchScheduleDAO matchScheduleDAO;
	
	@Autowired
	private VoteMatchDAO voteMatchDAO;
	
	@Autowired
	private TeamDAO teamDAO;
	
	@Autowired
	private MatchScheduleService matchScheduleService;
	
	//FA01
	@Override
	public void addAssignment(Assignment assignment) throws SQLException {
		assignmentDAO.addAssignment(assignment);
		
	}
	
	//FA04, FA10
	@Override
	public List<Assignment> showRegisteredAssignByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return assignmentDAO.showRegisteredAssignByTeam(searchCon);
	}
	
	//FA07, FA12, FA13
	@Override
	public List<Assignment> showRegisteredAssignAppliedByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		//양도글을 먼저 받아온다
		List<Assignment> assignments = assignmentDAO.showRegisteredAssignAppliedByTeam(searchCon);
		// 신청 팀과 글쓴이 연락처에 대한 정보를 받아온다
		if(assignments.size() > 0) {
			for(Assignment assignment : assignments) {
				List<AssignmentReservation> assignmentReservations = assignment.getAssignmentReservations();
				for(AssignmentReservation assignmentRes : assignmentReservations) {
					// 신청 팀 정보 받기
					int teamTakerId = assignmentRes.getTeamTaker().getTeamId();
					Team teamTaker = teamDAO.showTeamBasicInfo(teamTakerId);
					if(teamTaker != null) assignmentRes.setTeamTaker(teamTaker);
				}
			}
		}
		return assignments;
		
	}
	
	//FA02
	@Override
	public List<Assignment> searchAssignmentByFilter(Filter filter) throws SQLException {
		List<Assignment> assignments = new ArrayList<Assignment>();
		List<Assignment> list = assignmentDAO.searchAssignmentByFilter(filter);
		int page = filter.getPage();
		if(list.size() > page) {
			assignments.add(list.get(page));
			if(list.size() - 1 >= page + 1) assignments.add(list.get(page + 1)); 
		}
		return assignments;
	}
	
	//FA04
	@Override
	public void doApplyAssignment(AssignmentReservation assignmentRes) throws SQLException {
		assignmentDAO.doApplyAssignment(assignmentRes);
	}
	
	//FA08, FA09
	@Override
	@Transactional
	public void updateResStatus(Assignment assignment) throws SQLException {
		AssignmentReservation assignRes = assignment.getAssignmentReservations().get(0);
		//인원파악수락을 요청했다면, 자동으로 투표를 생성
		if(assignRes.getReservationStatus() == 1) {
			// 자동 투표 생성 메소드
			addVoteMatchByAssignment(assignment, assignRes);
		} else if (assignRes.getReservationStatus() == -1) {
			// 거절이면, 만들어진 투표를 삭제한다.
			int matchScheduleId = assignment.getMatchSchedule().getMatchScheduleId();
			int teamId = assignment.getAssignmentReservations().get(0).getTeamTaker().getTeamId();
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("matchScheduleId", matchScheduleId);
			searchCon.put("teamId", teamId);
			voteMatchDAO.deleteVoteMatchByMatchScheduleId(searchCon);
		}
		assignmentDAO.updateResStatus(assignRes);
	}
	
	@Async("threadPoolTaskExecutor")
	@Transactional
	@Override
	public Future<Integer> timeApplyFailure(Assignment assignment, AssignmentReservation assignRes) throws SQLException {
		int number=2;
		int waitingTime = assignment.getWaitingTime();
		try {
			Thread.sleep(1000 * 60 * 60 * waitingTime); // 시간을 한 시간 단위로 잡는다 : 1000 * 60 * 60 * var
			int status = assignmentDAO.checkAssignSuccessById(assignRes);
			if(status != 3) { // 마감시간까지도 완료되지 않았다면
				assignRes.setReservationStatus(-1);
				assignmentDAO.updateResStatus(assignRes);
				System.out.println("==================done====================");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			System.out.println(e2);
		}
		return new AsyncResult<Integer>(number);
	}
	
	public void addVoteMatchByAssignment(Assignment assignment, AssignmentReservation assignRes) throws SQLException {
		MatchSchedule matchSchedule = assignment.getMatchSchedule();
		// 투표를 생성한다.
		VoteMatch voteMatch = new VoteMatch();
		voteMatch.setMatchSchedule(matchSchedule);
		// 투표 마감시간을 현재 시간에서 + waitingTime으로 설정한다.
		int waitingTime = assignment.getWaitingTime();
		Long current = System.currentTimeMillis();
		Timestamp t = new Timestamp(current);  
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(t.getTime());
		cal.add(Calendar.HOUR, waitingTime);
		Timestamp dueDate = new Timestamp(cal.getTime().getTime());
		voteMatch.setDueDate(dueDate.toString());
		// 투표 작성자 설정
		voteMatch.setWriter(matchSchedule.getWriter());
		// 투표 팀아이디 설정
		Team team = new Team();
		team.setTeamId(assignRes.getTeamTaker().getTeamId());
		voteMatch.setTeam(team);
		// 투표 메모 설정
		voteMatch.setMemo("양도 중인 투표입니다. 마감시간이 중요하니 마감시간 전에 빠른 투표 부탁드립니다.");
		// 투표 생성
		voteMatchDAO.addVoteMatch(voteMatch);
		// 해당 팀의, 갓 생성한 투표Id를 가져온다
		int teamId = assignRes.getTeamTaker().getTeamId();
		int voteMatchId = voteMatchDAO.getLatestVoteMatchIdByTeam(teamId);
		// 투표 설정을 지인 추가 가능으로 설정한다 & 투표 설정 생성
		VoteMatchSetting voteSet = 
				new VoteMatchSetting(voteMatchId, 0, assignment.getMinNumber(), false, 
						false, true, -1, -1, null, -1, -1, null, -1, -1, -1);
		voteMatchDAO.addVoteMatchSetting(voteSet);	
	}
	
	//
	@Override
	public void completeAssignment(Assignment assignment, AssignmentReservation assignRes) throws SQLException {
		//기존 투표를 지운다
		int matchScheduleId = assignment.getMatchSchedule().getMatchScheduleId();
		int teamGiverId = assignment.getTeamGiver().getTeamId();
		HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
		searchCon.put("matchScheduleId", matchScheduleId);
		searchCon.put("teamId", teamGiverId);
		VoteMatch v = voteMatchDAO.showVoteMatchBasicByScheduleTeam(searchCon);
		int voteMatchId = 0;
		if(v != null) voteMatchId = v.getVoteMatchId();
		if(voteMatchId != 0) voteMatchDAO.deleteVoteMatchByVoteMatchId(voteMatchId);
		
		// 양도 상태를 확정으로 변경한다
		int assignmentId = assignment.getAssignmentId();
		int takerTeamId = assignRes.getTeamTaker().getTeamId();
		searchCon.put("assignmentId", assignmentId);
		searchCon.put("takerTeamId", takerTeamId);
		assignmentDAO.completeAssignment(searchCon);
		
		// 홈팀을 바꾼다
		HashMap<String, Integer> con = new HashMap<String, Integer>();
		con.put("takerTeamId", assignRes.getTeamTaker().getTeamId());
		con.put("matchScheduleId", matchScheduleId);
		matchScheduleDAO.changeHomeTeam(con);
		
		// 해당 일정을 확정시킨다
		con.put("teamId", assignRes.getTeamTaker().getTeamId());
		matchScheduleDAO.confirmMatchSchedule(con);
		
		// 엔트리를 등록한다
		matchScheduleService.addEntry(con);
		
		//나머지 팀을 실패로 한다
		con.put("assignmentId", assignment.getAssignmentId());
		assignmentDAO.failAssign(con);
		
	}
	
	//FA08, FA09
	public Assignment getAssignmentById(int assignmentId) throws SQLException {
		return assignmentDAO.getAssignmentById(assignmentId);
	}
	
	//FA05, FA11
	@Override
	public void deleteAssignment(int assignmentId) throws SQLException {
		assignmentDAO.deleteAssignment(assignmentId);
		
	}
	
	//FA06
	@Override
	public void renewAssign(int assignmentId) throws SQLException {
		assignmentDAO.renewAssign(assignmentId);
		
	}
	
	//FA14
	@Override
	public void deleteAssignRes(HashMap<String, Integer> searchCon) throws SQLException {
		assignmentDAO.deleteAssignRes(searchCon);
	}
	
	//FA15
	public void updateAssignment(Assignment assignment) throws SQLException {
		assignmentDAO.updateAssignment(assignment);
	}
	
}
