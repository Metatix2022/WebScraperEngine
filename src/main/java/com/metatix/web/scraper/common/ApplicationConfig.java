package com.metatix.web.scraper.common;

import java.io.File;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metatix.web.scraper.domain.WebScrapperConfig;

/**
 * @author S.Sathishkumar
 *
 */

@Configuration
@ComponentScan("com.metatix.web.scraper")
public class ApplicationConfig {

	@Value("#{systemProperties['appHomePath']}")
	private String appHomePath;
	
	@Bean("webScrapperConfig")
	@Scope("singleton")
	public WebScrapperConfig loadScraperInfo() {
		WebScrapperConfig webScrapperConfig = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			webScrapperConfig = objectMapper.readValue(new File(appHomePath+"/app_config.json"),WebScrapperConfig.class);
			System.out.println(webScrapperConfig.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return webScrapperConfig;
	}
	
	@Bean("applicationProperties")
	@Scope("singleton")
	@Autowired
	public ApplicationProperties ApplicationProperties(WebScrapperConfig webScrapperConfig) {
		ObjectMapper objectMapper = new ObjectMapper();
		Properties props = objectMapper.convertValue(webScrapperConfig.getScraperCommonConfig(), Properties.class);
		return new ApplicationProperties(props);
	}
	
}
