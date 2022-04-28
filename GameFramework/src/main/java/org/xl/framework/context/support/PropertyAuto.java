package org.xl.framework.context.support;

import org.xl.framework.build.factory.BeanFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.stream.Collectors;

public class PropertyAuto implements Auto {
	private static final Log LOG = Log.getLog(PropertyAuto.class);
	private final Map<String, Class<?>> classMap;
	private final BeanFactory beanFactory;

	public PropertyAuto(BeanFactory beanFactory, String pack) {
		this.beanFactory = beanFactory;
		this.classMap = ScanClass.scan(pack).getScanClassMap();
	}

	@Override
	public List<Object> autoInjection() {
		List<Object> objectList = new ArrayList<>();

		Object classObj = null;
		for(Class<?> value : classMap.values()) {
			if(value.isAnnotationPresent(GameStartClass.class)) {
				try {
					classObj = value.getConstructor().newInstance();
				} catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					e.printStackTrace();
				}
				for(Field field : value.getDeclaredFields()) {
					if(field.isAnnotationPresent(InstanceInjection.class)) {
//							System.out.println(field.getType());
						Object bean = beanFactory.getBean(field.getType());
						field.setAccessible(true);
						try {
							field.set(classObj, bean);
						} catch(IllegalAccessException e) {
							LOG.log("属性注入失败");
							System.exit(0);
						}
					}
				}

				List<Object> temp = new ArrayList<>();
				if(objectList.isEmpty()) {
					objectList.add(classObj);
				} else {
					for(Object o : objectList) {
						if(classObj != null && !o.getClass().equals(classObj.getClass())) {
							temp.add(classObj);
						}
					}
				}
				objectList.addAll(temp);
				temp.clear();
				temp = null;
			}
		}
		return objectList;
	}
}
