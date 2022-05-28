package com.aimilin.okhttp3.cookie;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class FileCookieStore extends MemoryCookieStore {
	private static final Logger log = LoggerFactory.getLogger(FileCookieStore.class);
	private File file = new File(System.getProperty("user.dir") + File.separator + "cookie.txt");

	public FileCookieStore() {
		super();
		this.read();
	}

	@Override
	public void add(HttpUrl httpUrl, List<Cookie> cookies) {
		super.add(httpUrl, cookies);
		this.write();
	}

	@Override
	public boolean remove(HttpUrl httpUrl, Cookie cookie) {
		boolean result = super.remove(httpUrl, cookie);
		this.write();
		return result;
	}

	@Override
	public boolean removeAll() {
		boolean result = super.removeAll();
		this.deleteFile();
		log.debug("清空Cookie！个数：{}, 结果：{}",this.cookies.size(), result);
		return result;
	}
	
	private void deleteFile() {
		if(this.file.exists()) {
			this.file.delete();
		}
	}

	public void write() {
		if (this.cookies == null || this.cookies.size() == 0) {
			this.deleteFile();
			return;
		}
		JSONObject j1 = new JSONObject();
		for (String k1 : this.cookies.keySet()) {
			JSONObject j2 = new JSONObject();
			ConcurrentHashMap<String, Cookie> valueMap = this.cookies.get(k1);
			for (String k2 : valueMap.keySet()) {
				j2.put(k2, new SerializableCookie().encode(valueMap.get(k2)));
			}
			j1.put(k1, j2);
		}

		try {
			Files.write(file.toPath(), j1.toJSONString().getBytes());
			log.debug("cookie ，写入文件：{}", file.toPath());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void read() {
		if (!file.exists()) {
			return;
		}
		try {
			String json = new String(Files.readAllBytes(file.toPath()));
			if(StringUtils.isBlank(json)) {
				return;
			}
			JSONObject obj = JSONObject.parseObject(json);
			for (String k1 : obj.keySet()) {
				JSONObject v1 = obj.getJSONObject(k1);
				for (String k2 : v1.keySet()) {
					String v2 = v1.getString(k2);
					if (!this.cookies.containsKey(k1)) {
						this.cookies.put(k1, new ConcurrentHashMap<String, Cookie>());
					}
					this.cookies.get(k1).put(k2, new SerializableCookie().decode(v2));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

}
