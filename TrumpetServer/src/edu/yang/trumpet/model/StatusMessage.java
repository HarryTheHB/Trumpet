
/***********************************************************************
 * Module:  StatusMessage.java
 * Author:  Yang
 * Edited:  Cong Feng
 * Purpose: Defines the Class StatusMessage for the transportation of status
 ***********************************************************************/
package edu.yang.trumpet.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;


public class StatusMessage extends Message {

	private int range;
   private Location location;
  // private static SimpleDateFormat DBDateFormat = new SimpleDateFormat("MM-dd-yy, HH:mm:ss");
   
   
   /** constructor
    * @param r describe the distribution range of this message
    * @param l describe the location of the user when posting this message
    *  */
   public StatusMessage(long id, String content, Timestamp time, int range, Location location) {
		super(id, content, time);
		this.range = range;
		this.location = new Location(location);
	}
   
   public StatusMessage(String content, Timestamp time, int range, Location location) {
		super(content, time);
		this.range = range;
		this.location = new Location(location);
	}
   
   public StatusMessage(StatusMessage sm){
	   super(sm.getId(), sm.getContent(), sm.getTime());
	   range = sm.getRange();
	   location = new Location(sm.getLocation());
   }
   /**
    * overloaded constructor: construct from json object
    * @param jo1 is the JSONOject for StatusMessage from server
    * @param jo2 is the JSONOject for Location got from server
 * @throws JSONException 
 * @throws ParseException 
 	*/
	public StatusMessage(JSONObject jo1) throws JSONException, ParseException {
		super(jo1.getLong("id"), jo1.getString("content"), Timestamp.valueOf(jo1.getString("date")));
		try {
			range = jo1.getInt("range");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		location = new Location(jo1.getJSONObject("location"));
	}

   public int getRange() {
      return range;
   }
   

   public Location getLocation() {
      return location;
   }
   
   public void setRange(int range) {
	this.range = range;
}

public void setLocation(Location location) {
	this.location = location;
}
/**
 * This class mainly for transform a StatusMessage to JSONObject
 * @return smsgjson as the  JSONObject 
 */ 
public JSONObject toJson(){
	   JSONObject smsgjson=new JSONObject();
	   JSONObject locjson=location.toJson();
	   try {
		   smsgjson.put("id", this.getId());
		   smsgjson.put("content", this.getContent());
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String stringdate = sdf.format(this.getTime());
		   smsgjson.put("date", stringdate);
		   smsgjson.put("range", this.range);
		   smsgjson.put("location", locjson);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return smsgjson;
   }

}