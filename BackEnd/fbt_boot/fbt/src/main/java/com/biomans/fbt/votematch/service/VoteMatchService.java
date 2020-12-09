package com.biomans.fbt.votematch.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.Invite;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.domain.VoteMatchSetting;

public interface VoteMatchService {
	//FV01, FV02, FS02
	public List<VoteMatch> showVoteMatchInfoByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//FV03
	public VoteMatch showVoteMatchInfoByScheduleId(int matchScheduleId) throws SQLException;
	//FV04
	public void addVoteMatchAndSetting(VoteMatch voteMatch) throws SQLException;
	//FV05, FV06
	public void addAttendance(VoteMatchResult voteMatchResult, VoteMatch voteMatch) throws SQLException;
	//FV05
	public void updateVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException;
	//FV07
	public void updateVoteMatch(VoteMatch voteMatch, int type) throws SQLException;
	//FV09
	public List<User> searchFriend(HashMap<String, String> searchCon) throws SQLException;
	//FV09
	public void inviteFriend(Invite invite) throws SQLException;
	//FV10
	public void updateVoteMatchSetting(VoteMatchSetting voteMatchSetting) throws SQLException;
	
	//V011 : 투표 결과 삭제
	public void deleteVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException;
	
}
