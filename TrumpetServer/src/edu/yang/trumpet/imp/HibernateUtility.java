package edu.yang.trumpet.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.yang.trumpet.dao.*;
import edu.yang.trumpet.dao.Location;
import edu.yang.trumpet.dmo.MessagePair;
import edu.yang.trumpet.model.*;


/**
 * Hibernate Utility class
 * include all actions that deal with database query
 * @author daiyang
 *
 */
public class HibernateUtility {
	private static final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
	
	public static void closeSession() {
		sessionFactory.close();
	}
	
	/**
	 * search all status posted by user him/herself
	 * @param uid user's id
	 * @return
	 */
	public static List<MessagePair> searchMyStatus(int uid) {
		//System.out.println("my status hibernet "+uid);
		Session session = sessionFactory.openSession();
		List<MessagePair> resultList = new ArrayList();
		String sql = "FROM Status WHERE idUser ='"+uid+"' ORDER BY time DESC";
		String sql2 = "FROM Location WHERE idLocation ='";
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         String name = searchUsername(uid);
	         List statusList = session.createQuery(sql).list(); 
	         for (Iterator iterator = 
	                           statusList.iterator(); iterator.hasNext();){
	            Status s = (Status) iterator.next(); 
	            List locationList = session.createQuery(sql2+s.getIdLocation()+"'").list();
	            Iterator iter = locationList.iterator();
	            edu.yang.trumpet.dao.Location location = (Location)iter.next();

	            edu.yang.trumpet.model.Location loc = new edu.yang.trumpet.model.Location(location.getLongitude(), location.getLatitude(), location.getName());
	            StatusMessage status = new StatusMessage(s.getIdStatus(), s.getContent(), s.getTime(), s.getCoverRange(), loc);
	            resultList.add(new MessagePair(name,status));
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
//	      Iterator<StatusMessage> iter = resultList.iterator();
//			while(iter.hasNext()){
//				StatusMessage sm = iter.next();
//				System.out.println("status content: "+sm.getContent());
//			}
		return resultList;
	}
	
	/**
	 * Search notification for one user
	 * @param uid
	 * @return
	 */
	public static List<MessagePair> searchNotification(int uid) {
		List<MessagePair> result = new ArrayList<MessagePair>();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			String sql = "FROM Notification WHERE idUser="+uid;
			List<Notification> notifications = session.createQuery(sql).list();
			for (Notification n : notifications){
				String sqlSearchEachStatus = "FROM Status WHERE idStatus="+n.getIdStatus();
				List<Status> eachStatus = session.createQuery(sqlSearchEachStatus).list();
				for (Status s : eachStatus){
					String sqlSearchLocation = "FROM Location WHERE idLocation="+s.getIdLocation();
					List<Location> l = session.createQuery(sqlSearchLocation).list();
					Iterator i = l.iterator();
					Location location = (Location) i.next();
					edu.yang.trumpet.model.Location loc = new edu.yang.trumpet.model.Location(location.getLongitude(), location.getLatitude(), location.getName());
	            	StatusMessage status = new StatusMessage(s.getIdStatus(), s.getContent(), s.getTime(), s.getCoverRange(), loc);
					MessagePair mp = new MessagePair(searchUsername(s.getIdUser()), status);
					result.add(mp);
				}
			}
		}catch(HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}
	
	/**
	 * insert the relation that relate one user and one status
	 * @param urs
	 */
	public static void insertRelate(UserRelateStatus urs){
		//System.out.println("start");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	      try{
	    	 //System.out.println("second");
	         tx = session.beginTransaction();
	         session.save(urs);
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	/**
	 * Search one user's profile
	 * @param uid
	 * @return
	 */
	public static Profile serachProfile(int uid) {
		Session session = sessionFactory.openSession();
		String sql = "FROM User WHERE idUser ='"+uid+"'";
	      Transaction tx = null;
	      try{
	    	  tx = session.beginTransaction();
	    	  List users = session.createQuery(sql).list();
	    	  Iterator iter = users.iterator();
	    	
	    	  User user = (User)iter.next();
	    	  Integer pid = new Integer(user.getIdPersonalInfo());
	    	  String sql2 = "FROM PersonalInfo WHERE idPersonalInfo ='"+pid+"'";
	    	  List pis = session.createQuery(sql2).list();
	    	  PersonalInfo pi = (PersonalInfo)pis.iterator().next();
	    	  Profile profile = new Profile(pi.getNickName(),pi.getAge(), pi.getGender(), pi.getDescription(), pi.getVisibility());
	    	  tx.commit();
	    	  return profile;
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return null;
	}
	
	/**
	 * update one user's personal infomation
	 * @param uid
	 * @param li loginfo
	 * @param p profile
	 * @return
	 */
	public static ResultInfo updateProfile(int uid, LogInfo li, Profile p) {
		//System.out.println("HibernateUtility");
		Session session = sessionFactory.openSession();
		String sql = "FROM User WHERE idUser ='"+uid+"'";
	      Transaction tx = null;
	      try{
	    	  tx = session.beginTransaction();
	    	  List users = session.createQuery(sql).list();
	    	  Iterator iter = users.iterator();
	    	
	    	  User user = (User)iter.next();
	    	  Integer pid = new Integer(user.getIdPersonalInfo());
	    	  //System.out.println("uid: "+uid);
	    	  //System.out.println("pid: "+pid);
	    	  String sql2 = "FROM PersonalInfo WHERE idPersonalInfo ='"+pid+"'";
	    	  List pis = session.createQuery(sql2).list();
	    	  PersonalInfo pi = (PersonalInfo)pis.iterator().next();
	    	  pi.setAge(p.getAge());
	    	  pi.setDescription(p.getSelfDiscription());
	    	  pi.setGender(p.getGender());
	    	  pi.setNickName(p.getNickName());
	    	  pi.setVisibility(p.getVisibility());
	    	  session.update(pi);
	    	  tx.commit();
	    	  return new ResultInfo("change success", true);
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return new ResultInfo("change failed", false);
	}
	
	/**
	 * Comment insert method
	 * @param uid user id of comment
	 * @param cm comment message
	 * @return
	 */
	public  static ResultInfo insertComment(int uid, CommentMessage cm){
	//	System.out.println("insert comment start");
		Session session = sessionFactory.openSession();
		Notification n = new Notification();
	//	System.out.println(cm.getSid());
		Comment comment = new Comment(uid, cm.getContent(), (int)cm.getSid(), cm.getTime());
		String sqlSearchRelate = "FROM UserRelateStatus WHERE idStatus = "+ cm.getSid();
		//Notification notification = new Notification(uid, (int)cm.getSid(), cm.getTime());
		Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.save(comment);
	         List userRelateStatusList = session.createQuery(sqlSearchRelate).list(); 
	         boolean exists = false;
	         for (Iterator iterator = 
	                           userRelateStatusList.iterator(); iterator.hasNext();){
	            UserRelateStatus urs = (UserRelateStatus) iterator.next(); 
	            if(urs.getIdUser() != uid && urs.isSubscribe()){
	            	String sqlSearchNoti = "FROM Notification WHERE User_idUser="+urs.getIdUser()+" AND Status_idStatus="+ cm.getSid();
	            	List existedNoti = session.createQuery(sqlSearchNoti).list();
	            	boolean notificationExist = false;
	            	for (Iterator iterator1 = existedNoti.iterator(); iterator1.hasNext();){
	            		iterator1.next();
	            		notificationExist = true;
	            	}
	            	n = new Notification(urs.getIdUser(), (int) cm.getSid(), cm.getTime());
	            	if (notificationExist == false){
	            		session.save(n);
	            	}else{
	            		session.merge(n);;
	            		//session.delete(n);
	            		//session.save(n);
	            	}
	            }
	            else 
	            	exists = true;
	         }
	         if (exists == false){
	        	 UserRelateStatus newURS = new UserRelateStatus(uid, (int) cm.getSid(), true);
	        	 session.save(newURS);
	         }
	         tx.commit();
	         return new ResultInfo("comment succeeded", true);
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return new ResultInfo("comment failed", false);
	}
	
	/**
	 * new user information insert method
	 * @param logInfo 
	 * @param profile
	 * @return
	 */
	public static ResultInfo insertUser(LogInfo logInfo, Profile profile){
		//System.out.println("start");
		Session session = sessionFactory.openSession();
		PersonalInfo pi = new PersonalInfo(profile);
		Integer pid = null;
		Integer uid = null;
		Transaction tx = null;
	      try{
	    	 //System.out.println("second");
	         tx = session.beginTransaction();
	         pid = (Integer)session.save(pi);
	         User usr = new User(pid, logInfo);
	         uid = (Integer)session.save(usr);
	         System.out.println("uid :"+uid);
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      if(uid != 0)
	    	  return new ResultInfo(uid.toString(), true);
	      else 
	    	  return new ResultInfo("register failure", false);
	}
	
	/**
	 * Remove one notification of for a user
	 * @param uid
	 * @param sid
	 */
	public static void removeNotification(int uid, int sid){
		
		String sql = "FROM Notification WHERE User_idUser = "+uid+" AND Status_idStatus = "+sid;
		System.out.println(sql);
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	      try{
	    	  tx = session.beginTransaction();
	    	  if(session.createQuery(sql).list().iterator().hasNext()){
	    		  Notification n = (Notification)session.createQuery(sql).list().iterator().next();
	    		  session.delete(n);
	    	  }
	    	  tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	/**
	 * comment search method
	 * search all comments of certain status
	 * @param sid status id
	 * @return a map that include 
	 */
	public static List<MessagePair> searchComment(int sid) {
		List<MessagePair> resultList = new ArrayList();
		System.out.println("search comment start "+sid);
		Session session = sessionFactory.openSession();
		String sql = "FROM Comment WHERE Status_idStatus ='"+sid+"' ORDER BY time DESC";
		Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List statusList = session.createQuery(sql).list(); 
	         for (Iterator iterator = 
	                           statusList.iterator(); iterator.hasNext();){
	            Comment c = (Comment) iterator.next(); 
	            CommentMessage status = new CommentMessage(c.getIdComment(), c.getContent(), c.getTime(), c.getIdStatus());
	            resultList.add(new MessagePair(searchUsername(c.getIdUser()),status));
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return resultList;
	}
	
	
	/**
	 * status search method
	 * search all recent message that the client can received
	 * @param l location of client
	 * @return 
	 */
	public static List<MessagePair> searchStatus(edu.yang.trumpet.model.Location l){
		Session session = sessionFactory.openSession();
		List<MessagePair> resultList = new ArrayList();
		double lo = l.getLongitude();
		double la = l.getLatitude();
		String sql = "FROM Status ORDER BY time DESC";
		String sql2 = "FROM Location WHERE idLocation ='";
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List statusList = session.createQuery(sql).list(); 
	         for (Iterator iterator = 
	                           statusList.iterator(); iterator.hasNext();){
	            Status s = (Status) iterator.next(); 
	            List locationList = session.createQuery(sql2+s.getIdLocation()+"'").list();
	            Iterator iter = locationList.iterator();
	            edu.yang.trumpet.dao.Location location = (Location)iter.next();
//	            System.out.println("location id: "+location.getIdLocation());
	            int dist = getDistance(la, lo,location.getLatitude(), location.getLongitude());
	            //System.out.println(dist);
	            if(dist <= s.getCoverRange()){
//	            	System.out.println(s.getContent());
	            	edu.yang.trumpet.model.Location loc = new edu.yang.trumpet.model.Location(location.getLongitude(), location.getLatitude(), location.getName());
	            	StatusMessage status = new StatusMessage(s.getIdStatus(), s.getContent(), s.getTime(), s.getCoverRange(), loc);
	            	resultList.add(new MessagePair(searchUsername(s.getIdUser()),status));
	            }
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
//	      Iterator<StatusMessage> iter = resultList.iterator();
//			while(iter.hasNext()){
//				StatusMessage sm = iter.next();
//				System.out.println("status content: "+sm.getContent());
//			}
		return resultList;
	}
	
	/**
	 * search if user exists in database by the login information
	 * @param li
	 * @return
	 */
	public static ResultInfo searchUser(LogInfo li){
		  Session session = sessionFactory.openSession();
	      String sql = "FROM User WHERE email ='"+li.getEmail()+"' AND password ='"+li.getPassword()+"'";
	      Transaction tx = null;
	      try{
	    	  tx = session.beginTransaction();
	    	  List users = session.createQuery(sql).list();
	    	  Iterator iter = users.iterator();
	    	  if(!iter.hasNext()){
	    		  tx.commit();
	    		  return new ResultInfo("Login failed", false);
	    	  }
	    	  else {
	    		  User user = (User)iter.next();
	    		  Integer uid = new Integer(user.getIdUser());
	    		  tx.commit();
	    		  return new ResultInfo(uid.toString(), true);
	    	  }
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return null;
	}
	
	/**
	 * search user's nickname by user's id
	 * @param uid
	 * @return
	 */
	public static String searchUsername(int uid) {
		Session session = sessionFactory.openSession();
	      String sql = "FROM User WHERE idUser ='"+uid+"'";
	      Integer pid = null;
	      String sql2 = "FROM PersonalInfo WHERE idPersonalInfo ='";
	      Transaction tx = null;
	      try{
	    	  tx = session.beginTransaction();
	    	  List users = session.createQuery(sql).list();
	    	  Iterator iter = users.iterator();
	    	  if(!iter.hasNext()){
	    		  tx.commit();
	    		  return null;
	    	  }
	    	  else {
	    		  User user = (User)iter.next();
	    		  pid = user.getIdPersonalInfo();
//	    		  System.out.println("profile id: "+pid);
	    		  List personalInfo = session.createQuery(sql2+pid+"'").list();
	    		  Iterator piIter = personalInfo.iterator();
	    		  PersonalInfo pi = (PersonalInfo) piIter.next();
	    		  tx.commit();
	    		  return pi.getNickName();
	    	  }
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return null;
	}
	
	/**
	 * Search one user's id by his/her nickname
	 * @param name
	 * @return
	 */
	public static int searchUserId(String  name){
		//System.out.println("start");
		Session session = sessionFactory.openSession();
	      String sql = "FROM User WHERE idPersonalInfo ='";
	      Integer pid = null;
	      String sql2 = "FROM PersonalInfo WHERE nickname ='"+name+"'";
	      Transaction tx = null;
	      try{
	    	  tx = session.beginTransaction();
	    	  List users = session.createQuery(sql2).list();
	    	  Iterator iter = users.iterator();
	    	  if(!iter.hasNext()){
	    		  tx.commit();
	    		  return 0;
	    	  }
	    	  else {
	    		  PersonalInfo pi = (PersonalInfo)iter.next();
	    		  pid = pi.getIdPersonalInfo();
	    		  //System.out.println("profile id: "+pid);
	    		  List personalInfo = session.createQuery(sql+pid+"'").list();
	    		  Iterator piIter = personalInfo.iterator();
	    		  User usr = (User) piIter.next();
	    		  tx.commit();
	    		  return usr.getIdUser();
	    	  }
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return 0;
	}
	
	/**
	 * insert a new status into database
	 * @param sm status message
	 * @param uid user's id
	 * @return
	 */
	public static ResultInfo insertStatus(StatusMessage sm, int uid) {
		Session session = sessionFactory.openSession();
		
		edu.yang.trumpet.dao.Location l = new Location(sm.getLocation().getLongitude(), sm.getLocation().getLatitude(), sm.getLocation().getTextDiscription());
		Integer lid = null;
		Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         lid = (Integer)session.save(l);
	         
	         //System.out.println("longtitud: "+l.getLongitude()+" latitude: "+l.getLatitude()+" description: "+l.getName());
	         //System.out.println("uid: "+uid);
	         Status s = new Status(uid, lid, sm.getContent(), sm.getTime(), sm.getRange());
	         int sid = (Integer)session.save(s);
	         String sqlSearchRelate = "FROM UserRelateStatus WHERE idStatus = "+ sid+" and idUser = "+uid;
	         //System.out.println("sid: "+sid);
	         List userRelateStatusList = session.createQuery(sqlSearchRelate).list(); 
	      
	         if(userRelateStatusList.isEmpty()){	
	        	 UserRelateStatus newURS = new UserRelateStatus(uid, (int)sid, true);
	        	 session.save(newURS);
	         }
//	         System.out.println("uid :"+uid);
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return new ResultInfo("post success", true);
	}
	
	/**
	 * test mehod
	 * @param logInfo
	 */
	public static void ListUser(LogInfo logInfo){
	      Session session = sessionFactory.openSession();
	      String sql = "FROM User WHERE email ='"+logInfo.getEmail()+"' AND password ='"+logInfo.getPassword()+"'";
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List users = session.createQuery(sql).list(); 
	         for (Iterator iterator = 
	                           users.iterator(); iterator.hasNext();){
	            User user = (User) iterator.next(); 
//	            System.out.println("uid: " + user.getIdUser());
//	            System.out.println(" email: " + user.getEmail()); 
//	            System.out.println(" password:  " + user.getPassword()); 
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	
	/**
	 * calculate the approximate distance between two coordinates
	 * @param la
	 * @param lo
	 * @param d
	 * @param e
	 * @return
	 */
	private static int getDistance(double la, double lo, double d, double e) {
	    double earthRadius = 3958.75;
	    double dLat = Math.toRadians(d-la);
	    double dLng = Math.toRadians(e-lo);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(la)) * Math.cos(Math.toRadians(d)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;

	    int meterConversion = 1609;

	    return new Integer((int) (dist * meterConversion)).intValue();
	    }
}
	
