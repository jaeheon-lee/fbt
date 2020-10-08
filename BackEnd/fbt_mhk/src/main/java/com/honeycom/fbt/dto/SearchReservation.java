package com.honeycom.fbt.dto;

public class SearchReservation {
	private int searchId;
	private int teamIdTaker;
	private int teamMemberIdTaker;
	private int reservationStatus;
	
	public SearchReservation() {
		super();
	}

	public SearchReservation(int searchId, int teamIdTaker, int teamMemberIdTaker, int reservationStatus) {
		super();
		this.searchId = searchId;
		this.teamIdTaker = teamIdTaker;
		this.teamMemberIdTaker = teamMemberIdTaker;
		this.reservationStatus = reservationStatus;
	}

	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
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
		return "SearchReservation [searchId=" + searchId + ", teamIdTaker=" + teamIdTaker + ", teamMemberIdTaker="
				+ teamMemberIdTaker + ", reservationStatus=" + reservationStatus + "]";
	}
	
}
