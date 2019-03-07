package com.unind.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented  
@Inherited  
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})
public @interface SimpleAuth {

	/**
	 * 认证权限信息
	 * <p>eg. 资源编码:权限编码</p>
	 * @return
	 */
	String authCode() default "";
	/**
	 * 资源名称
	 * @return
	 */
	String resrceName() default "";
	/**
	 * 权限名称
	 * @return
	 */
	String permName() default "";
}
