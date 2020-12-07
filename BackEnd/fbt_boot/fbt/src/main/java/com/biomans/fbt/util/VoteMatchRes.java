package com.biomans.fbt.util;

import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.domain.VoteMatch;

public class VoteMatchRes {
	private VoteMatch voteMatch;
	private SearchReservation searchReservation;
	
	public VoteMatchRes() {}
	public VoteMatchRes(VoteMatch voteMatch, SearchReservation searchReservation) {
		super();
		this.voteMatch = voteMatch;
		this.searchReservation = searchReservation;
	}
	public VoteMatch getVoteMatch() {
		return voteMatch;
	}
	public void setVoteMatch(VoteMatch voteMatch) {
		this.voteMatch = voteMatch;
	}
	public SearchReservation getSearchReservation() {
		return searchReservation;
	}
	public void setSearchReservation(SearchReservation searchReservation) {
		this.searchReservation = searchReservation;
	}
	@Override
	public String toString() {
		return "voteMatchRes [voteMatch=" + voteMatch + ", searchReservation=" + searchReservation + "]";
	}
	
	
}
