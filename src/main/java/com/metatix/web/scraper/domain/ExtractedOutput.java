package com.metatix.web.scraper.domain;
/**
 * @author S.Sathishkumar
 *
 */
public class ExtractedOutput {
	private String title;
	private String eventLink;
	private String date;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEventLink() {
		return eventLink;
	}
	public void setEventLink(String eventLink) {
		this.eventLink = eventLink;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setPropertyValue(String propName, String propValue) {
		if(propName.equalsIgnoreCase("title")) {
			this.title=propValue;
		}else if(propName.equalsIgnoreCase("eventLink")) {
			this.eventLink=propValue;
		}else if(propName.equalsIgnoreCase("date")) {
			this.date=propValue;
		}
	}
	
	public String getAsCSVValue() {
		return this.date+","+this.title+","+this.eventLink+"\n";
	}
	
}
