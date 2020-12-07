package com.biomans.fbt.util;

import java.util.HashMap;
import java.util.List;

public class Session {
	private String email;
	private String name;
	private String image;
	private List<HashMap<String, String>> teams;
	
	public Session() {}

	public Session(String email, String name, String image, List<HashMap<String, String>> teams) {
		super();
		this.email = email;
		this.name = name;
		this.image = image;
		this.teams = teams;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<HashMap<String, String>> getTeams() {
		return teams;
	}

	public void setTeams(List<HashMap<String, String>> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "Session [email=" + email + ", name=" + name + ", image=" + image + ", teams=" + teams + "]";
	}

	
	
}
