package com.metatix.web.scraper;
import java.io.IOException;
/**
 * @author S.Sathishkumar
 *
 */
public class TestSelenium {

	public static void main(String s[]) throws IOException {
		
		Thread tt = new Thread(new TestJob());
		tt.start();
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,250)");
		
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("LayoutFooterContainer")));
		
		
		//final WebElement we = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/div[2]/div[4]/a[1]/div[2]/div[2]"));
	   // WebElement we1  = we.findElement(By.xpath("div[@class='header']"));
	    //System.out.println(we.getText());
		//System.out.println(driver.getPageSource());
	    //div[2]/div[2]/div[2]/a[1]/div/div[1]/div[2]/div
	    /*
		 * List<WebElement> lwe =
		 * we.findElements(By.className("ui card EventCardMini")); for(WebElement
		 * detailElement : lwe) {
		 * System.out.println(detailElement.findElement(By.xpath("/div/div[2]/div"))); }
		 */
	    
	    
	    //EventCardMini__Attendees__More   
	}
}
