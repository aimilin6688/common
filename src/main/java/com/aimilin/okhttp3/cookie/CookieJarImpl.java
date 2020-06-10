package com.aimilin.okhttp3.cookie;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CookieJarImpl implements CookieJar {

	private CookieStore cookieStore;

	public CookieJarImpl(CookieStore cookieStore) {
		if (cookieStore == null) {
			throw new IllegalArgumentException("cookieStore can not be null.");
		}
		this.cookieStore = cookieStore;
	}

	public CookieStore getCookieStore() {
		return this.cookieStore;
	}

	@Override
	public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
		this.cookieStore.add(url, cookies);
	}

	@Override
	public List<Cookie> loadForRequest(HttpUrl url) {
		return this.cookieStore.get(url);
	}
}