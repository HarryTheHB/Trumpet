package edu.yang.trumpet.dao;

import java.io.Serializable;

public class UserRelateStatus implements Serializable{
	private int idUser;
	private int idStatus;
	private boolean subscribe;
	private static final long serialVersionUID = 1L;
	public UserRelateStatus(){}
	public UserRelateStatus(int idUser, int idStatus, boolean subscribe) {
		super();
		this.idUser = idUser;
		this.idStatus = idStatus;
		this.subscribe = subscribe;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}
	public boolean isSubscribe() {
		return subscribe;
	}
	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}
	
	
}
