package com.leeframework.beans;

import com.leeframework.beans.lifecycle.DisposableBean;
import com.leeframework.beans.lifecycle.InitailizingBean;

public class Student implements InitailizingBean, DisposableBean {
	
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}

	@Override
	public void afterPropertiesSet() {
		System.out.println("afterPropertiesSet");
	}
	
	@Override
	public void destroy() {
		System.out.println("ÆÄ±«ÀÚ È£Ãâ");		
	}
	

}
