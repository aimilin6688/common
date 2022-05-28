package com.aimilin.okhttp3.cookie;

import java.util.ArrayList;
import java.util.List;


import okhttp3.Cookie;
import okhttp3.HttpUrl;
import org.junit.Test;

class FileCookieStoreTest {

	@Test
	void test() {
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
