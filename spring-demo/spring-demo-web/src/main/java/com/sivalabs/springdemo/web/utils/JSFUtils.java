package com.sivalabs.springdemo.web.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Siva
 *
 */
public class JSFUtils
{
	private JSFUtils()
	{
	}
	
	public static void addInfoMessage(String property, String message)
	{
		FacesContext.getCurrentInstance().addMessage(property, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	public static void addErrorMessage(String property, String message)
	{
		FacesContext.getCurrentInstance().addMessage(property, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}

}
