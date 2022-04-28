package org.xl.framework.context.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在main方法所在类上添加此注解
 * 注解默认起始配置文件为 application.xml
 */
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface TextGameFramework {
	String value() default "application.xml";
}
