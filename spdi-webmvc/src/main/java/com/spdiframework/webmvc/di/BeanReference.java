package com.spdiframework.webmvc.di;

public class BeanReference {
	private String target;
	private String refName;
	private String injectLocation;
	
	public BeanReference(String target, String refName, String injectLocation) {
		this.target = target;
		this.refName = refName;
		this.injectLocation = injectLocation;
	}
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}
	public String getInjectLocation() {
		return injectLocation;
	}
	public void setInjectLocation(String injectLocation) {
		this.injectLocation = injectLocation;
	}

}
