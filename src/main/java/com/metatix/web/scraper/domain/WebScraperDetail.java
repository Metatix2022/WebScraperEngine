package com.metatix.web.scraper.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author S.Sathishkumar
 *
 */
public class WebScraperDetail {

	@JsonProperty(value="url", required = true)
	private String url;
	
	@JsonProperty(value="urlLoadWaitTime", required = false)
	private Long urlLoadWaitTime;
	
	@JsonProperty(value="websiteName", required = true)
	private String websiteName;
	
	@JsonProperty(value="elements", required = true)
	private Element element;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getUrlLoadWaitTime() {
		return urlLoadWaitTime;
	}

	public void setUrlLoadWaitTime(Long urlLoadWaitTime) {
		this.urlLoadWaitTime = urlLoadWaitTime;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	
	
}
