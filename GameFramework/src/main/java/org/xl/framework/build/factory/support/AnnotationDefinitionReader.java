package org.xl.framework.build.factory.support;

import org.xl.framework.build.factory.BeanDefinitionException;

public interface AnnotationDefinitionReader<T> {
	T getRegistry();
	void loadDefinitions(String pack) throws BeanDefinitionException;
}
