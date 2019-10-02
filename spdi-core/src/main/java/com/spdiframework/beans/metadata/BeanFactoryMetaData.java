package com.spdiframework.beans.metadata;

import java.util.ArrayList;
import java.util.HashMap;

public class BeanFactoryMetaData {
	
	private HashMap<String, BeanEntry> beanEntries = new HashMap<>();
	private HashMap<String, BeanReference> beanReferences = new HashMap<>();
	private HashMap<String, ArrayList<BeanDependency>> dependencies = new HashMap<>();
	
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
	public void addDefendencies(String beanName, BeanDependency dependency) {
		ArrayList<BeanDependency> target = dependencies.get(beanName);
		
		if(target==null)
		{
			target = new ArrayList<>();
			dependencies.put(beanName, target);
		}
		
		target.add(dependency);
	}
	
	public ArrayList<BeanDependency> getDependency(String beanName) {
		return dependencies.get(beanName);
	}

	@Override
	public String toString() {
		return "BeanFactoryMetaData [beanEntries=" + beanEntries + ", beanReferences=" + beanReferences
				+ ", dependencies=" + dependencies + "]";
	}
}
