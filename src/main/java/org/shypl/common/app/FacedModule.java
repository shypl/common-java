package org.shypl.common.app;

public class FacedModule<F> extends Module<F> {
	private final F facade;

	@SuppressWarnings("unchecked")
	public FacedModule(F facade) {
		super((Class<F>)facade.getClass());
		this.facade = facade;
	}

	@Override
	public final F getFacade() {
		return facade;
	}
}
