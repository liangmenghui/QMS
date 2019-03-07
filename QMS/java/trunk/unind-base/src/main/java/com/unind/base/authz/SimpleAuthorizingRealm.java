package com.unind.base.authz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.Encodes;

import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.user.SysUser;

public class SimpleAuthorizingRealm extends AuthorizingRealm {
	/**
     * The default query used to retrieve account data for the user.
     */
    protected static final String DEFAULT_AUTHENTICATION_QUERY = "select id, pk_group, pk_org, bs_code, bs_email, bs_mobile, bs_name, bs_password, bs_salt, bs_status from sys_user where bs_code = ? and bs_is_del = 0";
    
    /**
     * The default query used to retrieve account data for the user when {@link #saltStyle} is COLUMN.
     */
    protected static final String DEFAULT_SALTED_AUTHENTICATION_QUERY = "select bs_password, bs_salt from sys_user where bs_code = ? and bs_is_del = 0";

    /**
     * The default query used to retrieve the roles that apply to a user.
     */
    protected static final String DEFAULT_USER_ROLES_QUERY = "select r.bs_code from sys_user_roles_agg ur left join sys_role r on r.id=ur.pk_role left join sys_user u on u.id=ur.pk_user where u.bs_code = ?";

    /**
     * The default query used to retrieve permissions that apply to a particular role.
     */
    protected static final String DEFAULT_PERMISSIONS_QUERY = "select c.bs_code as resrce_code, c.bs_name as resrce_name, p.bs_code as perm_code, p.bs_name as perm_name from sys_role_perms_agg rp left join sys_role r on r.id=rp.pk_role left join sys_perm p on p.id=rp.pk_perm left join sys_resrce c on c.id=rp.pk_resrce where r.bs_code = ? and c.bs_leaf = ?";

    private static final Logger log = LoggerFactory.getLogger(JdbcRealm.class);
    
    /**
     * Password hash salt configuration. <ul>
     *   <li>NO_SALT - password hashes are not salted.</li>
     *   <li>CRYPT - password hashes are stored in unix crypt format.</li>
     *   <li>COLUMN - salt is in a separate column in the database.</li> 
     *   <li>EXTERNAL - salt is not stored in the database. {@link #getSaltForUser(String)} will be called
     *       to get the salt</li></ul>
     */
    public enum SaltStyle {NO_SALT, CRYPT, COLUMN, EXTERNAL};

    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/
    protected DataSource dataSource;

    protected String authenticationQuery = DEFAULT_AUTHENTICATION_QUERY;

    protected String userRolesQuery = DEFAULT_USER_ROLES_QUERY;

    protected String permissionsQuery = DEFAULT_PERMISSIONS_QUERY;

    protected boolean permissionsLookupEnabled = false;
    
    protected SaltStyle saltStyle = SaltStyle.NO_SALT;

    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/

    /*--------------------------------------------
    |  A C C E S S O R S / M O D I F I E R S    |
    ============================================*/
    
    /**
     * Sets the datasource that should be used to retrieve connections used by this realm.
     *
     * @param dataSource the SQL data source.
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Overrides the default query used to retrieve a user's password during authentication.  When using the default
     * implementation, this query must take the user's username as a single parameter and return a single result
     * with the user's password as the first column.  If you require a solution that does not match this query
     * structure, you can override {@link #doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)} or
     * just {@link #getPasswordForUser(java.sql.Connection,String)}
     *
     * @param authenticationQuery the query to use for authentication.
     * @see #DEFAULT_AUTHENTICATION_QUERY
     */
    public void setAuthenticationQuery(String authenticationQuery) {
        this.authenticationQuery = authenticationQuery;
    }

    /**
     * Overrides the default query used to retrieve a user's roles during authorization.  When using the default
     * implementation, this query must take the user's username as a single parameter and return a row
     * per role with a single column containing the role name.  If you require a solution that does not match this query
     * structure, you can override {@link #doGetAuthorizationInfo(PrincipalCollection)} or just
     * {@link #getRoleNamesForUser(java.sql.Connection,String)}
     *
     * @param userRolesQuery the query to use for retrieving a user's roles.
     * @see #DEFAULT_USER_ROLES_QUERY
     */
    public void setUserRolesQuery(String userRolesQuery) {
        this.userRolesQuery = userRolesQuery;
    }

