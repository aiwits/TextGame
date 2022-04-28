package org.xl.framework.build.factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AttributeDefinitionRegistry implements Iterable<AttributeDefinitionValueMap> {
	private final Map<String, AttributeDefinitionValueMap> valueMap;

	public AttributeDefinitionRegistry() {
		this.valueMap = new HashMap<>();
	}

	public void putValueMap(String id, AttributeDefinitionValueMap value) {
		if(valueMap.get(id) != null) {
			return;
		}
		valueMap.put(id, value);
	}

	public boolean containsKey(String key) {
		return valueMap.containsKey(key);
	}

	public boolean containsValue(AttributeDefinitionValueMap value) {
		return valueMap.containsValue(value);
	}

	public AttributeDefinitionValueMap getValueMap(String id) {
		if(id != null && !"".equals(id)) {
			return valueMap.get(id);
		}
		return null;
	}

	public String[] getKeys() {
		return valueMap.keySet().toArray(new String[0]);
	}

	public boolean isEmpty() {
		return valueMap.isEmpty();
	}

	@Override
	public Iterator<AttributeDefinitionValueMap> iterator() {
		return valueMap.values().iterator();
	}
}
