package edu.yang.trumpet.action;

import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.yang.trumpet.dmo.User;
import edu.yang.trumpet.imp.HibernateUtility;
import edu.yang.trumpet.model.*;

public class ActionTest {
	public static void main(String args[]) {
		LogInfo li = new LogInfo("asdasdF@gmail.com", "sda222");
		Profile p = new Profile("nadasd", 939, "m", "coaf", 1);
		ResultInfo ri = HibernateUtility.insertUser(li, p);
//
//		Location l = new Location(125.22, 312.31, "what happened here");
//		Timestamp time = Timestamp.valueOf("1989-07-28 01:02:03");
//		StatusMessage sm = new StatusMessage("what is this feeling", time, 50, l);
//		ResultInfo r = HibernateUtil.searchUser(li);
//		User usr = new User(Integer.parseInt(r.getMessage()));
//		ResultInfo ri = usr.post(sm);
//			System.out.println("result: "+ri.getMessage());
//			System.out.println("flag: "+ri.isFlag());
		
//		Location l = new Location(124.12, 311.02, "where is it");
//		User u = new User(5);
//		JSONArray ja = u.getAllStatus(l);
//		System.out.println(ja);
		
//		CommentMessage cm = new CommentMessage(0, "nice one", time, 13);
//		System.out.println(u.getCommentList(13));
//		System.out.println(HibernateUtil.searchUsername(5));
	}
}
