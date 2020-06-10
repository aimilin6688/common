package com.aimilin.okhttp3;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class URLParser {

	private String host;
	private Integer port;
	private String protocol;

	// use LinkedHashMap to keep the order of items
	private LinkedHashMap<String, List<String>> params = new LinkedHashMap<String, List<String>>();
	private String path;
	private String userInfo;
	private String query;
	private String charset;
	private boolean hasDomain = true;

	public URLParser(String url) throws MalformedURLException {
		this(url, "utf-8");
	}

	/**
	 * http://user:password@host:port/aaa/bbb;xxx=xxx?eee=ggg&fff=ddd&fff=lll
	 * 
	 * @throws MalformedURLException
	 */
	public URLParser(final String url, final String charset) throws MalformedURLException {
		checkNull(url, "url");
		if (charset != null && !Charset.isSupported(charset)) {
			throw new IllegalArgumentException("charset is not supported: " + charset);
		}
		final URL u;
		if (url.matches("\\w+[:][/][/].*")) {
			hasDomain = true;
			u = new URL(url);
		} else {
			hasDomain = false;
			u = new URL("http://dummy" + (url.contains("/")?((url.startsWith("/") ? url : ("/" + url))):(url.startsWith("?")?url:("?"+url))));
		}

		this.charset = charset;
		if (hasDomain) {
			this.protocol = u.getProtocol();
			this.host = u.getHost();
			this.port = u.getPort();
			if (this.port != null && this.port == -1) {
				this.port = null;
			}
			this.path = u.getPath();
			this.userInfo = u.getUserInfo();
		} else {
			try {
				this.path = url.startsWith("/") ? u.getPath() : u.getPath().substring(1);
			} catch (Exception e) {
			}
		}
		this.query = u.getQuery();
		this.params = parseQueryString(substringAfter((url.indexOf("?") == -1 && url.indexOf("&") != -1)?"?"+url:url, "?"));
	}

	public void addParam(String name, String value) {
		addParams(name, Arrays.asList(value));
	}

	public void addParams(String name, List<String> values) {
		List<String> list = getOrCreate(params, name);
		for (String value : values) {
			list.add(encode(value));
		}
	}

	public void removeParams(String name) {
		if (name == null) {
			return;
		}
		this.params.remove(name);
	}

	public void updateParams(String name, String... values) {
		checkNull(name, "name");
		if (values.length == 0) {
			throw new IllegalArgumentException("values should not be empty");
		}
		List<String> list = getOrCreate(params, name);
		list.clear();
		for (String value : values) {
			list.add(encode(value));
		}
	}

	public List<String> getRawParams(String name) {
		checkNull(name, "name");
		return this.params.get(name);
	}

	public String getRawParam(String name) {
		List<String> params = getRawParams(name);
		return params == null ? null : params.get(0);
	}

	public String getParam(String name) throws UnsupportedEncodingException {
		String value = getRawParam(name);
		return value == null ? null : decode(value);
	}

	public List<String> getParams(String name) {
		List<String> rawParams = getRawParams(name);
		if (rawParams == null) {
			return null;
		}
		List<String> params = new ArrayList<String>();
		for (String value : rawParams) {
			params.add(decode(value));
		}
		return params;
	}

	public Map<String, String> getSimple() {
		Map<String, String> map = new HashMap<String, String>();
		for (String name : this.params.keySet()) {
			map.put(name, StringUtils.join(getParams(name), ","));
		}
		return map;
	}

	public String getHost() {
		return host;
	}

	public Integer getPort() {
		return port;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getPath() {
		return path;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public String getCharset() {
		return charset;
	}

	public String getQuery() {
		return query;
	}

	public String createQueryString() {
		if (this.params.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (String name : this.params.keySet()) {
			List<String> values = this.params.get(name);
			for (String value : values) {
				if (sb.length() > 0) {
					sb.append("&");
				}
				sb.append(name).append("=").append(value);
			}
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.protocol != null) {
			sb.append(this.protocol).append("://");
		}
		if (this.userInfo != null) {
			sb.append(this.userInfo).append("@");
		}
		if (this.host != null) {
			sb.append(host);
		}
		if (this.port != null) {
			sb.append(":").append(this.port);
		}
		sb.append(this.path);
		String query = createQueryString();
		if (query.trim().length() > 0) {
			sb.append("?").append(query);
		}

		return sb.toString();
	}

	public String decode(String value) {
		return charset == null ? value : URIComponent.decode(value, charset);
	}

	public  String encode(String value) {
		return charset == null ? value : URIComponent.encode(value, charset);
	}

	private List<String> getOrCreate(Map<String, List<String>> map, String name) {
		checkNull(name, "name");
		List<String> list = map.get(name);
		if (list == null) {
			list = new ArrayList<String>();
			map.put(name, list);
		}
		return list;
	}

	private  void checkNull(Object value, String fieldName) {
		if (value == null) {
			throw new IllegalArgumentException(fieldName + " should not be null");
		}
	}

	private LinkedHashMap<String, List<String>> parseQueryString(String query) {
		LinkedHashMap<String, List<String>> params = new LinkedHashMap<String, List<String>>();
		if (isBlank(query)) {
			return params;
		}
		String[] items = query.split("&");
		for (String item : items) {
			String name = substringBefore(item, "=");
			String value = substringAfter(item, "=");
			List<String> values = getOrCreate(params, name);
			values.add(encode(value));
		}
		return params;
	}

	private  boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}

	private  String substringBefore(String str, String sep) {
		int index = str.indexOf(sep);
		return index == -1 ? "" : str.substring(0, index);
	}

	private String substringAfter(String str, String sep) {
		int index = str.indexOf(sep);
		return index == -1 ? "" : str.substring(index + 1);
	}
}