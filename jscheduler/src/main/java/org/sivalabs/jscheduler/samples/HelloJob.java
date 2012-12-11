/**
 * 
 */
package org.sivalabs.jscheduler.samples;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.sivalabs.jscheduler.QuartzJob;

/**
 * @author Siva
 *
 */
public class HelloJob extends QuartzJob 
{

	@Override
	public void execute()
	{
		System.err.println("Hello Job :"+new Date());	
		System.err.println("Say Hello to "+getMergedJobDataValue("NAME"));
	}

	@Override
	public String getJobId()
	{
		return "HelloJobId";
	}

	@Override
	public String getCronSchedule()
	{
		return "0/5 * * * * ?";
	}
	@Override
	public Map<String, Object> getJobData()
	{
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("NAME", "siva");
		return data;
	}
}
