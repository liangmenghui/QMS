package com.unind.base.provider.admin.user.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.unind.base.dao.admin.SysGroupDao;
import com.unind.base.dao.admin.SysOrgDao;
import com.unind.base.dao.admin.SysUserDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.data.DataHelper;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.group.SysGroup;
import com.unind.base.domain.admin.org.SysOrg;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.user.SysUserService;
import com.unind.base.provider.context.SessionContextUtils;

@Service
@Transactional(rollbackFor=BusinessException.class)
public class SysUserImpl extends BaseService implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysGroupDao sysGroupDao;
	@Autowired
	private SysOrgDao sysOrgDao;

	public ApiResponseResult add(SysUser user) throws BusinessException {
		if(StringUtils.isEmpty(user.getBsCode()) || StringUtils.isEmpty(user.getBsCode().trim())) {
			return ApiResponseResult.failure("帐号不能为空");
		}
		int counts = sysUserDao.countByBsCode(user.getBsCode());
		if(counts > 0) {
			return ApiResponseResult.failure("帐号已存在");
		}
		if(SessionContextUtils.isSupervisor()) {
			//仅运行超级管理员添加超级管理员用户
			List<SysGroup> rootGroups = sysGroupDao.findByBsCode("~");
			user.setPkGroup(null == rootGroups ? null : rootGroups.iterator().next().getId());
			List<SysOrg> sysOrgs = sysOrgDao.findByBsCode("~");
			user.setPkOrg(null == sysOrgs ? null : sysOrgs.iterator().next().getId());
		}else {
			user.setPkGroup(SessionContextUtils.getPkGroup());
			user.setPkOrg(SessionContextUtils.getPkOrg());
		}
		user.setBsCode(user.getBsCode().trim());
		user.setBsName(user.getBsName().trim());

		user.setBsPassword("123456");
		this.entryptPassword(user);
		user.setBsCreatedTime(new Date());
		sysUserDao.save(user);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult edit(SysUser user) throws BusinessException {
		if (null == user || null == user.getId()) {
			return ApiResponseResult.failure("记录ID为必填项");
		}
		SysUser o = sysUserDao.findOne(user.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		if(!StringUtils.equals(user.getBsCode(), o.getBsCode())) {
			int counts = sysUserDao.countByBsCode(user.getBsCode());
			if(counts > 0) {
				return ApiResponseResult.failure("帐号已存在");
			}
		}
		if(null==user.getPkGroup()) {
			List<SysGroup> rootGroups = sysGroupDao.findByBsCode("~");
			o.setPkGroup(null == rootGroups ? null : rootGroups.iterator().next().getId());
		}else {
			o.setPkGroup(user.getPkGroup());
		}
		if(null==user.getPkOrg()) {
			List<SysOrg> sysOrgs = sysOrgDao.findByBsCode("~");
			o.setPkOrg(null == sysOrgs ? null : sysOrgs.iterator().next().getId());
		}else {
			o.setPkOrg(user.getPkOrg());
		}
		o.setBsComment(user.getBsComment());
		o.setBsCode(user.getBsCode().trim());
		o.setBsName(user.getBsName().trim());
		o.setBsEmail(user.getBsEmail());
		o.setBsMobile(user.getBsMobile());
		o.setBsStatus(user.getBsStatus());
		o.setBsModifiedTime(new Date());
		o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
		sysUserDao.save(o);
		return ApiResponseResult.success();
	}

	public ApiResponseResult editpassword(SysUser user) throws BusinessException {
		if (null == user || null == user.getId()) {
			return ApiResponseResult.failure("记录ID为必填项");
		}
		SysUser o = sysUserDao.findOne(user.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		o.setBsPassword(user.getBsPassword());
		this.entryptPassword(o);
		o.setBsModifiedTime(new Date());
		sysUserDao.save(o);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult delete(SysUser user) throws BusinessException {
		SysUser o = sysUserDao.findOne(user.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		if(SessionContextUtils.isSupervisor(o.getBsCode())) {
			//不允许删除超级管理员
			return ApiResponseResult.failure("没有操作权限");
		}
		o.setBsIsDel(BaseEnumConstants.TRUE);
		sysUserDao.save(o);
		return ApiResponseResult.success();
	}

	@Transactional(readOnly = true)
	public ApiResponseResult getlist(SysUser user, PageRequest pageRequest) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		if(!SessionContextUtils.isSupervisor()) {
			if(SessionContextUtils.getCurrentUser().getPkGroup()!=-1) {
				filters.add(new SearchFilter("pkGroup", Operator.EQ, SessionContextUtils.getPkGroup()));
			}
		}

		if(StringUtils.isNotEmpty(user.getBsCode())) {
			filters.add(new SearchFilter("bsCode", Operator.EQ, user.getBsCode()));
		}
		if(StringUtils.isNotEmpty(user.getBsName())) {
			filters.add(new SearchFilter("bsName", Operator.LIKE, user.getBsName()));
		}
		if(StringUtils.isNotEmpty(user.getBsMobile())) {
			filters.add(new SearchFilter("bsMobile", Operator.EQ, user.getBsMobile()));
		}
		if(null!=user.getBsStatus()) {
			filters.add(new SearchFilter("bsStatus", Operator.EQ, user.getBsStatus()));
		}
		//用户列表不查询自己本人
//		filters.add(new SearchFilter("bsCode", Operator.EQ, SessionContextUtils.getUsername()));
		Specifications<SysUser> spec = Specifications.where(super.and(filters, SysUser.class));
		Page<SysUser> page = sysUserDao.findAll(spec, pageRequest);
		return ApiResponseResult.success().data(DataHelper.convertToDatagrid(page.getContent(), page.getTotalElements()));
	}

	public SysUser getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		SysUser user = (SysUser) subject.getPrincipal();
		if (user == null) {
			return null;
		}
		return user;
	}

	public boolean isCurrentUserPassword(String plainPassword) {
		SysUser user = this.getCurrentUser();
		if (user == null) {
			return false;
		}

		final String entryptPassword = entryptPassword(plainPassword, Encodes.decodeHex(user.getBsSalt()));
		return StringUtils.equals(user.getBsPassword(), entryptPassword);
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash.
	 */
	private void entryptPassword(SysUser user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setBsSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getBsPassword().getBytes(CHARSET), salt, HASH_INTERATIONS);
		user.setBsPassword(Encodes.encodeHex(hashPassword));
	}

	private String entryptPassword(String plainPassword, byte[] salt) {
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(CHARSET), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword);
	}

}
