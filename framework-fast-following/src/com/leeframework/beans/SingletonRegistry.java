package com.leeframework.beans;

import java.util.HashMap;

public class SingletonRegistry {
	private HashMap<String, Object> registry;
	
	public SingletonRegistry() {
		registry = new HashMap<>();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName, Class<T> clazz) {
		return (T)registry.get(beanName);
	}
	
	public void registry(BeanEntry beanEntry) {
		System.out.println("각종 빈 생성작업 후");
		registry.put(beanEntry.getBeanName(), new BeanEntry(BeanEntry.class, "테스트엔트리", Scope.SINGLETON));
	}
	
}
