package com.biomans.fbt.util;

public class TeamFilter {
	private String teamName;
	private String siDo;
	private String siGun;
	private String area;
	private int order;
	
	public TeamFilter() {}

	public TeamFilter(String teamName, String siDo, String siGun, String area, int order) {
		super();
		this.teamName = teamName;
		this.siDo = siDo;
		this.siGun = siGun;
		this.area = area;
		this.order = order;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSiDo() {
		return siDo;
	}

	public void setSiDo(String siDo) {
		this.siDo = siDo;
	}

	public String getSiGun() {
		return siGun;
	}

	public void setSiGun(String siGun) {
		this.siGun = siGun;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "TeamFilter [teamName=" + teamName + ", siDo=" + siDo + ", siGun=" + siGun + ", area=" + area
				+ ", order=" + order + "]";
	}
	
}
