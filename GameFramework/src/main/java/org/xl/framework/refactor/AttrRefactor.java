package org.xl.framework.refactor;

import org.apache.commons.lang3.ArrayUtils;
import org.xl.framework.build.factory.AttributeDefinitionRegistry;
import org.xl.framework.build.factory.AttributeDefinitionValueMap;
import org.xl.framework.context.ApplicationContext.GetRegistry;
import org.xl.framework.context.support.Log;

import java.lang.reflect.Field;
import java.util.Map;

public class AttrRefactor {
	private static final Log LOG = Log.getLog(AttrRefactor.class);

	public static <T> T refactor(Object o, String id, Class<T> type) {
		Class<?> aClass = o.getClass();
		Map<String, Object> registry = GetRegistry.registry();
		AttributeDefinitionRegistry attr = (AttributeDefinitionRegistry) registry.get(GetRegistry.ATTR);
		AttributeDefinitionValueMap value = attr.getValueMap(id);
		Field[] fields = ArrayUtils.addAll(aClass.getDeclaredFields(), aClass.getSuperclass().getDeclaredFields());
		T t = type.cast(o);
		for(Field field : fields) {
			try {
				aClass.getMethod(startUpperCase(field.getName()),field.getType())
						.invoke(t, field.getType().cast(value.get(field.getName())));
			} catch(Exception e) {
//						System.out.println("Exception");
				LOG.error("属性映射失败");
			}
		}
		return t;
	}

	private static String startUpperCase(String string) {
		return "set" + string.substring(0, 1).toUpperCase() + string.substring(1);
	}
}
