package com.leeframework.beans.metadata;

import java.util.HashMap;

public class BeanFactoryMetaData {
	
	private HashMap<String, BeanEntry> beanEntries = new HashMap<>();
	private HashMap<String, BeanReference> beanReferences = new HashMap<>();
	
	public BeanFactoryMetaData() {}
	
	public BeanEntry getEntry(String beanName) {
		return beanEntries.get(beanName);
	}
	public BeanFactoryMetaData addEntry(String beanName, BeanEntry entry) {
		beanEntries.put(beanName, entry);
		return this;
	}
	public BeanReference getReference(String beanName) {
		return beanReferences.get(beanName);
	}
	public BeanFactoryMetaData addReference(String beanName, BeanReference reference) {
		beanReferences.put(beanName, reference);
		return this;
	}
	public HashMap<String, BeanEntry> getBeanEntries() {
		return beanEntries;
	}
	
	public HashMap<String, BeanReference> getBeanReferences() {
		return beanReferences;
	}
}
