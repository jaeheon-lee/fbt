package com.biomans.fbt.domain;

public class VoteMatchResult {
	private int attendance;
	private TeamMember teamMember;
	private User user;
	
	public VoteMatchResult() {
		super();
	}

	public VoteMatchResult(int attendance, TeamMember teamMember, User user) {
		super();
		this.attendance = attendance;
		this.teamMember = teamMember;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "VoteMatchResult [attendance=" + attendance + ", teamMember=" + teamMember + ", user=" + user + "]";
	}
}
