package com.unind.base.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.unind.base.domain.admin.user.SysUser;

/**
 * 
 * @author tanxiang
 *
 */
public interface SysUserDao extends CrudRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {

	public int countByBsCode(String bsCode);

	public List<SysUser> findByBsCode(String bsCode);
}
