package com.aimilin.okhttp3;


import org.junit.Test;

class URLParserTest {

	@Test
	void test() throws Exception {
		String url = "https://mall.xiangtuan.xyz/#/productDetail?refSpm=30.2&productId=711213509893210378&activityNo=711210195155730695&shopId=vYaN8mfsJd&distributorId=vapwnzn4ev&channel=h5Mall&price=+100&name=中国";

		URLParser parser = new URLParser(url);

		// get basic infomation 
		System.out.println(parser.getHost()); 
		System.out.println(parser.getPort()); 
		System.out.println(parser.getProtocol()); 
		System.out.println(parser.getPath());
		System.out.println(parser.getQuery());
		System.out.println(parser.getUserInfo());
		System.out.println(parser.getCharset());

		// get paramsa
		System.out.println(parser.getParam("refSpm"));
		System.out.println(parser.getParam("activityNo"));
		System.out.println(parser.getParams("shopId"));

		// update params
		parser.removeParams("eee");
		parser.addParam("ggg", "444");
		parser.updateParams("fff", "555");

		// create query string
		System.out.println(parser.createQueryString());

		// full url
		System.out.println(parser.toString());
		System.out.println(parser.getParam("price"));
		System.out.println(parser.getParam("name"));

		URLParser parser2 = new URLParser("a=111&b=222&c=333");
		System.out.println(parser2.getHost());
		System.out.println(parser2.getParam("a"));
	}

}
