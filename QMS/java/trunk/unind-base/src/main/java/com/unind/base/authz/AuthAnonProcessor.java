package com.unind.base.authz;

import java.lang.reflect.Method;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unind.base.annotation.SimpleAuth;
import com.unind.base.provider.context.SessionContextUtils;

public class AuthAnonProcessor {
	private static Logger logger = LoggerFactory.getLogger(AuthAnonProcessor.class);

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (!bean.getClass().isAnnotationPresent(Controller.class)) {
			return bean;
		}
		String urlMapping = null;
		if (bean.getClass().isAnnotationPresent(RequestMapping.class)) {
			RequestMapping classMapping = bean.getClass().getAnnotation(RequestMapping.class);
			urlMapping = classMapping.value()[0];
		}
		Method[] methods = bean.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(SimpleAuth.class)) {
				SimpleAuth simpleAuth = method.getAnnotation(SimpleAuth.class);
				boolean isPermitted = SecurityUtils.getSubject().isPermitted(simpleAuth.authCode());
				if(isPermitted) {
					logger.debug("用户{}访问{}执行{}操作时因权限不足导致失败。{}", SessionContextUtils.getUsername(), simpleAuth.resrceName(), simpleAuth.permName(), urlMapping);
				}
			}
		}
		return bean;
	}
}