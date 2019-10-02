package com.mvcsupporter.core;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mvcsupporter.di.ContainerConfiguration;
import com.mvcsupporter.utils.ReflectionUtils;

public class WebApplicationContainer extends Container {

	private ArrayList<Object> loadedController;
	
	private static final Logger logger = LoggerFactory.getLogger(WebApplicationContainer.class);
	
	private static final class Singleton {
		private static final WebApplicationContainer instance = new WebApplicationContainer();
	}
	
	private WebApplicationContainer(){}
	
	public static WebApplicationContainer getInstance() {
		return Singleton.instance;
	}
	
	public WebApplicationContainer(ContainerConfiguration configuration) {
		super(configuration, "Web-Application");
		loadedController = new ArrayList<Object>();
		loadAllController(configuration.getControllerScanPackage());
	}
	
	public WebApplicationContainer(String configurationPath) {
		super(configurationPath, "Web-Application");
		loadedController = new ArrayList<Object>();
		loadAllController(this.getConfiguration().getControllerScanPackage());
	}
	
	@Override
	public void lazyLoad(String configurationPath, String containerType) {
		super.lazyLoad(configurationPath, containerType);
		loadedController = new ArrayList<Object>();
		loadAllController(this.getConfiguration().getControllerScanPackage());
	}
	
	public void loadAllController(String controllerScanPath)
	{
		ArrayList<Class<?>> classNames = ReflectionUtils.scanPackage(controllerScanPath);
		
		for(Class<?> className : classNames)
		{
			loadedController.add(ReflectionUtils.createInstance(className));
			logger.info("Load Controller - [{}]", className);
		}
	}
	
	public ArrayList<Object> getLoadedController() {
		return loadedController;
	}

	public void setLoadedController(ArrayList<Object> loadedController) {
		this.loadedController = loadedController;
	}

}
