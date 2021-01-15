package com.biomans.fbt.matchschedule.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.TeamScore;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.util.MatchResultCollection;
import com.biomans.fbt.util.SearchKey;

public interface MatchScheduleService {
	// FS01
	public MatchSchedule showMatchScheduleByMatchScheduleId(int matchScheduleId) throws SQLException;
	// FS02 
	public List<MatchSchedule> showMatchSchduleByTeamPeriod(SearchKey searchKey) throws SQLException;
	// FV16
	public void confirmMatchSchedule(HashMap<String, Integer> searchCon) throws SQLException;
	//FS07
	public MatchSchedule showMatchScheduleResultByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	// FS09
	public List<TeamMember> showAttendVotedMember(HashMap<String, Integer> searchCon) throws SQLException;
	// FS10
	public HashMap<String, List<User>> showAttendFriendEmploy(HashMap<String, Integer> searchCon) throws SQLException;
	//FS11
	public MatchResultCollection showMatchResultCollection(HashMap<String, Integer> searchCon) throws SQLException;
	//FS11
	public void updateMatchResult(MatchResultCollection matchResultCollection) throws SQLException;
	//FS12
	public List<MatchSchedule> showMatchSchduleByUserPeriod(HashMap<String, String> searchCon) throws SQLException;
	//FS14
	public MatchSchedule showMatchScheduleResultByUser(HashMap<String, Integer> searchCon, String email) throws SQLException;
	//FS15
	public void addTeamScore(TeamScore teamScore) throws SQLException;
	//FS16
	public void updateTeamScore(TeamScore teamScore) throws SQLException;
	//FS17
	public List<MatchSchedule> showFutureSchedule(int teamId) throws SQLException;
	//FS18
	public void updateMatchSchedule(MatchSchedule matchSchedule) throws SQLException;
	// FS19: 일정 삭제 
	public void deleteMatchSchedule(int matchScheduleId) throws SQLException;
	
	//S001: 일정 등록
	public void addMatchSchedule(MatchSchedule matchSchedule) throws SQLException;
	// S002: 방금 동록한 일정 ID 출력
	public int showLatestMatchScheduleIdById(int teamId) throws SQLException;
	
	
	// S005-2: 등록된 개인 경기 일정 출력
	;

	// S009
	public void addMatchResultCollection(MatchResultCollection matchResultCollection) throws SQLException;
	// S010
	
	
	
}
