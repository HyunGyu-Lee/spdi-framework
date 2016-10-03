package com.leeframework.beans;

import java.util.Set;

import com.leeframework.beans.aware.BeanFactoryAware;
import com.leeframework.beans.aware.BeanNameAware;
import com.leeframework.beans.factory.AbstractBeanFactory;
import com.leeframework.context.ApplicationContext;
import com.leeframework.context.ApplicationContextAware;

public class Student implements BeanNameAware, BeanFactoryAware, ApplicationContextAware {
	
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
	public void setBeanName(String beanName) {
		System.out.println("BeanNameAware를 통해 bean이 자신의 이름을 알 수 있습니다."+beanName);
	}

	@Override
	public void setBeanFactory(AbstractBeanFactory beanFactory) {
		System.out.println("BeanFactoryAware를 통해 빈에서 빈팩토리에 접근을 가능하게 합니다.");
		System.out.println("Student빈에서 모든 빈 엔트리 출력");
		Set<String> k = beanFactory.getBeanFactoryMetaData().getBeanEntries().keySet();
		
		for(String kk : k) {
			System.out.println(beanFactory.getBeanFactoryMetaData().getEntry(kk));
		}
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		System.out.println("ApplicationContextAware를 통해 빈이 Context에 접근할 수 있습니다.");
		System.out.println(applicationContext);
	}
	
}
