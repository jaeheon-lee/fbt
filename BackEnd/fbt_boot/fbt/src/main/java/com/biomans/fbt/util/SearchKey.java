package com.biomans.fbt.util;

public class SearchKey {
	private String email;
	private int teamId;
	private int voteStatus;
	private String startTime;
	private String endTime;
	
	public SearchKey() {}

	public SearchKey(String email, int teamId, int voteStatus, String startTime, String endTime) {
		super();
		this.email = email;
		this.teamId = teamId;
		this.voteStatus = voteStatus;
		this.startTime = startTime;
		this.endTime = endTime;
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

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "SearchKey [email=" + email + ", teamId=" + teamId + ", voteStatus=" + voteStatus + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}
}
