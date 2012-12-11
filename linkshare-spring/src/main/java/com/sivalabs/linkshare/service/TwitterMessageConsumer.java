package com.sivalabs.linkshare.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.Message;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.social.twitter.api.Tweet;
//import org.springframework.stereotype.Component;

/**
 * Twitter Message Consumer using the Service Activator
 * 
 */
//@Component
public class TwitterMessageConsumer {

	private static Logger LOG = LoggerFactory.getLogger(TwitterMessageConsumer.class);

	@ServiceActivator
	public void consume(Message<Tweet> message) {
		Tweet tweet = message.getPayload();
		LOG.info("Fetched Tweet Text from @" + tweet.getFromUser() + " # "+ tweet.getText());
	}
}