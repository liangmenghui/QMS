package com.unind.base.configure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.JdbcUtils;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.unind.base.authz.ComplicatePermissionsAuthorizationFilter;
import com.unind.base.authz.ComplicateRolesAuthorizationFilter;
import com.unind.base.authz.SimpleAuthorizationFilter;
import com.unind.base.authz.SimpleAuthorizingRealm;
import com.unind.base.web.Constants;

@Configuration
public class ShiroConfiguration {
	protected final static Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
	@Autowired
	private DataSource dataSource;
//	@Autowired
//	private AppConfig appConfig;
    @Autowired
    private ApplicationContext appContext;

	@Bean
	public EhCacheManager ehCacheManager() {
		EhCacheManager ehcacheManager = new EhCacheManager();
		ehcacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
		return ehcacheManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setLoginUrl(Constants.CONTEXT_PATH + "/login");
		bean.setSuccessUrl(Constants.CONTEXT_PATH + "/index");
		bean.setUnauthorizedUrl(Constants.CONTEXT_PATH + "/error");
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		//读取配置文件
		this.loadExtraAuthProps(filterChainDefinitionMap);
		//读取数据库
		this.loadResrce(filterChainDefinitionMap);
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		filterChainDefinitionMap.put("/**", "auth");
		bean.setSecurityManager(securityManager);
		Map<String, Filter> filters = bean.getFilters();
		filters.put("perm", new ComplicatePermissionsAuthorizationFilter());
		filters.put("role", new ComplicateRolesAuthorizationFilter());
		filters.put("auth", new SimpleAuthorizationFilter());
		return bean;
	}

	private void loadExtraAuthProps(Map<String, String> filterChainDefinitionMap) {
		Resource resrce = appContext.getResource("classpath:/auth.properties");
		if (resrce.exists()) {
            try {
            	loadProps(resrce, filterChainDefinitionMap);
            } catch (Exception e) {
                throw new RuntimeException("读取auth.properties出错!", e);
            }
        }
	}

	private void loadResrce(Map<String, String> filterChainDefinitionMap) {
//		select r.bs_code as role_code, r.bs_name as role_name, c.bs_code as resrce_code, c.bs_name as resrce_name, p.bs_code as perm_code, p.bs_name as perm_name from sys_role_perms_agg rp left join sys_role r on r.id=rp.pk_role left join sys_perm p on p.id=rp.pk_perm left join sys_resrce c on c.id=rp.pk_resrce where pk_role = 1 and c.bs_leaf = 1;
        Connection conn = null;
		try {
			String sql = "select r.bs_code as resrce_code, p.bs_code as perm_code, p.bs_name as perm_name, r.bs_url from sys_resrce_perm_agg rp left join sys_resrce r on r.id=rp.pk_resrce left join sys_perm p on p.id=rp.pk_perm where r.bs_leaf=? and r.bs_is_del=? order by r.pk_parent, r.bs_sort_no asc";
            conn = dataSource.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
            	Map<String, List<String>> resrcePerms = new LinkedHashMap<String, List<String>>();
            	List<String> perms;
                ps = conn.prepareStatement(sql);
                ps.setInt(1, 1);
                ps.setInt(2, 0);
                // Execute query
                rs = ps.executeQuery();
                // Loop over results and add each returned role to a set
                while (rs.next()) {
                    String resrceCode = rs.getString("resrce_code");
                    String permCode = rs.getString("perm_code");
                    String bsUrl = (null==rs.getString("bs_url")?null:StringUtils.trim(rs.getString("bs_url")));
                    if(StringUtils.isEmpty(resrceCode) || StringUtils.isEmpty(permCode) || StringUtils.isEmpty(bsUrl)) {
                    	continue;
                    }
                    if(resrcePerms.containsKey(bsUrl)) {
                    	resrcePerms.get(bsUrl).add(permCode);
                    	continue;
                    }
                    perms = new ArrayList<String>();
                    perms.add(resrceCode);
                    perms.add(permCode);
                    resrcePerms.put(bsUrl, perms);
                }
                
            	Iterator<Entry<String, List<String>>> it = resrcePerms.entrySet().iterator();
            	Entry<String, List<String>> entry;
            	while (it.hasNext()) {
            		entry = it.next();
            		StringBuilder permStr = new StringBuilder();
            		for (int i = 0; i < entry.getValue().size(); i++) {
            			if(i==0) {
            				permStr.append("perm[" + entry.getValue().get(i) + ":is_allowed_visit");
            			}else if(i == entry.getValue().size()-1){
            				permStr.append("," + entry.getValue().get(i) + "]");
            			}else {
            				permStr.append("," + entry.getValue().get(i));
            			}
					}
            		filterChainDefinitionMap.put(entry.getKey(), permStr.toString());
            	}
            } finally {
                JdbcUtils.closeResultSet(rs);
                JdbcUtils.closeStatement(ps);
            }

        } catch (SQLException e) {
            final String message = "There was a SQL error while authorization resrce";
            // Rethrow any SQL errors as an authorization exception
            throw new AuthorizationException(message, e);
        } finally {
            JdbcUtils.closeConnection(conn);
        }
	}

	private void loadProps(Resource resrce, Map<String, String> filterChainDefinitionMap) throws IOException {
		InputStreamReader in = new InputStreamReader(resrce.getInputStream());
		BufferedReader br = new BufferedReader(in);
		String line;
		String[] params;
		while(null!=(line = br.readLine())) {
			params = line.trim().split("=");
			if(params.length != 2) {
				logger.warn("auth.properties argument {} is invalid", line);
				continue;
			}
			if (null == params[0] || "".equals(params[0].trim())
					|| null == params[1] || "".equals(params[1].trim())) {
				logger.warn("auth.properties argument {}={} is invalid", params[0], params[1]);
				continue;
			}
			logger.info("auth.properties argument {}={} has been loaded", params[0].trim(), params[1].trim());
			if(params[0].trim().startsWith("#")) {
				continue;
			}
			filterChainDefinitionMap.put(params[0].trim(), params[1].trim());
		}
	}

	@Bean
	public SecurityManager securityManager(SimpleAuthorizingRealm simpleAuthorizingRealm) {
		SecurityManager securityManager = new DefaultWebSecurityManager(simpleAuthorizingRealm);
		return securityManager;
	}

	@Bean
	public SimpleAuthorizingRealm simpleAuthorizingRealm(EhCacheManager ehCacheManager) {
		SimpleAuthorizingRealm realm = new SimpleAuthorizingRealm();
		realm.setCacheManager(ehCacheManager);
		realm.setAuthenticationCacheName("shiro");
		realm.setDataSource(dataSource);
		realm.setName("simpleDbRealm");

		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Constants.HASH_ALGORITHM);
		matcher.setHashIterations(Constants.HASH_INTERATIONS);

		realm.setCredentialsMatcher(matcher);
		return realm;
	}
}

