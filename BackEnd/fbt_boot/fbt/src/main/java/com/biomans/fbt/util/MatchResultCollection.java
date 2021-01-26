package com.biomans.fbt.util;

import java.util.List;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.Entry;
import com.biomans.fbt.domain.MatchResult;
import com.biomans.fbt.domain.TeamScore;

public class MatchResultCollection {
	private List<Entry> entries;
	private List<EmpScore> empScores;
	private TeamScore teamScore;
	private MatchResult matchResult;
	private Boolean isHomeTeam;
	
	public MatchResultCollection() {}

	public MatchResultCollection(List<Entry> entries, List<EmpScore> empScores, TeamScore teamScore,
			MatchResult matchResult, Boolean isHomeTeam) {
		super();
		this.entries = entries;
		this.empScores = empScores;
		this.teamScore = teamScore;
		this.matchResult = matchResult;
		this.isHomeTeam = isHomeTeam;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public List<EmpScore> getEmpScores() {
		return empScores;
	}

	public void setEmpScores(List<EmpScore> empScores) {
		this.empScores = empScores;
	}

	public TeamScore getTeamScore() {
		return teamScore;
	}

	public void setTeamScore(TeamScore teamScore) {
		this.teamScore = teamScore;
	}

	public MatchResult getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(MatchResult matchResult) {
		this.matchResult = matchResult;
	}

	public Boolean getIsHomeTeam() {
		return isHomeTeam;
	}

	public void setIsHomeTeam(Boolean isHomeTeam) {
		this.isHomeTeam = isHomeTeam;
	}

	@Override
	public String toString() {
		return "MatchResultCollection [entries=" + entries + ", empScores=" + empScores + ", teamScore=" + teamScore
				+ ", matchResult=" + matchResult + ", isHomeTeam=" + isHomeTeam + "]";
	}

	
}
