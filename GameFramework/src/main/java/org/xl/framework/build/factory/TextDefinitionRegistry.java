package org.xl.framework.build.factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TextDefinitionRegistry implements Iterable<TextDefinitionValue> {
	private final Map<String, TextDefinitionValue> descriptionValues;

	public TextDefinitionRegistry() {
		this.descriptionValues = new HashMap<>();
	}

	public void putValue(String id, TextDefinitionValue value) {
		if(id.equals(value.getId())) {
			descriptionValues.put(id, value);
		} else {
			throw new IllegalArgumentException("id信息不匹配");
		}
	}

	public boolean containsKey(String key) {
		return descriptionValues.containsKey(key);
	}

	public boolean containsValue(TextDefinitionValue value) {
		return descriptionValues.containsValue(value);
	}

	public TextDefinitionValue[] getValues() {
		return descriptionValues.values().toArray(new TextDefinitionValue[0]);
	}

	public String[] getKeys() {
		return descriptionValues.keySet().toArray(new String[0]);
	}

	public TextDefinitionValue getValue(String id) {
		if(id !=null && !"".equals(id)) {
			return descriptionValues.get(id);
		}
		return null;
	}

	public boolean isEmpty() {
		return descriptionValues.isEmpty();
	}

	@Override
	public Iterator<TextDefinitionValue> iterator() {
		return descriptionValues.values().iterator();
	}
}
