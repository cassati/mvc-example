package com.example.framework.core.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日历帮助类
 * @author linjian 
 * @version 2.0
 * @create_date 2013-6-18 上午9:31:32
 */
public class CalendarUtils {
	/**
	 * 时间计算，相减在num加个负号
	 * @author linjian 
	 * @create_date 2013-6-18 上午9:33:10
	 * @param time
	 * @param num
	 * @param area
	 * @return 返回计算结果
	 */
	public static String clac_time(String time, Integer num, String area){
		String rs = "";
		try {
			Date dat = new SimpleDateFormat(DateUtils.FORMATE_TIME).parse(time);
			Calendar cld = Calendar.getInstance();
			cld.setTime(dat);
			//天数相加减
			if(area.equals("dd")){
				cld.add(Calendar.DATE, num);
			}else if(area.equals("MM")){
				//月份相减
				cld.add(Calendar.MONTH, num);
			}
			rs = new SimpleDateFormat(DateUtils.FORMATE_TIME).format(cld.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**两个时间比较
	 * @author linjian 
	 * @create_date 2013-6-18 上午10:19:04
	 * @param time1
	 * @param time2
	 * @return 比较结果，小于0 说明time1 小于 time2，等于0则两个时间相等，大于则time1 大于 time2
	 */
	public static int compare_time(String time1, String time2){
		Calendar cld1 = Calendar.getInstance();
		Calendar cld2 = Calendar.getInstance();
		try {
			cld1.setTime(new SimpleDateFormat(DateUtils.FORMATE_TIME).parse(time1));
			cld2.setTime(new SimpleDateFormat(DateUtils.FORMATE_TIME).parse(time2));
			return cld1.compareTo(cld2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 提取时间到日期
	 * @author linjian 
	 * @create_date 2013-6-18 上午10:25:47
	 * @param time
	 * @return 去掉时分秒
	 */
	public static String getDateByTime(String time){
		return time.split(" ")[0];
	}
	
	/**
	 * 时间计算，相减在num加个负号
	 * @author linjian 
	 * @create_date 2013-6-18 上午9:33:10
	 * @param time
	 * @param num
	 * @param area
	 * @return 返回计算结果
	 */
	public static String clac_time(String time, String formate, Integer num, String area){
		String rs = "";
		try {
			Date dat = new SimpleDateFormat(formate).parse(time);
			Calendar cld = Calendar.getInstance();
			cld.setTime(dat);
			//天数相加减
			if(area.equals("dd")){
				cld.add(Calendar.DATE, num);
			}else if(area.equals("MM")){
				//月份相减
				cld.add(Calendar.MONTH, num);
			}
			rs = new SimpleDateFormat(formate).format(cld.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	/**
	 * 获得当前周- 周一的日期
	 * @author songlc 
	 * @create_date 2016-4-21 9:33:10
	 * @return 返回计算结果
	 */
	public static String getCurrentMonday() {
    	Calendar currentDate = new GregorianCalendar();
    	currentDate.setFirstDayOfWeek(Calendar.MONDAY);
    	currentDate.set(Calendar.HOUR_OF_DAY, 0);
    	currentDate.set(Calendar.MINUTE, 0);
    	currentDate.set(Calendar.SECOND, 0);
    	currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String preMonday = new SimpleDateFormat(DateUtils.FORMATE_DATE).format((Date)currentDate.getTime().clone());;
        return preMonday;
    }
    
	/**
	 * 获得当前周- 周日  的日期
	 * @author songlc 
	 * @create_date 2016-4-21 9:33:10
	 * @return 返回计算结果
	 */
	public static String getPreviousSunday() {
    	Calendar currentDate = new GregorianCalendar();
    	currentDate.setFirstDayOfWeek(Calendar.MONDAY);
    	currentDate.set(Calendar.HOUR_OF_DAY, 23);
    	currentDate.set(Calendar.MINUTE, 59);
    	currentDate.set(Calendar.SECOND, 59);
    	currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    	String preMonday = new SimpleDateFormat(DateUtils.FORMATE_DATE).format((Date)currentDate.getTime().clone());;
        return preMonday;
    }
}
