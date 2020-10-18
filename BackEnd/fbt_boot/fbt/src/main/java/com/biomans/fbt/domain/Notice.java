package com.biomans.fbt.domain;

import java.util.List;

public class Notice {
	private int noticeId;
	private String content;
	private String date;
	private Team team;
	private List<Integer> teamMembers;
	
	public Notice() {
		super();
	}

	public Notice(int noticeId, String content, String date, Team team, List<Integer> teamMembers) {
		super();
		this.noticeId = noticeId;
		this.content = content;
		this.date = date;
		this.team = team;
		this.teamMembers = teamMembers;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<Integer> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<Integer> teamMembers) {
		this.teamMembers = teamMembers;
	}

	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", content=" + content + ", date=" + date + ", team=" + team
				+ ", teamMembers=" + teamMembers + "]";
	}

	
}
