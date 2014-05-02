package edu.yang.trumpet.dmo;

import java.util.List;

import edu.yang.trumpet.imp.HibernateUtility;
import edu.yang.trumpet.model.CommentMessage;
import edu.yang.trumpet.model.ResultInfo;
import edu.yang.trumpet.model.StatusMessage;

/**
 * include data and operation for a status
 * @author daiyang
 *
 */
public class UserStatus {
	
	private StatusMessage statusMsg;

	public UserStatus(StatusMessage statusMsg) {
		super();
		this.statusMsg = statusMsg;
	}
	
	public UserStatus(long sid){
		searchStatus(sid);
	}
	
	public UserStatus(){
		
	};
	
	public StatusMessage getStatusMsg() {
		return statusMsg;
	}
	
	/**
	 * insert a status into database
	 * @param uid user's id
	 * @return
	 */
	public ResultInfo insertStatus(long uid) {
		return HibernateUtility.insertStatus(statusMsg, (int)uid);
	}
	
	/**
	 * search for a status
	 * @param sid status' id
	 */
	public void searchStatus(long sid){
		//jdbc
	}
	
	public ResultInfo dropStatus(long sid) {
		//jdbc
		return null;
	}
	
	public ResultInfo insertToNotification(long sid) {
		//jdbc
		return null;
	}
	
	public ResultInfo dropFromNotification(long sid) {
		//jdbc
		return null;
	}
	
}
