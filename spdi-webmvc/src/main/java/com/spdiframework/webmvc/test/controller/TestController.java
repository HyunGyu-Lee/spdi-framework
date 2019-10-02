package com.spdiframework.webmvc.test.controller;

import com.spdiframework.webmvc.annotation.Controller;
import com.spdiframework.webmvc.annotation.Service;

@Controller
public class TestController {
	
	@Service(name="test")
	public String service() {
		return "main";
	}

}
