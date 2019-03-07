package com.unind.base.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.unind.base.domain.admin.org.SysOrg;

/**
 * 组织
 * @author tanxiang
 *
 */
public interface SysOrgDao extends CrudRepository<SysOrg, Long>, JpaSpecificationExecutor<SysOrg> {

	public int countByBsCode(String bsCode);

	public List<SysOrg> findByBsCode(String bsCode);
}
