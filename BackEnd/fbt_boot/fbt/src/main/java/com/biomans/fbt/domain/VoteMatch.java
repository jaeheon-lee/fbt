package com.biomans.fbt.domain;

public class VoteMatch {
	private int voteMatchId;
	private String contents;
	private String dueDate;
	private int voteStatus;
	private String voteRegDate;
	private String writer;
	private Team team;
	private MatchSchedule matchSchedule;
	
	public VoteMatch() {}
	
	public VoteMatch(int voteMatchId, String contents, String dueDate, int voteStatus, String voteRegDate,
			String writer, Team team, MatchSchedule matchSchedule) {
		super();
		this.voteMatchId = voteMatchId;
		this.contents = contents;
		this.dueDate = dueDate;
		this.voteStatus = voteStatus;
		this.voteRegDate = voteRegDate;
		this.writer = writer;
		this.team = team;
		this.matchSchedule = matchSchedule;
	}

	public int getVoteMatchId() {
		return voteMatchId;
	}

	public void setVoteMatchId(int voteMatchId) {
		this.voteMatchId = voteMatchId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public int getVoteStatus() {
		return voteStatus;
	}

	public void setVoteStatus(int voteStatus) {
		this.voteStatus = voteStatus;
	}

	public String getVoteRegDate() {
		return voteRegDate;
	}

	public void setVoteRegDate(String voteRegDate) {
		this.voteRegDate = voteRegDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public MatchSchedule getMatchSchedule() {
		return matchSchedule;
	}

	public void setMatchSchedule(MatchSchedule matchSchedule) {
		this.matchSchedule = matchSchedule;
	}

	@Override
	public String toString() {
		return "VoteMatch [voteMatchId=" + voteMatchId + ", contents=" + contents + ", dueDate=" + dueDate
				+ ", voteStatus=" + voteStatus + ", voteRegDate=" + voteRegDate + ", writer=" + writer + ", team="
				+ team + ", matchSchedule=" + matchSchedule + "]";
	}
}
