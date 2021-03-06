/**
 * 
 */
package com.test;

import org.jsoup.nodes.Document;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * @author Administrator
 * 
 */
public class Testaa extends BreadthCrawler {

	public Testaa(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
 
	}

	@Override
	public void visit(Page page, CrawlDatums arg1) {

		if (page.matchUrl("http://blog.csdn.net/.*/article/details/.*")) {
//			System.out.println(page.getHtml());
            String title = page.select("div[class=article_title]").first().text();
            String author = page.select("div[id=blog_userface]").first().text();
            System.out.println("title:" + title + "\tauthor:" + author);
        }
		 
	}

	public static void main(String[] args) throws Exception {
		Testaa crawler = new Testaa("crawl", true);
	 
	        crawler.addSeed("http://blog.csdn.net/.*");
	        crawler.addRegex("http://blog.csdn.net/.*/article/details/.*");

	        /*可以设置每个线程visit的间隔，这里是毫秒*/
	        //crawler.setVisitInterval(1000);
	        /*可以设置http请求重试的间隔，这里是毫秒*/
	        //crawler.setRetryInterval(1000);

	        crawler.setThreads(1);
	        crawler.start(2);
	}
}
