package com.biomans.fbt.domain;

import java.util.ArrayList;

public class Search {
	private int searchId;
	private Team teamGiver;
	private MatchSchedule matchSchedule;
	private TeamMember teamMember;
	private String searchRegDate;
	private int waitingTime;
	private int minNumber;
	private String dueDate;
	private String content;
	private ArrayList<SearchReservation> searchReservations;
	
	public Search() {}

	public Search(int searchId, Team teamGiver, MatchSchedule matchSchedule, TeamMember teamMember,
			String searchRegDate, int waitingTime, int minNumber, String dueDate, String content,
			ArrayList<SearchReservation> searchReservations) {
		super();
		this.searchId = searchId;
		this.teamGiver = teamGiver;
		this.matchSchedule = matchSchedule;
		this.teamMember = teamMember;
		this.searchRegDate = searchRegDate;
		this.waitingTime = waitingTime;
		this.minNumber = minNumber;
		this.dueDate = dueDate;
		this.content = content;
		this.searchReservations = searchReservations;
	}

	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	public Team getTeamGiver() {
		return teamGiver;
	}

	public void setTeamGiver(Team teamGiver) {
		this.teamGiver = teamGiver;
	}

	public MatchSchedule getMatchSchedule() {
		return matchSchedule;
	}

	public void setMatchSchedule(MatchSchedule matchSchedule) {
		this.matchSchedule = matchSchedule;
	}

	public TeamMember getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(TeamMember teamMember) {
		this.teamMember = teamMember;
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

	public int getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<SearchReservation> getSearchReservations() {
		return searchReservations;
	}

	public void setSearchReservations(ArrayList<SearchReservation> searchReservations) {
		this.searchReservations = searchReservations;
	}

	@Override
	public String toString() {
		return "Search [searchId=" + searchId + ", teamGiver=" + teamGiver + ", matchSchedule=" + matchSchedule
				+ ", teamMember=" + teamMember + ", searchRegDate=" + searchRegDate + ", waitingTime=" + waitingTime
				+ ", minNumber=" + minNumber + ", dueDate=" + dueDate + ", content=" + content + ", searchReservations="
				+ searchReservations + "]";
	}

	
	
}
