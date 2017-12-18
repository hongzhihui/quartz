package com.josehp.demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloScheduler {
	public static void main(String[] args) throws SchedulerException {
		//创建一个JobDetail实例    与hellojob.class绑定
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("myJob", "group1")
				.usingJobData("message","hello myjob1")
				.usingJobData("FloatJobValue",3.14f)
				.build();
		/*System.out.println("jobDetail name :"+jobDetail.getKey().getName());
		System.out.println("jobDetail group :"+jobDetail.getKey().getGroup());
		System.out.println("jobDetail jobClass :"+jobDetail.getKey().getClass().getName());
		*/
		//创建一个Trigger实例  定义立即执行  每隔两秒钟重复执行一次
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTigger", "group1")
				.usingJobData("message","hello mytrigger1")
				.usingJobData("DoubleTriggerValue",2.0D)
				.startNow()
				.withSchedule(SimpleScheduleBuilder
						.simpleSchedule()
						.withIntervalInSeconds(2)
						.repeatForever())
				.build();
		//创建Scheduler实例
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.start();
		
		Date date = new Date();
		SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current Time Is :"+sf.format(date));
		//编写业务逻辑
		System.out.println("Hello World!");
		
		scheduler.scheduleJob(jobDetail,trigger);
	}
}
