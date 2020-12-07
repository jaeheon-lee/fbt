package com.biomans.fbt.domain;

public class SearchReservation {
	private int searchId;
	private Team teamTaker;
	private String writer;
	private int reservationStatus;
	
	public SearchReservation() {}

	public SearchReservation(int searchId, Team teamTaker, String writer, int reservationStatus) {
		super();
		this.searchId = searchId;
		this.teamTaker = teamTaker;
		this.writer = writer;
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
		return "SearchReservation [searchId=" + searchId + ", teamTaker=" + teamTaker + ", writer=" + writer
				+ ", reservationStatus=" + reservationStatus + "]";
	}
	
	
}
