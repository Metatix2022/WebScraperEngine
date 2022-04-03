package com.metatix.web.scraper.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author S.Sathishkumar
 *
 */
public class ActionDetail {
	@JsonProperty(value="action", required = true)
	private String action;
	
	@JsonProperty(value="elementValue", required = false)
	private String elementValue;
	
	@JsonProperty(value="actionClose", required = false)
	private String actionClose;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getElementValue() {
		return elementValue;
	}
	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}
	public String getActionClose() {
		return actionClose;
	}
	public void setActionClose(String actionClose) {
		this.actionClose = actionClose;
	}
	
	
}
