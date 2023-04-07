package com.aimilin.utils;

import java.util.Enumeration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
		return getUserId(getSession());
	}
	
	public static String getId() {
		return getSession().getId();
	}
	
	public static Long getUserId(HttpSession session) {
		try {
			Object obj = get(session, USER_ID);
			if(obj != null) {
				return Long.valueOf(obj.toString());
			}
			return null;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static void setUserId(HttpSession session,Long userId) {
		session.setAttribute(USER_ID, userId);
	}
	
	public static void setUserId(Long userId) {
		set(USER_ID, userId);
	}
	
	public static void removeUserId() {
		delete(getSession(), USER_ID);
	}
	
	public static void removeUserId(HttpSession session) {
		delete(session, USER_ID);
	}
	
	public static void set(String key, Object value) {
		getSession().setAttribute(key, value);
	}
	
	public static void set(HttpSession session,String key, Object value) {
		session.setAttribute(key, value);
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> T get(HttpSession session,String key) {
		try {
			return (T)session.getAttribute(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static <T> T get(String key) {
		return get(getSession(), key);
	}
	
	public static void delete(String key) {
		delete(getSession(), key);
	}
	
	public static void delete(HttpSession session,String key) {
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
