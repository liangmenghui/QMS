package com.unind.base.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.unind.base.domain.admin.group.SysGroup;

/**
 * 组织
 * @author tanxiang
 *
 */
public interface SysGroupDao extends CrudRepository<SysGroup, Long>, JpaSpecificationExecutor<SysGroup> {

	public int countByBsCode(String bsCode);

	public List<SysGroup> findByBsCode(String bsCode);
}
