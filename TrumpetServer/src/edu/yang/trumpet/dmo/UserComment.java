package edu.yang.trumpet.dmo;

import edu.yang.trumpet.imp.HibernateUtility;
import edu.yang.trumpet.model.CommentMessage;
import edu.yang.trumpet.model.ResultInfo;

/**
 * include the data and relate operation for comment
 * @author daiyang
 *
 */
public class UserComment {
	private CommentMessage comment;

	public UserComment(CommentMessage comment) {
		super();
		this.comment = comment;
	}
	
	/**
	 * insert a new comment for a status
	 * @return
	 */
	public ResultInfo insertComment(int uid) {
		return HibernateUtility.insertComment(uid, comment);
	}
		
}
