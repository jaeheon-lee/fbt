package com.biomans.fbt.votematch.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.domain.VoteMatchSetting;

public interface VoteMatchService {
	//V02-2, FS02
	public List<VoteMatch> showVoteMatchInfoByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//FV03
	public VoteMatch showVoteMatchInfoByScheduleId(HashMap<String, Integer> searchCon) throws SQLException;
	//V01-6
	public void addVoteMatchAndSetting(VoteMatch voteMatch) throws SQLException;
	//V03-4
	public HashMap<String, Integer> addAttendance(VoteMatchResult voteMatchResult, VoteMatch voteMatch) throws SQLException;
	//V03-6
	public void updateVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException;
	//V05-3, V06-1
	public void updateVoteMatch(VoteMatch voteMatch, int type, String teamName) throws SQLException;
	//FV10
	public void updateVoteMatchSetting(VoteMatchSetting voteMatchSetting) throws SQLException;
	//FV15
	public List<VoteMatch> loadEndedVoteMatch(HashMap<String, Integer> searchCon) throws SQLException;
	//FV17
	public VoteMatch showVoteMatchInfoById(int voteMatchId) throws SQLException;
	
	//V011 : 투표 결과 삭제
	public void deleteVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException;
	
}
