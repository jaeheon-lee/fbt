package com.biomans.fbt.matchschedule.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.TeamScore;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.util.MatchResultCollection;
import com.biomans.fbt.util.SearchKey;

public interface MatchScheduleService {
	// FS01
	public MatchSchedule showMatchScheduleByMatchScheduleId(int matchScheduleId) throws SQLException;
	
	
	
	//S001: 일정 등록
	public void addMatchSchedule(MatchSchedule matchSchedule) throws SQLException;
	// S002: 방금 동록한 일정 ID 출력
	public int showLatestMatchScheduleIdById(int teamId) throws SQLException;
	// S00N: 일정 삭제 
	public void deleteMatchSchedule(int matchScheduleId) throws SQLException;
	// S005-1: 등록된 팀 경기 일정 출력 
	public List<MatchSchedule> showMatchSchduleByTeamPeriod(SearchKey searchKey) throws SQLException;
	// S005-2: 등록된 개인 경기 일정 출력
	public List<MatchSchedule> showMatchSchduleByUserPeriod(HashMap<String, String> searchCon) throws SQLException;
	// S007
	public List<TeamMember> showAttendVotedMember(String voteMatchId) throws SQLException;
	// S008
	public HashMap<String, List<User>> showAttendFriendEmploy(HashMap<String, String> searchCon) throws SQLException;
	// S009
	public void addMatchResultCollection(MatchResultCollection matchResultCollection) throws SQLException;
	// S010
	public MatchSchedule showMatchScheduleResult(int matchScheduleId) throws SQLException;
	// S011
	public void addTeamScore(TeamScore teamScore) throws SQLException;
}