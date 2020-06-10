package com.aimilin.okhttp3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
	public static boolean isDebug = false;

	private static CookieStore cookieStore = new FileCookieStore();
	public static OkHttpClient okHttpClient = new OkHttpClient.Builder()//
			.cookieJar(new CookieJarImpl(cookieStore))//
			.addInterceptor(new HeaderInterceptor())//
			.build();

	public static void clearCookie() {
		cookieStore.removeAll();
	}

	public static boolean isError(String result) {
		return StringUtils.contains(result, "\"state\":\"fail\"");
	}

	public static String get(String url) {
		Request request = new Request.Builder().url(url).build();
		return execute(request);
	}

	public static String ajaxGet(String url) {
		return ajaxGet(url, null);
	}

	public static String ajaxGet(String url, Headers headers) {
		Request.Builder b = new Request.Builder();
		if (headers != null && headers.size() > 0) {
			b.headers(headers);
		}
		b.header("X-Requested-With", "XMLHttpRequest");
		Request request = b.url(url).build();
		return execute(request);
	}

	public static String post(String url, RequestBody body) {
		Request request = new Request.Builder().post(body)//
				.url(url)//
				.build();
		return execute(request);
	}

	public static String ajaxPost(String url, RequestBody body) {
		return ajaxPost(url, body, null);
	}

	public static String ajaxPost(String url, RequestBody body, Headers headers) {
		Request.Builder b = new Request.Builder();
		if (headers != null && headers.size() > 0) {
			b.headers(headers);
		}
		b.header("X-Requested-With", "XMLHttpRequest");
		Request request = b.post(body).url(url).build();//
		return execute(request);
	}

	public static String execute(Request request) {
		try (Response response = okHttpClient.newCall(request).execute()) {
			debug(response);
			if (response.code() == 200) {
				return response.body().string();
			} else {
				log.warn("请求：{}，结果：{}", request, response);
				return response.body().string();
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return e.getMessage();
		}
	}

	// 打印日志
	private static void debug(Response response) {
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
