package edu.yang.trumpet.action;

import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.dmo.LoginUserList;
import edu.yang.trumpet.dmo.User;

/**
 * action class that handle the comment search of status request
 * @author daiyang
 *
 */
public class CommentRequestAction extends TemplateAction{

	@Override
	public void run() {
		JSONObject jo = super.requestJSON();
		try {
			//System.out.println("comment reqest start");
			int uid = super.getUid();
			//int uid = jo.getInt("uid");
			int sid = jo.getInt("sid");
			//if(LoginUserList.getInstance().hasUser(uid)){
				User usr = new User(uid);
//				System.out.println(usr.getCommentList(sid));
				super.respondJSONArray(usr.getCommentList(sid));
			//}
		} catch (NumberFormatException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
