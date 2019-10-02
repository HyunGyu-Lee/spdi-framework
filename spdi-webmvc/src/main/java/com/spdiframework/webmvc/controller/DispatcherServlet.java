package com.spdiframework.webmvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spdiframework.webmvc.views.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spdiframework.webmvc.core.WebApplicationContainer;

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
		// mappingHandler�� ���� ������ ã�Ƴ��� � �޼ҵ����� ã����
		// �� �ָ� invoke �ؼ� result(view)�� �޾ƿ��� �ְ� �־����
		// �ش� view�� viewResolver�� ó�� �� ����� ���⼭ ������ �Ǵ� ������ �Ѵ�.
	}
	
}
