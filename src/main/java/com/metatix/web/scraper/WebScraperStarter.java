package com.metatix.web.scraper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.metatix.web.scraper.common.ApplicationConfig;

/**
 * @author S.Sathishkumar
 *
 */
public class WebScraperStarter {

	public static void main(String []args) {
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		System.out.println("Web Scraper Started...");
	}
}
