package com.leeframework.beans;

import com.leeframework.beans.streotype.Bean;
import com.leeframework.beans.streotype.Configuration;

@Configuration
public class MyBeanConfig {

	@Bean(name="h", initMethod="init", destroyMethod="destory")
	public Student student(String t, Student test) {
		Student student = new Student();		
		return student;
	}
	
	@Bean
	public Student test() {
		return new Student();
	}
	
}
