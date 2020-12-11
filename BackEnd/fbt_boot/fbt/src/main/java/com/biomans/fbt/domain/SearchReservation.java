package com.biomans.fbt.domain;

public class SearchReservation {
	private int searchId;
	private Team teamTaker;
	private TeamMember teamMember;
	private int reservationStatus;
	
	public SearchReservation() {}

	public SearchReservation(int searchId, Team teamTaker, TeamMember teamMember, int reservationStatus) {
		super();
		this.searchId = searchId;
		this.teamTaker = teamTaker;
		this.teamMember = teamMember;
		this.reservationStatus = reservationStatus;
	}

	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
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
		return "SearchReservation [searchId=" + searchId + ", teamTaker=" + teamTaker + ", teamMember=" + teamMember
				+ ", reservationStatus=" + reservationStatus + "]";
	}

	
	
}
