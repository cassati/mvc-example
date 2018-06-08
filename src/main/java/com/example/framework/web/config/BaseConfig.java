package com.example.framework.web.config;

import com.example.framework.core.common.CommonConstant;
import com.example.framework.core.springside.PropertiesLoader;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 
 * @author liaoyp
 * @date 2016年8月2日
 */
public class BaseConfig extends CommonConstant {

	/**
	 * 当前对象实例
	 */
	private static BaseConfig baseConfig = new BaseConfig();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("base.properties");

	/**
	 * 项目部署根路径
	 */
	private static String realPath = null;

	/**
	 * 存放下载任务生成的文件的相对目录
	 */
	private static String downloadDir = null;

	/**
	 * 每个用户允许的最大的同时下载任务数
	 */
	private static Integer maxDownloadingPerUser = null;

	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";

	/**
	 * 获取当前对象实例
	 */
	public static BaseConfig getInstance() {
		return baseConfig;
	}
	
	/**
	 * 获取配置 ${fns:getConfig('adminPath')}
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
	
	/**
	 * 获取管理端根路径
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	/**
	 * 获取URL后缀
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	
	/**
	 * 获取积分兑换比率
	 */
	public static String getIntegralRate() {
		String integral_rate=getConfig("integral_rate");
		if(StringUtils.isBlank(integral_rate)){
			integral_rate="100";
		}
		return integral_rate;
	}
	
	/**
	 * 获取基础平台路径
	 * @param 
	 * @return 
	 * @throws 
	 * @author zhengkw 
	 * @version V1.0
	 * @create on: 2017年2月7日
	 */
	public static String getHczdSys() {
		String hczd_sys=getConfig("hczd.sys");
		if(StringUtils.isBlank(hczd_sys)){
			hczd_sys="100";
		}
		return hczd_sys;
	}

    /**
     * 获取工程路径
     * @return
     */
    public static String getProjectPath(){
    	// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = BaseConfig.getConfig("projectPath");
		if (StringUtils.isNotBlank(projectPath)){
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null){
				while(true){
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()){
						break;
					}
					if (file.getParentFile() != null){
						file = file.getParentFile();
					}else{
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
    }

	public static Integer getDownloadTaskPoolSize(){
		Integer defaultPoolSize = 8;
		Integer poolSize = null;

		String size = getConfig("downloadTaskPoolSize");
		if(StringUtils.isBlank(size)) {
			return defaultPoolSize;
		}

		try {
			poolSize = Integer.valueOf(size);
		} catch (NumberFormatException e) {
			poolSize = defaultPoolSize;
		}
		return poolSize;
	}

	/**
	 * 设置项目部署根路径，只允许设置一次
	 */
	public static void setRealPath(String path) {
		if (realPath != null) {
			return;
		}
		realPath = path;
	}

	/**
	 * 项目部署根路径
	 */
	public static String getRealPath() {
		return realPath;
	}

	/**
	 * 存放下载任务生成的文件的相对目录
	 */
	public static String getDownloadDir() {
		if (downloadDir != null) {
			return downloadDir;
		}

		downloadDir = getConfig("downloadDir");
		if(StringUtils.isBlank(downloadDir)) {
			downloadDir = "download_files/";
		}
		return downloadDir;
	}

	/**
	 * 每个用户允许的最大的同时下载任务数
	 */
	public static Integer getMaxDownloadingPerUser(){
		if (maxDownloadingPerUser != null) {
			return maxDownloadingPerUser;
		}

		Integer defaultSize = 1;
		String str = getConfig("maxDownloadingPerUser");
		if(StringUtils.isBlank(str)) {
			maxDownloadingPerUser = defaultSize;
			return defaultSize;
		}

		try {
			maxDownloadingPerUser = Integer.valueOf(str);
		} catch (NumberFormatException e) {
			maxDownloadingPerUser = defaultSize;
		}
		return maxDownloadingPerUser;
	}
}
