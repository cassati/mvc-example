package com.example.framework.core.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实体完整性验证辅助类
 * @author linjian 
 * @version 2.0
 * @create_date 2013-6-24 上午10:13:33
 */
public class ValidateUtil {
	
	private static final String onlyNum = "^[0-9]*{1}";
	
	
	/** 
	* 验证身份证号码 
	* @param id_number 
	* @return 
	*/  
	public static Boolean checkNID(String id_number){
		if(id_number.length() != 15 && id_number.length() != 18){  
			return false;  
		}  
		String string = id_number.substring(0, id_number.length() - 1);
		if(!string.matches(onlyNum)){  
			return false;  
		}  
		return true;  
	}  
	
	/** 
	* 验证邮箱 
	* @param email 
	* @return 
	*/  
	  
	public static Boolean checkEmail(String email) {
		String check = "^[a-zA-Z0-9_-]+\\@{1}[a-zA-Z0-9_-]+(\\.{1}[a-zA-Z0-9_-]+)+$";
		//String check = "^([a-zA-Z0-9_\\-])+\\@(([a-zA-Z0-9_\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";  
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		boolean isMatched = matcher.matches();  
		return isMatched;
	}
	
	/**手机号码验证
	 * @author linjian 
	 * @create_date 2013-6-27 上午11:02:13
	 * @param mobile
	 * @return 验证结果
	 */
	public static Boolean checkMoblie(String mobile){
		String mobileRegex="^\\d{11}$";
		Pattern regex = Pattern.compile(mobileRegex);
		Matcher matcher = regex.matcher(mobile);
		return matcher.matches();
	}
	
	
	/**电话号码验证
	 * @author linhui
	 * @create_date 2013-7-18 上午11:02:13
	 * @param phonenumber
	 * @return 验证结果
	 */
    public static boolean isTelephone(String phonenumber) {
        String phone = "0\\d{2,3}-\\d{7,8}";
        Pattern p = Pattern.compile(phone);
        Matcher m = p.matcher(phonenumber);
        return m.matches();
    }
    
    /**
     * 验证金额
     * @author linhui
     * @create_date 2013-8-16 下午2:11:20
     * @param moneyNum
     * @return
     */
    public static boolean isMoney(String moneyNum){
    	String money = "(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$";
    	Pattern p = Pattern.compile(money);
    	Matcher m = p.matcher(moneyNum);
    	return m.matches();
    }
    
    
    /**
     * 验证数字
     * @author linhui
     * @create_date 2014-1-22 上午9:42:11
     * @param number
     * @return
     */
    public static boolean isNumber(String number){
    	String num = "^[1-9]\\d*$";
    	Pattern p = Pattern.compile(num);
    	Matcher m = p.matcher(number);
    	return m.matches();
    }
    
    /**
     * 判断是否为以小数点开头的浮点数
     * @author zhangjy
     * @create_date 2017-05-08 上午10:00:00
     * @param decimal
     * @return
     */
    public static boolean checkDecimal(String number) {
        return number.matches("\\.\\d+$");  
    }    
    
    /**
     * 验证用户名
     * @author caoqian
     * @version 2.0
     * @create_date 2015-9-18 上午11:03:58 
     * @param name
     * @return
     */
    public static boolean isUserName(String name){
    	String test = "^[a-z|A-Z|\\d|_]+$";
    	Pattern p = Pattern.compile(test);
    	Matcher m = p.matcher(name);
    	return m.matches();
    }
    
   /**
    * 验证客户登录名
    * @author liangxh
    * @version 2.0
    * @create_date 2015-11-20 上午11:16:39
    * @param username
    * @return
    */
	public static boolean isLoginUserName(String username){
		String nameRule = "^[a-zA-Z][a-zA-Z0-9_]*$";
		Pattern p = Pattern.compile(nameRule);
    	Matcher m = p.matcher( username);
    	return m.matches();
	}
    
    /**
     * 验证时间	
     * @author linhui
     * @create_date 2013-8-16 下午2:11:20
     * @param time
     * @return
     */
    public static boolean isTime(String time){
    	String t = "^\\d{4}-(0?[1-9]|[1][012])-(0?[1-9]|[12][0-9]|[3][01])[\\s]+([0-1][0-9]|2?[0-3]):([0-5][0-9]):([0-5][0-9])$";
    	Pattern p = Pattern.compile(t);
    	Matcher m = p.matcher(time);
    	return m.matches();
    }
    
