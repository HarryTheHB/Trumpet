package edu.yang.trumpet.action;

import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.dmo.LoginUserList;
import edu.yang.trumpet.dmo.User;

public class NotificationRequestAction extends TemplateAction{

	@Override
	public void run() {
		JSONObject jo = super.requestJSON();
		try {
				int uid = super.getUid();
				//int uid = jo.getInt("uid");
				User usr = new User(uid);
				//System.out.println(usr.getNotification());
				super.respondJSONArray(usr.getNotification());
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
