package com.mvcsupporter.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mvcsupporter.core.WebApplicationContainer;
import com.mvcsupporter.web.views.ViewResolver;

public class DispatcherServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	
	private static WebApplicationContainer container;

	private static MappingHandler mappingHandler;
	private static ViewResolver viewResolver;
	
	@Override
	public void init() throws ServletException {
		container = WebApplicationContainer.getInstance(); 
		mappingHandler = container.getBean("mappingHandler", MappingHandler.class);
		viewResolver = container.getBean("viewResolver", ViewResolver.class);
		logger.info("Init ViewResolver - [prefix:({}), suffix:({})]", viewResolver.getPrefix(), viewResolver.getSuffix());
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doDispatch(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doDispatch(request, response);
	}
	
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewName = mappingHandler.mapping(request);
		String prefix = viewResolver.getPrefix();
		String suffix = viewResolver.getSuffix();
		
		getServletContext().getRequestDispatcher(prefix+viewName+suffix).forward(request, response);
		// mappingHandler가 누가 일할지 찾아낸다 어떤 메소드인지 찾은후
		// 그 애를 invoke 해서 result(view)를 받아오는 애가 있어야함
		// 해당 view를 viewResolver가 처리 후 결과를 여기서 포워딩 또는 뭔가를 한다.
	}
	
}
