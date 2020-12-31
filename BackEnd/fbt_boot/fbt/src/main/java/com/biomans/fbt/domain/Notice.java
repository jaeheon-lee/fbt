package com.biomans.fbt.domain;

import java.util.HashMap;
import java.util.List;

public class Notice {
	private int noticeId;
	private String content;
	private String pageName;
	private String regDate;
	private int isRead;
	private Team giverTeam;
	private User giverUser;
	private Team takerTeam;
	private User takerUser;
	private List<User> takerUsers;
	private VoteMatch voteMatch;
	private HashMap<String, String> params;
	
	public Notice() {}

	public Notice(int noticeId, String content, String pageName, String regDate, int isRead, Team giverTeam,
			User giverUser, Team takerTeam, User takerUser, List<User> takerUsers, VoteMatch voteMatch,
			HashMap<String, String> params) {
		super();
		this.noticeId = noticeId;
		this.content = content;
		this.pageName = pageName;
		this.regDate = regDate;
		this.isRead = isRead;
		this.giverTeam = giverTeam;
		this.giverUser = giverUser;
		this.takerTeam = takerTeam;
		this.takerUser = takerUser;
		this.takerUsers = takerUsers;
		this.voteMatch = voteMatch;
		this.params = params;
	}

	public List<User> getTakerUsers() {
		return takerUsers;
	}

	public void setTakerUsers(List<User> takerUsers) {
		this.takerUsers = takerUsers;
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

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public Team getGiverTeam() {
		return giverTeam;
	}

	public void setGiverTeam(Team giverTeam) {
		this.giverTeam = giverTeam;
	}

	public User getGiverUser() {
		return giverUser;
	}

	public void setGiverUser(User giverUser) {
		this.giverUser = giverUser;
	}

	public Team getTakerTeam() {
		return takerTeam;
	}

	public void setTakerTeam(Team takerTeam) {
		this.takerTeam = takerTeam;
	}

	public User getTakerUser() {
		return takerUser;
	}

	public void setTakerUser(User takerUser) {
		this.takerUser = takerUser;
	}

	public VoteMatch getVoteMatch() {
		return voteMatch;
	}

	public void setVoteMatch(VoteMatch voteMatch) {
		this.voteMatch = voteMatch;
	}

	public HashMap<String, String> getParams() {
		return params;
	}

	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", content=" + content + ", pageName=" + pageName + ", regDate="
				+ regDate + ", isRead=" + isRead + ", giverTeam=" + giverTeam + ", giverUser=" + giverUser
				+ ", takerTeam=" + takerTeam + ", takerUser=" + takerUser + ", takerUsers=" + takerUsers
				+ ", voteMatch=" + voteMatch + ", params=" + params + "]";
	}
	
}
