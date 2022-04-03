package com.metatix.web.scraper.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.metatix.web.scraper.common.FindByEnum;
/**
 * @author S.Sathishkumar
 *
 */
public class Element {

	@JsonProperty(value="eventList", required = true)
	private String eventList;
	
	@JsonProperty(value="findBy", required = true)
	private FindByEnum findBy;
	
	@JsonProperty(value="ElementDetail", required = true)
	private List<ElementDetail> elementDetails;
	
	public String getEventList() {
		return eventList;
	}
	public void setEventList(String eventList) {
		this.eventList = eventList;
	}
	public FindByEnum getFindBy() {
		return findBy;
	}
	public void setFindBy(FindByEnum findBy) {
		this.findBy = findBy;
	}
	public List<ElementDetail> getElementDetails() {
		return elementDetails;
	}
	public void setElementDetails(List<ElementDetail> elementDetails) {
		this.elementDetails = elementDetails;
	}
	
	
}
