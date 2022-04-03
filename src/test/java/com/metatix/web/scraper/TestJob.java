package com.metatix.web.scraper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author S.Sathishkumar
 *
 */
public class TestJob implements Runnable{

	public void run() {
		try {
			parsePage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void parsePage1() throws IOException {
		System.setProperty("webdriver.chrome.driver","C:\\Sathish\\Software\\chromedriver_98\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", 
				"--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions",
				"--no-sandbox","--disable-dev-shm-usage");
		
		WebDriver driver = new ChromeDriver(options);
		
		
		driver.get("https://somniumspace.com/events");
		
		//System.out.println(driver.getTitle());
		System.out.println("Loading....");
		WebDriverWait wait = new WebDriverWait(driver, 100000);
		try {
			synchronized (wait) {
				wait.wait(5000);	
			}
			
			//final WebElement we4 = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div"));
			final List<WebElement> xx = driver.findElements(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div[@class='styles_card__1ImMO']"));
			for (WebElement ww : xx) {
				//System.out.println(ww.getAttribute("href"));
				//div[1]/div[3]/div[1]/span[1]
				WebElement inE = ww.findElement(By.xpath(".//div[@class='styles_infoHeader__KQn-l']/div[@class='styles_data__1fnM4']/span[1]"));
				System.out.println(inE.getText());
			
				WebElement inE1 =  ww.findElement(By.className("styles_description__3T6wh")); 
				System.out.println(inE1.getText());
				
				  inE1.click();
				  System.out.println(driver.getCurrentUrl());
				  
				  
			  Actions action = new Actions(driver);
				  action.sendKeys(Keys.ESCAPE).perform();
				  
				  
				
			System.out.println("------");
			}
			
			
			
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
System.out.println("Load Completed.");
	    
		FileOutputStream ff = new FileOutputStream(new File("C:\\Sathish\\testscrap.txt"));		  
		try {
			ff.write(driver.getPageSource().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ff.close();
		
		driver.quit();
		
	}
	
	public void parsePage() throws IOException {
		System.setProperty("webdriver.chrome.driver","C:\\Sathish\\Software\\chromedriver_98\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", 
				"--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions",
				"--no-sandbox","--disable-dev-shm-usage");
		
		WebDriver driver = new ChromeDriver(options);
		
		
		driver.get("https://events.decentraland.org/");
		//System.out.println(driver.getTitle());
		System.out.println("Loading....");
		WebDriverWait wait = new WebDriverWait(driver, 100000);
		try {
			synchronized (wait) {
				wait.wait(5000);	
			}
			
			//final WebElement we = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/div[2]/div[4]/a[31]/div[2]/div[1]/div[1]"));
			/*
			 * System.out.println(we.getText());
			 * //*[@id="gatsby-focus-wrapper"]/main/div[2]/div[4]/a[31]/div[2]/div[1]/div[1]
			 * 
			 * final WebElement we1 = driver.findElement(By.xpath(
			 * "//*[@id=\"gatsby-focus-wrapper\"]/main/div[2]/div[4]/a[31]/div[2]/div[2]"));
			 * System.out.println(we1.getText());
			 * 
			 * final WebElement we2 = driver.findElement(By.xpath(
			 * "//*[@id=\"gatsby-focus-wrapper\"]/main/div[2]/div[4]/a[31]/div[1]/div[1]"));
			 * System.out.println(we2.getText());
			 * 
			 * final WebElement we3 = driver.findElement(By.xpath(
			 * "//*[@id=\"gatsby-focus-wrapper\"]/main/div[2]/div[4]/a[31]"));
			 * System.out.println(we3.getAttribute("href"));
			 * 
			 */
			//final WebElement we4 = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/div[2]/div[4]"));
			final List<WebElement> xx = driver.findElements(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/div[2]/div[4]/a"));
			for (WebElement ww : xx) {
				System.out.println(ww.getAttribute("href"));
				
				WebElement inE = ww.findElement(By.xpath(".//div[@class='EventCard__Cover']/div"));
				//WebElement inE = we.findElement(By.tagName("div"));
				System.out.println(inE.getText());
				
				WebElement inE1 = ww.findElement(By.xpath(".//div[@class='content']/div/div[@class='EventDate']"));
				System.out.println(inE1.getText());
				
				WebElement inE2 = ww.findElement(By.xpath(".//div[@class='content']/div[@class='header']"));
				System.out.println(inE2.getText());
				
				
				//*[@id="gatsby-focus-wrapper"]/main/div[2]/div[4]/a[1]/div[2]/div[2]
				System.out.println("------");
			}
			
			
			
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
System.out.println("Load Completed.");
	    
		FileOutputStream ff = new FileOutputStream(new File("C:\\Sathish\\testscrap.txt"));		  
		try {
			ff.write(driver.getPageSource().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ff.close();
		
		driver.quit();
		
	}

}
