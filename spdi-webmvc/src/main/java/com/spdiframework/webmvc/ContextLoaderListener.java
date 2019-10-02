package com.spdiframework.webmvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spdiframework.webmvc.core.ContainerFactory;
import com.spdiframework.webmvc.core.WebApplicationContainer;


public class ContextLoaderListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(ContextLoaderListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		
		ServletContext context = e.getServletContext();
		
		String contextPath = context.getRealPath("/");	// Context �� ���		
		String log4J = "default package:log4j.xml";	// log4j �������� ���		
		String containerConfiguration = context.getInitParameter("container-config");	// container ���� ���� ���
		
		logger.info("Init Log4J - [{}]", log4J);
		logger.info("Init container - [{}]", "/WEB-INF/"+containerConfiguration);
		
		ContainerFactory.setContextPath(contextPath);
		
		//WebApplicationContainer container = new WebApplicationContainer(containerConfiguration);
		
		WebApplicationContainer.getInstance().lazyLoad(ContainerFactory.getContextPath()+containerConfiguration, "Web-Application");
		
	}
	
}
