package com.biomans.fbt.domain;

public class VoteMatchResult {
	private String voteMatchId;
	private String voteMatchResultId;
	private int attendance;
	private TeamMember teamMember;
	private User user;
	
	public VoteMatchResult() {
		super();
	}

	public VoteMatchResult(String voteMatchId, String voteMatchResultId, int attendance, TeamMember teamMember, User user) {
		super();
		this.voteMatchId = voteMatchId;
		this.voteMatchResultId = voteMatchResultId;
		this.attendance = attendance;
		this.teamMember = teamMember;
		this.user = user;
	}

	public String getVoteMatchId() {
		return voteMatchId;
	}

	public void setVoteMatchId(String voteMatchId) {
		this.voteMatchId = voteMatchId;
	}

	public String getVoteMatchResultId() {
		return voteMatchResultId;
	}

	public void setVoteMatchResultId(String voteMatchResultId) {
		this.voteMatchResultId = voteMatchResultId;
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
		return "VoteMatchResult [voteMatchId=" + voteMatchId + ", voteMatchResultId=" + voteMatchResultId
				+ ", attendance=" + attendance + ", teamMember=" + teamMember + ", user=" + user + "]";
	}
}
