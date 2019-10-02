package com.spdiframework.beans;

import com.spdiframework.beans.streotype.Bean;
import com.spdiframework.beans.streotype.Configuration;

@Configuration
public class MyBeanConfig {

	@Bean(initMethod="init", destroyMethod="destory")
	public Student student(String t, Student test) {
		Student student = new Student();		
		return student;
	}
	
	@Bean
	public Student test() {
		return new Student();
	}
	
}
