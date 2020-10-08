package com.honeycom.fbt.dto;

import java.util.List;

public class Search {
	private int searchId;
	private int teamIdGiver;
	private int teamIdTaker;
	private int teamMemberIdGiver;
	private int teamMemberIdTaker;
	private int searchStatus;
	private int matchInfoId;
	private String searchRegDate;
	private int waitingTime;
	private List<SearchReservation> searchReservations;
	
	public Search() {
		super();
	}

	public Search(int searchId, int teamIdGiver, int teamIdTaker, int teamMemberIdGiver, int teamMemberIdTaker,
			int searchStatus, int matchInfoId, String searchRegDate, int waitingTime,
			List<SearchReservation> searchReservations) {
		super();
		this.searchId = searchId;
		this.teamIdGiver = teamIdGiver;
		this.teamIdTaker = teamIdTaker;
		this.teamMemberIdGiver = teamMemberIdGiver;
		this.teamMemberIdTaker = teamMemberIdTaker;
		this.searchStatus = searchStatus;
		this.matchInfoId = matchInfoId;
		this.searchRegDate = searchRegDate;
		this.waitingTime = waitingTime;
		this.searchReservations = searchReservations;
	}

	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	public int getTeamIdGiver() {
		return teamIdGiver;
	}

	public void setTeamIdGiver(int teamIdGiver) {
		this.teamIdGiver = teamIdGiver;
	}

	public int getTeamIdTaker() {
		return teamIdTaker;
	}

	public void setTeamIdTaker(int teamIdTaker) {
		this.teamIdTaker = teamIdTaker;
	}

	public int getTeamMemberIdGiver() {
		return teamMemberIdGiver;
	}

	public void setTeamMemberIdGiver(int teamMemberIdGiver) {
		this.teamMemberIdGiver = teamMemberIdGiver;
	}

	public int getTeamMemberIdTaker() {
		return teamMemberIdTaker;
	}

	public void setTeamMemberIdTaker(int teamMemberIdTaker) {
		this.teamMemberIdTaker = teamMemberIdTaker;
	}

	public int getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(int searchStatus) {
		this.searchStatus = searchStatus;
	}

	public int getMatchInfoId() {
		return matchInfoId;
	}

	public void setMatchInfoId(int matchInfoId) {
		this.matchInfoId = matchInfoId;
	}

	public String getSearchRegDate() {
		return searchRegDate;
	}

	public void setSearchRegDate(String searchRegDate) {
		this.searchRegDate = searchRegDate;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public List<SearchReservation> getSearchReservations() {
		return searchReservations;
	}

	public void setSearchReservations(List<SearchReservation> searchReservations) {
		this.searchReservations = searchReservations;
	}

	@Override
	public String toString() {
		return "Search [searchId=" + searchId + ", teamIdGiver=" + teamIdGiver + ", teamIdTaker=" + teamIdTaker
				+ ", teamMemberIdGiver=" + teamMemberIdGiver + ", teamMemberIdTaker=" + teamMemberIdTaker
				+ ", searchStatus=" + searchStatus + ", matchInfoId=" + matchInfoId + ", searchRegDate=" + searchRegDate
				+ ", waitingTime=" + waitingTime + ", searchReservations=" + searchReservations + "]";
	}	

}
