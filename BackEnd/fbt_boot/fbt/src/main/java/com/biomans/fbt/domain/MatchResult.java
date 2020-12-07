package com.biomans.fbt.domain;

public class MatchResult {
	private int isSet;
	private int homeResult;
	private int awayResult;
	private int homeScore;
	private int awayScore;
	private MatchSchedule matchSchedule;
	
	public MatchResult() {}

	public MatchResult(int isSet, int homeResult, int awayResult, int homeScore, int awayScore,
			MatchSchedule matchSchedule) {
		super();
		this.isSet = isSet;
		this.homeResult = homeResult;
		this.awayResult = awayResult;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.matchSchedule = matchSchedule;
	}

	public int getIsSet() {
		return isSet;
	}

	public void setIsSet(int isSet) {
		this.isSet = isSet;
	}

	public int getHomeResult() {
		return homeResult;
	}

	public void setHomeResult(int homeResult) {
		this.homeResult = homeResult;
	}

	public int getAwayResult() {
		return awayResult;
	}

	public void setAwayResult(int awayResult) {
		this.awayResult = awayResult;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}

	public MatchSchedule getMatchSchedule() {
		return matchSchedule;
	}

	public void setMatchSchedule(MatchSchedule matchSchedule) {
		this.matchSchedule = matchSchedule;
	}

	@Override
	public String toString() {
		return "MatchResult [isSet=" + isSet + ", homeResult=" + homeResult + ", awayResult=" + awayResult
				+ ", homeScore=" + homeScore + ", awayScore=" + awayScore + ", matchSchedule=" + matchSchedule + "]";
	}

	
}