    /**
     * Overrides the default query used to retrieve a user's permissions during authorization.  When using the default
     * implementation, this query must take a role name as the single parameter and return a row
     * per permission with three columns containing the fully qualified name of the permission class, the permission
     * name, and the permission actions (in that order).  If you require a solution that does not match this query
     * structure, you can override {@link #doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)} or just
     * {@link #getPermissions(java.sql.Connection,String,java.util.Collection)}</p>
     * <p/>
     * <b>Permissions are only retrieved if you set {@link #permissionsLookupEnabled} to true.  Otherwise,
     * this query is ignored.</b>
     *
     * @param permissionsQuery the query to use for retrieving permissions for a role.
     * @see #DEFAULT_PERMISSIONS_QUERY
     * @see #setPermissionsLookupEnabled(boolean)
     */
    public void setPermissionsQuery(String permissionsQuery) {
        this.permissionsQuery = permissionsQuery;
    }

    /**
     * Enables lookup of permissions during authorization.  The default is "false" - meaning that only roles
     * are associated with a user.  Set this to true in order to lookup roles <b>and</b> permissions.
     *
     * @param permissionsLookupEnabled true if permissions should be looked up during authorization, or false if only
     *                                 roles should be looked up.
     */
    public void setPermissionsLookupEnabled(boolean permissionsLookupEnabled) {
        this.permissionsLookupEnabled = permissionsLookupEnabled;
    }
    
    /**
     * Sets the salt style.  See {@link #saltStyle}.
     * 
     * @param saltStyle new SaltStyle to set.
     */
    public void setSaltStyle(SaltStyle saltStyle) {
        this.saltStyle = saltStyle;
        authenticationQuery = DEFAULT_SALTED_AUTHENTICATION_QUERY;
    }

    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        Connection conn = null;
        SimpleAuthenticationInfo info = null;
        try {
            conn = dataSource.getConnection();

            String password = null;
            String salt = null;
            int status = BaseEnumConstants.USER_DISABLED;
            SysUser queryResults = getPasswordForUser(conn, username);
            password = queryResults.getBsPassword();
            salt = (StringUtils.isEmpty(queryResults.getBsSalt()) || StringUtils.isEmpty(queryResults.getBsSalt().trim()))?null:queryResults.getBsSalt();
            status = queryResults.getBsStatus();

            if (queryResults.getBsCode() == null) {
                throw new UnknownAccountException("No account found for user [" + username + "]");
            }
            if(status==BaseEnumConstants.USER_DISABLED) {
            	throw new UnknownAccountException("account has been disabled for user [" + username + "]");
            }
            if(status==BaseEnumConstants.USER_LOCKED) {
            	throw new LockedAccountException("account has been locked for user [" + username + "]");
            }

            info = new SimpleAuthenticationInfo(queryResults, password.toCharArray(), getName());
            
            if (salt != null) {
    			byte[] salts = Encodes.decodeHex(salt);
                info.setCredentialsSalt(ByteSource.Util.bytes(salts));
            }

        } catch (SQLException e) {
            final String message = "There was a SQL error while authenticating user [" + username + "]";
            if (log.isErrorEnabled()) {
                log.error(message, e);
            }

            // Rethrow any SQL errors as an authentication exception
            throw new AuthenticationException(message, e);
        } finally {
            JdbcUtils.closeConnection(conn);
        }

