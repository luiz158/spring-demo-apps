/**
 * 
 */
package org.sivalabs.jscheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Siva
 *
 */
public abstract class QuartzJob implements Job
{
	private JobExecutionContext jec = null;
	
	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException
	{
		JobDataMap jobDataMap = jec.getMergedJobDataMap();
		jobDataMap.putAll(getJobData());
		this.jec = jec;
		executeJob();
	}
	
	public JobExecutionContext getJobExecutionContext(){
		return this.jec;
	}
	
	public abstract void execute();
	
	public abstract String getJobId();
	
	public abstract String getCronSchedule();
	
	public String getTriggerId()
	{
		return getJobId()+"_trg";
	}
	
	public Map<String, Object> getJobData()
	{
		return new HashMap<String, Object>();
	}
	
	public Object getMergedJobDataValue(String key)
	{
		return getJobExecutionContext().getMergedJobDataMap().get(key);
	}
	private void executeJob()
	{
		insertJobStartDetails();
		execute();
		updateJobExecutionStatus();
	}
	
	private void insertJobStartDetails()
	{
		//TODO Add logic to store Job Start info	
		System.err.println("Job["+getJobId()+"] started at "+new Date());
	}
	
	private void updateJobExecutionStatus()
	{
		//TODO Add logic to update Job Execution Status		
		System.err.println("Job["+getJobId()+"] completed at "+new Date());
	}

	
}
