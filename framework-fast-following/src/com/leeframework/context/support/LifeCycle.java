package com.leeframework.context.support;

public abstract class LifeCycle {
	
	private boolean isInitailizeHooking = true;
	private boolean isShutdownHooking = true;
	
	public boolean isInitailizeHooking() {
		return isInitailizeHooking;
	}
	public void setInitailizeHooking(boolean isInitailizeHooking) {
		this.isInitailizeHooking = isInitailizeHooking;
	}
	public boolean isShutdownHooking() {
		return isShutdownHooking;
	}
	public void setShutdownHooking(boolean isShutdownHooking) {
		this.isShutdownHooking = isShutdownHooking;
	};
	
	public void initailizeHook(){};
	public void shutdownHook(){};

	public abstract void initailize();
	public abstract void shutdown();
	
	public abstract void refresh();
	
	public abstract void close();
}