    /**
     * 检验车牌号(例:闽D*)
     * 该规则已失效 zhengkw 2015-08-25
     * 
     * @author: zhengjz
     * @version 2.0
     * @date： 日期：2015-07-09 时间：下午3:46:09
     * @param vehicle_no
     * @return
     */
    @Deprecated
    public static boolean isVehicle_other(String vehicle_no) {
    	String t = "^[\u4e00-\u9fa5]{1}[A-Z]{1}\\*{1}$";
    	Pattern p = Pattern.compile(t);
    	Matcher m = p.matcher(vehicle_no);
    	return m.matches();
    	
    }
    
    /**
     * 判断是否是中文
     * @author caoqian
     * @version 2.0
     * @create_date 2015-10-27 下午1:39:31 
     * @param str
     * @return
     */
    public static boolean isChinese(String str){
    	String t = "^[\u4e00-\u9fa5]+$";
    	Pattern p = Pattern.compile(t);
    	Matcher m = p.matcher(str);
    	return m.matches();
    }
    
   
    /**  
     * 校验银行卡卡号  
     * @author linjian
     * @create_date 2013-11-1 上午8:57:52
     * @param cardId 银行卡卡号
     * @return 校验结果
     */   
    public static boolean checkBankCard(String cardId) {
    	char  bit = getBankCardCheckCode(cardId.substring( 0 , cardId.length() -  1 ));  
    	if (bit ==  'N' ){  
    		return   false ;  
    	}  
    	return  cardId.charAt(cardId.length() -  1 ) == bit;  
    } 
    
    /**  
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位  
     * @author linjian
     * @create_date 2013-11-1 上午8:57:52
     * @param nonCheckCodeCardId  
     * @return  校验结果
     */   
    public static char getBankCardCheckCode(String nonCheckCodeCardId){
        if (nonCheckCodeCardId ==  null  || nonCheckCodeCardId.trim().length() ==  0   
                || !nonCheckCodeCardId.matches("\\d+" )) {  
         //如果传的不是数据返回N   
            return   'N' ;  
        }  
        char [] chs = nonCheckCodeCardId.trim().toCharArray();  
        int  luhmSum =  0 ;  
        for ( int  i = chs.length -  1 , j =  0 ; i >=  0 ; i--, j++) {  
            int  k = chs[i] -  '0' ;  
            if (j %  2  ==  0 ) {  
                k *= 2 ;  
                k = k / 10  + k %  10 ;  
            }  
            luhmSum += k;             
        }  
        return  (luhmSum %  10  ==  0 ) ?  '0'  : ( char )(( 10  - luhmSum %  10 ) +  '0' );  
    }
    
    /** 验证字符串是否有包含中文符号
     * @author: chenfm
     * @version 2.0
     * @date： 日期：2014-4-3 时间：下午1:36:57
     * @param name
     * @return
     */
    public static boolean ChineseSign(String name){
    	String t = "[\u3002\uFF1F\uFF01\uFF0C\u3001\uFF1B\uFF1A\u300C\u300D\u300E\u300F\u2018\u2019\u201C\u201D\uFF08\uFF09\u3014\u3015\u3010\u3011\u2014\u2026\u2013\uFF0E\u300A\u300B\u3008\u3009\uffe5]";
    	Pattern p = Pattern.compile(t);
    	Matcher m = p.matcher(name);
    	return m.find();
    }
    
    /**
     * 判断输入的字符串是否满足时间格式 ： yyyy-MM-dd HH:mm:ss
     * @param patternString 需要验证的字符串
     * @return 合法返回 true ; 不合法返回false
     */
    public static boolean isTimeLegal(String patternString) {
           
        Pattern a= Pattern.compile("^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$");
    
        Matcher b=a.matcher(patternString);
        if(b.matches()) {
              return true;
         } else {
               return false;
         }
    }
    
    /**
     * 验证小数
     * @author youcb
     * @version 1.0
     * @create_date 2014-10-30上午11:30:54
     * @param number
     * @return
     */
    public static boolean isDecimal(String number){
    	String num = "^(([0-9]*).([0-9]{1,2}))|(([0-9]*))$";
    	Pattern p = Pattern.compile(num);
    	Matcher m = p.matcher(number);
    	return m.matches();
    }
    
    /**
     * 验证浮点数
     * @author caoqian
     * @create_date 2016-4-7 下午3:09:57
     * @param number
     * @return
     */
    public static boolean isFloat(String number){
    	String num = "^(\\d+)(\\.\\d+)?$";
    	Pattern p = Pattern.compile(num);
    	Matcher m = p.matcher(number);
    	return m.matches();
    }
    
    /**
     * 验证组织机构代码
     * @author caoqian
     * @version 2.0
     * @create_date 2014-10-30上午11:30:54
     * @param number
     * @return
     */
    public static boolean isOrg_code(String org_code){
    	String num = "^[0-9A-Z]{8}-{1}[0-9A-Z]{1}$";
    	Pattern p = Pattern.compile(num);
    	Matcher m = p.matcher(org_code);
    	return m.matches();
    }
    
