package org.xl.framework.context;

import org.apache.commons.lang3.ArrayUtils;
import org.xl.framework.build.factory.*;
import org.xl.framework.build.factory.support.*;
import org.xl.framework.context.support.Log;
import org.xl.framework.context.support.WrapperClassByType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ApplicationContext implements BeanFactory {
	private static final Log LOG = Log.getLog(ApplicationContext.class);
	private final Map<Class<?>, Object> OBJECT_POOL = new HashMap<>();
	private static final Map<String, Object> REGISTRY = new HashMap<>(3);
	private final BeanDefinitionRegistry BEAN_REGISTRY;
	private final TextDefinitionRegistry TEXT_REGISTRY;
	private final AttributeDefinitionRegistry ATTR_REGISTRY;

	public ApplicationContext(String application, String textApplication, String attrApplication, BeanDefinitionRegistry definitions) {
		XmlDefinitionReader reader = null;

		reader = new XmlBeanDefinitionReader(definitions);
		reader.loadDefinitions(application);
		BEAN_REGISTRY = (BeanDefinitionRegistry) reader.getRegistry();

		reader = new XmlTextDefinitionReader();
		reader.loadDefinitions(textApplication);
		TEXT_REGISTRY = (TextDefinitionRegistry) reader.getRegistry();

		reader = new XmlAttributeDefinitionReader();
		reader.loadDefinitions(attrApplication);
		ATTR_REGISTRY = (AttributeDefinitionRegistry) reader.getRegistry();

		REGISTRY.put("bean", BEAN_REGISTRY);
		REGISTRY.put("text", TEXT_REGISTRY);
		REGISTRY.put("attr", ATTR_REGISTRY);
	}

	public ApplicationContext(String application, String textApplication, BeanDefinitionRegistry definitions) {
		this(application, textApplication, application, definitions);
	}

	public ApplicationContext(String application, BeanDefinitionRegistry definitions) {
		this(application, application, definitions);
	}

	public static class GetRegistry {
		public static final String ATTR = "attr";
		public static final String TEXT = "text";
		public static final String BEAN = "bean";

		public static Map<String, Object> registry() {
			return REGISTRY;
		}
	}

	private static String fieldNameToSettingMethod(String fieldName) {
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	@Override
	public List<Object> getBean(String name) throws BeansException {
		BeanDefinition beanDefinition = BEAN_REGISTRY.getBeanDefinition(name);
		String clazz = beanDefinition.getClassName();
		PropertyDefinitionValue[] propertyDefinitionValues = beanDefinition.toArray();
		try {
			Class<?> aClass = Class.forName(clazz);
			List<Object> list = new ArrayList<>();
			for(PropertyDefinitionValue value : propertyDefinitionValues) {
				setValueAndPropertyToObject(aClass, value, list, (Object[]) null);
			}
			return list;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> List<T> getBean(String name, Class<T> requiredType) throws BeansException {
		BeanDefinition beanDefinition = BEAN_REGISTRY.getBeanDefinition(name);
		String clazz = beanDefinition.getClassName();
		PropertyDefinitionValue[] propertyDefinitionValues = beanDefinition.toArray();
		try {
			Class<?> aClass = Class.forName(clazz);
			List<Object> list = new ArrayList<>();
			List<T> tList = new ArrayList<>();
			for(PropertyDefinitionValue value : propertyDefinitionValues) {
				setValueAndPropertyToObject(aClass, value, list, (Object[]) null);
			}
			list.forEach(o -> tList.add(requiredType.cast(o)));
			return tList;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getBean(String name, Object... args) throws BeansException {
		BeanDefinition beanDefinition = BEAN_REGISTRY.getBeanDefinition(name);
		String clazz = beanDefinition.getClassName();
		PropertyDefinitionValue[] propertyDefinitionValues = beanDefinition.toArray();
		try {
			Class<?> aClass = Class.forName(clazz);
			List<Object> list = new ArrayList<>();
			for(PropertyDefinitionValue value : propertyDefinitionValues) {
				setValueAndPropertyToObject(aClass, value, list, args);
			}
			return list;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> T getBean(Class<T> requiredType) throws BeansException {

		Object o = OBJECT_POOL.get(requiredType);
		if(o != null) {
			return requiredType.cast(o);
		}

		Iterator<BeanDefinition> iterator = BEAN_REGISTRY.iterator();
		T cast = null;
		List<Object> list = new ArrayList<>();
		while(iterator.hasNext()) {
			BeanDefinition next = iterator.next();
			Class<?> aClass = null;
			try {
				aClass = Class.forName(next.getClassName());
			} catch(ClassNotFoundException e) {
//				throw new BeansException("未找到Bean对应的class");
				LOG.error("未找到Bean对应的class");
			}
			if(requiredType == aClass) {
				PropertyDefinitionValue[] propertyDefinitionValues = next.toArray();
				for(PropertyDefinitionValue value : propertyDefinitionValues) {
					// 以下在对 cast 进行初始化
					setValueAndPropertyToObject(aClass, value, list, (Object[]) null);
				}
			}
		}
		if(!list.isEmpty()) {
			cast = requiredType.cast(list.get(0));
			OBJECT_POOL.put(requiredType, cast);
		}
		return cast;
	}

	@Override
	public <T> List<T> getBean(Class<T> requiredType, Object... args) throws BeansException {
		return null;
	}

	private void setValueAndPropertyToObject(Class<?> aClass, PropertyDefinitionValue value, List<Object> list, Object... args) {
		String attr = value.getAttrRef();
		String text = value.getTextRef();

		Object o = null;
		try {
			o = aClass.getConstructor().newInstance(args);
		} catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		AttributeDefinitionValueMap valueMap = ATTR_REGISTRY.getValueMap(attr);
		TextDefinitionValue textValue = TEXT_REGISTRY.getValue(text);
		if(valueMap == null) {
			LOG.error((o == null ? "对象为null，且" : o.getClass().getName() + "-") + "没有属性集");
			list.add(o);
			return;
		}

		Field[] fields = ArrayUtils.addAll(aClass.getDeclaredFields(), aClass.getSuperclass().getDeclaredFields());
		for(Field field : fields) {
			Class<?> type = field.getType();
			try{
				aClass.getMethod(fieldNameToSettingMethod(field.getName()), type)
						.invoke(o, WrapperClassByType.findWrapperClassByType((String) valueMap.get(field.getName()), type));
			} catch(Exception e) {
//				System.out.println("Exception");
				LOG.error("属性映射失败");
			}
		}
		list.add(o);
	}
}
