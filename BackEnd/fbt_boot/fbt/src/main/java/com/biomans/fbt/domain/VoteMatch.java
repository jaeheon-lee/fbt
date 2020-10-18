package com.biomans.fbt.domain;

import java.util.ArrayList;

public class VoteMatch {
	private String voteMatchId;
	private String contents;
	private String dueDate;
	private int voteStatus;
	private String voteRegDate;
	private String writer;
	private int friendEmp;
	private Team team;
	private MatchSchedule matchSchedule;
	private VoteMatchSetting voteMatchSetting;
	private ArrayList<VoteMatchResult> VoteMatchResults;
	private int totalNum;
	private int votedNum;
	private int abscentNum;
	private int attendNum;
	private int friendNum;
	
	public VoteMatch() {}

	public VoteMatch(String voteMatchId, String contents, String dueDate, int voteStatus, String voteRegDate,
			String writer, int friendEmp, Team team, MatchSchedule matchSchedule, VoteMatchSetting voteMatchSetting,
			ArrayList<VoteMatchResult> voteMatchResults, int totalNum, int votedNum, int abscentNum, int attendNum,
			int friendNum) {
		super();
		this.voteMatchId = voteMatchId;
		this.contents = contents;
		this.dueDate = dueDate;
		this.voteStatus = voteStatus;
		this.voteRegDate = voteRegDate;
		this.writer = writer;
		this.friendEmp = friendEmp;
		this.team = team;
		this.matchSchedule = matchSchedule;
		this.voteMatchSetting = voteMatchSetting;
		VoteMatchResults = voteMatchResults;
		this.totalNum = totalNum;
		this.votedNum = votedNum;
		this.abscentNum = abscentNum;
		this.attendNum = attendNum;
		this.friendNum = friendNum;
	}

	public String getVoteMatchId() {
		return voteMatchId;
	}

	public void setVoteMatchId(String voteMatchId) {
		this.voteMatchId = voteMatchId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
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

	public ArrayList<VoteMatchResult> getVoteMatchResults() {
		return VoteMatchResults;
	}

	public void setVoteMatchResults(ArrayList<VoteMatchResult> voteMatchResults) {
		VoteMatchResults = voteMatchResults;
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

	@Override
	public String toString() {
		return "VoteMatch [voteMatchId=" + voteMatchId + ", contents=" + contents + ", dueDate=" + dueDate
				+ ", voteStatus=" + voteStatus + ", voteRegDate=" + voteRegDate + ", writer=" + writer + ", friendEmp="
				+ friendEmp + ", team=" + team + ", matchSchedule=" + matchSchedule + ", voteMatchSetting="
				+ voteMatchSetting + ", VoteMatchResults=" + VoteMatchResults + ", totalNum=" + totalNum + ", votedNum="
				+ votedNum + ", abscentNum=" + abscentNum + ", attendNum=" + attendNum + ", friendNum=" + friendNum
				+ "]";
	}
}
