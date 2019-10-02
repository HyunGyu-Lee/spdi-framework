package com.spdiframework.webmvc.views;

public class ViewResolver {
	private String prefix;
	private String suffix;
	
	public ViewResolver() {}
	
	public ViewResolver(String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
	}
	
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public String toString() {
		return "ViewResolver [prefix=" + prefix + ", suffix=" + suffix + "]";
	}
}
