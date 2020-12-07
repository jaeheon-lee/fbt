package com.biomans.fbt.util;

public class Attendance {
	private int searchId;
	private int minNumber;
	private int totalMember;
	private int totalFriend;
	private int reservationStatus;
	
	public Attendance() {}
	public Attendance(int searchId, int minNumber, int totalMember, int totalFriend, int reservationStatus) {
		super();
		this.searchId = searchId;
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
		return "Attendance [searchId=" + searchId + ", minNumber=" + minNumber + ", totalMember=" + totalMember
				+ ", totalFriend=" + totalFriend + ", reservationStatus=" + reservationStatus + "]";
	}
}
