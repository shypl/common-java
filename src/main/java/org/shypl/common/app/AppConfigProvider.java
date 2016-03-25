package org.shypl.common.app;

import org.shypl.common.util.YamlConfigLoader;

import java.util.HashMap;
import java.util.Map;

public class AppConfigProvider {
	private final AppEnvironment env;
	private final Map<Class<?>, Object> configs = new HashMap<>();

	public AppConfigProvider(AppEnvironment env) {
		this.env = env;
	}

	public <T> T get(Class<T> type) {
		synchronized (configs) {
			@SuppressWarnings("unchecked")
			T config = (T)configs.get(type);

			if (config == null) {
				config = load(type);
				configs.put(type, config);
			}

			return config;
		}
	}

	public void free(Class<?> type) {
		configs.remove(type);
	}

	private <T> T load(final Class<T> type) {
		String fileName = type.getSimpleName();
		int i = fileName.indexOf("Config");
		if (i == 0) {
			fileName = fileName.substring(6); // remove "Config" prefix
		}
		else if (i > 0) {
			fileName = fileName.substring(i);
		}

		fileName = fileName.toLowerCase() + ".yml";

		try {
			return YamlConfigLoader.load(env.getPathToConfig(fileName), type);
		}
		catch (Exception e) {
			throw new RuntimeException("Unable to load config by type " + type.getName(), e);
		}
	}
}
