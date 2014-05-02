package edu.yang.trumpet.dmo;

import edu.yang.trumpet.imp.HibernateUtility;
import edu.yang.trumpet.model.LogInfo;
import edu.yang.trumpet.model.Profile;
import edu.yang.trumpet.model.ResultInfo;

/**
 * Include user information and related operation
 * @author daiyang
 *
 */
public class UserInfo {
	private long uid;
	private LogInfo logInfo;
	private Profile profile;
	
	
	public UserInfo(long uid, LogInfo logInfo, Profile profile) {
		super();
		this.uid = uid;
		this.logInfo = logInfo;
		this.profile = profile;
	}
	
	public UserInfo(long uid){
		this.uid = uid;
	}

	public LogInfo getLogInfo() {
		return logInfo;
	}
	
	/**
	 * insert user's login information into database
	 * @return
	 */
	public ResultInfo editLogInfo(LogInfo li, Profile p){	
		return HibernateUtility.updateProfile((int)uid, li, p);
	}
	
	public Profile getMyProfile() {
		profile = HibernateUtility.serachProfile((int)uid);
		return profile;
	}
	/**
	 * insert user's profile information into database
	 * @return
	 */
	public ResultInfo editProfile(LogInfo li, Profile p){
		//System.out.println("UserInfo");
		return HibernateUtility.updateProfile((int)uid, li, p);
	}

	public Profile getProfile(String name) {
		int nid = HibernateUtility.searchUserId(name);
		if(nid ==0)
			return null;
		else{
			profile = HibernateUtility.serachProfile((int)nid);
			return profile;
		}
	}
	
}
