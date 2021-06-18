package com.eshop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class XDate {
	private static SimpleDateFormat df = new SimpleDateFormat();
	
	public String format(Date date, String resultPattern) {
		df.applyPattern(resultPattern);
		return df.format(date);
	}
	
	public  Date parse(String time, String timePattern) {
		try {
			df.applyPattern(timePattern);
			return df.parse(time);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Date now() {
		return new Date();
	}
	
	@SuppressWarnings("incomplete-switch")
	public static Date add(Date origin, int timeToAdd, TimeUnit unit) {
		long time = origin.getTime();
		switch (unit) {
		case MILLISECONDS:
			time += timeToAdd;
			break;
		case SECONDS:
			time += timeToAdd * 1000;
			break;
		case MINUTES:
			time += timeToAdd * 60 * 1000;
			break;
		case HOURS:
			time += timeToAdd * 60 * 60 * 1000;
			break;
		case DAYS:
			time += timeToAdd * 24 * 60 * 60 * 1000;
			break;
		}
		origin.setTime(time);
		return origin;
	}
}
