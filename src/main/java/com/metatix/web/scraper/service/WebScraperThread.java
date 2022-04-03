package com.metatix.web.scraper.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.metatix.web.scraper.common.AppConstants;
import com.metatix.web.scraper.common.ApplicationProperties;
import com.metatix.web.scraper.common.FindByEnum;
import com.metatix.web.scraper.domain.ActionDetail;
import com.metatix.web.scraper.domain.Element;
import com.metatix.web.scraper.domain.ElementDetail;
import com.metatix.web.scraper.domain.ExtractedOutput;
import com.metatix.web.scraper.domain.WebScraperDetail;

/**
 * @author S.Sathishkumar
 *
 */

@Component
public class WebScraperThread implements Runnable{
	protected static final long SLEEP_FOR_2_SEC = 1 *2 * 1000;
	
	@Autowired
	private WebScraperScheduler webScraperScheduler;
	
	@Autowired
	private WebScraperWorker webScraperWorker;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Value("#{systemProperties['appHomePath']}")
	private String appHomePath;
	
	private Integer numberOfParallelJobAllowed=0;
	
	@PostConstruct
	public void startService() {
		numberOfParallelJobAllowed=Integer.parseInt(applicationProperties.getProperty(AppConstants.NUMBER_OF_JOBS_RUN_PARALLEL,"2"));
		
		File outPutFolder = new File(appHomePath+"/scraper_output");
		if(outPutFolder.exists()==false) {
			outPutFolder.mkdirs();
			outPutFolder.setReadable(true, false);
			outPutFolder.setExecutable(true, false);
			outPutFolder.setWritable(true, false);
		}
		
		new Thread(this).start();
	}
	
	public void run() {
		while(true) {
			try {
				if(webScraperWorker.getCurrentJobQueueSize()<numberOfParallelJobAllowed) {
					final WebScraperDetail webScraperDetail = webScraperScheduler.getJobQueue().take();
					webScraperWorker.submitJob(new Callable<Boolean>() {
						public Boolean call() throws Exception {
							return executeWebScraper(webScraperDetail);
						}
					});
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					Thread.sleep(SLEEP_FOR_2_SEC);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean executeWebScraper(WebScraperDetail webScraperDetail) {
		System.out.println(webScraperDetail.getUrl());
		List<ExtractedOutput> extractedOutputs = parseAndExtractInfo(webScraperDetail);
		writeIntoFile(extractedOutputs, webScraperDetail.getWebsiteName());
		return true;
	}
	
	public List<ExtractedOutput> parseAndExtractInfo(WebScraperDetail webScraperDetail) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors",
				"--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");

		WebDriver driver = new ChromeDriver(options);
		driver.get(webScraperDetail.getUrl());
		
		System.out.println("Extracting info from Website : "+ webScraperDetail.getWebsiteName()+ ". Please wait...");
		if (webScraperDetail.getUrlLoadWaitTime() > 0L) {
			WebDriverWait wait = new WebDriverWait(driver, 100000);
			try {
				synchronized (wait) {
					wait.wait(webScraperDetail.getUrlLoadWaitTime());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Element element = webScraperDetail.getElement();
		// Find root element List
		List<WebElement> rootElementList = null;
		if (FindByEnum.XPATH == element.getFindBy()) {
			rootElementList = driver.findElements(By.xpath(element.getEventList()));
		} else if (FindByEnum.CLASSNAME == element.getFindBy()) {
			rootElementList = driver.findElements(By.className(element.getEventList()));
		}

		List<ExtractedOutput> extractedOutputs = new ArrayList<ExtractedOutput>();
		// Iterate element config and read
		for (WebElement webElement : rootElementList) {
			ExtractedOutput extractedOutput = new ExtractedOutput();
			for (ElementDetail elementDetail : element.getElementDetails()) {
				WebElement webChildElement = null;
				String elementValue = null;
				if (FindByEnum.XPATH == elementDetail.getFindBy()) {
					webChildElement = webElement.findElement(By.xpath(elementDetail.getElementValue()));
					elementValue = webChildElement.getText();
				} else if (FindByEnum.CLASSNAME == elementDetail.getFindBy()) {
					webChildElement = webElement.findElement(By.className(elementDetail.getElementValue()));
					elementValue = webChildElement.getText();
				} else if (FindByEnum.ATTRIBUTE== elementDetail.getFindBy()) {
					elementValue = webElement.getAttribute(elementDetail.getElementValue());
				} else if (FindByEnum.ACTION == elementDetail.getFindBy()) {
					ActionDetail actionDetail = elementDetail.getActionDetail();
					if (actionDetail.getAction().equals("click")) {
						webElement.click();
					} else {
						System.out.println("Mentioned Action not supported - " + actionDetail.getAction());
					}

					if (actionDetail.getElementValue().equals("driver.url")) {
						elementValue = driver.getCurrentUrl();
					} else {
						System.out.println(
								"Mentioned elmentVlaue under Action not supported - " + actionDetail.getElementValue());
					}

					if (actionDetail.getActionClose().equals("Keys.ESCAPE")) {
						Actions action = new Actions(driver);
						action.sendKeys(Keys.ESCAPE).perform();
					} else {
						System.out.println(
								"Mentioned Action close under Action not supported - " + actionDetail.getActionClose());
					}
				}

				extractedOutput.setPropertyValue(elementDetail.getElementName(), elementValue);

			}
			extractedOutputs.add(extractedOutput);
		}

		driver.quit();
		return extractedOutputs;
	}

	public void writeIntoFile(List<ExtractedOutput> extractedOutputs, String websiteName) {
		FileOutputStream fos = null;
		System.out.println("Writing Extracted info to the file for Site : "+ websiteName);
		try {
			fos = new FileOutputStream(new File(appHomePath+"/scraper_output/" + websiteName + ".csv"));
			for (ExtractedOutput extractedOutput : extractedOutputs) {
				fos.write(extractedOutput.getAsCSVValue().getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Process Completed !");
	}
	
}
