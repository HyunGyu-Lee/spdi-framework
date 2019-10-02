package com.mvcsupporter.core;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mvcsupporter.di.ConfigurationParser;
import com.mvcsupporter.di.ContainerConfiguration;
import com.mvcsupporter.utils.JSONUtils;

public class ContainerFactory {
	
	private static String contextPath = "";
	private static String configPath = "";
	
	private static final Logger logger = LoggerFactory.getLogger(ContainerFactory.class);
	
	public static String getContextPath() {
		return contextPath;
	}

	public static void setContextPath(String contextPath) {
		ContainerFactory.contextPath = contextPath+"\\WEB-INF\\";
	}

	public static ContainerConfiguration getContainerConfiguration(String conf) {
		configPath = conf;
		JSONObject json = null;
		ContainerConfiguration containerConfig = null;
		
		String jsonString = JSONUtils.readJsonFile(configPath);
		
		if(jsonString!=null)
		{
			json = JSONUtils.json(jsonString);
			
			containerConfig = ConfigurationParser.parse(json);
			
			logger.info("Read configuration - {}",containerConfig.toString());
			logger.info("Controller scan target - [{}]", containerConfig.getControllerScanPackage());			
		}
		else
		{
			logger.warn("Can not initiate container. Check [{}]", configPath);
		}
		return containerConfig;
	}

	public static String getConfigPath() {
		return configPath;
	}

	public static void setConfigPath(String configPath) {
		ContainerFactory.configPath = configPath;
	}
	
}
