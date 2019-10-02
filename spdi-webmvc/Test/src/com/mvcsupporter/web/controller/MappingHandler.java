package com.mvcsupporter.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mvcsupporter.core.WebApplicationContainer;
import com.mvcsupporter.di.Invocable;
import com.mvcsupporter.utils.ReflectionUtils;
import com.mvcsupporter.web.annotation.Service;
import com.mvcsupporter.web.annotation.Service.RequestMethod;

public class MappingHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(MappingHandler.class);
	
	public String mapping(HttpServletRequest request) {
		
		ArrayList<Object> controllers = WebApplicationContainer.getInstance().getLoadedController();		
		String serviceName = getServiceName(request);
		RequestMethod requestMethod = getRequestMethod(request);
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
	
	private RequestMethod getRequestMethod(HttpServletRequest request) {
		RequestMethod requestMethod = null;
		
		switch(request.getMethod())
		{
		case "GET" : requestMethod = RequestMethod.GET; break;
		case "POST" : requestMethod = RequestMethod.POST; break;
		}
		
		return requestMethod;
	}
	
	private String getServiceName(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String requestURI = request.getRequestURI();
		
		return requestURI.substring(contextPath.length()+1, requestURI.length());
	}
	
	public Invocable findService(ArrayList<Object> controllers, String serviceName, RequestMethod m) {
		
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
