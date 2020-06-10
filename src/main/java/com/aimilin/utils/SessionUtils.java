package com.aimilin.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtils {
	private static final Logger log = LoggerFactory.getLogger(SessionUtils.class);
	public static final String USER_ID = "user_id";
	
	public static HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession();
	}
	
	public static Long getUserId() {
		return Long.parseLong(getSession().getAttribute(USER_ID).toString());
	}
	
	public static void setUserId(Long userId) {
		set(USER_ID, userId);
	}
	
	public static void set(String key, Object value) {
		getSession().setAttribute(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(String key) {
		try {
			return (T)getSession().getAttribute(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static void delete(String key) {
		HttpSession session = getSession();
		Enumeration<String> keys = session.getAttributeNames();
		while(keys.hasMoreElements()) {
			String k = keys.nextElement();
			if(StringUtils.equals(key, k)) {
				session.removeAttribute(k);
				break;
			}
		}
	}
}
