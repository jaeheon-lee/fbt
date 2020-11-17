package com.biomans.fbt.util;

public class SearchKey {
	private String email;
	private int teamId;
	private int voteStatus;
	private String startTime;
	
	public SearchKey() {}

	public SearchKey(String email, int teamId, int voteStatus, String startTime) {
		super();
		this.email = email;
		this.teamId = teamId;
		this.voteStatus = voteStatus;
		this.startTime = startTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getVoteStatus() {
		return voteStatus;
	}

	public void setVoteStatus(int voteStatus) {
		this.voteStatus = voteStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "searchKey [email=" + email + ", teamId=" + teamId + ", voteStatus=" + voteStatus + ", startTime="
				+ startTime + "]";
	}	
}
