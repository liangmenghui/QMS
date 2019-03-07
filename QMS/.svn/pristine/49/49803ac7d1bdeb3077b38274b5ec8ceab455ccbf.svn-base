package com.unind.base.authz;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class SimpleAuthorizationFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		if(SecurityUtils.getSubject().isAuthenticated()) {
			return true;
		}
		return false;
	}

}
