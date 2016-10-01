package com.leeframework.beans;

import java.util.HashMap;

public class BeanFactoryMetaData {
	
	private HashMap<String, BeanEntry> beanEntries = new HashMap<>();
	
	public BeanFactoryMetaData() {
		beanEntries.put("test", new BeanEntry(BeanEntry.class, "test", Scope.SINGLETON));
	}
	public BeanEntry getEntry(String beanName) {
		return beanEntries.get(beanName);
	}
	public void addEntry(String beanName, BeanEntry entry) {
		beanEntries.put(beanName, entry);
	}
	public HashMap<String, BeanEntry> getBeanEntries() {
		return beanEntries;
	}
}
