package org.xl.framework.build.factory;

import java.util.Iterator;

public interface BeanDefinitionRegistry extends Iterable<BeanDefinition> {
	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionException;

	void removeBeanDefinition(String beanName) throws BeanDefinitionException;

	BeanDefinition getBeanDefinition(String beanName) throws BeanDefinitionException;

	boolean containsBeanDefinition(String beanName);

	String[] getBeanDefinitionNames();

	int getBeanDefinitionCount();

	boolean isBeanNameIsUse(String beanName);

	@Override
	Iterator<BeanDefinition> iterator();

}
