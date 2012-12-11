/**
 * 
 */
package com.sivalabs.linkshare.web;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.sivalabs.core.logging.Slf4JLogger;
import com.sivalabs.linkshare.entities.UserAccount;


/**
 * @author siva
 *
 */
public class WebUtils
{
	@Slf4JLogger
	private Logger logger;
	
	private static final String LOGIN_USER_KEY = "LOGIN_USER";
	//private static final String SPRING_SECURITY_USER_KEY = "SPRING_SECURITY_USER_KEY";
	
	
	
	/*@InitBinder
	protected void initBinder(HttpServletRequest request, 
		    ServletRequestDataBinder binder) throws Exception 
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    df.setLenient(false);
	    CustomDateEditor editor = new CustomDateEditor(df, true);
	    binder.registerCustomEditor(Date.class, editor);
	}*/
	
	public static com.sivalabs.linkshare.entities.UserAccount getLoggedInUser(HttpSession session) {
		UserAccount loginUser = (UserAccount) session.getAttribute(LOGIN_USER_KEY);
		/*if(loginUser == null)
		{
			throw new UserNotLoggedinException();
		}*/
		return loginUser;
	}
	
	public static void setLoggedInUser(HttpSession session, UserAccount userAccount) {
		session.setAttribute(LOGIN_USER_KEY, userAccount);
	}
	
	public static boolean isUserLoggedIn(HttpSession session) {
		return (session!=null 
				//&& getCurrentUserName()!=null 
				//&& !getCurrentUserName().equals("guest")
				&& session.getAttribute(LOGIN_USER_KEY) != null);
		//return getCurrentUser()!=null;
	}
	
	
	public static String getCurrentUserName() 
	{
		String username = null;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null && authentication.isAuthenticated())
		{
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			System.out.println("authorities--->"+authorities);
			for (GrantedAuthority grantedAuthority : authorities)
			{
				System.out.println(grantedAuthority.getAuthority());
			}
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
		}
		
		return username;
	}
}
