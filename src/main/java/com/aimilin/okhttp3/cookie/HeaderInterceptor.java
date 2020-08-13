package com.aimilin.okhttp3.cookie;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor{
	
	@Override
	public Response intercept(Chain chain) throws IOException {
		Map<String,String> headers = new HashMap<>();
		headers.put("accept", "text/html,application/xhtml+xml,application/xml,application/json,text/plain,*/*");
		headers.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7");
		headers.put("Host", "www.baigehuidi.com");
		headers.put("Connection", "keep-alive");
		headers.put("Cache-Control", "max-age=0");
		headers.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 10.0.0; zh-cn; GT-I9300 Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 MicroMessenger/5.2.380");
		
		// 如果head 已经存在，则不在设置，否则添加请求头
		Request r =chain.request();
		Set<String> h = r.headers().names();
		Request.Builder b = r.newBuilder();
		for (String name: headers.keySet()) {
			if(h.contains(name)) {
				continue;
			}else {
				b.addHeader(name, headers.get(name));
			}
		}
		return chain.proceed(b.build());
	}

}
