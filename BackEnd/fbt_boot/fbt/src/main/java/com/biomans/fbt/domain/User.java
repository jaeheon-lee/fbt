package com.biomans.fbt.domain;

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
	private String born_date;
	
	public User() {}

	public User(String email, String pass, String name, String phoneNum, String userRegDate, String recentLogin,
			String mainFoot, String position, String apiKey, int height, int weight, int wasPro, int gender,
			String born_date) {
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
		this.born_date = born_date;
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

	public String getBorn_date() {
		return born_date;
	}

	public void setBorn_date(String born_date) {
		this.born_date = born_date;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", pass=" + pass + ", name=" + name + ", phoneNum=" + phoneNum
				+ ", userRegDate=" + userRegDate + ", recentLogin=" + recentLogin + ", mainFoot=" + mainFoot
				+ ", position=" + position + ", apiKey=" + apiKey + ", height=" + height + ", weight=" + weight
				+ ", wasPro=" + wasPro + ", gender=" + gender + ", born_date=" + born_date + "]";
	}
	
	
}
