package com.leeframework.beans;

import com.leeframework.beans.annotation.Bean;
import com.leeframework.beans.annotation.Configuration;

@Configuration
public class MyBeanConfig {

	@Bean
	public Student student() {
		Student student = new Student();		
		return student;
	}
	
}
