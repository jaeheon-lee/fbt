package com.biomans.fbt.domain;

public class VoteMatchResult extends VoteMatch{
	private int attendance;
	private TeamMember teamMember;
	private int totalNum;
	
	public VoteMatchResult() {
		super();
	}

	public VoteMatchResult(int attendance, TeamMember teamMember, int totalNum) {
		super();
		this.attendance = attendance;
		this.teamMember = teamMember;
		this.totalNum = totalNum;
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

	@Override
	public String toString() {
		return "VoteMatchResult [attendance=" + attendance + ", teamMember=" + teamMember + ", totalNum=" + totalNum
				+ "]";
	}

	
}
