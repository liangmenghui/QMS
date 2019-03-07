package com.unind.base.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.unind.base.domain.admin.role.SysRole;

/**
 * 角色
 * @author tanxiang
 *
 */
public interface SysRoleDao extends CrudRepository<SysRole, Long>, JpaSpecificationExecutor<SysRole> {

	public int countByBsCode(String bsCode);

	public List<SysRole> findByBsCode(String bsCode);
}
