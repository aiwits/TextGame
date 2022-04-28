package org.xl.framework.build.factory.support;

import org.xl.framework.build.factory.BeanDefinitionException;

import java.io.File;

public interface XmlDefinitionReader<T> {
	T getRegistry();
	void loadDefinitions(String resource) throws BeanDefinitionException;
	void loadDefinitions(File file) throws BeanDefinitionException;
}
