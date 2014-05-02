package edu.yang.trumpet.dao;

import java.io.Serializable;
import java.sql.Timestamp;


public class Notification  implements Serializable{
	private int idUser;
	private int idStatus;
	private Timestamp time;
	private static final long serialVersionUID = 1L;
	public Notification(){}
	
	public Notification(int idUser, int idStatus, Timestamp time,
			int applicationPK) {
		super();
		this.idUser = idUser;
		this.idStatus = idStatus;
		this.time = time;
	}

	public Notification(int idUser, int idStatus, Timestamp time) {
		super();
		this.idUser = idUser;
		this.idStatus = idStatus;
		this.time = time;
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
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
