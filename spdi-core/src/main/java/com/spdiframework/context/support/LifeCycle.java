package com.spdiframework.context.support;

/***
 * @author dlgusrb0808@gmail.com
 */
public abstract class LifeCycle {

	private boolean isLoadHooking = true;
	private boolean isShutdownHooking = true;
	private boolean isLoaded = false;

	protected abstract void load();

	protected abstract void shutdown();

	protected abstract void refresh();

	protected boolean isLoadHooking() {
		return isLoadHooking;
	}

	protected void setLoadHooking(boolean isLoadHooking) {
		this.isLoadHooking = isLoadHooking;
	}

	protected boolean isShutdownHooking() {
		return isShutdownHooking;
	}

	protected void setShutdownHooking(boolean isShutdownHooking) {
		this.isShutdownHooking = isShutdownHooking;
	}

	protected void loadHook() {
	}

	protected void shutdownHook() {
	}

	protected boolean isLoaded() {
		return isLoaded;
	}

	protected void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}
}
