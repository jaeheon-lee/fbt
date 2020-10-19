package com.biomans.fbt.domain;

public class Invite {
	private int inviteId;
	private TeamMember teamMember;
	private User user;
	private VoteMatch voteMatch;
	
	public Invite() {
		super();
	}

	public Invite(int inviteId, TeamMember teamMember, User user, VoteMatch voteMatch) {
		super();
		this.inviteId = inviteId;
		this.teamMember = teamMember;
		this.user = user;
		this.voteMatch = voteMatch;
	}

	public int getInviteId() {
		return inviteId;
	}

	public void setInviteId(int inviteId) {
		this.inviteId = inviteId;
	}

	public TeamMember getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(TeamMember teamMember) {
		this.teamMember = teamMember;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VoteMatch getVoteMatch() {
		return voteMatch;
	}

	public void setVoteMatch(VoteMatch voteMatch) {
		this.voteMatch = voteMatch;
	}

	@Override
	public String toString() {
		return "Invite [inviteId=" + inviteId + ", teamMember=" + teamMember + ", user=" + user + ", voteMatch="
				+ voteMatch + "]";
	}
}
