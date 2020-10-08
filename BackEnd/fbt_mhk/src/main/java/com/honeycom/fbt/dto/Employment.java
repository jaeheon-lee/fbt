package com.honeycom.fbt.dto;

import java.util.List;

public class Employment {
	private int employmentId;
	private int teamIdGiver;
	private int teamMemberIdGiver;
	private int employmentStatus;
	private int matchInfoId;
	
	private List<EmploymentResult> employmentResults;
	
	public Employment() {
		super();
	}

	public Employment(int employmentId, int teamIdGiver, int teamMemberIdGiver, int employmentStatus, int matchInfoId,
			List<EmploymentResult> employmentResults) {
		super();
		this.employmentId = employmentId;
		this.teamIdGiver = teamIdGiver;
		this.teamMemberIdGiver = teamMemberIdGiver;
		this.employmentStatus = employmentStatus;
		this.matchInfoId = matchInfoId;
		this.employmentResults = employmentResults;
	}

	public int getEmploymentId() {
		return employmentId;
	}

	public void setEmploymentId(int employmentId) {
		this.employmentId = employmentId;
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

	public int getMatchInfoId() {
		return matchInfoId;
	}

	public void setMatchInfoId(int matchInfoId) {
		this.matchInfoId = matchInfoId;
	}

	public List<EmploymentResult> getEmploymentResults() {
		return employmentResults;
	}

	public void setEmploymentResults(List<EmploymentResult> employmentResults) {
		this.employmentResults = employmentResults;
	}

	@Override
	public String toString() {
		return "Employment [employmentId=" + employmentId + ", teamIdGiver=" + teamIdGiver + ", teamMemberIdGiver="
				+ teamMemberIdGiver + ", employmentStatus=" + employmentStatus + ", matchInfoId=" + matchInfoId
				+ ", employmentResults=" + employmentResults + "]";
	}

			
}
