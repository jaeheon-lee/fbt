package com.biomans.fbt.domain;

public class VoteMatchResult {
	private int voteMatchId;
	private String voteMatchResultId;
	private int attendance;
	private String teamMemberId;
	private TeamMember teamMember;
	private String email;
	private User user;
	
	public VoteMatchResult() {
		super();
	}

	public VoteMatchResult(int voteMatchId, String voteMatchResultId, int attendance, String teamMemberId,
			TeamMember teamMember, String email, User user) {
		super();
		this.voteMatchId = voteMatchId;
		this.voteMatchResultId = voteMatchResultId;
		this.attendance = attendance;
		this.teamMemberId = teamMemberId;
		this.teamMember = teamMember;
		this.email = email;
		this.user = user;
	}

	public int getVoteMatchId() {
		return voteMatchId;
	}

	public void setVoteMatchId(int voteMatchId) {
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

	public String getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(String teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public TeamMember getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(TeamMember teamMember) {
		this.teamMember = teamMember;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
				+ ", attendance=" + attendance + ", teamMemberId=" + teamMemberId + ", teamMember=" + teamMember
				+ ", email=" + email + ", user=" + user + "]";
	}

}
