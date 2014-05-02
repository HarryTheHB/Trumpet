package edu.yang.trumpet.action;

import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.dmo.LoginUserList;
import edu.yang.trumpet.dmo.User;
import edu.yang.trumpet.model.CommentMessage;
import edu.yang.trumpet.model.ResultInfo;

/**
 * action class that handle the comment action
 * @author daiyang
 *
 */
public class CommentAction extends TemplateAction{

	@Override
	public void run() {
		JSONObject jo = super.requestJSON();
		try {
			int uid = super.getUid();
//			System.out.println("comment start ");
//			System.out.println("comment uid"+uid);
			//int uid = jo.getInt("uid");
			//System.out.println(jo.getJSONObject("comment"));
			CommentMessage cm = new CommentMessage(jo.getJSONObject("comment"));
			//if(LoginUserList.getInstance().hasUser(uid)){
				User usr = new User(uid);
				super.respondJSONObject(usr.addComment(cm));
			//}
		} catch (NumberFormatException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
