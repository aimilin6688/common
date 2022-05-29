package com.aimilin.okhttp3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aimilin.okhttp3.cookie.CookieJarImpl;
import com.aimilin.okhttp3.cookie.CookieStore;
import com.aimilin.okhttp3.cookie.FileCookieStore;
import com.aimilin.okhttp3.cookie.HeaderInterceptor;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class HttpUtils {
	private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
	
	private static HttpUtils httpUtils;
	private boolean isDebug = false;
	private CookieStore cookieStore;
	private OkHttpClient okHttpClient;
	
	/**
	 * 如果需要单例调用该方法
	 * @return
	 */
	public static HttpUtils getInstance() {
		if(httpUtils == null) {
			httpUtils = new HttpUtils();
		}
		return httpUtils;
	}

	/**
	 * 新建一个空白请求实例
	 * @return HttpUtils
	 */
	public static HttpUtils newInstance(){
		return new HttpUtils(new OkHttpClient.Builder().build());
	}

	public HttpUtils() {
		this.okHttpClient = getClient();
	}

	public HttpUtils(boolean isDebug) {
		super();
		this.isDebug = isDebug;
		this.okHttpClient = getClient();
	}


	public HttpUtils(OkHttpClient okHttpClient) {
		super();
		this.okHttpClient = okHttpClient;
	}
	
	public HttpUtils(boolean isDebug, OkHttpClient okHttpClient) {
		super();
		this.isDebug = isDebug;
		this.okHttpClient = okHttpClient;
	}

	protected OkHttpClient getClient() {
		return new OkHttpClient.Builder()//
		.cookieJar(new CookieJarImpl(new FileCookieStore()))//
		.addInterceptor(new HeaderInterceptor())//
		.build();
	}
	
	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	public boolean isDebug() {
		return isDebug;
	}

	
	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public OkHttpClient getOkHttpClient() {
		return okHttpClient;
	}

	public void setOkHttpClient(OkHttpClient okHttpClient) {
		this.okHttpClient = okHttpClient;
	}

	public void clearCookie() {
		cookieStore.removeAll();
	}

	public String get(String url) {
		Request request = new Request.Builder().url(url).build();
		return execute(request);
	}
	
	public String get(String url,Headers headers, boolean isNew) {
		Request.Builder b = new Request.Builder();
		if (headers != null && headers.size() > 0) {
			if(isNew) {
				b.headers(headers);
			}else {
				for (String name : headers.names()) {
					b.header(name, headers.get(name));
				}
			}
		}
		Request request = b.url(url).build();
		return execute(request);
	}

	public String ajaxGet(String url) {
		return ajaxGet(url, null, true);
	}
	
	public String ajaxGet(String url, Headers headers) {
		return ajaxGet(url, headers, true);
	}

	public  String ajaxGet(String url, Headers headers, boolean isNew) {
		Request.Builder b = new Request.Builder();
		if (headers != null && headers.size() > 0) {
			if(isNew) {
				b.headers(headers);
			}else {
				for (String name : headers.names()) {
					b.header(name, headers.get(name));
				}
			}
		}
		b.header("X-Requested-With", "XMLHttpRequest");
		Request request = b.url(url).build();
		return execute(request);
	}

	public  String post(String url, RequestBody body) {
		Request request = new Request.Builder().post(body)//
				.url(url)//
				.build();
		return execute(request);
	}

	public  String ajaxPost(String url, RequestBody body) {
		return ajaxPost(url, body, null, true);
	}
	
	public  String ajaxPost(String url, RequestBody body,Headers headers) {
		return ajaxPost(url, body, headers, true);
	}

	/**
	 * 通过ajax提交请求
	 * @param url 请求地址
	 * @param body 发送请求内容
	 * @param headers 请求头，可以是null
	 * @param isNew true 覆盖原有请求头，false 添加请求头，已存在的会覆盖
	 * @return
	 */
	public  String ajaxPost(String url, RequestBody body, Headers headers, boolean isNew) {
		Request.Builder b = new Request.Builder();
		if (headers != null && headers.size() > 0) {
			if(isNew) {
				b.headers(headers);
			}else {
				for (String name : headers.names()) {
					b.header(name, headers.get(name));
					if(this.isDebug) {
						System.out.println(String.format("Set Header, %s: %s", name, headers.get(name)));
					}
				}
			}
		}
		b.header("X-Requested-With", "XMLHttpRequest");
		Request request = b.post(body).url(url).build();//
		return execute(request);
	}
	
	public String execute(OkHttpClient httpClient, Request request) {
		try (Response response = httpClient.newCall(request).execute()) {
			debug(response);
			if (response.code() == 200) {
				String result =  response.body().string();
				log.debug("请求：{}，结果:{}", response.request().url(), StringEscapeUtils.unescapeJava(result));
				return result;
			} else {
				log.warn("请求：{}，结果：{}", request, response);
				return response.body().string();
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	public  String execute(Request request) {
		return execute(this.okHttpClient, request);
	}

	// 打印日志
	protected  void debug(Response response) {
		if (!isDebug) {
			return;
		}
		Request r = response.request();
		System.out.println("---------------------" + r.url().toString() + "------------------------");
		System.out.println("Request:");
		Headers hs = r.headers();
		for (String name : hs.names()) {
			System.out.println(String.format("%30s: %s", name, hs.get(name)));
		}
		RequestBody requestBody = r.body();
		if (requestBody != null) {
			try {
				Buffer buffer = new Buffer();
				requestBody.writeTo(buffer);
				Charset charset = Charset.forName("utf-8");
				System.out.println("Request params:");
				URLParser p = new URLParser(buffer.readString(charset));
				for (Map.Entry<String, String> e : p.getSimple().entrySet()) {
					System.out.println(String.format("%30s: %s", e.getKey(), p.decode(e.getValue())));
				}
			} catch (Exception e) {
			}
		}

		System.out.println("Response:");
		Headers hs2 = response.headers();
		for (String name : hs2.names()) {
			System.out.println(String.format("%30s: %s", name, hs2.get(name)));
		}
	}
}
