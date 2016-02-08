package org.shypl.common.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppEnvironment {
	public final Path              path;
	public final String            type;
	public final boolean           testMode;
	public final AppConfigProvider configs;

	public AppEnvironment(String path) {
		this(Paths.get(path));
	}

	public AppEnvironment(Path path) {
		this.path = path.toAbsolutePath();

		try {
			type = Files.newBufferedReader(getPathTo("private/config/env"), StandardCharsets.UTF_8).readLine();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}

		testMode = type == null || !(type.equals("prod") || type.equals("production"));

		configs = new AppConfigProvider(this);
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
}
