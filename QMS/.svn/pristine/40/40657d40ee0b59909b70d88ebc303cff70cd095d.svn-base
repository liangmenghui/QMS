package com.unind.base.dao.admin;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.unind.base.domain.admin.role.SysRolegroup;

/**
 * 角色组
 * @author tanxiang
 *
 */
public interface SysRolegroupDao extends CrudRepository<SysRolegroup, Long>, JpaSpecificationExecutor<SysRolegroup> {

	public int countByBsCode(String bsCode);

	public SysRolegroup findByBsCode(String bsCode);
}
