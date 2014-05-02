package edu.yang.trumpet.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.SessionMap;
import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.dmo.CodeSender;
import edu.yang.trumpet.dmo.LoginUserList;
import edu.yang.trumpet.imp.HibernateUtility;
import edu.yang.trumpet.model.LogInfo;
import edu.yang.trumpet.model.ResultInfo;

/**
 * action class that handle the login request
 * @author daiyang
 *
 */
public class LoginAction extends TemplateAction{
	private CodeSender cs;
	
	@Override
	public void run() {
		JSONObject jo = super.requestJSON();
		//SessionMap session = super.getHttpSession();
		//System.out.println(jo);
		LogInfo li = new LogInfo(jo);
		ResultInfo ri = HibernateUtility.searchUser(li);
		if(ri.isFlag()){
			//System.out.println("login uid: "+ri.getMessage());
//			session.setAttribute("uid", ri.getMessage());
			super.getSession().put("uid", ri.getMessage());
			//System.out.println("session uid exists: "+super.getSession().get("uid"));
		}
		super.respondJSONObject(ri.toJson());
	}

}
