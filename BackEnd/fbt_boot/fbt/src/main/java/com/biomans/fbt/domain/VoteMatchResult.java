package com.biomans.fbt.domain;

public class VoteMatchResult {
	private int attendance;
	private TeamMember teamMember;
	private int totalNum;
	private int votedNum;
	private int abscentNum;
	private int attendNum;
	private int friendNum;
	
	public VoteMatchResult() {
		super();
	}

	public VoteMatchResult(int attendance, TeamMember teamMember, int totalNum, int votedNum, int abscentNum,
			int attendNum, int friendNum) {
		super();
		this.attendance = attendance;
		this.teamMember = teamMember;
		this.totalNum = totalNum;
		this.votedNum = votedNum;
		this.abscentNum = abscentNum;
		this.attendNum = attendNum;
		this.friendNum = friendNum;
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	public TeamMember getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(TeamMember teamMember) {
		this.teamMember = teamMember;
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
		return "VoteMatchResult [attendance=" + attendance + ", teamMember=" + teamMember + ", totalNum=" + totalNum
				+ ", votedNum=" + votedNum + ", abscentNum=" + abscentNum + ", attendNum=" + attendNum + ", friendNum="
				+ friendNum + "]";
	}
}
