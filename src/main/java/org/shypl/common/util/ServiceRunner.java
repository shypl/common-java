package org.shypl.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class ServiceRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRunner.class);
	
	private final List<Service> services;
	private final Deque<Service> runningServices = new LinkedList<>();
	
	public ServiceRunner(Service... services) {
		this.services = asList(services);
	}
	
	public synchronized void start() {
		LOGGER.info("Starting");
		
		if (!runningServices.isEmpty()) {
			throw new IllegalStateException();
		}
		
		for (Service service : services) {
			String name = service.getClass().getName();
			try {
				LOGGER.info("Service {}: Starting", name);
				service.start();
				LOGGER.info("Service {}: Started", name);
				
				runningServices.addFirst(service);
			}
			catch (Throwable e) {
				LOGGER.error("Service {}: Start fail", name, e);
				stop();
				return;
			}
		}
		Runtime.getRuntime().addShutdownHook(new Thread(this::stop, "shutdown"));
		
		LOGGER.info("Started");
	}
	
	public synchronized void stop() {
		LOGGER.info("Stopping");
		
		for (Service service : runningServices) {
			String name = service.getClass().getName();
			try {
				LOGGER.info("Service {}: Stopping", name);
				service.stop();
				LOGGER.info("Service {}: Stopped", name);
			}
			catch (Throwable e) {
				LOGGER.error("Service {}: Stop fail", name, e);
			}
		}
		
		runningServices.clear();
		
		LOGGER.info("Stopped");
	}
}
