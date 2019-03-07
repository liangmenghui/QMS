package com.unind.base.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.unind.base.domain.admin.perm.SysPerm;

/**
 * 组织
 * @author tanxiang
 *
 */
public interface SysPermDao extends CrudRepository<SysPerm, Long>, JpaSpecificationExecutor<SysPerm> {

	public int countByBsCode(String bsCode);

	public List<SysPerm> findByBsCode(String bsCode);
}
