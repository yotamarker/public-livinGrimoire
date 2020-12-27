package com.yotamarker.lgkotlin1;

public class Person {
	private String name = "";
	private Boolean active = false;
	private String phone = "";
	private String skill = "";
	private String profession = "";
	private String jutsu = "hadouken";
	// location
	private String email = "";
	private String id = "";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getJutsu() {
		return jutsu;
	}

	public void setJutsu(String jutsu) {
		this.jutsu = jutsu;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void deleteFriend() {
		name = "";
		active = false;
		phone = "";
		skill = "";
		profession = "";
		jutsu = "hadouken";
		// location
		email = "";
		id = "";
	}
}
