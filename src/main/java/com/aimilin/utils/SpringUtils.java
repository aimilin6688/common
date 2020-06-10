package com.aimilin.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware{
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if(SpringUtils.applicationContext == null) {
			SpringUtils.applicationContext = applicationContext;
		}
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
	
	public static <T> T getBean(String name, @Nullable Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}
}
