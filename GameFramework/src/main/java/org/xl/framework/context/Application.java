package org.xl.framework.context;

import org.xl.framework.build.factory.BeanDefinitionRegistry;
import org.xl.framework.build.factory.SimpleBeanDefinitionRegistry;
import org.xl.framework.build.factory.support.AnnotationBeanDefinitionReader;
import org.xl.framework.build.factory.support.AnnotationDefinitionReader;
import org.xl.framework.build.factory.support.XmlAttributeDefinitionReader;
import org.xl.framework.context.support.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/**
 * 框架的初始化，启动类
 */
public class Application {

	public static void run(Class<?> aClass) {
		TextGameFramework annotation = aClass.getAnnotation(TextGameFramework.class);
		if(annotation != null) {
//			Method[] methods = aClass.getDeclaredMethods();
			try {
//				Object o = aClass.getConstructor().newInstance();
//				for(Method method : methods) {
//					if(!method.getName().equals("main") && method.getReturnType().equals(void.class) &&
//							method.getParameterCount() == 0 &&
//							method.getModifiers() == Modifier.PUBLIC) {
//						method.invoke(o);
//					}
//				}
				run(aClass, annotation.value());
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new TextGameFrameworkException("启动类未添加正确的注解");
		}
	}

	private static void run(Class<?> aClass, String applicationConfig) {
		Map<String, String> config = Configuration.parser(applicationConfig).getConfig(aClass);
		BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		AnnotationDefinitionReader<BeanDefinitionRegistry> reader = new AnnotationBeanDefinitionReader(registry);
		reader.loadDefinitions(config.get("scan"));
		List<Object> objects = new PropertyAuto(new ApplicationContext(config.get("bean"), registry), config.get("scan")).autoInjection();
		run(objects);
	}

	private static void run(List<Object> objects) {
		if(objects != null && objects.size() != 0) {
			objects.forEach(o -> {
				Class<?> aClass = o.getClass();
				Method[] methods = aClass.getDeclaredMethods();
				for(Method method : methods) {
					if(method.isAnnotationPresent(MainMethod.class) && method.getParameterCount() == 0) {
						try {
							method.invoke(o);
						} catch(InvocationTargetException | IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
	}
}
