package com.biomans.fbt.util;

public class Attendance {
	private int searchId;
	private int assignmentId;
	private int minNumber;
	private int totalMember;
	private int totalFriend;
	private int reservationStatus;
	
	public Attendance() {}

	public Attendance(int searchId, int assignmentId, int minNumber, int totalMember, int totalFriend,
			int reservationStatus) {
		super();
		this.searchId = searchId;
		this.assignmentId = assignmentId;
		this.minNumber = minNumber;
		this.totalMember = totalMember;
		this.totalFriend = totalFriend;
		this.reservationStatus = reservationStatus;
	}

	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	public int getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	public int getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}

	public int getTotalMember() {
		return totalMember;
	}

	public void setTotalMember(int totalMember) {
		this.totalMember = totalMember;
	}

	public int getTotalFriend() {
		return totalFriend;
	}

	public void setTotalFriend(int totalFriend) {
		this.totalFriend = totalFriend;
	}

	public int getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(int reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	@Override
	public String toString() {
		return "Attendance [searchId=" + searchId + ", assignmentId=" + assignmentId + ", minNumber=" + minNumber
				+ ", totalMember=" + totalMember + ", totalFriend=" + totalFriend + ", reservationStatus="
				+ reservationStatus + "]";
	}

}
