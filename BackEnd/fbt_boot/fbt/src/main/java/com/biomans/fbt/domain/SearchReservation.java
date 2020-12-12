package com.biomans.fbt.domain;

public class SearchReservation {
	private int searchResId;
	private int searchId;
	private Team teamTaker;
	private TeamMember teamMember;
	private int reservationStatus;
	
	public SearchReservation() {}

	public SearchReservation(int searchResId, int searchId, Team teamTaker, TeamMember teamMember,
			int reservationStatus) {
		super();
		this.searchResId = searchResId;
		this.searchId = searchId;
		this.teamTaker = teamTaker;
		this.teamMember = teamMember;
		this.reservationStatus = reservationStatus;
	}

	public int getSearchResId() {
		return searchResId;
	}

	public void setSearchResId(int searchResId) {
		this.searchResId = searchResId;
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
		return "SearchReservation [searchResId=" + searchResId + ", searchId=" + searchId + ", teamTaker=" + teamTaker
				+ ", teamMember=" + teamMember + ", reservationStatus=" + reservationStatus + "]";
	}

	
}
