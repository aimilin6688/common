package com.aimilin.okhttp3;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

/**
 * Provides methods for encoding or decoding URI components.
 */
public abstract class URIComponent {
	
	public static String encode(String component) {
		return encode(component,  "utf-8") ;
	}

	/**
	 * Encodes the given component.
	 *
	 * @param component Component string to be encoded
	 * @return the encoded component
	 */
	public static String encode(String component, String enc) {
		try {
			if(StringUtils.isBlank(component)) {
				return component;
			}
			return URLEncoder.encode(component, enc) //
					.replaceAll("\\+", "%20")//
					.replaceAll("\\%21", "!")//
					.replaceAll("\\%27", "'")//
					.replaceAll("\\%28", "(")//
					.replaceAll("\\%29", ")")//
					.replaceAll("\\%7E", "~");
		} catch (Exception e) {
			return component;
		}
	}
	
	public static String decode(String component) {
		return decode(component,  "utf-8") ;
	}

	/**
	 * Decodes the given component.
	 *
	 * @param component Component string to be decoded
	 * @return the decoded component
	 */
	public static String decode(String component, String enc) {
		try {
			if(StringUtils.isBlank(component)) {
				return component;
			}
			return URLDecoder.decode(component, enc);
		} catch (Exception e) {
			return component;
		}
	}
}