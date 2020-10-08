package com.honeycom.fbt.dto;

public class VoteResult {
	private int voteId;
	private int teamMemberId;
	private int attendance;
	
	public VoteResult() {}

	public VoteResult(int voteId, int teamMemberId, int attendance) {
		super();
		this.voteId = voteId;
		this.teamMemberId = teamMemberId;
		this.attendance = attendance;
	}

	public int getVoteId() {
		return voteId;
	}

	public void setVoteId(int voteId) {
		this.voteId = voteId;
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

	@Override
	public String toString() {
		return "VoteResult [voteId=" + voteId + ", teamMemberId=" + teamMemberId + ", attendance=" + attendance + "]";
	}
	
	

}
