package edu.yang.trumpet.action;

import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.dmo.LoginUserList;
import edu.yang.trumpet.dmo.User;
import edu.yang.trumpet.model.LogInfo;
import edu.yang.trumpet.model.Profile;

public class ProfileUpdateAction extends TemplateAction{

	@Override
	public void run() {
		JSONObject jo = super.requestJSON();
		try {
			int uid = super.getUid();
			Profile p = new Profile(jo.getJSONObject("profile"));
			System.out.println(jo);
			LogInfo li = null;
//			LogInfo li = new LogInfo(jo.getJSONObject("loginfo"));
//			if(LoginUserList.getInstance().hasUser(uid)){
				User usr = new User(uid);
				super.respondJSONObject(usr.editProfile(p, li));
//			}
		} catch (NumberFormatException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
