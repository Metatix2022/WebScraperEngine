package com.metatix.web.scraper.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.metatix.web.scraper.common.FindByEnum;
/**
 * @author S.Sathishkumar
 *
 */
public class ElementDetail {

	@JsonProperty(value="elementName", required = true)
	private String elementName;
	
	@JsonProperty(value="findBy", required = true)
	private FindByEnum findBy;
	
	@JsonProperty(value="elementValue", required = false)
	private String elementValue;
	
	@JsonProperty(value="actionDetail", required = false)
	private ActionDetail actionDetail;
	
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public FindByEnum getFindBy() {
		return findBy;
	}
	public void setFindBy(FindByEnum findBy) {
		this.findBy = findBy;
	}
	public String getElementValue() {
		return elementValue;
	}
	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}
	public ActionDetail getActionDetail() {
		return actionDetail;
	}
	public void setActionDetail(ActionDetail actionDetail) {
		this.actionDetail = actionDetail;
	}
	
	
}
