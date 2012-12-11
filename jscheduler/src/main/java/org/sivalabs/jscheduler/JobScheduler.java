/**
 * 
 */
package org.sivalabs.jscheduler;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author Siva
 *
 */
public class JobScheduler
{
	private Scheduler scheduler;
	//private boolean schedulerStarted = false;
	
	void start()
	{
		try
		{
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			//schedulerStarted = true;
		} catch (SchedulerException e)
		{
			throw new JobSchedulingException(e);
		}
	}
	
	void shutdown()
	{
		try
		{
			if(!isShutdown())
			{
				scheduler.shutdown(true);
			}
			else
			{
				System.err.println("Scheduler is already shutdown.");
			}
		} catch (SchedulerException e)
		{
			throw new JobSchedulingException(e);
		}
	}
	
	void scheduleJob(QuartzJob quartzJob)
	{
		if(isShutdown())
		{
			throw new JobSchedulingException("Scheduler is already shutdown.");
		}
		
		JobDetail jobDetail = QuartzJobBuilder.buildJobDetail(quartzJob);
		Trigger trigger = QuartzJobBuilder.getTrigger(quartzJob);
		try
		{
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e)
		{
			throw new JobSchedulingException(e);
		}
	}
	
	boolean isShutdown()
	{
		try
		{
			return scheduler.isShutdown();
		} catch (SchedulerException e)
		{
			e.printStackTrace();
		}
		return true;
	}
}
