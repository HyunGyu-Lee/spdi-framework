package com.spdiframework.webmvc.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.spdiframework.webmvc.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spdiframework.webmvc.core.WebApplicationContainer;
import com.spdiframework.webmvc.di.Invocable;
import com.spdiframework.webmvc.utils.ReflectionUtils;

public class MappingHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(MappingHandler.class);
	
	public String mapping(HttpServletRequest request) {
		
		ArrayList<Object> controllers = WebApplicationContainer.getInstance().getLoadedController();		
		String serviceName = getServiceName(request);
		Service.RequestMethod requestMethod = getRequestMethod(request);
		String viewName = "";
		
		Invocable invocable = findService(controllers, serviceName, requestMethod);
		
		if(invocable!=null)
		{
			invocable.setParameters(request);
			viewName = (String)invocable.invoke();
		}
		else
		{
			logger.info("Cannot mappaing service - [{}]", serviceName);
		}
		
		return viewName;
	}
	
	private Service.RequestMethod getRequestMethod(HttpServletRequest request) {
		Service.RequestMethod requestMethod = null;
		
		switch(request.getMethod())
		{
		case "GET" : requestMethod = Service.RequestMethod.GET; break;
		case "POST" : requestMethod = Service.RequestMethod.POST; break;
		}
		
		return requestMethod;
	}
	
	private String getServiceName(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String requestURI = request.getRequestURI();
		
		return requestURI.substring(contextPath.length()+1, requestURI.length());
	}
	
	public Invocable findService(ArrayList<Object> controllers, String serviceName, Service.RequestMethod m) {
		
		for(Object controller : controllers)
		{
			ArrayList<Method> methods = ReflectionUtils.findAnnotatedMethodsInClass(controller.getClass(), Service.class);
			Method method = ReflectionUtils.findMethodByServiceName(methods, serviceName, m);
			
			if(method!=null)
			{
				return new Invocable(controller, method);
			}
		}
		
		return null;
	}
	
}
