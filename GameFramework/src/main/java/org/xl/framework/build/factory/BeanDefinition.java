package org.xl.framework.build.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BeanDefinition implements Iterable<PropertyDefinitionValue> {
	private String beanName;
	private String className;
	private final List<PropertyDefinitionValue> propertyDefinitionValues;

	public BeanDefinition() {
		propertyDefinitionValues = new ArrayList<>();
	}

	public void addProperty(PropertyDefinitionValue value) {
		if(value == null) {
			throw new NullPointerException("Null");
		}
		propertyDefinitionValues.add(value);
	}

	public boolean isEmpty() {
		return propertyDefinitionValues.isEmpty();
	}

	public PropertyDefinitionValue[] toArray() {
		return propertyDefinitionValues.toArray(new PropertyDefinitionValue[0]);
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public Iterator<PropertyDefinitionValue> iterator() {
		return propertyDefinitionValues.iterator();
	}
}
