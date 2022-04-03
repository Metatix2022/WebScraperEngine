package com.metatix.web.scraper.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author S.Sathishkumar
 *
 */
public class ScraperCommonConfig {
	@JsonProperty(value="webDriverPath", required = true)
	private String webDriverPath;
	
	@JsonProperty(value="numberOfJobsRunParallel", required = true)
	private int numberOfJobsRunParallel;
	
	@JsonProperty(value="outputType", required = false)
	private String outputType;
	
	@JsonProperty(value="cronExpression", required = true)
	private String cronExpression;

	public String getWebDriverPath() {
		return webDriverPath;
	}

	public void setWebDriverPath(String webDriverPath) {
		this.webDriverPath = webDriverPath;
	}

	public int getNumberOfJobsRunParallel() {
		return numberOfJobsRunParallel;
	}

	public void setNumberOfJobsRunParallel(int numberOfJobsRunParallel) {
		this.numberOfJobsRunParallel = numberOfJobsRunParallel;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
	
}
