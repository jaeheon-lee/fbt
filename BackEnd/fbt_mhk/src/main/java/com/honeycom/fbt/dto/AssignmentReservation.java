package com.honeycom.fbt.dto;

public class AssignmentReservation {
	private int assignmentId;
	private int teamIdTaker;
	private int teamMemberIdTaker;
	private int reservationStatus;
	
	
	
	public AssignmentReservation() {
		super();
	}



	public AssignmentReservation(int assignmentId, int teamIdTaker, int teamMemberIdTaker, int reservationStatus) {
		super();
		this.assignmentId = assignmentId;
		this.teamIdTaker = teamIdTaker;
		this.teamMemberIdTaker = teamMemberIdTaker;
		this.reservationStatus = reservationStatus;
	}



	public int getAssignmentId() {
		return assignmentId;
	}



	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}



	public int getTeamIdTaker() {
		return teamIdTaker;
	}



	public void setTeamIdTaker(int teamIdTaker) {
		this.teamIdTaker = teamIdTaker;
	}



	public int getTeamMemberIdTaker() {
		return teamMemberIdTaker;
	}



	public void setTeamMemberIdTaker(int teamMemberIdTaker) {
		this.teamMemberIdTaker = teamMemberIdTaker;
	}



	public int getReservationStatus() {
		return reservationStatus;
	}



	public void setReservationStatus(int reservationStatus) {
		this.reservationStatus = reservationStatus;
	}



	@Override
	public String toString() {
		return "AssignmentResult [assignmentId=" + assignmentId + ", teamIdTaker=" + teamIdTaker
				+ ", teamMemberIdTaker=" + teamMemberIdTaker + ", reservationStatus=" + reservationStatus + "]";
	}
	
	
}
