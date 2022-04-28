package org.xl.framework.build.factory;

import java.util.*;
import java.util.function.BiConsumer;

public class AttributeDefinitionValueMap {
	private final Map<String, Object> attrValues;

	public AttributeDefinitionValueMap(Map<String, Object> attrValues) {
		this.attrValues = attrValues;
	}

	public AttributeDefinitionValueMap() {
		this.attrValues = new HashMap<>();
	}

	public void put(String key, Object value) {
		attrValues.put(shortLineToHump(key), value);
	}

	public Object get(String ket) {
		return attrValues.get(ket);
	}

	public void forEach(BiConsumer<String, Object> action) {
		attrValues.forEach(action);
	}

	public Set<String> keySet() {
		return attrValues.keySet();
	}

	private static String shortLineToHump(String key) {
		StringBuilder methodName = new StringBuilder();
		String[] split = key.split("-");
		methodName.append(split[0]);
		for(int i = 1; i < split.length; i++) {
			methodName.append(split[i].substring(0, 1).toUpperCase()).append(split[i].substring(1));
		}
		return methodName.toString();
	}
}
