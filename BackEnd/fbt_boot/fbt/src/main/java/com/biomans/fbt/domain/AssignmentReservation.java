package com.biomans.fbt.domain;

public class AssignmentReservation {
	private int asssignmetResId;
	private int assignmentId;
	private Team teamTaker;
	private TeamMember teamMember;
	private int reservationStatus;
	
	public AssignmentReservation() {}

	public AssignmentReservation(int asssignmetResId, int assignmentId, Team teamTaker, TeamMember teamMember,
			int reservationStatus) {
		super();
		this.asssignmetResId = asssignmetResId;
		this.assignmentId = assignmentId;
		this.teamTaker = teamTaker;
		this.teamMember = teamMember;
		this.reservationStatus = reservationStatus;
	}

	public int getAsssignmetResId() {
		return asssignmetResId;
	}

	public void setAsssignmetResId(int asssignmetResId) {
		this.asssignmetResId = asssignmetResId;
	}

	public int getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	public Team getTeamTaker() {
		return teamTaker;
	}

	public void setTeamTaker(Team teamTaker) {
		this.teamTaker = teamTaker;
	}

	public TeamMember getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(TeamMember teamMember) {
		this.teamMember = teamMember;
	}

	public int getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(int reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	@Override
	public String toString() {
		return "AssignmentReservation [asssignmetResId=" + asssignmetResId + ", assignmentId=" + assignmentId
				+ ", teamTaker=" + teamTaker + ", teamMember=" + teamMember + ", reservationStatus=" + reservationStatus
				+ "]";
	}

}
