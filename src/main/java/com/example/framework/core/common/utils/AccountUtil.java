package com.example.framework.core.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;


/**
 * 客户账户帮助类
 * @author linjian 
 * @version 2.0
 * @create_date 2013-6-14 下午5:48:44
 */
public class AccountUtil {
	
	//账户金额默认格式(带2位小数)
    public static final String MONEY_DEFAULT_FORMAT="#.##";
	
	/**根据格式四舍五入
	 * @author linjian 
	 * @create_date 2013-7-26 下午5:12:02
	 * @param num
	 * @param format
	 * @return 结果
	 */
	public static String format(String num, String format){
		if(StringUtils.isBlank(num))return "0";
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(Double.parseDouble(num));
	}
	
	/**
	 * 根据金额加密
	 * @author linjian 
	 * @create_date 2013-6-14 下午5:49:57
	 * @param balance
	 * @return
	 */
	public static String serByBalance(String balance){
		return encrypt(balance);
	}
	
	public static void main(String[] args) {
//		System.out.println(sum("1979.4950000000001", "134.2", "#.##"));
//		System.out.println("123".equals(null));
//		System.out.println(new DecimalFormat("#.##").format(1979.4950000000001));// 1979.5
//
//		System.out.println("默认舍入：");
//		DecimalFormat df1 = new DecimalFormat("#.##");
//		System.out.println(1.185 + " -> " + df1.format(1.185));// 1.18
//		System.out.println(1.175 + " -> " + df1.format(1.175));// 1.18
//		
//		System.out.println("标准四舍五入：");
//		DecimalFormat df2 = new DecimalFormat("#.##");
//		df2.setRoundingMode(RoundingMode.HALF_UP);
//		System.out.println(1.185 + " -> " + df2.format(1.185));// 1.19
//		System.out.println(1.175 + " -> " + df2.format(1.175));// 1.18
		System.out.println(encrypt("2522.10"));;
	}
	
