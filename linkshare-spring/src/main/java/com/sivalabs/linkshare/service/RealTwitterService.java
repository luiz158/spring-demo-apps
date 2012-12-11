/**
 * 
 */
package com.sivalabs.linkshare.service;

import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.message.GenericMessage;

public class RealTwitterService implements TwitterService
{

	private MessageChannel twitterOutbound;
	public void setTwitterOutbound(MessageChannel twitterOutbound)
	{
		this.twitterOutbound = twitterOutbound;
	}
	@SuppressWarnings("rawtypes")
	public void sendMessage(String msg) {
		@SuppressWarnings("unchecked")
		Message message = new GenericMessage(msg);

		twitterOutbound.send(message);
	}
}
