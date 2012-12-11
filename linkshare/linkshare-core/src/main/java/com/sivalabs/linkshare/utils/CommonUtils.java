/**
 * 
 */
package com.sivalabs.linkshare.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Siva
 *
 */
public class CommonUtils
{
	public static String generateRandomString(int length)
	{
		return RandomStringUtils.randomAlphanumeric(length);
		//return UUID.randomUUID().toString();
	}

}
