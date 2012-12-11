package com.sivalabs.core.logging;

/**
 * Auto injects the underlying implementation of logger into the bean with field
 * having annotation <code>Logger</code>.
 * 
 * Credit where it is due: http://jgeeks.blogspot.in/2008/10/auto-injection-of-logger-into-spring.html
 */
import java.lang.reflect.Field;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import static org.springframework.util.ReflectionUtils.FieldCallback;

public class LoggerInjector implements BeanPostProcessor
{

	public Object postProcessBeforeInitialization(final Object bean, String beanName)
			throws BeansException
	{
		
		ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback()
		{
			public void doWith(Field field) throws IllegalArgumentException,
					IllegalAccessException
			{
				// make the field accessible if defined private
				ReflectionUtils.makeAccessible(field);
				
				if (field.getAnnotation(Slf4JLogger.class) != null)
				{
					//System.err.println("------------postProcessBeforeInitialization------------------"+bean.toString());
					//System.err.println("--------field.getAnnotation()-----------------");
					org.slf4j.Logger logger = LoggerFactory.getLogger(bean.getClass());
					field.set(bean, logger);
					//System.err.println(bean.getClass()+":"+bean.hashCode()+":"+bean.toString());
					//System.err.println("Injected Logger-->"+field.get("logger"));
				}
			}
		});
		return bean;
	}

	public Object postProcessAfterInitialization(final Object bean,
			String beanName) throws BeansException
	{
		return bean;
	}
}
