package com.metatix.web.scraper.common;
/**
 * @author S.Sathishkumar
 *
 */
public enum FindByEnum {
	XPATH ("xpath"),
	CLASSNAME ("className"),
	ACTION ("action"),
	ATTRIBUTE("attribute");
	
	private String findBy;
	
	private FindByEnum (String findBy) {
		this.findBy=findBy;
	}

	public String getFindBy() {
		return findBy;
	}

	public void setFindBy(String findBy) {
		this.findBy = findBy;
	}
	
	
	
}
