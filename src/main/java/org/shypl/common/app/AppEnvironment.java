package org.shypl.common.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppEnvironment {
	private final Path              path;
	private final String            type;
	private final boolean           testMode;
	private final AppConfigProvider configProvider;

	public AppEnvironment(String path) {
		this(Paths.get(path));
	}

	public AppEnvironment(Path path) {
		this.path = path.toAbsolutePath();

		try {
			Path pathToEnv = getPathTo("private/config/env");
			if (Files.exists(path)) {
				try (BufferedReader reader = Files.newBufferedReader(pathToEnv, StandardCharsets.UTF_8)) {
					type = reader.readLine();
				}
			}
			else {
				type = "dev";
			}
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}

		testMode = type == null || !(type.equals("prod") || type.equals("production"));

		configProvider = new AppConfigProvider(this);
	}

	public Path getPath() {
		return path;
	}

	public Path getPathTo(String target) {
		return path.resolve(target);
	}

	public Path getPathToConfig(String target) {
		Path file = getPathTo("private/config/" + type + '/' + target);
		if (!Files.exists(file)) {
			file = getPathTo("private/config/" + target);
			if (!Files.exists(file)) {
				throw new RuntimeException("Config is not exists (" + target + ')');
			}
		}

		return file;
	}

	public String getType() {
		return type;
	}

	public boolean isTestMode() {
		return testMode;
	}

	public <T> T getConfig(Class<T> type) {
		return configProvider.get(type);
	}

	public AppConfigProvider getConfigProvider() {
		return configProvider;
	}
}
