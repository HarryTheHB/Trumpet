package edu.yang.trumpet.dmo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.yang.trumpet.imp.HibernateUtility;
import edu.yang.trumpet.model.Location;
import edu.yang.trumpet.model.StatusMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * include data and operation for status list
 * @author daiyang
 *
 */
public class UserStatusList {
	private List<MessagePair> statusList;
	
	/**
	 * Search all recent status the user can get
	 * @param uid user's id
	 * @param l user's current location
	 * @return JSONArray of status list
	 */
	public JSONArray SearchAllStatus(Location l){
		statusList = HibernateUtility.searchStatus(l);
		return this.toJsonArray();
	}
	
	/**
	 * search all status posted by user him/her self
	 * @param uid user's id
	 * @return JSONArray of status list
	 */
	public JSONArray SearchMyStatus(int uid){
		statusList = HibernateUtility.searchMyStatus(uid);
		return this.toJsonArray();
	}
	
	/**
	 * search all status user has subscribed with comments updated 
	 * @param uid user's id
	 * @return JSONArray of status list
	 */
	public JSONArray SearchNotification(int uid){
		statusList = HibernateUtility.searchNotification(uid);
		return this.toJsonArray();
	}
	
	public JSONArray toJsonArray(){
		JSONArray resultArray = new JSONArray();
		Iterator<MessagePair> iter = statusList.iterator();
		int i = 0;
		while(iter.hasNext()){
			JSONObject jo = new JSONObject();
			try {
				MessagePair mp = iter.next();
				jo.put("nickname", mp.getUserName());
				jo.put("status", mp.getMessage().toJson());
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
