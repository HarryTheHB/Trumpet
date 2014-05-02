

/***********************************************************************
 * Module:  Comment.java
 * Author:  Yang
 * Edited:  Cong Feng
 * Purpose: Defines the Class Comment that for the transportation of comment 
 ***********************************************************************/

package edu.yang.trumpet.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;


public class CommentMessage extends Message {

	private long sid;
	//private static SimpleDateFormat DBTimeFormat = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");
   /** constructor
    * @param sid an id for each commentmessage without duplication
    *  */
	public CommentMessage(long id, String content, Timestamp time, long sid) {
		super(id, content, time);
		this.sid = sid;
	}
	
	public CommentMessage(String content, Timestamp time, long sid) {
		super(content, time);
		this.sid = sid;
	}
	
	public CommentMessage(CommentMessage cm){
		super(cm.getId(), cm.getContent(), cm.getTime());
		this.sid = cm.getSid();
	}
	
	public void setSid(long sid) {
		this.sid = sid;
	}

	/**
	 * overloaded constructor: construct from json object
	 * @param jo
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public CommentMessage(JSONObject jo) throws JSONException{
		super(jo.getLong("id"), jo.getString("content"), Timestamp.valueOf(jo.getString("date")));
		try {
			sid = jo.getLong("sid");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   /**
    * 
    * @return
    */
   public long getSid() {
	      // TODO: implement
	      return sid;
	   }
   /**
    * This class mainly for transform a CommentMessage to JSONObject 
    */
   public JSONObject toJson(){
	   JSONObject cmtjson=new JSONObject();
	   try {
		   cmtjson.put("id", this.getId());
		   cmtjson.put("sid", this.sid);
		   cmtjson.put("content", this.getContent());
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String stringdate = sdf.format(this.getTime());
		   cmtjson.put("date", stringdate);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return cmtjson;
   }

}