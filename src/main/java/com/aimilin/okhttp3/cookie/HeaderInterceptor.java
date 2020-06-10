package com.aimilin.okhttp3.cookie;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor{
	
	@Override
	public Response intercept(Chain chain) throws IOException {
		Request r = chain.request();
		r.headers();
		Request.Builder builder = r.newBuilder()//
				.header("accept", "text/html,application/xhtml+xml,application/xml,application/json,text/plain,*/*")//
				.header("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7")//
				.header("Host", "www.baigehuidi.com")//
				.header("Connection", "keep-alive")//
				.header("Cache-Control", "max-age=0")//
				.header("Referer", "http://www.baigehuidi.com/")//
				.header("User-Agent", "Mozilla/5.0 (Linux; U; Android 10.0.0; zh-cn; GT-I9300 Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 MicroMessenger/5.2.380")//
				;
		
		return chain.proceed(builder.build());
	}

}
