package com.honeycom.fbt.dto;

public class Attendant {
	private int attendantId;
	private int matchScheduleId;
	private int teamMemberId;
	private int attendance;
	private MatchReview matchReview;
	
	
	public Attendant() {}

	public Attendant(int matchScheduleId, int teamMemberId, int attendance, MatchReview matchReview) {
		super();
		this.matchScheduleId = matchScheduleId;
		this.teamMemberId = teamMemberId;
		this.attendance = attendance;
		this.matchReview = matchReview;
	}


	public Attendant(int attendantId, int matchScheduleId, int teamMemberId, int attendance, MatchReview matchReview) {
		super();
		this.attendantId = attendantId;
		this.matchScheduleId = matchScheduleId;
		this.teamMemberId = teamMemberId;
		this.attendance = attendance;
		this.matchReview = matchReview;
	}


	public int getAttendantId() {
		return attendantId;
	}


	public void setAttendantId(int attendantId) {
		this.attendantId = attendantId;
	}


	public int getMatchScheduleId() {
		return matchScheduleId;
	}


	public void setMatchScheduleId(int matchScheduleId) {
		this.matchScheduleId = matchScheduleId;
	}


	public int getTeamMemberId() {
		return teamMemberId;
	}


	public void setTeamMemberId(int teamMemberId) {
		this.teamMemberId = teamMemberId;
	}


	public int getAttendance() {
		return attendance;
	}


	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}


	public MatchReview getMatchReview() {
		return matchReview;
	}


	public void setMatchReview(MatchReview matchReview) {
		this.matchReview = matchReview;
	}


	@Override
	public String toString() {
		return "Attendant [attendantId=" + attendantId + ", matchScheduleId=" + matchScheduleId + ", teamMemberId="
				+ teamMemberId + ", attendance=" + attendance + ", matchReview=" + matchReview + "]";
	}

}
