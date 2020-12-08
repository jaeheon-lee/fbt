package com.biomans.fbt.votematch.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.biomans.fbt.domain.Invite;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchResult;
import com.biomans.fbt.domain.VoteMatchSetting;

public interface VoteMatchDAO {
	//V001-1
	public List<VoteMatch> showVoteMatchInfoByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//V001-2
	public List<VoteMatch> showVoteMatchNumByVote(int teamId) throws SQLException;
	//V002-1
	public List<VoteMatchResult> showVoteMatchResultByVote(int voteMatchId) throws SQLException;
	//V002-2
	public VoteMatch showVoteMatchNumByScheduleId(int matchScheduleId) throws SQLException;
	//V003-1 
	public VoteMatch showVoteMatchInfoByScheduleId(int matchScheduleId) throws SQLException;
	//V003-2
	public ArrayList<VoteMatchResult> showVoteMatchResultByScheduleId(int matchScheduleId)throws SQLException;
	
	//V004
	public void addVoteMatch(VoteMatch voteMatch) throws SQLException;
	//V005
	public int getLatestVoteMatchIdByTeam(int teamId) throws SQLException;
	//V006
	public void addVoteMatchSetting(VoteMatchSetting voteMatchSetting) throws SQLException;
	
	
	
	
	//V002 : 투표 결과 수정
	public void updateVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException;
	//V003 : 투표 입력
	public void addAttendance(VoteMatchResult voteMatchResult) throws SQLException;
	// 상대방 찾기를 통한 투표인지 확인
	public String checkBySearch(HashMap<String, String> searchCon) throws SQLException;
	//V004 : 지인 초청
	public void inviteFriend(Invite invite) throws SQLException;

	
	//V007 : 투표 마감
	public void endVoteMatch(VoteMatch voteMatch) throws SQLException;
	//V008 : 투표 내용 수정
	public void updateVoteMatch(VoteMatch voteMatch) throws SQLException;
	//V009 : 투표 삭제
	public void deleteVoteMatch(String voteMatchId) throws SQLException;
	//V010: 투표 설정 수정
	public void updateVoteMatchSetting(VoteMatchSetting voteMatchSetting) throws SQLException;
	//V011 : 투표 결과 삭제
	public void deleteVoteMatchResult(VoteMatchResult voteMatchResult) throws SQLException;
	//V013 : 지인 검색하기
	public List<User> searchFriend(HashMap<String, String> searchCon) throws SQLException;
	
	
}
