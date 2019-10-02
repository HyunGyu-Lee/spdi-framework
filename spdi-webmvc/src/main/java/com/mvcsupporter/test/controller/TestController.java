package com.mvcsupporter.test.controller;

import javax.servlet.http.HttpServletRequest;

import com.mvcsupporter.web.annotation.Controller;
import com.mvcsupporter.web.annotation.Service;
import com.mvcsupporter.web.annotation.Service.RequestMethod;

@Controller
public class TestController {
	
	@Service(name="test")
	public String service(HttpServletRequest request) {
		System.out.println("테스트 서비스입니다.");
		System.out.println("요청 파라미터 : "+request.getParameter("test"));
		return "main";
	}
	
	@Service(name="verygood", method = RequestMethod.POST)
	public String wow(HttpServletRequest request) {
		System.out.println("베리굳에서 처리후 b.jsp로 넘긴다");
		return "b";
	}
	
	
}
