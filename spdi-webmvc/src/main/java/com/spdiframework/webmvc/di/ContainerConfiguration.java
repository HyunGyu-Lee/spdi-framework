package com.spdiframework.webmvc.di;

import java.util.ArrayList;

public class ContainerConfiguration extends Configuration {

	private String containerId;
	private String controllerScanPackage;
	private ArrayList<BeanConfiguration> beanConfigurations;

	public ContainerConfiguration(){}
	
	public ContainerConfiguration(String containerId, String controllerScanPackage, ArrayList<BeanConfiguration> beanConfigurations) {
		this.containerId = containerId;
		this.controllerScanPackage = controllerScanPackage;
		this.beanConfigurations = beanConfigurations;
	}

	public ArrayList<BeanConfiguration> getBeanConfigurations() {
		return beanConfigurations;
	}
	public void setBeanConfigurations(ArrayList<BeanConfiguration> beanConfigurations) {
		this.beanConfigurations = beanConfigurations;
	}
	public String getContainerId() {
		return containerId;
	}
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	public String getControllerScanPackage() {
		return controllerScanPackage;
	}
	public void setControllerScanPackage(String controllerScanPackage) {
		this.controllerScanPackage = controllerScanPackage;
	}

	@Override
	public String toString() {
		return "ContainerConfiguration [containerId=" + containerId + ", controllerScanPackage=" + controllerScanPackage
				+ ", beanConfigurations=" + beanConfigurations + "]";
	}
	
}
