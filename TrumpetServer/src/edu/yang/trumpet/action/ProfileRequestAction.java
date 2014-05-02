package edu.yang.trumpet.action;

import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.dmo.LoginUserList;
import edu.yang.trumpet.dmo.User;

public class ProfileRequestAction extends TemplateAction{

	@Override
	public void run() {
		JSONObject jo = super.requestJSON();
		try {
			System.out.println("start");
			int uid = super.getUid();
			String name = jo.getString("nickname");
			System.out.println("nickname: "+name);
			
				User usr = new User(uid);
				super.respondJSONArray(usr.viewProfile(name));
			
		} catch (NumberFormatException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
