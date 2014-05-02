package edu.yang.trumpet.action;

import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.dmo.LoginUserList;
import edu.yang.trumpet.model.ResultInfo;

public class LogoutAction extends TemplateAction{

	@Override
	public void run() {
		super.logout();	
	}

}
