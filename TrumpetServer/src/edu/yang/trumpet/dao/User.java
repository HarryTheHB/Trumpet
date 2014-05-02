package edu.yang.trumpet.dao;

import edu.yang.trumpet.model.LogInfo;

public class User {
	private int idUser;
	private int idPersonalInfo;
	private String email;
	private String password;
	
	public User(){}
	public User(int idUser, int idPersonalInfo, String email, String password) {
		super();
		this.idUser = idUser;
		this.idPersonalInfo = idPersonalInfo;
		this.email = email;
		this.password = password;
	}
	public User(Integer pid, LogInfo logInfo) {
		this.idPersonalInfo = pid;
		this.email = logInfo.getEmail();
		this.password = logInfo.getPassword();
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdPersonalInfo() {
		return idPersonalInfo;
	}
	public void setIdPersonalInfo(int idPersonalInfo) {
		this.idPersonalInfo = idPersonalInfo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
