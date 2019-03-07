package com.unind.base.authz;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
/**
 * urls权限过滤
 * @author tanyjan
 *
 */
public class ComplicatePermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		Subject subject = getSubject(request, response);
		String[] perms = (String[]) (String[]) mappedValue;
		boolean isPermitted = false;
		if ((perms != null) && (perms.length > 0)) {
			/*if (perms.length == 1) {
				if (!subject.isPermitted(perms[0])) {
					isPermitted = false;
				}
			} else if (!subject.isPermittedAll(perms)) {
				isPermitted = false;
			}*/
			for (int i = 0; i < perms.length; i++) {
				if (subject.isPermitted(perms[0])) {
					isPermitted = true;
					break;
				}
			}
		}
		return isPermitted;
	}

}
