package com.biomans.fbt.domain;

import java.util.ArrayList;

public class Assignment {
	private int assignmentId;
	private Team teamGiver;
	private MatchSchedule matchSchedule;
	private TeamMember teamMember;
	private String regDate;
	private String dueDate;
	private String content;
	private int minNumber;
	private int waitingTime;
	private ArrayList<AssignmentReservation> assignmentReservations;
	
	public Assignment() {}

	public Assignment(int assignmentId, Team teamGiver, MatchSchedule matchSchedule, TeamMember teamMember,
			String regDate, String dueDate, String content, int minNumber, int waitingTime,
			ArrayList<AssignmentReservation> assignmentReservations) {
		super();
		this.assignmentId = assignmentId;
		this.teamGiver = teamGiver;
		this.matchSchedule = matchSchedule;
		this.teamMember = teamMember;
		this.regDate = regDate;
		this.dueDate = dueDate;
		this.content = content;
		this.minNumber = minNumber;
		this.waitingTime = waitingTime;
		this.assignmentReservations = assignmentReservations;
	}

	public int getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
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

	public int getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public ArrayList<AssignmentReservation> getAssignmentReservations() {
		return assignmentReservations;
	}

	public void setAssignmentReservations(ArrayList<AssignmentReservation> assignmentReservations) {
		this.assignmentReservations = assignmentReservations;
	}

	@Override
	public String toString() {
		return "Assignment [assignmentId=" + assignmentId + ", teamGiver=" + teamGiver + ", matchSchedule="
				+ matchSchedule + ", teamMember=" + teamMember + ", regDate=" + regDate + ", dueDate=" + dueDate
				+ ", content=" + content + ", minNumber=" + minNumber + ", waitingTime=" + waitingTime
				+ ", assignmentReservations=" + assignmentReservations + "]";
	}

	
	
}
