package org.shypl.common.app;

public abstract class PrivateModule extends FacedModule<PrivateModuleFacade> {
	public PrivateModule() {
		super(PrivateModuleFacade.INSTANCE);
	}
}
