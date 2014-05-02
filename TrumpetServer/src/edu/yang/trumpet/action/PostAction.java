package edu.yang.trumpet.action;

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.dmo.User;
import edu.yang.trumpet.model.Location;
import edu.yang.trumpet.model.ResultInfo;
import edu.yang.trumpet.model.StatusMessage;

/**
 * action class that handle the status post request
 * @author daiyang
 *
 */
public class PostAction extends TemplateAction{

	@Override
	public void run() {
		//System.out.println("post start");
		JSONObject jo = super.requestJSON();
		try {
			//System.out.println("try start");
//			System.out.println(jo.getInt("uid"));
			//int uid = jo.getInt("uid");
			int uid = super.getUid();
			//System.out.println("post uid is:"+uid);
			//System.out.println("post start");
			User usr = new User(uid);
			StatusMessage sm = new StatusMessage(jo.getJSONObject("status"));	
			ResultInfo rm = usr.post(sm);
			//System.out.println(rm.getMessage()+rm.isFlag());
			respondJSONObject(rm.toJson());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
