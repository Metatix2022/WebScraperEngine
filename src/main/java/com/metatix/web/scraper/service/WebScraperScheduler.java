package com.metatix.web.scraper.service;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.metatix.web.scraper.common.AppConstants;
import com.metatix.web.scraper.common.ApplicationProperties;
import com.metatix.web.scraper.domain.WebScraperDetail;
import com.metatix.web.scraper.domain.WebScrapperConfig;

/**
 * @author S.Sathishkumar
 *
 */
@Service
@EnableScheduling
public class WebScraperScheduler {

	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Autowired
	private WebScrapperConfig webScrapperConfig;
	
	private BlockingQueue<WebScraperDetail> jobQueue=null;
	
	public BlockingQueue<WebScraperDetail> getJobQueue() {
		return jobQueue;
	}

	@PostConstruct
	public void initializeQueue() {
		Integer numberOfParallelJob=Integer.parseInt(applicationProperties.getProperty(AppConstants.NUMBER_OF_JOBS_RUN_PARALLEL,"2"));
		jobQueue = new ArrayBlockingQueue<WebScraperDetail>(numberOfParallelJob);
		
		System.setProperty("webdriver.chrome.driver",applicationProperties.getProperty(AppConstants.WEB_DRIVER_PATH));
		
	}
	
	//@Scheduled(cron = "#{applicationProperties.getProperty('cronExpression')}")
	@Scheduled(initialDelay = 10, fixedDelay = 100000)
	public void scheduleJob() {
		System.out.println("Running Job - WebScraperScheduler " + new Date());
		for(WebScraperDetail webScraperDetail : webScrapperConfig.getWebScraperDetails()) {
			try {
				jobQueue.put(webScraperDetail);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
}
