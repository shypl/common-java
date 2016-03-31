package org.shypl.common.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ModularSystem {

	private final static Logger logger = LoggerFactory.getLogger(ModularSystem.class);

	private final Map<Class<?>, Object> facades       = new HashMap<>();
	private final Deque<Module<?>>      modules       = new ArrayDeque<>();
	private final ModuleFacades         moduleFacades = this::getModuleFacade;

	private boolean started;

	public void add(Module<?> module) {
		if (!modules.contains(module)) {

			Object facade = module.getFacade();
			Class<?> facadeClass = facade.getClass();

			if (!facadeClass.isAssignableFrom(PrivateModuleFacade.class)) {
				if (facades.containsKey(facadeClass)) {
					if (facades.get(facadeClass) != module) {
						throw new IllegalArgumentException("Module for facade " + facadeClass.getName() + " is already exists");
					}
				}
				else {
					facades.put(facadeClass, facade);
				}
			}

			modules.add(module);
		}
	}

	public void addMany(Module<?>... modules) {
		for (Module<?> module : modules) {
			add(module);
		}
	}

	public void addMany(Collection<Module<?>> modules) {
		modules.forEach(this::add);
	}

	public boolean start() {
		if (started) {
			throw new IllegalStateException("System is already started");
		}
		started = true;

		for (Module<?> module : modules) {
			try {
				module.startInternal(moduleFacades);
			}
			catch (Exception e) {
				logger.error("Fail to start module #" + module.getClass(), e);
				stop();
				started = false;
				return false;
			}
		}

		return true;
	}

	public void stop() {
		if (!started) {
			throw new IllegalStateException("System is not started");
		}
		started = false;


		for (Iterator<Module<?>> iterator = modules.descendingIterator(); iterator.hasNext(); ) {
			Module<?> module = iterator.next();
			try {
				module.stopInternal(moduleFacades);
			}
			catch (Exception e) {
				logger.error("Fail to stop module #" + module.getClass(), e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <F> F getModuleFacade(Class<F> type) {
		return (F)facades.get(type);
	}
}
