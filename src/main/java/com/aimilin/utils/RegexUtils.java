package com.aimilin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	public static String getText(String html, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(html);
		if(m.find()) {
			return m.group(1);
		}
		return "";
	}
}
