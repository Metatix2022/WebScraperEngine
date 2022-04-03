package com.metatix.web.scraper.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author S.Sathishkumar
 *
 */
public class WebScrapperConfig {

	@JsonProperty("ScraperCommonConfig")
	private ScraperCommonConfig scraperCommonConfig;

	@JsonProperty("WebScrapper")
	private List<WebScraperDetail> webScraperDetails;

	public List<WebScraperDetail> getWebScraperDetails() {
		return webScraperDetails;
	}

	public void setWebScraperDetails(List<WebScraperDetail> webScraperDetails) {
		this.webScraperDetails = webScraperDetails;
	}

	public ScraperCommonConfig getScraperCommonConfig() {
		return scraperCommonConfig;
	}

	public void setScraperCommonConfig(ScraperCommonConfig scraperCommonConfig) {
		this.scraperCommonConfig = scraperCommonConfig;
	}
	
	
	
	
}
