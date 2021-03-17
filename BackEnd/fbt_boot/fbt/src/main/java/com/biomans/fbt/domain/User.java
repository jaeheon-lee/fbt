package com.biomans.fbt.domain;

import java.util.List;

public class User {
	private String email;
	private String pass;
	private String name;
	private String phoneNum;
	private String userRegDate;
	private String recentLogin;
	private String mainFoot;
	private String position;
	private String apiKey;
	private int height;
	private int weight;
	private int wasPro; //0=비선출, 1=선출
	private int gender; //0=남자, 1= 여자
	private String bornDate;
	private String area;
	private String image;
	private int apiType;
	private double empAbility;
	private double empManner;
	private int totalRatedNum;
	private int totalEmpAttend;
	private ApiCategory apiCategory;
	private List<TeamMember> teamMembers;
	private List<EmpScore> empScores;
	
	public User() {}

	public User(String email, String pass, String name, String phoneNum, String userRegDate, String recentLogin,
			String mainFoot, String position, String apiKey, int height, int weight, int wasPro, int gender,
			String bornDate, String area, String image, int apiType, double empAbility, double empManner,
			int totalRatedNum, int totalEmpAttend, ApiCategory apiCategory, List<TeamMember> teamMembers,
			List<EmpScore> empScores) {
		super();
		this.email = email;
		this.pass = pass;
		this.name = name;
		this.phoneNum = phoneNum;
		this.userRegDate = userRegDate;
		this.recentLogin = recentLogin;
		this.mainFoot = mainFoot;
		this.position = position;
		this.apiKey = apiKey;
		this.height = height;
		this.weight = weight;
		this.wasPro = wasPro;
		this.gender = gender;
		this.bornDate = bornDate;
		this.area = area;
		this.image = image;
		this.apiType = apiType;
		this.empAbility = empAbility;
		this.empManner = empManner;
		this.totalRatedNum = totalRatedNum;
		this.totalEmpAttend = totalEmpAttend;
		this.apiCategory = apiCategory;
		this.teamMembers = teamMembers;
		this.empScores = empScores;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getUserRegDate() {
		return userRegDate;
	}

	public void setUserRegDate(String userRegDate) {
		this.userRegDate = userRegDate;
	}

	public String getRecentLogin() {
		return recentLogin;
	}

	public void setRecentLogin(String recentLogin) {
		this.recentLogin = recentLogin;
	}

	public String getMainFoot() {
		return mainFoot;
	}

	public void setMainFoot(String mainFoot) {
		this.mainFoot = mainFoot;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWasPro() {
		return wasPro;
	}

	public void setWasPro(int wasPro) {
		this.wasPro = wasPro;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBornDate() {
		return bornDate;
	}

	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getApiType() {
		return apiType;
	}

	public void setApiType(int apiType) {
		this.apiType = apiType;
	}

	public double getEmpAbility() {
		return empAbility;
	}

	public void setEmpAbility(double empAbility) {
		this.empAbility = empAbility;
	}

	public double getEmpManner() {
		return empManner;
	}

	public void setEmpManner(double empManner) {
		this.empManner = empManner;
	}

	public int getTotalRatedNum() {
		return totalRatedNum;
	}

	public void setTotalRatedNum(int totalRatedNum) {
		this.totalRatedNum = totalRatedNum;
	}

	public int getTotalEmpAttend() {
		return totalEmpAttend;
	}

	public void setTotalEmpAttend(int totalEmpAttend) {
		this.totalEmpAttend = totalEmpAttend;
	}

	public ApiCategory getApiCategory() {
		return apiCategory;
	}

	public void setApiCategory(ApiCategory apiCategory) {
		this.apiCategory = apiCategory;
	}

	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public List<EmpScore> getEmpScores() {
		return empScores;
	}

	public void setEmpScores(List<EmpScore> empScores) {
		this.empScores = empScores;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", pass=" + pass + ", name=" + name + ", phoneNum=" + phoneNum
				+ ", userRegDate=" + userRegDate + ", recentLogin=" + recentLogin + ", mainFoot=" + mainFoot
				+ ", position=" + position + ", apiKey=" + apiKey + ", height=" + height + ", weight=" + weight
				+ ", wasPro=" + wasPro + ", gender=" + gender + ", bornDate=" + bornDate + ", area=" + area + ", image="
				+ image + ", apiType=" + apiType + ", empAbility=" + empAbility + ", empManner=" + empManner
				+ ", totalRatedNum=" + totalRatedNum + ", totalEmpAttend=" + totalEmpAttend + ", apiCategory="
				+ apiCategory + ", teamMembers=" + teamMembers + ", empScores=" + empScores + "]";
	}

	
}
