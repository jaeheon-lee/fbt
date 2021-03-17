package com.biomans.fbt.matchschedule.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.Entry;
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
	//
	public MatchSchedule showConfirmedMatchSchedule(HashMap<String, Integer> searchCon) throws SQLException;
	// FV16
	public void confirmMatchSchedule(HashMap<String, Integer> searchCon) throws SQLException;
	public void addEntry(HashMap<String, Integer> searchCon) throws SQLException;
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
	//
	public void addAwayTeam(HashMap<String, Integer> searchCon) throws SQLException;
	// V06-1: 일정 삭제 
	public void deleteMatchSchedule(int matchScheduleId) throws SQLException;
	//S001: 일정 등록
	public void addMatchSchedule(MatchSchedule matchSchedule) throws SQLException;
	//
	public void doWait(Entry entry) throws SQLException;
	// S002: 방금 동록한 일정 ID 출력
	public int showLatestMatchScheduleIdById(int teamId) throws SQLException;
	// S009
	public void addMatchResultCollection(MatchResultCollection matchResultCollection) throws SQLException;
	public void updateTeamScoreDesc(TeamScore teamScore) throws SQLException;
	public void updateEmpScoreDesc(EmpScore es) throws SQLException;
	// S010
	
	// 대기를 참여로 바꾸기
	public void joinEntry(Entry entry) throws SQLException;
	// 참석취소하기
	public void deleteEntry(int entryId) throws SQLException;
	// 해당 팀, 일정의 엔트리 가져오기
	public List<Entry> getEntryByTeamSchedule(HashMap<String, Integer> searchCon) throws SQLException;
	
	
	
}