        return info;
    }

    @SuppressWarnings("resource")
	private SysUser getPasswordForUser(Connection conn, String username) throws SQLException {
    	SysUser result = new SysUser();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(authenticationQuery);
            ps.setString(1, username);

            // Execute query
            rs = ps.executeQuery();

            // Loop over results - although we are only expecting one result, since usernames should be unique
            boolean foundResult = false;
            while (rs.next()) {
                // Check to ensure only one row is processed
                if (foundResult) {
                    throw new AuthenticationException("More than one user row found for user [" + username + "]. Usernames must be unique.");
                }

                result.setId(rs.getLong(1));
                result.setPkGroup(rs.getLong(2));
                result.setPkOrg(rs.getLong(3));
                result.setBsCode(rs.getString(4));
                result.setBsEmail(rs.getString(5));
                result.setBsMobile(rs.getString(6));
                result.setBsName(rs.getString(7));
            	result.setBsPassword(rs.getString(8));
            	result.setBsSalt(rs.getString(9));
                result.setBsStatus(rs.getInt(10));

                foundResult = true;
            }
        } finally {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(ps);
        }

        return result;
    }

    /**
     * This implementation of the interface expects the principals collection to return a String username keyed off of
     * this realm's {@link #getName() name}
     *
     * @see #getAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        SysUser sysUser = (SysUser) getAvailablePrincipal(principals);
        String username = sysUser.getBsCode();

        Connection conn = null;
        Set<String> roleNames = null;
        Set<String> permissions = null;
        try {
            conn = dataSource.getConnection();

            // Retrieve roles and permissions from database
            roleNames = getRoleNamesForUser(conn, username);
//            if (permissionsLookupEnabled) {
                permissions = getPermissions(conn, username, roleNames);
//            }

        } catch (SQLException e) {
            final String message = "There was a SQL error while authorizing user [" + username + "]";
            if (log.isErrorEnabled()) {
                log.error(message, e);
            }

            // Rethrow any SQL errors as an authorization exception
            throw new AuthorizationException(message, e);
        } finally {
            JdbcUtils.closeConnection(conn);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissions);
        return info;

    }

    protected Set<String> getRoleNamesForUser(Connection conn, String username) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<String> roleNames = new LinkedHashSet<String>();
        try {
            ps = conn.prepareStatement(userRolesQuery);
            ps.setString(1, username);

            // Execute query
            rs = ps.executeQuery();

            // Loop over results and add each returned role to a set
            while (rs.next()) {

                String roleName = rs.getString(1);

                // Add the role to the list of names if it isn't null
                if (roleName != null) {
                    roleNames.add(roleName);
                } else {
                    if (log.isWarnEnabled()) {
                        log.warn("Null role name found while retrieving role names for user [" + username + "]");
                    }
                }
            }
        } finally {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(ps);
        }
        return roleNames;
    }

    protected Set<String> getPermissions(Connection conn, String username, Collection<String> roleNames) throws SQLException {
        PreparedStatement ps = null;
        Set<String> permissions = new LinkedHashSet<String>();
        try {
            ps = conn.prepareStatement(permissionsQuery);
            for (String roleName : roleNames) {
                ps.setString(1, roleName);
                ps.setInt(2, BaseEnumConstants.TRUE);
                ResultSet rs = null;
                try {
                	Map<String, String> unique = new HashMap<String, String>();
                	String permStr;
                    // Execute query
                    rs = ps.executeQuery();
                    // Loop over results and add each returned role to a set
                    while (rs.next()) {
                    	String resrceCode = rs.getString("resrce_code");
                    	String permCode = rs.getString("perm_code");
                    	if(unique.containsKey(resrceCode)) {
                    		if(StringUtils.isEmpty(permCode)) {
                    			continue;
                    		}
                    		permStr = unique.get(resrceCode);
                    		permStr += ("," + permCode);
                    		unique.put(resrceCode, permStr);
                    		continue;
                    	}
                    	permStr = "is_allowed_visit";
                		if(StringUtils.isEmpty(permCode)) {
                			continue;
                		}
                    	permStr += ("," + permCode);
                		unique.put(resrceCode, permStr);
                    }
                    Iterator<Entry<String, String>> it = unique.entrySet().iterator();
                    Entry<String, String> entry;
                    while(it.hasNext()) {
                    	entry = it.next();
                    	String permissionString = entry.getKey() + ":" + entry.getValue();
                    	// Add the permission to the set of permissions
                    	permissions.add(permissionString);
                    }
                } finally {
                    JdbcUtils.closeResultSet(rs);
                }

            }
        } finally {
            JdbcUtils.closeStatement(ps);
        }

        return permissions;
    }
    
    protected String getSaltForUser(String username) {
        return username;
    }
}
