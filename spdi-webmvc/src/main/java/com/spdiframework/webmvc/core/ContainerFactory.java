package com.spdiframework.webmvc.core;

import com.spdiframework.webmvc.di.ConfigurationParser;
import com.spdiframework.webmvc.di.ContainerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContainerFactory {

	private static String contextPath = "";
	private static String configPath = "";

	private static final Logger logger = LoggerFactory.getLogger(ContainerFactory.class);

	public static String getContextPath() {
		return contextPath;
	}

	public static void setContextPath(String contextPath) {
		ContainerFactory.contextPath = contextPath + "\\WEB-INF\\";
	}

	public static ContainerConfiguration getContainerConfiguration(String conf) {
		configPath = conf;
		ContainerConfiguration containerConfig = null;

		// TODO / [기존기능수정] Json 설정 파일 READ
		String jsonString = "";

		if (jsonString != null) {
			containerConfig = ConfigurationParser.parse(jsonString);

			logger.info("Read configuration - {}", containerConfig.toString());
			logger.info("Controller scan target - [{}]", containerConfig.getControllerScanPackage());
		} else {
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
