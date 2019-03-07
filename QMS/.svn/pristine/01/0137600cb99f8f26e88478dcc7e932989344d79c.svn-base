package com.unind.base.authz;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
/**
 * 角色权限过滤
 * @author tanyjan
 *
 */
public class ComplicateRolesAuthorizationFilter extends RolesAuthorizationFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) (String[]) mappedValue;
		if ((rolesArray == null) || (rolesArray.length == 0)) {
			return true;
		}
		Set<String> roles = CollectionUtils.asSet(rolesArray);
//		return subject.hasAllRoles(roles);
		//修改为或关系
		Iterator<String> it = roles.iterator();
		while (it.hasNext()) {
			if(subject.hasRole(it.next())) {
				return true;
			}
		}
		return false;
	}

}
