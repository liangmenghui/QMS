package com.unind.base.provider.admin.user;

import java.nio.charset.Charset;

import org.springframework.data.domain.PageRequest;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.BusinessException;

/**
 * 平台用户管理
 * @author Administrator
 *
 */
public interface SysUserService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(SysUser user) throws BusinessException;

	public ApiResponseResult edit(SysUser user) throws BusinessException;

	public ApiResponseResult editpassword(SysUser user) throws BusinessException;

	public ApiResponseResult delete(SysUser user) throws BusinessException;

	public ApiResponseResult getlist(SysUser user, PageRequest pageRequest) throws BusinessException;
}
