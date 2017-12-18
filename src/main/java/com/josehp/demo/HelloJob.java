package com.josehp.demo;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {
	
	private String message;
	private Float FloatJobValue;
	private Double DoubleTriggerValue;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Float getFloatJobValue() {
		return FloatJobValue;
	}
	
	public void setFloatJobValue(Float floatJobValue) {
		FloatJobValue = floatJobValue;
	}
	
	public Double getDoubleTriggerValue() {
		return DoubleTriggerValue;
	}
	
	public void setDoubleTriggerValue(Double doubleTriggerValue) {
		DoubleTriggerValue = doubleTriggerValue;
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Date date = new Date();
		SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current Exec Time Is :"+sf.format(date));
		//编写业务逻辑
	//	System.out.println("Hello World!");
		JobKey key =context.getJobDetail().getKey();
		System.out.println("My name and group are "+key.getName()+":"+key.getGroup());
		TriggerKey triggerKey = context.getTrigger().getKey();
		System.out.println("My name and group are "+triggerKey.getName()+":"+triggerKey.getGroup());
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		JobDataMap tdataMap =context.getTrigger().getJobDataMap();
		//合并
	//	JobDataMap merMap =context.getMergedJobDataMap();//如果key一样   tigger会覆盖job的值
		
	//	String jobMsg= dataMap.getString("message");
	//	Float jobFloatValue= dataMap.getFloat("FloatJobValue");
		
	//	String triggerMsg= tdataMap.getString("message");
	//	Double triggerDoubleValue= tdataMap.getDouble("DoubleTriggerValue");
		System.out.println("jobMsg is "+message);
		System.out.println("jobFloatValue is "+FloatJobValue);
		System.out.println("triggerMsg is "+message);
		System.out.println("triggerDoubleValue is "+DoubleTriggerValue);
	}
	
	
}
