package com.metatix.web.scraper.common;

import java.util.Properties;

/**
 * @author S.Sathishkumar
 *
 */
public class ApplicationProperties {
	
	public static Properties properties=null;

	public ApplicationProperties(Properties properties) {
		ApplicationProperties.properties=properties;
	}
	
	public static String getProperty(String key, String defaultValue) {
		String propertyValue=null;
		if(properties!=null) {
			propertyValue=properties.getProperty(key,defaultValue);
		}
		return propertyValue;
	}
	
	public static String getProperty(String key) {
		return getProperty(key,null);
	}
}
