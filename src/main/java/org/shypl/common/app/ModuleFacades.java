package org.shypl.common.app;

public interface ModuleFacades {
	<F> F get(Class<F> type);
}
