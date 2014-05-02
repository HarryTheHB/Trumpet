package edu.yang.trumpet.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class Comment {
	private int idComment;
	private int idUser;
	private String content;
	private int idStatus;
	private Timestamp time;
	
	public Comment(){}
	public Comment(int idComment, int idUser, String content, int idStatus,
			Timestamp time) {
		super();
		this.idComment = idComment;
		this.idUser = idUser;
		this.content = content;
		this.idStatus = idStatus;
		this.time = time;
	}
	public Comment(int idUser, String content, int idStatus,
			Timestamp time) {
		super();
		this.idUser = idUser;
		this.content = content;
		this.idStatus = idStatus;
		this.time = time;
	}
	public int getIdComment() {
		return idComment;
	}
	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
