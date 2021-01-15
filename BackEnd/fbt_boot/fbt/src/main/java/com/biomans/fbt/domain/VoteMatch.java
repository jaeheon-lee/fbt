package com.biomans.fbt.domain;

import java.util.ArrayList;

public class VoteMatch {
	private int voteMatchId;
	private String memo;
	private String dueDate;
	private int voteStatus;
	private String voteRegDate;
	private String writer;
	private int friendEmp;
	private int teamId;
	private Team team;
	private int matchScheduleId;
	private MatchSchedule matchSchedule;
	private VoteMatchSetting voteMatchSetting;
	private VoteMatchResult voteMatchResult;
	private ArrayList<VoteMatchResult> voteMatchResults;
	private int totalNum;
	private int votedNum;
	private int abscentNum;
	private int attendNum;
	private int friendNum;
	private int totalAttend;
	
	public VoteMatch() {}

	public VoteMatch(int voteMatchId, String memo, String dueDate, int voteStatus, String voteRegDate, String writer,
			int friendEmp, int teamId, Team team, int matchScheduleId, MatchSchedule matchSchedule,
			VoteMatchSetting voteMatchSetting, VoteMatchResult voteMatchResult,
			ArrayList<VoteMatchResult> voteMatchResults, int totalNum, int votedNum, int abscentNum, int attendNum,
			int friendNum, int totalAttend) {
		super();
		this.voteMatchId = voteMatchId;
		this.memo = memo;
		this.dueDate = dueDate;
		this.voteStatus = voteStatus;
		this.voteRegDate = voteRegDate;
		this.writer = writer;
		this.friendEmp = friendEmp;
		this.teamId = teamId;
		this.team = team;
		this.matchScheduleId = matchScheduleId;
		this.matchSchedule = matchSchedule;
		this.voteMatchSetting = voteMatchSetting;
		this.voteMatchResult = voteMatchResult;
		this.voteMatchResults = voteMatchResults;
		this.totalNum = totalNum;
		this.votedNum = votedNum;
		this.abscentNum = abscentNum;
		this.attendNum = attendNum;
		this.friendNum = friendNum;
		this.totalAttend = totalAttend;
	}

	public int getVoteMatchId() {
		return voteMatchId;
	}

	public void setVoteMatchId(int voteMatchId) {
		this.voteMatchId = voteMatchId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public int getVoteStatus() {
		return voteStatus;
	}

	public void setVoteStatus(int voteStatus) {
		this.voteStatus = voteStatus;
	}

	public String getVoteRegDate() {
		return voteRegDate;
	}

	public void setVoteRegDate(String voteRegDate) {
		this.voteRegDate = voteRegDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getFriendEmp() {
		return friendEmp;
	}

	public void setFriendEmp(int friendEmp) {
		this.friendEmp = friendEmp;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getMatchScheduleId() {
		return matchScheduleId;
	}

	public void setMatchScheduleId(int matchScheduleId) {
		this.matchScheduleId = matchScheduleId;
	}

	public MatchSchedule getMatchSchedule() {
		return matchSchedule;
	}

	public void setMatchSchedule(MatchSchedule matchSchedule) {
		this.matchSchedule = matchSchedule;
	}

	public VoteMatchSetting getVoteMatchSetting() {
		return voteMatchSetting;
	}

	public void setVoteMatchSetting(VoteMatchSetting voteMatchSetting) {
		this.voteMatchSetting = voteMatchSetting;
	}

	public VoteMatchResult getVoteMatchResult() {
		return voteMatchResult;
	}

	public void setVoteMatchResult(VoteMatchResult voteMatchResult) {
		this.voteMatchResult = voteMatchResult;
	}

	public ArrayList<VoteMatchResult> getVoteMatchResults() {
		return voteMatchResults;
	}

	public void setVoteMatchResults(ArrayList<VoteMatchResult> voteMatchResults) {
		this.voteMatchResults = voteMatchResults;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getVotedNum() {
		return votedNum;
	}

	public void setVotedNum(int votedNum) {
		this.votedNum = votedNum;
	}

	public int getAbscentNum() {
		return abscentNum;
	}

	public void setAbscentNum(int abscentNum) {
		this.abscentNum = abscentNum;
	}

	public int getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(int attendNum) {
		this.attendNum = attendNum;
	}

	public int getFriendNum() {
		return friendNum;
	}

	public void setFriendNum(int friendNum) {
		this.friendNum = friendNum;
	}

	public int getTotalAttend() {
		return totalAttend;
	}

	public void setTotalAttend(int totalAttend) {
		this.totalAttend = totalAttend;
	}

	@Override
	public String toString() {
		return "VoteMatch [voteMatchId=" + voteMatchId + ", memo=" + memo + ", dueDate=" + dueDate + ", voteStatus="
				+ voteStatus + ", voteRegDate=" + voteRegDate + ", writer=" + writer + ", friendEmp=" + friendEmp
				+ ", teamId=" + teamId + ", team=" + team + ", matchScheduleId=" + matchScheduleId + ", matchSchedule="
				+ matchSchedule + ", voteMatchSetting=" + voteMatchSetting + ", voteMatchResult=" + voteMatchResult
				+ ", voteMatchResults=" + voteMatchResults + ", totalNum=" + totalNum + ", votedNum=" + votedNum
				+ ", abscentNum=" + abscentNum + ", attendNum=" + attendNum + ", friendNum=" + friendNum
				+ ", totalAttend=" + totalAttend + "]";
	}

}
