package com.honeycom.fbt.dto;

public class Employment {
	private int employmentId;
	private int teamIdGiver;
	private int teamMemberIdGiver;
	private int employmentStatus;
	private String email;
	private int matchInfoId;
	
	public Employment() {
		super();
	}

	public Employment(int matchInfoId, int teamIdGiver, int teamMemberIdGiver, int employmentStatus, String email) {
		super();
		this.matchInfoId = matchInfoId;
		this.teamIdGiver = teamIdGiver;
		this.teamMemberIdGiver = teamMemberIdGiver;
		this.employmentStatus = employmentStatus;
		this.email = email;
	}

	public Employment(int employmentId, int matchInfoId, int teamIdGiver, int teamMemberIdGiver, int employmentStatus,
			String email) {
		super();
		this.employmentId = employmentId;
		this.matchInfoId = matchInfoId;
		this.teamIdGiver = teamIdGiver;
		this.teamMemberIdGiver = teamMemberIdGiver;
		this.employmentStatus = employmentStatus;
		this.email = email;
	}

	public int getEmploymentId() {
		return employmentId;
	}

	public void setEmploymentId(int employmentId) {
		this.employmentId = employmentId;
	}

	public int getMatchInfoId() {
		return matchInfoId;
	}

	public void setMatchInfoId(int matchInfoId) {
		this.matchInfoId = matchInfoId;
	}

	public int getTeamIdGiver() {
		return teamIdGiver;
	}

	public void setTeamIdGiver(int teamIdGiver) {
		this.teamIdGiver = teamIdGiver;
	}

	public int getTeamMemberIdGiver() {
		return teamMemberIdGiver;
	}

	public void setTeamMemberIdGiver(int teamMemberIdGiver) {
		this.teamMemberIdGiver = teamMemberIdGiver;
	}

	public int getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(int employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employment [employmentId=" + employmentId + ", matchInfoId=" + matchInfoId + ", teamIdGiver="
				+ teamIdGiver + ", teamMemberIdGiver=" + teamMemberIdGiver + ", employmentStatus=" + employmentStatus
				+ ", email=" + email + "]";
	}
	
	
}
