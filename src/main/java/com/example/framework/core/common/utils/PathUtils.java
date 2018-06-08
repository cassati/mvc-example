package com.example.framework.core.common.utils;

import org.apache.commons.lang3.StringUtils;


/**
 * @Description:路径获取工具类
 * @author:liaoyuping
 * @version: 
 * @create_date:2016年8月14日
 */
public class PathUtils {
	private static String rootPath;
	public static String getSysPath() {
		if(StringUtils.isBlank(rootPath)){
			rootPath = Thread.currentThread().getContextClassLoader().getResource("").toString();
			rootPath = rootPath.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");		
			String os = System.getProperty("os.name").toLowerCase();
	        if (!os.startsWith("win")) {  //不是window系统需要/开头	20180503	youcb
	        	rootPath ="/"+rootPath;
	        }  
			String separator = System.getProperty("file.separator");
			rootPath = rootPath.replaceAll("/", separator + separator);
		}
		return rootPath;
	}
	
	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}

	public static void main(String[] args) {
		System.out.println(getSysPath());
		System.out.println(System.getProperty("java.io.tmpdir"));
		System.out.println(getSeparator());
		System.out.println(getClassPath());
	}
}
