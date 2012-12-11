/**
 * 
 */
package org.sivalabs.jscheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

/**
 * @author Siva
 *
 */
public class QuartzJobBuilder
{
	private QuartzJobBuilder()
	{
	}
	
	public static JobDetail buildJobDetail(QuartzJob quartzJob)
	{
		return JobBuilder.newJob(quartzJob.getClass())
	            .withIdentity(quartzJob.getJobId())
	            .build();
	}
	
	public static Trigger getTrigger(QuartzJob quartzJob)
	{
		return TriggerBuilder.newTrigger()
			    .withIdentity(quartzJob.getTriggerId())
			    .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronSchedule()))
			    .build();
	}
	
}
