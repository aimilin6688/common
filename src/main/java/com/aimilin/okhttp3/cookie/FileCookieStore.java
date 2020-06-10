package com.aimilin.okhttp3.cookie;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
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

	public static void main(String[] args) {
		FileCookieStore f = new FileCookieStore();
		HttpUrl url = HttpUrl.parse("http://www.baidu.com/");
		List<Cookie> cookies = new ArrayList<Cookie>();
		String c = "_ga=GA1.2.407923647.1561461270; __gads=ID=7e18bbc2aa454e92:T=1568979117:S=ALNI_MYSS8x1o25FCJo6HiYpndv-c-iDnw; Hm_lvt_d8d668bc92ee885787caab7ba4aa77ec=1573149098; UM_distinctid=170cfd357791ae-080f66dbf2038f-4313f6a-100200-170cfd3577a37d; sc_is_visitor_unique=rx12176449.1589790324.9422FF4AD09A4F3A13997A2ABD2D0748.1.1.1.1.1.1.1.1.1-12123033.1571227097.1.1.1.1.1.1.1.1.1; __utma=2642927.407923647.1561461270.1589887613.1589887613.1; __utmz=2642927.1589887613.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; Hm_lvt_1e91e188a793fc12acbf77cc47464552=1590041463,1590701636; _gid=GA1.2.219595413.1590979751; _gat=1";
		for (String s : c.split("; ")) {
			cookies.add(Cookie.parse(url, s));
		}
		f.add(url, cookies);
		f.write();
		f.read();
		f.removeAll();
		System.out.println(f.cookies);
	}

}
