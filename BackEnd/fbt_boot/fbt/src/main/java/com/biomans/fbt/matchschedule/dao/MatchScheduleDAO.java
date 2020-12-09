package com.biomans.fbt.matchschedule.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.Entry;
import com.biomans.fbt.domain.MatchResult;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.TeamScore;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.util.SearchKey;

public interface MatchScheduleDAO {
	//S001
	public MatchSchedule showMatchScheduleByMatchScheduleId(int matchScheduleId) throws SQLException;
	//S002
	public int showLatestMatchScheduleIdById(int teamId) throws SQLException;
	//S003
	public void addMatchSchedule(MatchSchedule matchSchedule) throws SQLException;
	
	
	
	//일정 수정
	public void updateMatchSchedule(MatchSchedule matchSchedule) throws SQLException;
	// S00N: 일정 삭제 
	public void deleteMatchSchedule(int matchScheduleId) throws SQLException;
	// S005-1: 등록된 팀 경기 일정 출력 
	public List<MatchSchedule> showMatchSchduleByTeamPeriod(SearchKey searchKey) throws SQLException;
	// S005-2: 등록된 개인 경기 일정 출력
	public List<MatchSchedule> showMatchSchduleByUserPeriod(HashMap<String, String> searchCon) throws SQLException;
	public List<MatchSchedule> showMatchScheduleByEmployPeriod(String email) throws SQLException;
	
	// S007
	public List<TeamMember> showAttendVotedMember(String voteMatchId) throws SQLException;
	// S008
	public List<User> showAttendVotedFriend(String voteMatchId) throws SQLException;
	public List<User> showAcceptedEmploy(int matchScheduleId) throws SQLException;
	// S009
	public void addEntry(Entry entry) throws SQLException;
	public void addMatchResult(MatchResult matchResult) throws SQLException;
	public void addEmpScore(EmpScore empScore) throws SQLException;
	public void addTeamScore(TeamScore teamScore) throws SQLException;
	public int checkAwayTeam(int matchScheduleId) throws SQLException;
	// S010
	public MatchSchedule showMatchScheduleResult(int matchScheduleId) throws SQLException;
	
	//
	public void addAwayTeam(HashMap<String, String> con) throws SQLException;
	// A006
	public void changeHomeTeam(HashMap<String, Integer> con) throws SQLException;
}
