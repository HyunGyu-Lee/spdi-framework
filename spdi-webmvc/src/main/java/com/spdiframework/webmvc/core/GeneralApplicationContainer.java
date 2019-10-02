package com.spdiframework.webmvc.core;

import com.spdiframework.webmvc.di.ContainerConfiguration;

public class GeneralApplicationContainer extends Container {
	
	public GeneralApplicationContainer(String configuration) {
		super(configuration, "General-Application");
	}
	
	public GeneralApplicationContainer(ContainerConfiguration configuration) {
		super(configuration, "General-Application");
	}
	
	public static void main(String[] args) {
		Container con = new GeneralApplicationContainer("src/context-conf.json");
	}
	
}
