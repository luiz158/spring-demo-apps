/**
 * 
 */
package org.sivalabs.jscheduler;

import org.sivalabs.jscheduler.samples.HelloJob;

/**
 * @author Siva
 *
 */
public class JobSchedulerTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		JobScheduler jobScheduler = new JobScheduler();
		jobScheduler.start();
		//jobScheduler.shutdown();
		//Thread.sleep(10000);
		QuartzJob quartzJob = new HelloJob();
		jobScheduler.scheduleJob(quartzJob);
		//jobScheduler.shutdown();
	}

}
