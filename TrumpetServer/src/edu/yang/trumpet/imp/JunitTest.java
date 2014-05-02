package edu.yang.trumpet.imp;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import edu.yang.trumpet.dmo.MessagePair;
import edu.yang.trumpet.model.CommentMessage;
import edu.yang.trumpet.model.Profile;

public class JunitTest {

	//@Test
	public void test() {
		String name = "Christina A";
		int nid = HibernateUtility.searchUserId(name);
		if(nid ==0)
			System.out.println("no such person");
		else{
			Profile profile = HibernateUtility.serachProfile((int)nid);
			System.out.println(profile.getNickName()+","+profile.getSelfDiscription());
		}
	}
	
	//@Test
	public void CommentSearchTest() {
		List<MessagePair> lmp = HibernateUtility.searchComment(291);
		Iterator<MessagePair> iter = lmp.iterator();
		while(iter.hasNext()){
			MessagePair mp = iter.next();
			System.out.println(mp.getUserName()+","+mp.getMessage().getContent());
		}
	}
		
	//@Test
	public void MyStatusTest(){
		List<MessagePair> lmp = HibernateUtility.searchMyStatus(5);
		Iterator<MessagePair> iter = lmp.iterator();
		while(iter.hasNext()){
			MessagePair mp = iter.next();
			System.out.println(mp.getUserName()+","+mp.getMessage().getContent());
		}
	}
	
	@Test
	public void RemoveNotificationTest() {
		HibernateUtility.removeNotification(13, 292);
	}
	
	//@Test 
	public void commentTest(){
		Timestamp time = Timestamp.valueOf("2008-1-21 2:10:05");
		CommentMessage c = new CommentMessage("test comment?", time, 283);
		HibernateUtility.insertComment(20, c).getMessage();
	}

			
		


}
