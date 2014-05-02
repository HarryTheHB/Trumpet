package edu.yang.trumpet.action;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.yang.trumpet.dmo.UserInfo;
import edu.yang.trumpet.dmo.LoginUserList;
import edu.yang.trumpet.model.LogInfo;
import edu.yang.trumpet.model.Profile;
import edu.yang.trumpet.model.ResultInfo;

/**
 * Handle the register action from client
 * @author daiyang
 *
 */
public class RegisterActionTest extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	private HttpServletRequest request;  
    private HttpServletResponse response;
	private static final long serialVersionUID = -1241097291325077859L;

	@Override
	public void setServletRequest(HttpServletRequest request) {  
        this.request = request;  
    }  
  
    public void setServletResponse(HttpServletResponse response) {  
        this.response = response;     
    }  
    
    /**
     * perform the function of register
     * @param li location
     * @param p profile
     * @return result and uid
     */
    /*
    public ResultInfo register(LogInfo li, Profile p){
		UserInfo info = new UserInfo(li, p);
		return info.insertLogInfo();
	}
    */
    /**
     * handle the request of register
     * 
     */
    public void run(){
    	System.out.println("register start \n");
    	try {
    		if(request != null)
    			System.out.println("request not null \n");
//    		String parameterName = null;
//    		Enumeration enumeration = request.getParameterNames();
//    		
    		
    		Enumeration eNames = request.getParameterNames();
            while (eNames.hasMoreElements()) {
               String name = (String) eNames.nextElement();
               String value = normalize(request.getParameter(name));
               System.out.println("name="+ name + " value=" + value + "\n");
            }
            
            java.io.BufferedReader rd = request.getReader();
            String str;
            while ( (str=rd.readLine())!=null) {
            
//            while (enumeration.hasMoreElements()) {
//                parameterName = (String) enumeration.nextElement();
//                System.out.println("Parameter = " + parameterName);
//            }
    		//System.out.println("parameter name not null. It's "+parameterName+"\n");
    			
    		//String name = request.getParameterNames().toString();
    		//if(request.getParameter("logLInfo") != null){
    			JSONObject logJO = new JSONObject(str);
    			System.out.println(logJO.toString());
    			System.out.println(logJO.getString("email"));
    			System.out.println(logJO.getString("password"));
    			//JSONObject proJO = new JSONObject(request.getParameter("profile"));
    			System.out.println("parameter not null \n");
    			if(logJO != null) {
    				LogInfo li = new LogInfo(logJO);	
    				System.out.println("logInfo \n");
    				System.out.println("Email: "+li.getEmail()+" Password: "+li.getPassword());
			//}
    			}
            }
			//ResultInfo rm = register(new LogInfo(logJO), new Profile(proJO));
			//LoginUserList.getInstance().addUser(Integer.parseInt(rm.getMessage()));
    		//System.out.println("this is a test \n");
			ResultInfo rm = new ResultInfo("3", false);		
			this.response.setCharacterEncoding("UTF-8");  
            this.response.getWriter().write(rm.toJson().toString()); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private String normalize(String value)
    {
       StringBuffer sb = new StringBuffer();
       for (int i = 0; i < value.length(); i++) {
          char c = value.charAt(i);
          sb.append(c);
          if (c == ';')
             sb.append("<br>");
       }
       return sb.toString();
    }

}
