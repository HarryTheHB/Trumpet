package edu.yang.trumpet.dao;



import java.sql.Timestamp;
import java.util.Date;

import edu.yang.trumpet.model.StatusMessage;

public class Status {
	private int idStatus;
	private int idUser;
	private int idLocation;
	private String content;
	private Timestamp time;
	private int coverRange;
	
	public Status(){}
	public Status(int idStatus, int idUser, int idLocation, String content,
			Timestamp time, int coverRange) {
		super();
		this.idStatus = idStatus;
		this.idUser = idUser;
		this.idLocation = idLocation;
		this.content = content;
		this.time = time;
		this.coverRange = coverRange;
	}
	
	public Status(int idUser, int idLocation, String content,
			Timestamp time, int coverRange) {
		super();
		this.idUser = idUser;
		this.idLocation = idLocation;
		this.content = content;
		this.time = time;
		this.coverRange = coverRange;
	}
	
	public int getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdLocation() {
		return idLocation;
	}
	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getCoverRange() {
		return coverRange;
	}
	public void setCoverRange(int coverRange) {
		this.coverRange = coverRange;
	}
	
	
}
