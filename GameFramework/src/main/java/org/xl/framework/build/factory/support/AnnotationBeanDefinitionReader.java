package org.xl.framework.build.factory.support;

import org.xl.framework.build.factory.BeanDefinition;
import org.xl.framework.build.factory.BeanDefinitionException;
import org.xl.framework.build.factory.BeanDefinitionRegistry;
import org.xl.framework.build.factory.PropertyDefinitionValue;
import org.xl.framework.context.support.Bean;
import org.xl.framework.context.support.ScanClass;

import java.util.Map;

public class AnnotationBeanDefinitionReader implements AnnotationDefinitionReader<BeanDefinitionRegistry> {
	private final BeanDefinitionRegistry registry;

	public AnnotationBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}

	@Override
	public BeanDefinitionRegistry getRegistry() {
		return registry;
	}

	@Override
	public void loadDefinitions(String pack) throws BeanDefinitionException {
		Map<String, Class<?>> classMap = ScanClass.scan(pack).getScanClassMap();
		classMap.forEach((k, v) -> {
			if(v.isAnnotationPresent(Bean.class)) {
				BeanDefinition beanDefinition = new BeanDefinition();
				Bean bean = v.getAnnotation(Bean.class);
				beanDefinition.addProperty(new PropertyDefinitionValue(bean.attr(), bean.text()));
				beanDefinition.setBeanName(k);
				beanDefinition.setClassName(v.getName());
				registry.registerBeanDefinition(k, beanDefinition);
			}
		});
	}
}
