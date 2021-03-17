package com.biomans.fbt.matchschedule.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.Entry;
import com.biomans.fbt.domain.MatchResult;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.TeamScore;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.util.AvgScore;
import com.biomans.fbt.util.ScoreDescInput;
import com.biomans.fbt.util.SearchKey;

public interface MatchScheduleDAO {
	//S001
	public MatchSchedule showMatchScheduleByMatchScheduleId(int matchScheduleId) throws SQLException;
	//S002
	public int showLatestMatchScheduleIdById(int teamId) throws SQLException;
	//
	public MatchSchedule showConfirmedMatchSchedule(HashMap<String, Integer> searchCon) throws SQLException;
	//S003
	public void addMatchSchedule(MatchSchedule matchSchedule) throws SQLException;
	//S004
	public void addAwayTeam(HashMap<String, Integer> con) throws SQLException;
	// S005-1
	public List<MatchSchedule> showMatchSchduleByTeamPeriod(SearchKey searchKey) throws SQLException;
	// S005-2
	public List<MatchSchedule> showMatchSchduleByUserPeriod(HashMap<String, String> searchCon) throws SQLException;
	public List<MatchSchedule> showMatchScheduleByEmployPeriod(HashMap<String, String> searchCon) throws SQLException;
	public List<MatchSchedule> showMatchScheduleByFriendPeriod(HashMap<String, String> searchCon) throws SQLException;
	// S006: 일정 수정
	public void updateMatchSchedule(MatchSchedule matchSchedule) throws SQLException;
	// S007
	public List<TeamMember> showAttendVotedMember(HashMap<String, Integer> searchCon) throws SQLException;
	// S008
	public List<User> showAttendVotedFriend(HashMap<String, Integer> searchCon) throws SQLException;
	public List<User> showAcceptedEmploy(HashMap<String, Integer> searchCon) throws SQLException;
	// S009
	public void addEntry(Entry entry) throws SQLException;
	public void addMatchResult(MatchResult matchResult) throws SQLException;
	public void addEmpScore(EmpScore empScore) throws SQLException;
	public void addTeamScore(TeamScore teamScore) throws SQLException;
	public int checkAwayTeam(int matchScheduleId) throws SQLException;
	// S010
	public MatchSchedule showMatchScheduleResult(HashMap<String, Integer> searchCon) throws SQLException;
	// S011
	public void changeHomeTeam(HashMap<String, Integer> con) throws SQLException;
	// V06-1: 일정 삭제 
	public void deleteMatchSchedule(int matchScheduleId) throws SQLException;
	// S013
	public void confirmMatchSchedule(HashMap<String, Integer> searchCon) throws SQLException;
	// S014
	public List<TeamScore> getTeamScores(int teamId) throws SQLException;
	// S015
	public void updateTeamScoreDesc(Team team) throws SQLException;
	// S016
	public List<EmpScore> getEmpScores(String email) throws SQLException;
	// S017
	public void updateEmpScoreDesc(ScoreDescInput scoreDescInput) throws SQLException;
	// S018
	public EmpScore getEmpScoreByScheduleIdEmail(HashMap<String, String> con) throws SQLException;
	// S019
	public void updateEntry(Entry entry) throws SQLException;
	public void updateMatchResult(MatchResult matchResult) throws SQLException;
	public void updateEmpScore(EmpScore empScore) throws SQLException;
	public void updateTeamScore(TeamScore teamScore) throws SQLException;
	// S020
	public AvgScore getAvgTeamScore(int takerTeamId) throws SQLException;
	// S021
	public AvgScore getAvgEmpScore(String email) throws SQLException;
	// S022
	public List<MatchSchedule> showFutureSchedule(int teamId) throws SQLException; 
	// 대기를 참여로 바꾸기
	public void joinEntry(Entry entry) throws SQLException;
	// 참석취소하기
	public void deleteEntry(int entryId) throws SQLException;
	// 해당 팀, 일정의 엔트리 가져오기
	public List<Entry> getEntryByTeamSchedule(HashMap<String, Integer> searchCon) throws SQLException;

}