    /**
     * 验证营业执照号
     * @author caoqian
     * @version 2.0
     * @create_date 2014-10-30上午11:30:54
     * @param number
     * @return
     */
    public static boolean isLicense_no(String license_no){
    	String num = "^[0-9]{15}$";
    	Pattern p = Pattern.compile(num);
    	Matcher m = p.matcher(license_no);
    	return m.matches();
    }
    
    /**
     * 验证税务登记号
     * @author caoqian
     * @version 2.0
     * @create_date 2014-10-30上午11:30:54
     * @param number
     * @return
     */
    public static boolean isTax_no(String tax_no){
    	String num = "^[0-9A-Z]{15}$";
    	Pattern p = Pattern.compile(num);
    	Matcher m = p.matcher(tax_no);
    	return m.matches();
    }
    
    /**
     * 验证微众网银用户名是否合法
     * 1.要求必须是6-20位的数字或者字符，不允许中文
     * @author hecx
     * @version 2.0
     * @create_date 2015-8-19上午11:30:54
     * @param number
     * @return
     */
    public static boolean isWebankOnlinName (String username){
    	String num = "[A-Za-z0-9]{6,20}";
    	Pattern p = Pattern.compile(num);
    	Matcher m = p.matcher(username);
    	return m.matches();
    }
    
    /**
     * 判断网络是否正常连接
     * @author linhui
     * @create_date 2015-7-14 上午9:54:33
     * @param addr
     * @return
     */
    public static boolean isConnect(String addr){
    	URL url = null;
    	HttpURLConnection con = null;
    	int state = -1; 
        try { 
        	url = new URL(addr);
        	con = (HttpURLConnection) url.openConnection();
            state = con.getResponseCode(); 
            if (state == 200) {  
            	return true; 
            }else{
            	return false;
            }
        } catch (IOException e) {
        	e.printStackTrace();
            return false;
        }  
    }
    /**
     * 颜色转换器(颜色转html)
     * @author zhengjz
     * @date 2015-11-30下午3:03:05
     *  @param color
     *  @return   
     */
    public static String color_to_html(String contant){
    	String color=contant.split("@")[0];
    	if("红色".equals(color)){
    		return "<span style=\"color:red\">"+contant.split("@")[1]+"</span>";
    	}else if("绿色".equals(color)){
    		return "<span style=\"color:green\">"+contant.split("@")[1]+"</span>";
    	}
    	return contant;
    }
    /**
     * 颜色转换器(html转颜色)
     * @author zhengjz
     * @date 2015-11-30下午3:03:05
     *  @param color
     *  @return   
     */
    public static String html_to_color(String contant){
    	if(contant.contains("color")){
    		String color=contant.split("color")[1].substring(1,(contant.split("color")[1].indexOf(";")<0?contant.split("color")[1].indexOf("\""):contant.split("color")[1].indexOf(";")));
    		if("red".equals(color)){
    			color="红色";
    		}else if("green".equals(color)){
    			color="绿色";
    		}else{
    			color="";
    		}
    		if(StringUtils.isNotBlank(color)){
    			contant=contant.substring(contant.indexOf(">")+1,contant.lastIndexOf("<"));
    			return color+"@"+contant;
    		}else{
    			return contant;
    		}
    	}
    	return contant;
    }
    /**
     * 过滤sql关键字(html转sql)
     * @author zhengjz
     * @date 2015-11-30下午3:47:28
     *  @param contant
     *  @return   
     */
    public static String html_ro_sql(String contant){
    	return contant.replace("from", "f_from").replace("set", "s_set").replace("into", "i_into").replace("--", "——").replace("or", "o_or");
    }
    
    /**
     * 过滤sql关键字(sql转html)
     * @author zhengjz
     * @date 2015-11-30下午3:47:28
     *  @param contant
     *  @return   
     */
    public static String sql_ro_html(String contant){
    	return contant.replace("f_from", "from").replace("s_set", "set").replace("i_into", "into").replace("——", "--");
    }
    
    public static void main(String[] args) {
		System.out.println(isFloat("00001111.00001232000213"));
	}
    
	/**
	 * 是否是数字字符串
	 * @author caoqian 
	 * @create_date 2015-08-20 15:08:26
	 * @param num
	 * @return 
	 */
	public static Boolean isOnlyNum(String num){
		String mobileRegex="^\\d+$";
		Pattern regex = Pattern.compile(mobileRegex);
		Matcher matcher = regex.matcher(num);
		return matcher.matches();
	}
}
