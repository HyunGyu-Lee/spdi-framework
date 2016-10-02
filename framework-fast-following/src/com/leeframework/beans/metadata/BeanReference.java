package com.leeframework.beans.metadata;

public class BeanReference {
	private String refName;
	private String injectField;
	
	public BeanReference(String refName, String injectField) {
		this.refName = refName;
		this.injectField = injectField;
	}
	
	public String getInjectField() {
		return injectField;
	}

	public void setInjectField(String injectField) {
		this.injectField = injectField;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}
	
}
