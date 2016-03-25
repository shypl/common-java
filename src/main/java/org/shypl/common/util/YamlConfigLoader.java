package org.shypl.common.util;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class YamlConfigLoader {

	public static <T> T load(Path file, Class<T> type) throws IOException, InstantiationException, IllegalAccessException {
		@SuppressWarnings("unchecked")
		final Map<String, Object> data = (Map<String, Object>)new Yaml().load(Files.newBufferedReader(file, StandardCharsets.UTF_8));
		return constructObject(type, data);
	}

	@SuppressWarnings("unchecked")
	private static <T> T constructObject(final Class<T> type, final Map<String, Object> data) throws IllegalAccessException, InstantiationException {
		T object = type.newInstance();
		if (data != null) {
			for (Field field : type.getFields()) {
				Object value = data.get(field.getName());
				if (value != null) {
					field.set(object, constructValue(field.getType(), value,
						(value instanceof Map && field.getType().isAssignableFrom(Map.class)) ? (ParameterizedType)field.getGenericType() : null));
				}
			}
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	private static Object constructValue(Class<?> type, Object value, ParameterizedType genericType) throws InstantiationException, IllegalAccessException {
		if (value == null) {
			return null;
		}

		if (type.isArray()) {
			return constructArray(type.getComponentType(), (List)value);
		}

		if (type.isPrimitive()) {
			return value;
		}

		if (type.isAssignableFrom(String.class)) {
			return value.toString();
		}

		if (type.isAssignableFrom(Path.class)) {
			return Paths.get(value.toString());
		}

		if (type.isAssignableFrom(File.class)) {
			return new File(value.toString());
		}

		if (type.isEnum()) {
			Class<Enum> enumType = (Class<Enum>)type;
			Object enumValue;
			try {
				enumValue = Enum.valueOf(enumType, value.toString().toUpperCase());
			}
			catch (IllegalArgumentException e) {
				enumValue = Enum.valueOf(enumType, value.toString());
			}
			return enumValue;
		}

		if (value instanceof Map) {
			Map<String, Object> map = (Map<String, Object>)value;

			if (type.isAssignableFrom(Map.class)) {
				return constructMap(genericType, map);
			}

			return constructObject(type, map);
		}

		return value;
	}

	@SuppressWarnings("ConstantConditions")
	private static Object constructMap(ParameterizedType type, Map<String, Object> data) throws IllegalAccessException, InstantiationException {
		final Map<String, Object> map = new LinkedHashMap<>(data.size());

		Type valueType = type.getActualTypeArguments()[1];
		Class<?> valueClass = (Class<?>)valueType;
		ParameterizedType genericType = valueType instanceof ParameterizedType ? (ParameterizedType)valueType : null;


		for (Map.Entry<String, Object> entry : data.entrySet()) {
			map.put(entry.getKey(), constructValue(valueClass, entry.getValue(), genericType));
		}

		return map;
	}

	private static Object constructArray(Class<?> type, List list) throws IllegalAccessException, InstantiationException {
		final int size = list.size();
		Object array = Array.newInstance(type, size);

		if (type.isArray()) {
			for (int i = 0; i < size; i++) {
				Array.set(array, i, constructArray(type.getComponentType(), (List)list.get(i)));
			}
		}
		else if (type.isPrimitive()) {
			for (int i = 0; i < size; i++) {
				Array.set(array, i, list.get(i));
			}
		}
		else if (type.isAssignableFrom(String.class)) {
			for (int i = 0; i < size; i++) {
				Object string = list.get(i);
				Array.set(array, i, string == null ? null : string.toString());
			}
		}
		else {
			for (int i = 0; i < size; i++) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>)list.get(i);
				Array.set(array, i, constructObject(type, map));
			}
		}

		return array;
	}
}
