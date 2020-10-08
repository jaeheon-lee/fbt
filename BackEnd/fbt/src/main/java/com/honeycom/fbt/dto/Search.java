package com.honeycom.fbt.dto;

public class Search {
	private int searchId;
	private int teamIdGiver;
	private int teamIdTaker;
	private int teamMemberIdGiver;
	private int teamMemberIdTaker;
	private int searchStatus;
	private int matchInfoId;
	public Search() {
		super();
	}

	public Search(int searchId, int matchInfoId, int teamIdGiver, int teamIdTaker, int teamMemberIdGiver,
			int teamMemberIdTaker, int searchStatus) {
		super();
		this.searchId = searchId;
		this.matchInfoId = matchInfoId;
		this.teamIdGiver = teamIdGiver;
		this.teamIdTaker = teamIdTaker;
		this.teamMemberIdGiver = teamMemberIdGiver;
		this.teamMemberIdTaker = teamMemberIdTaker;
		this.searchStatus = searchStatus;
	}
	
	

	public Search(int matchInfoId, int teamIdGiver, int teamIdTaker, int teamMemberIdGiver, int teamMemberIdTaker,
			int searchStatus) {
		super();
		this.matchInfoId = matchInfoId;
		this.teamIdGiver = teamIdGiver;
		this.teamIdTaker = teamIdTaker;
		this.teamMemberIdGiver = teamMemberIdGiver;
		this.teamMemberIdTaker = teamMemberIdTaker;
		this.searchStatus = searchStatus;
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

	@Override
	public String toString() {
		return "Search [searchId=" + searchId + ", matchInfoId=" + matchInfoId + ", teamIdGiver=" + teamIdGiver
				+ ", teamIdTaker=" + teamIdTaker + ", teamMemberIdGiver=" + teamMemberIdGiver + ", teamMemberIdTaker="
				+ teamMemberIdTaker + ", searchStatus=" + searchStatus + "]";
	}
}
