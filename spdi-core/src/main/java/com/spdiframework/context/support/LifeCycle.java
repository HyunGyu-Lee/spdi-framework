package com.spdiframework.context.support;

public abstract class LifeCycle {
	
	private boolean isLoadHooking = true;
	private boolean isShutdownHooking = true;
	private boolean isLoaded = false;

	public abstract void load();
	public abstract void shutdown();
	public abstract void refresh();
	
	public boolean isLoadHooking() {
		return isLoadHooking;
	}

	public void setLoadHooking(boolean isLoadHooking) {
		this.isLoadHooking = isLoadHooking;
	}
	
	public boolean isShutdownHooking() {
		return isShutdownHooking;
	}
	
	public void setShutdownHooking(boolean isShutdownHooking) {
		this.isShutdownHooking = isShutdownHooking;
	};
	
	public void loadHook(){};
	public void shutdownHook(){};

	public boolean isLoaded() {
		return isLoaded;
	}
	
	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}
}
