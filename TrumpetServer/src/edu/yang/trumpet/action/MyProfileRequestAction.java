package edu.yang.trumpet.action;

import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.dmo.LoginUserList;
import edu.yang.trumpet.dmo.User;

public class MyProfileRequestAction extends TemplateAction{

	@Override
	public void run() {
		JSONObject jo = super.requestJSON();
		try {
			int uid = super.getUid();
//			if(LoginUserList.getInstance().hasUser(uid)){
				User usr = new User(uid);
				super.respondJSONArray(usr.viewMyProfile());
//			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
