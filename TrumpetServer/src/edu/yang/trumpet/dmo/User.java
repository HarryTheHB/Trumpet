package edu.yang.trumpet.dmo;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import edu.yang.trumpet.model.*;


/**
 * Basic User unit
 * Every time server receive a user's request, a object of this class will be established
 * Most user action will be performed through the object of this class
 * @author daiyang
 *
 */
public class User {

	private int uid;

	public User(int uid) {
		super();
		this.uid = uid;
	}
	
/* Consider to move out of this class
	public ResultMessage register(LogInfo li, Profile p){
		UserInfo info = new UserInfo(li, p);
		return insertResult = info.insertLogInfo();
		//return insertResult;
	}
	
	public ResultMessage login(LogInfo li){
		UserInfo info = new UserInfo(li, null);
		return info.checkLogin();
		//return checkResult;
	}
*/	
	
	public long getUid() {
		return uid;
	}

	/**
	 *  perform the function of insert a new post
	 * @return return a JSONObject of a ResultMessage
	 */
	public ResultInfo post(StatusMessage sm){
		UserStatus newStatus = new UserStatus(sm);
		return newStatus.insertStatus(uid);
		//return insertResult;
	}
	

	//theses two may not work appropriately.
	/**
	 * perform the function of getting user's own status
	 * @return return a JSONArray of StatusMessage List
	 */
	public JSONArray getMyStatus(){
		UserStatusList statusList = new UserStatusList();
		return statusList.SearchMyStatus(uid);
	}	
	/**
	 *  perform the function of getting recently new status
	 * @param l user's current location
	 * @return return JASONArray of all StatusMessage the user can get within a certain time 
	 */
	public JSONArray getAllStatus(Location l){
		UserStatusList statusList = new UserStatusList();
		return statusList.SearchAllStatus(l);
	}
	
	/**
	 *  perform the function of insert a new comment for a status
	 * @param cm user's comment to some message
	 * @return JSONObject of ResultMessage
	 */
	public JSONObject addComment(CommentMessage cm){
		UserComment comment = new UserComment(cm);
		return comment.insertComment((int)uid).toJson();
	}	
	
	/**
	 * perform the function of getting all comments of a status
	 * @param sid id of the message
	 * @return JSONArray of comments
	 */
	public JSONArray getCommentList(int sid){
		UserCommentList commentList = new UserCommentList();
//		System.out.println("User "+commentList.SearchComments(sid, uid));
		return commentList.SearchComments(sid, uid);
	}
	
	/**
	 * perform the function of getting new notifications
	 * @return JSONArray of status that have comments updated
	 */
	public JSONArray getNotification(){
		UserStatusList statusList = new UserStatusList();
		return statusList.SearchNotification(uid);
	}
	
	/**
	 * perform the function of add the user to the notification list of certain status
	 * @param sid id of status
	 * @return result message
	 */
	public ResultInfo subscribeStatus(int sid){
		UserStatus status = new UserStatus();
		return status.insertToNotification(sid);
	}
	
	/**
	 * perform the function of remove the user from the notification list of certain status
	 * @param sid id of status
	 * @return result message 
	 */
	public ResultInfo unsubscribeStatus(int sid){
		UserStatus status = new UserStatus();
		return status.dropFromNotification(sid);
	}
	
	/**
	 * perform the function of delete one of user's posted status
	 * @param sid
	 * @return
	 */
	public JSONObject deleteMyStatus(int sid){
		UserStatus status = new UserStatus();		
		return status.dropStatus(sid).toJson();
	}
	
	/**
	 * perform the function of  search other users' profile
	 * @param name nickname of user
	 * @return
	 */
	public JSONArray viewProfile(String name){
//		System.out.println("name"+name);
		UserInfo ui = new UserInfo(uid);
		JSONObject jo = ui.getProfile(name).toJson();
		//System.out.println(jo);
		JSONArray ja = new JSONArray();
		ja.put(jo);
		return ja;
	}
	
	/**
	 * perform the function of update user's personal information
	 * @param p new profile data
	 * @param li new login data
	 * @return
	 */
	public JSONObject editProfile(Profile p, LogInfo li){
		//System.out.println("User");
		UserInfo ui = new UserInfo(uid);
		return ui.editProfile(li, p).toJson();
	}

	/**
	 * perform the function of search user's own profile
	 * @return
	 */
	public JSONArray viewMyProfile() {
		UserInfo ui = new UserInfo(uid);
		//System.out.println("uid"+uid);
		JSONObject jo = ui.getMyProfile().toJson();
		//System.out.println(jo);
		JSONArray ja = new JSONArray();
		ja.put(jo);
		return ja;
	}
	
}
	
