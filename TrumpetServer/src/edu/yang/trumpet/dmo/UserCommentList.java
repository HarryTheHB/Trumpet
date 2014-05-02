package edu.yang.trumpet.dmo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.imp.HibernateUtility;
import edu.yang.trumpet.model.CommentMessage;
import edu.yang.trumpet.model.Location;
import edu.yang.trumpet.model.StatusMessage;

/**
 * include data and operation for comment list
 * @author daiyang
 *
 */
public class UserCommentList {
	private List<MessagePair> commentList = new ArrayList();
	
	/**
	 * search all comments of a status
	 * @param sid id of the status
	 * @return JSONArray of comments
	 */
	public JSONArray SearchComments(int sid, int uid){
		commentList = HibernateUtility.searchComment(sid);
		HibernateUtility.removeNotification(uid, sid);
		System.out.println("UserCommentList: "+this.toJsonArray());
		return this.toJsonArray();
	}
		
	public JSONArray toJsonArray(){
		JSONArray resultArray = new JSONArray();
		Iterator<MessagePair> iter = commentList.iterator();
		int i = 0;
		while(iter.hasNext()){
			JSONObject jo = new JSONObject();
			try {
				MessagePair mp = iter.next();
				jo.put("nickname", mp.getUserName());
				jo.put("comment", mp.getMessage().toJson());
				resultArray.put(i, jo);
				i++;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultArray;
	}
}
