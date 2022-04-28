package org.xl.framework.build.factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SimpleBeanDefinitionRegistry implements BeanDefinitionRegistry {
	private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

	public SimpleBeanDefinitionRegistry() {
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionException {
		if(beanName == null || beanName.length() == 0) {
//			throw new IllegalArgumentException("bean name not be empty");
			beanName = null;
		}
		if(beanDefinition == null) {
			throw new IllegalArgumentException("beanDefinition not be null");
		}
		this.beanDefinitionMap.put(beanName, beanDefinition);
	}

	@Override
	public void removeBeanDefinition(String beanName) throws BeanDefinitionException {
		if(this.beanDefinitionMap.remove(beanName) == null) {
			throw new BeanDefinitionException(beanName + " Bean does not exist");
		}
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanName) throws BeanDefinitionException {
		BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
		if(beanDefinition == null) {
			throw new BeanDefinitionException(beanName + "Bean does not exist");
		} else {
			return beanDefinition;
		}
	}

	@Override
	public boolean containsBeanDefinition(String beanName) {
		return this.beanDefinitionMap.containsKey(beanName);
	}

	@Override
	public String[] getBeanDefinitionNames() {
		return this.beanDefinitionMap.keySet().toArray(new String[0]);
	}

	@Override
	public int getBeanDefinitionCount() {
		return this.beanDefinitionMap.size();
	}

	@Override
	public boolean isBeanNameIsUse(String beanName) {
		return this.containsBeanDefinition(beanName);
	}

	@Override
	public Iterator<BeanDefinition> iterator() {
		return this.beanDefinitionMap.values().iterator();
	}
}
