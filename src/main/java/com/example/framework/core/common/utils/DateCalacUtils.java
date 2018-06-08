package com.example.framework.core.common.utils;

import com.example.framework.core.common.CommonConstant;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期计算辅助类
 * @author linjian 
 * @version 2.0
 * @create_date 2013-6-20 下午2:49:06
 */
public class DateCalacUtils implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**获取当前时间
	 * @author linjian
	 * @create_date 2013-12-23 下午5:37:01
	 * @return 当前时间
	 */
	public static String getCurrentTime(){
		return new SimpleDateFormat(DateUtils.FORMATE_TIME).format(new Date());
	}
	
	/**
	 * 获取当前日期
	 * @author youcb
	 * @version 1.0
	 * @create_date 2015-1-5上午9:56:50
	 * @return
	 */
	public static String getCurrentDate(){
		return new SimpleDateFormat(DateUtils.FORMATE_DATE).format(new Date());
	}
	
	/**
	 * 获取当前月份
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2017年3月1日
	 */
	public static String getCurrentMonth(){
		return new SimpleDateFormat(DateUtils.FORMATE_MONTH).format(new Date());
	}
	
	/**
	 * 获取上月份
	 * @return  
	 * @author youcb 
	 * @date 2018-5-11 下午5:27:31
	 * @version V1.0
	 */
	public static String getLastMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		return new SimpleDateFormat(DateUtils.FORMATE_MONTH).format(calendar.getTime());
	}
	
	/**
	 * 获取今天，0点0分0秒
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2016年12月16日
	 */
	public static Date today(){
		Date today = null;
		try {
			today = new SimpleDateFormat(DateUtils.FORMATE_DATE).parse(getCurrentDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return today;
	}
	
	/**
	 * 获取明天，0点0分0秒
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2016年12月16日
	 */
	public static Date tomorrow(){
		Date today = today();
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.setTime(today);
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		return tomorrow.getTime();
	}
	
	/**
	 * 获取昨天，0点0分0秒
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2016年12月16日
	 */
	public static Date yestoday(){
		Date today = today();
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.setTime(today);
		tomorrow.add(Calendar.DAY_OF_MONTH, -1);
		return tomorrow.getTime();
	}
	
	/**
	 * 获取指定日期的所在月份的开始日期，即yyyy-MM-01 00:00:00
	 * @param dateStr 只需要 yyyy-MM
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2017年3月2日
	 */
	public static Date getStartDateOfMonth(String dateStr){
		if(StringUtils.isBlank(dateStr)) {
			return null;
		}
		if(dateStr.length() < 7) {
			return null;
		}
		//只需要 yyyy-MM
		if(dateStr.length() > 7) {
			dateStr = dateStr.substring(0, 7);
		}
		dateStr = dateStr  + "-01";
		
		Date result = null;
		try {
			result = new SimpleDateFormat(DateUtils.FORMATE_DATE).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取指定日期的所在月份的开始日期，即yyyy-MM-01 00:00:00
	 * @param year  年份yyyy
	 * @param month 以1开始的月份
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2017年3月2日
	 */
	public static Date getStartDateOfMonth(Integer year, Integer month){
		String dateStr = "";
		if(month < 10) {
			dateStr = year + "-0" + month; 
		}else {
			dateStr = year + "-" + month;
		}
		return getStartDateOfMonth(dateStr);
	}
	
	/**
	 * 获取指定日期的所在月份的开始日期，即yyyy-MM-01 00:00:00
	 * @param date 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2017年3月2日
	 */
	public static Date getStartDateOfMonth(Date date){
		if(null == date) {
			return null;
		}
		
		String dateStr = new SimpleDateFormat(DateUtils.FORMATE_DATE).format(date);
		Date result = getStartDateOfMonth(dateStr);
		return result;
	}
	
	/**
	 * 获取下个月的开始日期
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2017年3月3日
	 */
	public static Date getNextMonth(Date date){
		Date d = getStartDateOfMonth(date);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH, 1);
		return c.getTime();
	}
	
	/**
	 * 获取指定日期的第二天，0点0分0秒
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2017年3月7日
	 */
	public static Date getNextDay(Date date){
		String dateStr = new SimpleDateFormat(DateUtils.FORMATE_DATE).format(date);
		
		Date result = null;
		try {
			result = new SimpleDateFormat(DateUtils.FORMATE_DATE).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.setTime(result);
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		return tomorrow.getTime();
	}
	
	/**
	 * 获取指定日期字符串
	 * @author youcb
	 * @version 1.0
	 * @create_date 2015-1-5上午9:56:50
	 * @return
	 */
	public static String getStrByDate(Date date){
		return new SimpleDateFormat(DateUtils.FORMATE_DATE).format(date);
	}
	
	/**
	 * 获取指定日期时间字符串
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2017年6月1日
	 */
	public static String getStrByTime(Date date){
		return new SimpleDateFormat(DateUtils.FORMATE_TIME).format(date);
	}
	
	/**
	 * 计算指定时间加上指定时间区域数量后的结果
	 * @author linjian 
	 * @create_date 2013-7-1 上午11:27:30
	 * @param date 指定时间
	 * @param part 计算部分
	 * @param num 计算数量
	 * @param ret_format 返回结果格式
	 * @return 计算后的时间
	 */
	public static String getDateStamp(String date, String part, int num, String ret_format){
		Calendar cld = Calendar.getInstance();
		try {
			cld.setTime(new SimpleDateFormat(DateUtils.FORMATE_TIME).parse(date));
			if(StringUtils.isNotBlank(part)){
				if(part.equals("date") || part.equals("dd"))
					cld.add(Calendar.DATE, num);
				else if(part.equals("year") || part.equals("yyyy"))
					cld.add(Calendar.YEAR, num);
				else if(part.equals("month") || part.equals("MM"))
					cld.add(Calendar.MONTH, num);
				else if(part.equals("hour") || part.equals("HH"))
					cld.add(Calendar.HOUR, num);
				else if(part.equals("minute") || part.equals("mm"))
					cld.add(Calendar.MINUTE, num);
				else if(part.equals("second") || part.equals("ss"))
					cld.add(Calendar.SECOND, num);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(StringUtils.isNotBlank(ret_format)){
			return new SimpleDateFormat(ret_format).format(cld.getTime());
		}else{
			return new SimpleDateFormat(DateUtils.FORMATE_TIME).format(cld.getTime());
		}
	}
	
	/**获取时间相减差距
	 * @author linjian 
	 * @create_date 2013-7-30 下午8:59:05
	 * @param time1
	 * @param time2
	 * @param part
	 * @return 差值
	 */
	public static int getTimeStamp(String time1, String time2, String part){
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		try {
			cal1.setTime(new SimpleDateFormat(DateUtils.FORMATE_TIME).parse(time1));
			cal2.setTime(new SimpleDateFormat(DateUtils.FORMATE_TIME).parse(time2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long s_times = cal1.getTimeInMillis() - cal2.getTimeInMillis();
		if(StringUtils.isNotBlank(part)){
			if(part.equals("date") || part.equals("dd"))
				return (int)(s_times / 1000 / 60 / 60 / 24);
			else if(part.equals("hour") || part.equals("HH"))
				return (int)(s_times / 1000 / 60 / 60);
			else if(part.equals("minute") || part.equals("mm"))
				return (int)(s_times / 1000 / 60);
			else if(part.equals(DateUtils.AREA_MONTH)){
				return ((Integer.parseInt(time1.substring(0, 4)) - Integer.parseInt(time2.substring(0, 4))) * 12 + (Integer.parseInt(time1.substring(5, 7)) - Integer.parseInt(time2.substring(5, 7))));
			}
		}else{
			throw new RuntimeException("获取时间差部分异常");
		}
		return 0;
	}
	
	
	/**
	 * 获取该月的最后一天
	 * @author linjian 
	 * @create_date 2013-7-2 上午11:46:25
	 * @param date_time 时间种子
	 * @return 该月的最后一天
	 */
	public static String getLastDayOfMonth(String date_time, String rs_format){
		if(StringUtils.isBlank(rs_format)){
			rs_format = DateUtils.FORMATE_TIME;
		}
		Calendar cld = Calendar.getInstance();
		try {
		cld.setTime(new SimpleDateFormat(DateUtils.FORMATE_TIME).parse(date_time));
		cld.set(Calendar.DAY_OF_MONTH, cld.getActualMaximum(Calendar.DAY_OF_MONTH));
		cld.set(Calendar.HOUR_OF_DAY, 23);
		cld.set(Calendar.MINUTE, 59);
		cld.set(Calendar.SECOND, 59);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat(rs_format).format(cld.getTime());
	}
	
	/**
	 * @author linjian 
	 * @create_date 2013-7-30 下午8:55:17
	 * @param date_time1
	 * @param date_time2
	 * @return
	 */
	public static int compare(String date_time1, String date_time2){
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		try {
			cal1.setTime(new SimpleDateFormat(DateUtils.FORMATE_TIME).parse(date_time1));
			cal2.setTime(new SimpleDateFormat(DateUtils.FORMATE_TIME).parse(date_time2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal1.compareTo(cal2);
	}
	
	public static int compare(Date date_time1, Date date_time2){
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date_time1);
		cal2.setTime(date_time2);
		return cal1.compareTo(cal2);
	}
	
	/**
	 * @author hecx 
	 * @create_date 2016-5-04 下午8:55:17
	 * @param date1 日期1  格式：yyyy-MM-dd
	 * @param date2 日期2 格式：yyyy-MM-dd
	 * @return
	 */
	public static int compareByDate(String date1, String date2){
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		try {
			cal1.setTime(new SimpleDateFormat(DateUtils.FORMATE_DATE).parse(date1));
			cal2.setTime(new SimpleDateFormat(DateUtils.FORMATE_DATE).parse(date2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal1.compareTo(cal2);
	}
	/**
	 * 获取该月的第一天
	 * @author linjian 
	 * @create_date 2013-7-2 上午11:46:25
	 * @param date_time 时间种子
	 * @return 该月的最后一天
	 */
	public static String getFirstDayOfMonth(String date_time, String rs_format){
		if(StringUtils.isBlank(rs_format)){
			rs_format = DateUtils.FORMATE_TIME;
		}
		Calendar cld = Calendar.getInstance();
		try {
			cld.setTime(new SimpleDateFormat(DateUtils.FORMATE_TIME).parse(date_time));
			cld.set(Calendar.DAY_OF_MONTH, cld.getActualMinimum(Calendar.DAY_OF_MONTH));
			cld.set(Calendar.HOUR_OF_DAY, 0);
			cld.set(Calendar.MINUTE, 0);
			cld.set(Calendar.SECOND, 0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat(rs_format).format(cld.getTime());
	}
	
	/**
	 * 获得指定日期的前一天
	 * 
	 * @param specifiedDay
	 * @return
	 * @throws Exception
	 */
	public static String getSpecifiedDayBefore(String specifiedDay) {
		String dayBefore = "";
		try {
			if(StringUtils.isNotBlank(specifiedDay)){
				SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
				Calendar c = Calendar.getInstance();
				Date date = dateFormat.parse(specifiedDay);
	
				c.setTime(date);
				int day = c.get(Calendar.DATE);
				c.set(Calendar.DATE, day - 1);
				int hour = c.get(Calendar.HOUR);
				c.set(Calendar.HOUR, hour);
				dayBefore = dateFormat.format(c.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dayBefore;
	}

	/**
	 * 获得指定日期的前n天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayBefore(String specifiedDay, int n) {
		String dayBefore = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
			Calendar c = Calendar.getInstance();
			Date date = dateFormat.parse(specifiedDay);

			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day - n);
			int hour = c.get(Calendar.HOUR);
			c.set(Calendar.HOUR, hour);

			dayBefore = dateFormat.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dayBefore;
	}
	
	/**
	 * 获得指定日期的前n天（包含時分秒）
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedTimeBefore(String specifiedDay, int n) {
		String dayBefore = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_FORMAT);
			Calendar c = Calendar.getInstance();
			Date date = dateFormat.parse(specifiedDay);

			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day - n);

			dayBefore = dateFormat.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dayBefore;
	}

	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
		String dayAfter = "";
		try {
			Calendar c = Calendar.getInstance();
			Date date = null;
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);

			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day + 1);
			dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dayAfter;
	}

	/**
	 * 获得指定日期的后n天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay, int n) {
		String dayAfter = "";
		try {
			Calendar c = Calendar.getInstance();
			Date date = null;

			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);

			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day + n);

			dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dayAfter;
	}

	public static String SicenToComm(double value) { // 保留小数点后3位（四舍五入），且不按科学计数法输出
		String retValue = null;
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		retValue = df.format(value);
		retValue = retValue.replaceAll(",", "");
		return retValue;
	}

	/**
	 * 获得某年某月的第几天
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getMonthDay(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
		return dateFormat.format(c.getTime());
	}

	/**
	 * 获取日期年份
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getYear(String date) throws ParseException {
		date = parseDate(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		return calendar.get(Calendar.YEAR);
	}

	private static String parseDate(String date) throws ParseException {
		if (StringUtils.isNotBlank(date)) {
			if (date.length() < 8) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
				SimpleDateFormat f = new SimpleDateFormat(
						CommonConstant.DATETIME_STORE_DATE_MINUTE_FORMAT);
				date = dateFormat.format(f.parse(date));
			}
		}
		return date;
	}

	/**
	 * 获取日期月份
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getMonth(String date) throws ParseException {
		date = parseDate(date);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
		calendar.setTime(dateFormat.parse(date));
		return (calendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获取日期号
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getDay(String date) throws ParseException {
		date = parseDate(date);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
		calendar.setTime(dateFormat.parse(date));
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取月份起始日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getMinMonthDate(String date) throws ParseException {
		date = parseDate(date);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime()) + " 00:00:00";
	}

	/**
	 * 获取月份最后日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getMaxMonthDate(String date) throws ParseException {
		date = parseDate(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime()) + " 23:59:59";
	}

	/**
	 * 根据指定月份获取月份起始日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getMinMonthDateByMonth(String date, Integer month)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
		date = parseDate(date);
		String m = date.substring(4, 7);
		date = date.replaceFirst(m, "-" + month);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 根据指定月份获取月份最后日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getMaxMonthDateByMonth(String date, Integer month)
			throws ParseException {
		date = parseDate(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
		String m = date.substring(4, 7);
		date = date.replaceFirst(m, "-" + month);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获取下个月份最后日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getNextMaxMonthDate(String date) throws ParseException {
		date = parseDate(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
		String m = date.substring(5, 7);
		date = date.replaceFirst("-" + m, "-" + (Integer.valueOf(m) + 1));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获取下个月份起始日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getNextMinMonthDate(String date) throws ParseException {
		date = parseDate(date);
		String m = date.substring(5, 7);
		date = date.replaceFirst("-" + m, "-" + (Integer.valueOf(m) + 1));
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获取上个月份最后日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getLastMaxMonthDate(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
		date = parseDate(date);
//		String m = date.substring(5, 7);
//		date = date.replaceFirst("-" + m, "-" + (Integer.valueOf(m) - 1));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
//		calendar.set(Calendar.DAY_OF_MONTH,
//				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获取上个月份起始日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getLastMinMonthDate(String date) throws ParseException {
		date = parseDate(date);
//		String m = date.substring(5, 7);
//		date = date.replaceFirst("-" + m, "-" + (Integer.valueOf(m) - 1));
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
//		calendar.set(Calendar.DAY_OF_MONTH,
//				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}
	
	public static int twoDateCalculatingTheNumber(String start, String end) {
		int quot = 0;
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_DATE_FORMAT);
			Date date1 = dateFormat.parse(end);
			Date date2 = dateFormat.parse(start);
			long quot1 = (date1.getTime() - date2.getTime());
			quot1 = (quot1) / 1000 / 60 / 60 / 24;
			quot = (int) quot1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}
	/**
	 * 获得两个时间段之间的小时差
	 * @param start
	 * @param end
	 * @return
	 */
	public static double twoDateCalculatingToHour(String start, String end) {
		double quot = 0;
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_FORMAT);
			Date date1 = dateFormat.parse(end);
			Date date2 = dateFormat.parse(start);
			long quot1 = (date1.getTime() - date2.getTime());
			quot = (quot1) / 1000.0 / 60.0 / 60.0;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}
	
	/**获取某个时间的前N个小时
	 * @author: chenfm
	 * @version 2.0
	 * @date： 日期：2013-8-22 时间：上午11:01:28
	 * @param date 时间
	 * @param hour 小时
	 * @return
	 */
	public static String getOneHoursAgoTime(String date, Integer hour) {
		String oneHoursAgoTime = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_FORMAT);
			long msec = dateFormat.parse(date).getTime() - hour*3600000;
	        oneHoursAgoTime = new SimpleDateFormat(CommonConstant.DATETIME_FORMAT).format(msec);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return oneHoursAgoTime;
    } 
	
	/**
	 * 转换不同格式的日期
	 * @author linhui
	 * @create_date 2013-10-11 下午3:35:01
	 * @param oldDate
	 * @return
	 * @throws ParseException
	 */
	public static String translateFormat(String oldDate, String oldFormat, String newFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
        Date date;
        String newDate;
		try {
			date = sdf.parse(oldDate);
			newDate = new SimpleDateFormat(newFormat).format(date);
		} catch (ParseException e) {
			newDate = oldDate;
		}
        
        return newDate;
	}
	
	/**获取时间段内的月份列表
	 * @author linjian
	 * @create_date 2013-11-5 上午9:39:13
	 * @param fromDate
	 * @param toDate
	 * @return 时间间隔内的月份列表格式例如：2013-09
	 */
	public static List<String> getMonthsFromTo(String fromDate, String toDate){
		List<String> listStr = null;
		try {
			int fromYear = Integer.parseInt(fromDate.split("-")[0]);
			int toYear = Integer.parseInt(toDate.split("-")[0]);
			int fromMonth = Integer.parseInt(fromDate.split("-")[1]);
			int toMonth = Integer.parseInt(toDate.split("-")[1]);
			//如果开始日期小于结束日期
			if(fromYear == toYear && fromMonth <= toMonth || fromYear < toYear){
				int months = (toYear - fromYear) * 12 + (toMonth - fromMonth);
				listStr = new ArrayList<String>();
				for(int i = fromMonth; i <= (fromMonth + months); i++){
					listStr.add((fromYear + i / 13) + "-" + (((i - 1) % 12 + 1) < 10 ? ("0" + ((i - 1) % 12 + 1)) : ((i - 1) % 12 + 1)));
				}
			}else{
				throw new RuntimeException("结束日期不能小于开始日期");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listStr;
	}
	
	/**
	 * 获取时间段内的月份列表
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2017年3月3日
	 */
	public static List<Date> getMonthsFromTo(Date fromDate, Date toDate){
		List<Date> list = new ArrayList<>();
		Date from = getStartDateOfMonth(fromDate);
		Date to = getStartDateOfMonth(toDate);
		Date tmp = null;
		
		if(fromDate.getTime() > toDate.getTime()) {
			tmp = from;
			from = to;
			to = tmp;
		}
		
		tmp = from;
		while(tmp.getTime() <= toDate.getTime()) {
			list.add(tmp);
			tmp = getNextMonth(tmp);
		}
		
		return list;
	}
	
	/**获取两个时间的实际天数差（例如：2014-03-21 12:00:00 到 2014-03-23 01:00:00 天数差为3天）
	 * @author: chenfm
	 * @version 2.0
	 * @date： 日期：2014-3-21 时间：下午3:03:10
	 * @return
	 */
	public static int actualCalculatingDay(String startTime, String endTime){
		SimpleDateFormat format = new SimpleDateFormat(CommonConstant.DATETIME_FORMAT);
		int actual_days = 1;
		try {
			if(endTime.length() == 19 && startTime.length()==19){
				long end = format.parse(endTime).getTime();
				long start = format.parse(startTime).getTime();
				if(end > start){
					actual_days = actual_days + ((int)(end/86400000) - (int)(start/86400000));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actual_days;
	}
	
	/**比较时间大小(time > time2 返回 true)
	 * @author: chenfm
	 * @version 2.0
	 * @date： 日期：2014-3-22 时间：下午2:44:25
	 * @param time
	 * @param time2
	 * @return
	 */
	public static boolean casinoWar(String time, String time2, String date_format){
		boolean bool = false;
		SimpleDateFormat format = new SimpleDateFormat(date_format);
		try {
			if(format.parse(time).getTime()>=format.parse(time2).getTime()){
				bool = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 测试函数
	 * @author linjian 
	 * @create_date 2013-6-20 下午3:07:38
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		//System.out.println(getTimeStamp("2014-03-22 00:00:00", "2014-03-14 00:00:00", DateUtils.AREA_DAY)/3);
		//System.out.println("2013-10-12 00:00:00".substring(0,4));
//		System.out.println(getSpecifiedDayAfter("2014-03-22 00:00:00", 0));
		//System.out.println(getTimeStamp(getSpecifiedDayAfter("2014-11-22", 3)+" 00:00:00", getCurrentTime(), DateUtils.AREA_DAY));
		//System.out.println(twoDateCalculatingToHour(HZ_Date_CalacUtil.getCurrentTime(), "2015-04-25 23:59:59"));
		//System.out.println(dateDiff( "2015-03-28 14:00:00","2015-03-29 01:00:00", DateUtils.FORMATE_TIME));
		/*System.out.println(getMaxMonthDate(getCurrentDate()));
		System.out.println(getLastMaxMonthDate(getCurrentDate()));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		System.out.println(sdf.format(cal.getTime()));*/
//		SimpleDateFormat sdf_month=new SimpleDateFormat("yyyy-MM");
//		SimpleDateFormat sdf_date=new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal=Calendar.getInstance();
//		String date="2015-02-01";
//		cal.setTime(sdf_date.parse(date));
//		//cal.add(Calendar.MONTH, -1);
//		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//		String current_month_full=sdf_date.format(cal.getTime())+" 23:59:59";
//		String current_month=sdf_month.format(cal.getTime());
//		
//		cal=Calendar.getInstance();
//		cal.setTime(sdf_date.parse(date));
//		cal.add(Calendar.MONTH, -1);
//		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//		String last_month_full=sdf_date.format(cal.getTime())+" 23:59:59";
//		System.out.println(current_month_full);
//		System.out.println(current_month);
//		System.out.println(last_month_full);
		//System.out.println(HZ_Date_CalacUtil.actualCalculatingDay("2014-07-29 17:05:25", "2015-07-29 11:39:28"));
//		System.out.println(DateCalacUtils.daysDiff("2016-11-22","2016-11-23"));
//        String StrD ="2017-02-03 23:00:00";
//        SimpleDateFormat sdfd =new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
//        Date dat =sdfd.parse(StrD);
//		System.out.println(DateCalacUtils.daysBetween(new Date(), dat));
		System.out.println(getLastMonth());
		System.out.println(getFirstDayOfMonth(0));
	}
	
	/**
	 * 获取两个时间小时差(不足一小时是算一小时)
	 * @author linhui
	 * @create_date 2014-3-27 上午9:19:07
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @return
	 */
	public static int dateDiff(String startTime, String endTime,
                               String format) {
        // 按照传入的格式生成一个simpledateformate对象   
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数   
        long nh = 1000 * 60 * 60;// 一小时的毫秒数   
        long nm = 1000 * 60;// 一分钟的毫秒数   
        long diff;   
        long day = 0;   
        long hour = 0;   
        long min = 0;   
        // 获得两个时间的毫秒时间差异   
        try {   
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();   
            day = diff / nd;// 计算差多少天   
            hour = diff % nd / nh + day * 24;// 计算差多少小时   
            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟   
            //不足一小时以一小时计算
            if(min%60>0){
            	hour = hour +1;
            } 
        } catch (ParseException e) {
            e.printStackTrace();   
        }   
          return (int)hour;
    }
	
	/**
	 * 获取两个时间的天数差(不足一天算一天)
	 * @author caixx
	 * @create_time 2015-5-13 下午3:16:21
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static int daysBetween(String time1, String time2, String format) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		long days = 0;
		try {
			cal1.setTime(new SimpleDateFormat(format).parse(time1));
			cal2.setTime(new SimpleDateFormat(format).parse(time2));
			// 间隔天数
			long s_times = cal1.getTimeInMillis() - cal2.getTimeInMillis();
			days = s_times / (24 * 60 * 60 * 1000);
			if (s_times % (24 * 60 * 60 * 1000) > 0) {
				days += 1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return (int)days;
	} 
	/**
	 * 返回两个日期相差的天数，不比较时间部分。(start - end)
	 * @author zhengkw
	 * @create_date 2015-5-15下午1:44:29
	 * @param start 开始时间(yyyy-MM-dd)
	 * @param end   结束时间(yyyy-MM-dd)
	 * @return 相差的天数
	 */
	public static int daysDiff(String start, String end){
		if(StringUtils.isBlank(start) || StringUtils.isBlank(end)
				|| start.length() < 10 || end.length() < 10) {
			throw new RuntimeException("日期格式不正确，无法比较。请使用指定格式：yyyy-MM-dd");
		}
		
		if(start.length() > 10) {
			start = start.substring(0, 10);
		}
		start += " 00:00:00";
		
		if(end.length() > 10) {
			end = end.substring(0, 10);
		}
		end += " 00:00:00";
		
		int diff = 0;
		try {
			diff = DateCalacUtils.getTimeStamp(start, end, DateUtils.AREA_DAY);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("日期格式不正确，无法比较。请使用指定格式：yyyy-MM-dd");
		}
		return diff;
	}
	
	/**
	 * 获取一年后的时间
	 * @author jintong
	 * @version 2.0
	 * @create_date 2015-9-9下午2:25:24
	 * @param date
	 * @return
	 */
	public static String getNextYearDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.FORMATE_DATE);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(date));
			calendar.add(Calendar.DAY_OF_MONTH, 365);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 获得指定日期的前N个月
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedMonthBefore(String specifiedDay, int mth) {
		String monthBefore = "";
		try {
			Calendar c = Calendar.getInstance();
			Date date = null;
			date = new SimpleDateFormat("yy-MM").parse(specifiedDay);

			c.setTime(date);
			int month = c.get(Calendar.MONTH);
			c.set(Calendar.MONTH, month - mth);
			monthBefore = new SimpleDateFormat("yyyy-MM").format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return monthBefore;
	}

	/**
	 * 判断日期是否相同
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2016年11月18日
	 */
	public static Boolean isSameDate(Date date1, Date date2) {
		if(null == date1 || null == date2) {
			return false;
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
				&& cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE));
	}
	
	/**
	 * 计算逾期天数。
	 * @author zhengkw
	 * @create_date 2015-5-9下午5:06:15
	 * @param dueDay 还款截止日期(yyyy-MM-dd)
	 * @return
	 */
	public static String calcOverdueDays(String dueDay){
		int d = DateCalacUtils.daysDiff(DateCalacUtils.getCurrentDate(), dueDay);
		if(d < 0) {
			d = 0;
		}
		return ""+d;
	}
	
	
	/**获取时间相减差距（忽略时分秒）
	 * @author liaoyp 
	 * @create_date 2017-1-13 16:33:44
	 * @param time1
	 * @param time2
	 * @return 差值
	 */
	public static int daysBetween(Date time1, Date time2){
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(time1);
		cal2.setTime(time2);
		//设置时间为0时   
		cal1.set(java.util.Calendar.HOUR_OF_DAY, 0);
		cal1.set(java.util.Calendar.MINUTE, 0);
		cal1.set(java.util.Calendar.SECOND, 0);
		cal1.set(java.util.Calendar.MILLISECOND, 0);

		cal2.set(java.util.Calendar.HOUR_OF_DAY, 0);
		cal2.set(java.util.Calendar.MINUTE, 0);
		cal2.set(java.util.Calendar.SECOND, 0);
		cal2.set(java.util.Calendar.MILLISECOND, 0);
		long s_times = cal1.getTimeInMillis() - cal2.getTimeInMillis();
		return (int)(s_times / 1000 / 60 / 60 / 24);
	}

	/**获取时间相减差距（忽略时分秒）
	 * @author hecx
	 * @create_date 2017-1-13 16:33:44
	 * @param time1
	 * @param time2
	 * @return 差值
	 * @throws Exception
	 */
	public static int daysBetween(String time1, String time2) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.FORMATE_DATE);
		Date date1= sdf.parse(time1);
		Date date2= sdf.parse(time2);
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		//设置时间为0时
		cal1.set(java.util.Calendar.HOUR_OF_DAY, 0);
		cal1.set(java.util.Calendar.MINUTE, 0);
		cal1.set(java.util.Calendar.SECOND, 0);
		cal1.set(java.util.Calendar.MILLISECOND, 0);

		cal2.set(java.util.Calendar.HOUR_OF_DAY, 0);
		cal2.set(java.util.Calendar.MINUTE, 0);
		cal2.set(java.util.Calendar.SECOND, 0);
		cal2.set(java.util.Calendar.MILLISECOND, 0);
		long s_times = cal1.getTimeInMillis() - cal2.getTimeInMillis();
		return (int)(s_times / 1000 / 60 / 60 / 24);
	}

	/**
	 * @Description 操作时间日期，返回23:59:59
	 * @author liaoyp
	 * @create_time 2017年1月13日下午5:20:50
	 * @param date
	 * @param num  增加天数或减少天数
	 * @return
	 *    Date
	 */
	public static Date operDate(Date date, int num){
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Calendar cld = Calendar.getInstance();

		cld.setTime(date);
		cld.add(Calendar.DATE, num);
		cld.set(java.util.Calendar.HOUR_OF_DAY, 23);
		cld.set(java.util.Calendar.MINUTE, 59);
		cld.set(java.util.Calendar.SECOND, 59);
		return cld.getTime();
	}

	/**
	 * @Description 获取指定时间的月份天数
	 * @author zhoull
	 * @create_date 2017-9-20
	 * @param date
	 * @return
	 */
	public static int getDaysOfMonth(String date){
		int days = 1;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(date));
			days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}

	/**
	 * 获取当前日期前后m月份的第一天，默认上个月
	 * @param m
	 * @return
	 * @throws ParseException
	 * @author youcb
	 * @date 2018-4-24 上午9:31:59
	 * @version V1.0
	 */
	public static String getFirstDayOfMonth(Integer m) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATETIME_FORMAT);
		Calendar calendar = Calendar.getInstance();
		if(m==null){m=-1;}
		calendar.add(Calendar.MONTH,m );
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(java.util.Calendar.MILLISECOND, 0);
		return dateFormat.format(calendar.getTime());
	}
	
	/**
	 * @Description 判断指定时间是否是月末
	 * @author zhoull 
	 * @create_date 2017-10-1
	 * @param date
	 * @return
	 */
	public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); 
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true; 
        } 
        return false; 
    } 
}
