/***********************************************************************
 * Module:  Message.java
 * Author:  Yang
 * Edited:  Cong Feng
 * Purpose: Defines the father class of all type of message
 ***********************************************************************/

package edu.yang.trumpet.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.JSONObject;

/** father class of comment and status */
public abstract class Message {
	
	private long id;
   private String content;
   private Timestamp time;
 
   
   /**
    * 
    * @param id message id without duplication indicates the message
    * @param content the text user wants to send
    * @param date2 the time when the message is posted
    */
   public Message(long id, String content, Timestamp time) {
      this.id = id;
	  this.content = content;
      this.time = time;
   }
   
   public Message(String content, Timestamp time) {
	   this.content = content;
	   this.time = time;
}

public long getId(){
	   return id;
   }
   

   public String getContent() {
      return content;
   }
   

   
public abstract JSONObject toJson();

public void setId(long id) {
	this.id = id;
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


}