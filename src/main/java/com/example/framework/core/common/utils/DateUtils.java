package com.example.framework.core.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间格式常量
 * @author linjian 
 * @version 2.0
 * @create_date 2013-5-24 上午10:49:16
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils{
	/**年月时分秒**/
	public static String FORMATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 年月日
	 */
	public static String FORMATE_DATE = "yyyy-MM-dd";
	
	/**
	 * 年月
	 */
	public static String FORMATE_MONTH = "yyyy-MM";
	
	/**
	 * 日
	 */
	public static String AREA_DAY = "dd";
	/**
	 * 年
	 */
	public static String AREA_YEAR = "yyyy";
	/**
	 * 月
	 */
	public static String AREA_MONTH = "MM";
	
	/**
	 * 小时
	 */
	public static String AREA_HOUR = "HH";
	
	/**
	 * 分钟
	 */
	public static String AREA_MINUTE = "mm";
	/**
	 * 秒
	 */
	public static String AREA_SECOND = "ss";
	
	/**
	 * 格式化日期
	 * @author linjian 
	 * @create_date 2013-6-20 下午3:26:12
	 * @param date
	 * @param format
	 * @return 格式化日期结果
	 */
	public static String getFormatDate(Date date, String format){
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 格式化日期
	 * @author liaoyp 
	 * @create_date 2016-8-20 下午3:26:12
	 * @param date
	 * @param format
	 * @return 格式化日期结果
	 * @throws ParseException
	 */
	public static Date getFormatDate(String date, String format) throws ParseException {
			return new SimpleDateFormat(format).parse(date);
	}
	
	/**
	 * 获取日期
	 * @author caoqian 
	 * @create_date 2015-02-09 11:26:12
	 * @param str
	 * @param format
	 * @return Date
	 */
	public static Date getDateByString(String str, String format){
		SimpleDateFormat sf=new SimpleDateFormat(format);
		Date date=null;
		try {
			if(StringUtils.isNotBlank(str)) {
				date = sf.parse(str);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
    
    /**
	 * 获取该月的最后一天
	 * @param date_time
	 * @param source_format 源日期的格式
	 * @param rs_format     转换后的日期格式
	 * @return
	 * @author wuyj
	 * @create_date 2017年3月17日 上午11:09:00
	 */
	public static Date getLastDayOfMonth(String date_time, String source_format, String rs_format){
		if(StringUtils.isBlank(source_format)){
			source_format = FORMATE_TIME;
		}
		if(StringUtils.isBlank(rs_format)){
			rs_format = FORMATE_TIME;
		}
		Calendar cld = Calendar.getInstance();
		try {
		cld.setTime(new SimpleDateFormat(source_format).parse(date_time));
		cld.set(Calendar.DAY_OF_MONTH, cld.getActualMaximum(Calendar.DAY_OF_MONTH));
		cld.set(Calendar.HOUR_OF_DAY, 23);
		cld.set(Calendar.MINUTE, 59);
		cld.set(Calendar.SECOND, 59);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cld.getTime();
	}
}
