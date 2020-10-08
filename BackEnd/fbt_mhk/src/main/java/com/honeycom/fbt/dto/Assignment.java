package com.honeycom.fbt.dto;

import java.util.List;

public class Assignment {
	private int assignmentId;
	private int teamIdGiver;
	private int teamIdTaker;
	private int teamMemberIdGiver;
	private int teamMemberIdTaker;
	private int assignmentStatus;
	private int matchInfoId;
	
	private List<AssignmentReservation> assignmentResults;
	
	public Assignment() {
		super();
	}

	public Assignment(int assignmentId, int teamIdGiver, int teamIdTaker, int teamMemberIdGiver, int teamMemberIdTaker,
			int assignmentStatus, int matchInfoId, List<AssignmentReservation> assignmentResults) {
		super();
		this.assignmentId = assignmentId;
		this.teamIdGiver = teamIdGiver;
		this.teamIdTaker = teamIdTaker;
		this.teamMemberIdGiver = teamMemberIdGiver;
		this.teamMemberIdTaker = teamMemberIdTaker;
		this.assignmentStatus = assignmentStatus;
		this.matchInfoId = matchInfoId;
		this.assignmentResults = assignmentResults;
	}

	public int getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
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

	public int getAssignmentStatus() {
		return assignmentStatus;
	}

	public void setAssignmentStatus(int assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}

	public int getMatchInfoId() {
		return matchInfoId;
	}

	public void setMatchInfoId(int matchInfoId) {
		this.matchInfoId = matchInfoId;
	}

	public List<AssignmentReservation> getAssignmentResults() {
		return assignmentResults;
	}

	public void setAssignmentResults(List<AssignmentReservation> assignmentResults) {
		this.assignmentResults = assignmentResults;
	}

	@Override
	public String toString() {
		return "Assignment [assignmentId=" + assignmentId + ", teamIdGiver=" + teamIdGiver + ", teamIdTaker="
				+ teamIdTaker + ", teamMemberIdGiver=" + teamMemberIdGiver + ", teamMemberIdTaker=" + teamMemberIdTaker
				+ ", assignmentStatus=" + assignmentStatus + ", matchInfoId=" + matchInfoId + "]";
	}

			
}
