package com.biomans.fbt.votematch.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.domain.VoteMatchSetting;

public interface VoteMatchDAO {
	
	//
	public List<VoteMatchResult> getVoteMatchResults(HashMap<String, String> con) throws SQLException;
	
	
	
	//V02-2
	public List<VoteMatch> showVoteMatchInfoByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//V02-2
	public VoteMatch showVoteMatchNumByVote(int voteMatchId) throws SQLException;
	//V02-2
	public List<VoteMatchResult> showVoteMatchResultByVote(int voteMatchId) throws SQLException;
	//V001-2
	public VoteMatch showVoteMatchInfoByScheduleId(HashMap<String, Integer> searchCon) throws SQLException;
	//V001-3
	public VoteMatch showVoteMatchInfoById(int voteMatchId) throws SQLException;
	
	//V002-2
	public VoteMatch showVoteMatchNumByScheduleId(HashMap<String, Integer> searchCon) throws SQLException;
	
	//V003-2
	public ArrayList<VoteMatchResult> showVoteMatchResultByScheduleId(HashMap<String, Integer> searchCon) throws SQLException;
	
	//V01-6
	public void addVoteMatch(VoteMatch voteMatch) throws SQLException;
	//V01-6
	public int getLatestVoteMatchIdByTeam(int teamId) throws SQLException;
	//V01-6
	public void addVoteMatchSetting(VoteMatchSetting voteMatchSetting) throws SQLException;
	//V03-4
	public void addAttendance(VoteMatchResult voteMatchResult) throws SQLException;
	//V03-6
	public void updateVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException;
	//V03-5
	public String checkBySearch(HashMap<String, String> searchCon) throws SQLException;
	//V05-3, V06-1
	public void updateVoteMatch(VoteMatch voteMatch) throws SQLException;
	//V011
	public void updateVoteMatchSetting(VoteMatchSetting voteMatchSetting) throws SQLException;
	//V014-1
	public void deleteVoteMatchByVoteMatchId(int voteMatchId) throws SQLException;
	//V014-1
	public void deleteVoteMatchByMatchScheduleId(HashMap<String, Integer> searchCon) throws SQLException;
	//V015-1
	public VoteMatch showVoteMatchBasicByScheduleTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//V016
	public List<VoteMatch> loadEndedVoteMatch(HashMap<String, Integer> searchCon) throws SQLException;
	//V017
	public void deleteVoteMatchOfFailedTeam(HashMap<String, Integer> con) throws SQLException;	
	
	//V011 : 투표 결과 삭제
	public void deleteVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException;
	//V013 : 지인 검색하기
	
	
	
}