	/**
	 * 字符串数字相加
	 * @author linjian 
	 * @create_date 2013-6-14 下午6:10:06
	 * @param num1
	 * @param num2
	 * @return 计算结果
	 */
	public static String sum(String num1, String num2, String format){
		if(StringUtils.isBlank(num1))num1 = "0";
		if(StringUtils.isBlank(num2))num2 = "0";
		DecimalFormat df = new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.HALF_UP);
		Double d1 = new Double(num1);
		Double d2 = new Double(num2);
		return df.format(d1 + d2).toString();
	}
	
	/**
	 * 减法
	 * @author linjian 
	 * @create_date 2013-7-1 上午10:42:29
	 * @param num1
	 * @param num2
	 * @param format
	 * @return 差
	 */
	public static String subtraction(String num1, String num2, String format){
		if(StringUtils.isBlank(num1))num1 = "0";
		if(StringUtils.isBlank(num2))num2 = "0";
		DecimalFormat df = new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.HALF_UP);
		Double d1 = new Double(num1);
		Double d2 = new Double(num2);
		return df.format(d1 - d2).toString();
	}
	
	/**
	 * 除法
	 * @author linjian 
	 * @create_date 2013-7-1 上午10:25:18
	 * @param num1
	 * @param num2
	 * @param format
	 * @return 商
	 */
	public static String division(String num1, String num2, String format){
		if(StringUtils.isBlank(num1))num1 = "0";
		if(StringUtils.isBlank(num2))num2 = "0";
		if(Double.valueOf(num2) == 0d){
			return "0";
		}
		DecimalFormat df = new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.HALF_UP);
		Double d1 = new Double(num1);
		Double d2 = new Double(num2);
		return df.format(d1 / d2).toString();
	}
	
	/**
	 * 乘法
	 * @author linjian 
	 * @create_date 2013-7-1 上午10:41:36
	 * @param num1
	 * @param num2
	 * @param format
	 * @return 积
	 */
	public static String multiplication(String num1, String num2, String format){
		if(StringUtils.isBlank(num1))num1 = "0";
		if(StringUtils.isBlank(num2))num2 = "0";
		DecimalFormat df = new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.HALF_UP);
		Double d1 = new Double(num1);
		Double d2 = new Double(num2);
		return df.format(d1 * d2).toString();
	}

	/**
	 * 人民币转积分
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2017年1月12日
	 */
	public static String rmbToIntegral(String rmb) {
		rmb = format(rmb, "#.##");
		return multiplication(rmb, "100", "#");
	}
	
	/**
	 * 人民币转积分
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2017年1月12日
	 */
	public static Double rmbToIntegral(Double rmb) {
		return Double.parseDouble(rmbToIntegral(String.valueOf(rmb)));
	}
	
	/**
	 * 积分转人民币
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2017年1月12日
	 */
	public static String integralToRmb(String integral) {
		integral = format(integral, "#");
		return division(integral, "100", "#.##");
	}
	
	/**
	 * 积分转人民币
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2017年1月12日
	 */
	public static Double integralToRmb(Double integral) {
		return Double.parseDouble(integralToRmb(String.valueOf(integral)));
	}
	
	/**
	 *  除法  直接截取，不进行四舍五入
	 * @author liaoyp
	 * @create_time 2016-12-27下午8:36:52
	 * @param num1
	 * @param num2
	 * @param format
	 * @return
	 *    String
	 */
	public static String divisionDown(String num1, String num2, String format){
		if(StringUtils.isBlank(num1))num1 = "0";
		if(StringUtils.isBlank(num2))num2 = "0";
		if(Double.valueOf(num2) == 0d){
			return "0";
		}
		DecimalFormat df = new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.DOWN);
		BigDecimal d1 = new BigDecimal(num1);
		BigDecimal d2 = new BigDecimal(num2);
		return df.format(d1.divide(d2,8, BigDecimal.ROUND_DOWN)).toString();
	}
	
	/**
	 * 乘法(不进行四舍五入)
	 * @author liaoyp
	 * @create_time 2016-12-28上午11:05:28
	 * @param num1
	 * @param num2
	 * @param format
	 * @return
	 *    String
	 */
	public static String multiplicationDown(String num1, String num2, String format){
		if(StringUtils.isBlank(num1))num1 = "0";
		if(StringUtils.isBlank(num2))num2 = "0";
		DecimalFormat df = new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.DOWN);
		BigDecimal d1 = new BigDecimal(num1);
		BigDecimal d2 = new BigDecimal(num2);
		return df.format(d1.multiply(d2)).toString();
	}
	
	/**
	 * 大于
	 * @author huangyh 
	 * @create_date 2014-12-10 15:37:15
	 * @param num1
	 * @param num2
	 * @return 时候大于 boolean
	 */
	public static boolean greateThan(String num1, String num2){
		if(StringUtils.isBlank(num1))num1 = "0";
		if(StringUtils.isBlank(num2))num2 = "0";
		Double d1 = new Double(num1);
		Double d2 = new Double(num2);
		return d1>d2;
	}

	/**
	 * 大于等于
	 * @author huangyh 
	 * @create_date 2015-01-16 15:58:15
	 * @param num1
	 * @param num2
	 * @return 时候大于 boolean
	 */
	public static boolean greateEqual(String num1, String num2){
		if(StringUtils.isBlank(num1))num1 = "0";
		if(StringUtils.isBlank(num2))num2 = "0";
		Double d1 = new Double(num1);
		Double d2 = new Double(num2);
		return d1>=d2;
	}
	
	/**
	 * 等于
	 * @author caisb 
	 * @create_date 2015-8-20 下午5:08:14
	 * @param num1
	 * @param num2
	 * @return 是否等于  boolean
	 */
	public static boolean equal(String num1, String num2){
		if(StringUtils.isBlank(num1))num1 = "0";
		if(StringUtils.isBlank(num2))num2 = "0";
		Double d1 = new Double(num1);
		Double d2 = new Double(num2);
		return d1.equals(d2);
	}
	
	/**
	 * 加密方法
	 * @author linjian 
	 * @create_date 2013-5-25 下午1:50:35
	 * @param password
	 * @return 加密后的字符串
	 */
	public static String encrypt(String password) {

		MessageDigest md;
		try {

			md = MessageDigest.getInstance("MD5");
			
			int size = password.length()/2;
			md.update((password+(size!=0?password.substring(size-1,size):"")).getBytes());

			return Base64.encodeBase64String(md.digest());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}
	
}
