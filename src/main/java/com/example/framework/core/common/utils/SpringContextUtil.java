/**  
 * @author hecx
 * @version V1.0  
 * @date 2015-8-7 下午5:01:35 
 */
package com.example.framework.core.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Locale;

/**
 * 获取Spring上下文及国际化
 * 
 * @author bingchuan -->www.vijun.com
 * 
 */
public final class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext context;

	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext contex)
			throws BeansException {
		this.context = contex;
	}

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	public static String getMessage(String key) {
		return context.getMessage(key, null, Locale.getDefault());
	}
}
