package com.biomans.fbt.domain;

public class AssignmentReservation {
	private int asssignmetResId;
	private int assignmentId;
	private Team teamTaker;
	private String writer;
	private int reservationStatus;
	
	public AssignmentReservation() {}

	public AssignmentReservation(int asssignmetResId, int assignmentId, Team teamTaker, String writer,
			int reservationStatus) {
		super();
		this.asssignmetResId = asssignmetResId;
		this.assignmentId = assignmentId;
		this.teamTaker = teamTaker;
		this.writer = writer;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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
				+ ", teamTaker=" + teamTaker + ", writer=" + writer + ", reservationStatus=" + reservationStatus + "]";
	}

	
}
