/**
 * 
 */
package com.sivalabs.linkshare.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockTwitterService implements TwitterService
{
	private static Logger logger = LoggerFactory.getLogger(MockTwitterService.class);
	
	public void sendMessage(String msg) {
		logger.info("Posting Message to Twitter: "+msg);
	}
}
