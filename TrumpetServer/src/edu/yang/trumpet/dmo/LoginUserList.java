package edu.yang.trumpet.dmo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * singleton
 * save the users' who have logged in successfully
 * @author daiyang
 *
 */
public class LoginUserList {
	private static volatile LoginUserList INSTANCE = null;
	private List<Long> userList = new ArrayList();
	
	private LoginUserList(){		
	}
	
	public static LoginUserList getInstance(){
		if(INSTANCE == null){
			synchronized(LoginUserList.class){
				if(INSTANCE == null){
					INSTANCE = new LoginUserList();
				}
			}
		}
		return INSTANCE;
	}
	
	
	public void addUser(long uid){
		Long u = new Long(uid);
		userList.add(u);
	}
	
	public boolean hasUser(long uid){
		boolean flag = false;
		for(Iterator iter = userList.iterator(); iter.hasNext();){
			if(iter.next().equals(uid)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public void deleteUser(long uid){
		for(Iterator iter = userList.iterator(); iter.hasNext();){
			if(iter.next().equals(uid)){
				iter.remove();
				break;
			}
		}
	}

}
