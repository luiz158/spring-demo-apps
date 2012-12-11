package com.sivalabs.linkshare.web.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivalabs.linkshare.web.UserContext;


public class LoginCheckFilter implements Filter 
{
	private static final Logger logger = LoggerFactory.getLogger(LoginCheckFilter.class);
	
	private static List<String> publicURLPatterns = new ArrayList<String>();
	
	public void init(FilterConfig fConfig) throws ServletException {
		
		publicURLPatterns.add("/index.xhtml");
		publicURLPatterns.add("/index.jsf");
		publicURLPatterns.add("/index.jsp");
		
		publicURLPatterns.add("/login.xhtml");
		publicURLPatterns.add("/login.jsf");
		
		publicURLPatterns.add("/logout.xhtml");
		publicURLPatterns.add("/logout.jsf");
		
		publicURLPatterns.add(".css.jsf");
		publicURLPatterns.add(".js.jsf");
	}
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
		logger.debug("URI: "+uri);
		for (int i = 0; i < publicURLPatterns.size(); i++)
		{
			if(uri.endsWith(publicURLPatterns.get(i))){
				logger.debug("It is a pulic URI matching with :"+publicURLPatterns.get(i));
				chain.doFilter(request, response);
				return;
			}
		}
		
		UserContext userContext = (UserContext) httpRequest.getSession().getAttribute("USER_CTX_KEY");
		
		if(userContext == null){
			logger.debug("User not logged in. Redirecting to Login Page.");
			httpResponse.sendRedirect("login.jsf");
		}else{
			logger.debug("User already logged in. Continue...");
			chain.doFilter(request, response);
		}
	}
	
	

}
