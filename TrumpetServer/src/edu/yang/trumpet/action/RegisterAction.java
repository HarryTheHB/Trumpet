package edu.yang.trumpet.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.SessionMap;
import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.dmo.CodeSender;
import edu.yang.trumpet.dmo.LoginUserList;
import edu.yang.trumpet.imp.HibernateUtility;
import edu.yang.trumpet.model.LogInfo;
import edu.yang.trumpet.model.Profile;
import edu.yang.trumpet.model.ResultInfo;

/**
 * action class that handle the register request
 * @author daiyang
 *
 */
public class RegisterAction extends TemplateAction{
	private CodeSender cs;
	@Override
	public void run() {
		System.out.println("register start");
		JSONObject jo = super.requestJSON();
		SessionMap<String, Object> session = super.getSession();
//		System.out.println(jo);
		if(!session.containsKey("code")){
			LogInfo li;
			try {
				System.out.println("ask for code");
				li = new LogInfo(jo.getJSONObject("loginfo"));
				Profile p = new Profile(jo.getJSONObject("profile"));
//				session.setAttribute("email", li.getEmail());
//				session.setAttribute("password", li.getPassword());
				session.put("loginfo", li);
				session.put("profile", p);
				LogInfo lili = (LogInfo)session.get("loginfo");
				Profile pp = (Profile)session.get("profile");
				System.out.println(lili.getEmail()+lili.getPassword());
				
				
				cs = new CodeSender("Smtp.gmail.com", "oose.trumpet.services@gmail.com", "hlz636474", li.getEmail());
				cs.send();
				session.put("code", cs.getCode());
				System.out.println(cs.getCode());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println("verify code");
			ResultInfo ri;
			int code;
			try {
				code = jo.getInt("code");
//				System.out.println(code);
//				System.out.println(session.get("code").toString());
				if(code == Integer.parseInt(session.get("code").toString())){
//					LogInfo li = new LogInfo(session.getAttribute("email").toString(), session.getAttribute("password").toString());
					LogInfo li = (LogInfo)session.get("loginfo");
					Profile p = (Profile)session.get("profile");
//					System.out.println(li.getEmail()+li.getPassword());
//					LogInfo li = new LogInfo(jo.getJSONObject("loginfo"));
//					Profile p = new Profile(jo.getJSONObject("profile"));
					ri = HibernateUtility.insertUser(li, p);
					if(ri.isFlag()){
						int uid = Integer.parseInt(ri.getMessage());
						session.put("uid", uid);
//						System.out.println("uid :"+session.get("uid"));
					}
					session.remove("code");
					session.remove("email");
					session.remove("passwrod");
				}
				else{
					session.remove("code");
					session.remove("email");
					session.remove("passwrod");
					ri = new ResultInfo("Verification failed", false);
				}
				super.respondJSONObject(ri.toJson());
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
