package org.shypl.common.app;

public abstract class Module<F> {
	private boolean started;

	public abstract F getFacade();

	protected void start(ModuleFacades modules) throws Exception {
	}

	protected void stop(ModuleFacades modules) throws Exception {
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

