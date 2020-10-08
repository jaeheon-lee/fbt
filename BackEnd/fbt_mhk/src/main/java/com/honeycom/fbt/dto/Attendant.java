package com.honeycom.fbt.dto;

public class Attendant {
	private int attendantId;
	private int matchScheduleId;
	private int teamMemberId;
	private int attendance;
	private int employmentResultId;
	private ReviewFromTeam reviewFromTeam;
	private ReviewFromEmployee reviewFromEmployee;
	
	
	public Attendant() {}


	public Attendant(int attendantId, int matchScheduleId, int teamMemberId, int attendance, int employmentResultId,
			ReviewFromTeam reviewFromTeam, ReviewFromEmployee reviewFromEmployee) {
		super();
		this.attendantId = attendantId;
		this.matchScheduleId = matchScheduleId;
		this.teamMemberId = teamMemberId;
		this.attendance = attendance;
		this.employmentResultId = employmentResultId;
		this.reviewFromTeam = reviewFromTeam;
		this.reviewFromEmployee = reviewFromEmployee;
	}


	public int getAttendantId() {
		return attendantId;
	}


	public void setAttendantId(int attendantId) {
		this.attendantId = attendantId;
	}


	public int getMatchScheduleId() {
		return matchScheduleId;
	}


	public void setMatchScheduleId(int matchScheduleId) {
		this.matchScheduleId = matchScheduleId;
	}


	public int getTeamMemberId() {
		return teamMemberId;
	}


	public void setTeamMemberId(int teamMemberId) {
		this.teamMemberId = teamMemberId;
	}


	public int getAttendance() {
		return attendance;
	}


	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}


	public int getEmploymentResultId() {
		return employmentResultId;
	}


	public void setEmploymentResultId(int employmentResultId) {
		this.employmentResultId = employmentResultId;
	}


	public ReviewFromTeam getReviewFromTeam() {
		return reviewFromTeam;
	}


	public void setReviewFromTeam(ReviewFromTeam reviewFromTeam) {
		this.reviewFromTeam = reviewFromTeam;
	}


	public ReviewFromEmployee getReviewFromEmployee() {
		return reviewFromEmployee;
	}


	public void setReviewFromEmployee(ReviewFromEmployee reviewFromEmployee) {
		this.reviewFromEmployee = reviewFromEmployee;
	}


	@Override
	public String toString() {
		return "Attendant [attendantId=" + attendantId + ", matchScheduleId=" + matchScheduleId + ", teamMemberId="
				+ teamMemberId + ", attendance=" + attendance + ", employmentResultId=" + employmentResultId
				+ ", reviewFromTeam=" + reviewFromTeam + ", reviewFromEmployee=" + reviewFromEmployee + "]";
	}

	
}
