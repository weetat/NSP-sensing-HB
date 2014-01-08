package com.ncs.nsp.sensing.interceptor;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ncs.nsp.sensing.entity.SBIUser;
import com.ncs.nsp.sensing.manager.SBIUserManager;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

//import sg.com.ncs.util.Logger;

@SuppressWarnings("serial")
public class NSPInterceptor implements Interceptor, Serializable {
	private Log logger = LogFactory.getLog(NSPInterceptor.class);
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		logger.info("---- intercept NSPInterceptor start ---");
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String user_agent=request.getHeader("User-Agent");
		String uid=request.getHeader("UID");
		logger.info("---> request user-agent :" + user_agent);
		logger.info("---> request uid :" + uid);
	    // nsp uid not found
	    if(uid==null){
	    	logger.info("--- REDIRECT user to nsp log in ---");
	    	logger.info("---- intercept NSPInterceptor end ---");
	    	return null;
	    }
	    //nsp uid found
	    if(uid!=null){
	    	SBIUserManager sbiUserManager=new SBIUserManager();
	    	SBIUser sbiUser = sbiUserManager.querySBIUser(uid);
	        if(sbiUser!=null){
	    	logger.info("--- VALID log in ---");
	    	logger.info("--- sbiuser userid : " + sbiUser.getUser_id()+" ---");
	    	logger.info("---- intercept NSPInterceptor end ---");
	        }else{
	          return Action.ERROR;
	        }
	    }
	 	return actionInvocation.invoke();
		 
	}

}
