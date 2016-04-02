package org.shypl.common.app;

public abstract class Module<F> {
	private boolean initialized;
	private boolean started;

	public abstract F getFacade();

	protected void initialize(ModuleFacades modules) {
	}

	protected void start(ModuleFacades modules) throws Exception {
	}

	protected void stop(ModuleFacades modules) throws Exception {
	}

	void initializeInternal(ModuleFacades modules) {
		if (!initialized) {
			initialize(modules);
			initialized = true;
		}
	}

	void startInternal(ModuleFacades modules) throws Exception {
		if (!started) {
			start(modules);
			started = true;
		}
	}

	void stopInternal(ModuleFacades modules) throws Exception {
		if (started) {
			started = false;
			stop(modules);
		}
	}
}

