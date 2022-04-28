package org.xl.framework.build.factory;

import java.util.List;

public interface BeanFactory {
	List<Object> getBean(String name) throws BeansException;

	<T> List<T> getBean(String name, Class<T> requiredType) throws BeansException;

	Object getBean(String name, Object... args) throws BeansException;

	<T> T getBean(Class<T> requiredType) throws BeansException;

	<T> List<T> getBean(Class<T> requiredType, Object... args) throws BeansException;
}