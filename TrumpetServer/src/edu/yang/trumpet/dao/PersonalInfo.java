package edu.yang.trumpet.dao;

import edu.yang.trumpet.model.Profile;

public class PersonalInfo {
	private int idPersonalInfo;
	private String nickName;
	private int age;
	private String gender;
	private int visibility;
	private String description;
	
	public PersonalInfo(){}
	public PersonalInfo(int idPersonalInfo, String nickName, int age, String gender,
			int visibility, String description) {
		super();
		this.idPersonalInfo = idPersonalInfo;
		this.nickName = nickName;
		this.age = age;
		this.gender = gender;
		this.visibility = visibility;
		this.description = description;
	}
	public PersonalInfo(Profile profile) {
		this.nickName = profile.getNickName();
		this.age = profile.getAge();
		this.gender = profile.getGender();
		this.visibility = profile.getVisibility();
		this.description = profile.getSelfDiscription();
	}
	public int getIdPersonalInfo() {
		return idPersonalInfo;
	}
	public void setIdPersonalInfo(int idPersonalInfo) {
		this.idPersonalInfo = idPersonalInfo;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getVisibility() {
		return visibility;
	}
	public int isVisibility() {
		return visibility;
	}
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
