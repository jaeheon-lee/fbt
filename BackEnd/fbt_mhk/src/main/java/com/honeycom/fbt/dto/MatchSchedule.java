package com.honeycom.fbt.dto;

import java.util.List;

public class MatchSchedule {
	private int matchScheduleId;
	private int homeTeamId;
	private int matchInfoId;
	private int awayTeamId;
	
	private List<Attendant> attendants;
	
	public MatchSchedule() {}

	public MatchSchedule(int homeTeamId, int matchInfoId, int awayTeamId, List<Attendant> attendants) {
		super();
		this.homeTeamId = homeTeamId;
		this.matchInfoId = matchInfoId;
		this.awayTeamId = awayTeamId;
		this.attendants = attendants;
	}

	public MatchSchedule(int matchScheduleId, int homeTeamId, int matchInfoId, int awayTeamId,
			List<Attendant> attendants) {
		super();
		this.matchScheduleId = matchScheduleId;
		this.homeTeamId = homeTeamId;
		this.matchInfoId = matchInfoId;
		this.awayTeamId = awayTeamId;
		this.attendants = attendants;
	}

	public int getMatchScheduleId() {
		return matchScheduleId;
	}

	public void setMatchScheduleId(int matchScheduleId) {
		this.matchScheduleId = matchScheduleId;
	}

	public int getHomeTeamId() {
		return homeTeamId;
	}

	public void setHomeTeamId(int homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public int getMatchInfoId() {
		return matchInfoId;
	}

	public void setMatchInfoId(int matchInfoId) {
		this.matchInfoId = matchInfoId;
	}

	public int getAwayTeamId() {
		return awayTeamId;
	}

	public void setAwayTeamId(int awayTeamId) {
		this.awayTeamId = awayTeamId;
	}

	public List<Attendant> getAttendants() {
		return attendants;
	}

	public void setAttendants(List<Attendant> attendants) {
		this.attendants = attendants;
	}

	@Override
	public String toString() {
		return "MatchSchedule [matchScheduleId=" + matchScheduleId + ", homeTeamId=" + homeTeamId + ", matchInfoId="
				+ matchInfoId + ", awayTeamId=" + awayTeamId + ", attendants=" + attendants + "]";
	}
	

}
