package com.biomans.fbt.util;

import java.util.List;

import com.biomans.fbt.domain.EmpScore;
import com.biomans.fbt.domain.Entry;
import com.biomans.fbt.domain.MatchResult;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.TeamScore;
import com.biomans.fbt.domain.User;

public class MatchResultCollection {
	private List<Entry> entries;
	private List<EmpScore> empScores;
	private TeamScore teamScore;
	private MatchResult matchResult;
	
	public MatchResultCollection() {}
	public MatchResultCollection(List<Entry> entries, List<EmpScore> empScores, TeamScore teamScore,
			MatchResult matchResult) {
		super();
		this.entries = entries;
		this.empScores = empScores;
		this.teamScore = teamScore;
		this.matchResult = matchResult;
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
	
	@Override
	public String toString() {
		return "MatchResultCollection [entries=" + entries + ", empScores=" + empScores + ", teamScore=" + teamScore
				+ ", matchResult=" + matchResult + "]";
	}
}
