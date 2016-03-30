package org.shypl.common.app;

public class FacedModule<F> extends Module<F> {
	private final F facade;

	public FacedModule(F facade) {
		super();
		this.facade = facade;
	}

	@Override
	public final F getFacade() {
		return facade;
	}
}
