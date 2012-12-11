/**
 * 
 */
package com.sivalabs.linkshare.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sivalabs.core.logging.Slf4JLogger;
import com.sivalabs.linkshare.web.UserNotLoggedinException;
import com.sivalabs.linkshare.web.WebUtils;


/**
 * @author skatam
 *
 */
//@Component
public class LoginCheckInterceptor extends HandlerInterceptorAdapter
{
	@Slf4JLogger
	private Logger logger;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length());
		logger.debug("Request Path :"+ path);
		boolean go = true;
		if (!path.startsWith("/login.htm") 
				&& !path.startsWith("/registration.htm") 
				&& !path.startsWith("/resetPwd.htm") 
				&& !path.startsWith("/forgotPwd.htm")) 
		{
			//logger.debug("Secured URL : "+uri);
			try {
				boolean userLoggedIn =  WebUtils.isUserLoggedIn(request.getSession(false));
				if (!userLoggedIn) {
					//TODO go = false;
				}
			} catch (UserNotLoggedinException e) {
				logger.debug("UserAccount not loggedin");
				//TODO go = false;
			}
			if(!go){
				logger.debug("UserAccount not loggedin. Redirecting to Login screen.");
				response.sendRedirect("login.htm");
			}
		}
		
		return go;
	}
	
}
