package edu.yang.trumpet.action;

import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.dmo.User;

public class MyStatusRequestAction extends TemplateAction{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		JSONObject jo = super.requestJSON();
		try {
			System.out.println("My status starts");
			int uid = super.getUid();
			//int uid = jo.getInt("uid");
			System.out.println(jo);
			
				User usr = new User(uid);
				System.out.println("mystatus "+usr.getMyStatus());
				super.respondJSONArray(usr.getMyStatus());
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
