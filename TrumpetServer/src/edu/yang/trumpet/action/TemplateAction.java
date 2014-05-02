package edu.yang.trumpet.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import edu.yang.trumpet.dmo.LoginUserList;
import edu.yang.trumpet.model.ResultInfo;

/**
 * super class of all action
 * @author daiyang
 *
 */
public abstract class TemplateAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware{  
	  
    private static final long serialVersionUID = -989477296829078690L;  
  
    private HttpServletRequest request;  
    private HttpServletResponse response;  
    private String format;  
    public int division;
    
    //private HttpSession session;
    
    private SessionMap<String, Object> userSession ;
    
    public void setSession(Map<String, Object> session) {
     
       userSession = (SessionMap)session ;
     
    }
    
    public SessionMap<String, Object> getSession() {
    	return userSession;
    }
      
    public String getFormat() {  
        return format;  
    }  
  
    public void setFormat(String format) {  
        this.format = format;  
    }  
  
    public void setServletRequest(HttpServletRequest request) {  
        this.request = request;  
    }  
  
    public void setServletResponse(HttpServletResponse response) {  
        this.response = response;  
    }  
    
    /**
     * send json object to client
     * @param jo
     */
    public void respondJSONObject(JSONObject jo) {
    	try {  
            this.response.setCharacterEncoding("UTF-8");  
            this.response.getWriter().write(jo.toString());
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
    }
    
    /**
     * send json array to client
     * @param ja
     */
    public void respondJSONArray(JSONArray ja) {
    	try {  
            this.response.setCharacterEncoding("UTF-8");  
            this.response.getWriter().write(ja.toString());  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
    }
    
    /**
     * get json object from client
     * @return
     */
    public JSONObject requestJSON(){
    	java.io.BufferedReader rd;
		try {
			rd = request.getReader();
			String str = rd.readLine();
			System.out.println(str);
	        if (rd!=null) {
	        	return new JSONObject(str);
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    
    public boolean checkLogin() {
    	//System.out.println("login check start");
    	//session = request.getSession(false);
    	//System.out.println(session.getAttribute("uid"));
    	//if(session.getAttribute("uid") == null){
    	if(!userSession.containsKey("uid")){
    		System.out.println("login failed");
    		return false;
    	}
    	else{
    		System.out.println("login succeed " + userSession.get("uid"));
    		return true;
    	}
    	
    }
    
    public void logout() {
    	ResultInfo ri;
		if(checkLogin()){
			userSession.invalidate();
			ri = new ResultInfo("Logout successful!",true);
		}
		else{
			ri = new ResultInfo("already logout!", false);
			
		}
		respondJSONObject(ri.toJson());
    }
    
    public void main(){
    	if(checkLogin()) {
    		//System.out.println("start to run");
    		run();
    		//System.out.println("run finished");
    	}
    	else 
    		respondJSONObject(new ResultInfo("not login, please login", false).toJson());
    }
    
    public int getUid(){
    	//System.out.println("getuid start   "+userSession.get("uid"));
    	int uid = Integer.parseInt(userSession.get("uid").toString());
    	//System.out.println("getuid :"+uid);
    	return uid;
    	//System.out.println("getuid finish");
    }
  
    public abstract void run();

}
