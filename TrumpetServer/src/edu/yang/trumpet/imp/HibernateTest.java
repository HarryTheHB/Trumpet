package edu.yang.trumpet.imp;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.yang.trumpet.dao.Comment;
import edu.yang.trumpet.dao.User;
import edu.yang.trumpet.dao.UserRelateStatus;
import edu.yang.trumpet.dmo.MessagePair;
import edu.yang.trumpet.model.CommentMessage;
import edu.yang.trumpet.model.Location;
import edu.yang.trumpet.model.LogInfo;
import edu.yang.trumpet.model.Profile;
import edu.yang.trumpet.model.StatusMessage;

public class HibernateTest {
	public static void main(String args[]){
//		LogInfo li = new LogInfo("hlz@gmail.com", "11111");
//     	LogInfo li = new LogInfo("jiangyou@gmail.com", "010101");
//        HibernateUtil.searchUser(li);
//		Profile p = new Profile("afvdsa", 1421, "m", ".asdf.", 2);
//		HibernateUtil.insertUser(li, p);
//		Location l = new Location(125.22002, 312.31001, "where is it");
//		List<MessagePair> resultList = HibernateUtility.SearchStatus(l);
//		Iterator<MessagePair> iter = resultList.iterator();
//		while(iter.hasNext()){
//			MessagePair mp = iter.next();
//			System.out.println("name: "+ mp.getUserName());
//			System.out.println("status content: "+mp.getMessage().getContent());
//		}
//		Timestamp time = Timestamp.valueOf("2008-1-21 2:10:05");
//		CommentMessage c = new CommentMessage("What?", time, 13);
//		HibernateUtility.insertComment(7, c);
//		String[] a = new String[5];
//		String[] b = new String[5];
//		String[] c = new String[5];
//
//		a[1] = "I ";
//		a[2] = "You ";
//		a[3] = "He ";
//		a[4] = "She ";
//		a[0] = "They ";
//		
//		b[1] = "Like ";
//		b[2] = "need ";
//		b[3] = "want ";
//		b[4] = "hate ";
//		b[0] = "just did ";
//		
//		c[1] = "boy";
//		c[2] = "girl";
//		c[3] = "pizza";
//		c[4] = "study";
//		c[0] = "library";
//				
//		for(int i = 0; i<10; i++){
//			Timestamp time = Timestamp.valueOf("2013-11-19 20:"+getRandom()*10+getRandom()+":"+getRandom()*10+getRandom());
//			Location l = new Location(-76.61+((double)getRandom())/1000, 39.32+((double)getRandom())/1000, "Johns Hopkins University");
//			String content = a[(int)(getRandom()/4)-1]+b[(int)(getRandom()/4)-1]+c[(int)(getRandom()/4)-1];
//			StatusMessage sm = new StatusMessage(content, time, getRandom()*100+getRandom()*10+getRandom(), l);
//			HibernateUtility.insertStatus(sm, getRandom());
//		}
//		Profile p = HibernateUtility.serachProfile(5);
//		System.out.println(p.getAge()+","+p.getGender()+","+p.getNickName()+","+p.getSelfDiscription()+","+p.getVisibility());
//		Profile pp = new Profile("Christina A", 28, "f", "CaMa", 3);
//		System.out.println(HibernateUtility.updateProfile(5, null, pp).getMessage());
//		Profile ppp = HibernateUtility.serachProfile(5);
//		System.out.println(ppp.getAge()+","+ppp.getGender()+","+ppp.getNickName()+","+ppp.getSelfDiscription()+","+ppp.getVisibility());

		UserRelateStatus urs = new UserRelateStatus(5, 247, true);
		HibernateUtility.insertRelate(urs);
	}	
	
	private static int getRandom() {
		return 4 + (int)(Math.random() * ((19 - 4) + 1));
	}
	
}
