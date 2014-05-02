package edu.yang.trumpet.action;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.dmo.LoginUserList;
import edu.yang.trumpet.dmo.User;
import edu.yang.trumpet.model.Location;

public class AllStatusRequestAction extends TemplateAction{

	@Override
	public void run() {
		JSONObject jo = super.requestJSON();
		try {
			int uid = super.getUid();
			//int uid = jo.getInt("uid");
			//System.out.println(jo);
			Location location = new Location(jo.getJSONObject("location"));
			//System.out.println(location.getLatitude()+"   "+location.getLongitude());
			
				User usr = new User(uid);
				//System.out.println(usr.getAllStatus(location));
				super.respondJSONArray(usr.getAllStatus(location));
			
		} catch (NumberFormatException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
