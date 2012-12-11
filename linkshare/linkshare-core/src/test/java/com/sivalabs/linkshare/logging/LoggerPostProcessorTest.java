package com.sivalabs.linkshare.logging;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LoggerPostProcessorTest 
{
	
	public static void main(String[] args)
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-test.xml");
		System.out.println(ctx);
	}
}