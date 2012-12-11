/**
 * 
 */
package com.sivalabs.linkshare.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Siva
 *
 */
public class FacesUtils
{
	public static void addMessage(String msg){
		
		FacesContext.getCurrentInstance()
		.addMessage(FacesMessage.FACES_MESSAGES, new FacesMessage(msg));
	}
	
	public static UserContext getUserContext()
	{
		UserContext userContext = (UserContext) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USER_CTX_KEY");
		return userContext;
	}
}
