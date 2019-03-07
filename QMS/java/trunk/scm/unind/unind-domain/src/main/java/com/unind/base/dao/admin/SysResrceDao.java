package com.unind.base.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.unind.base.domain.admin.resrce.SysResrce;

/**
 * 资源
 * @author tanxiang
 *
 */
public interface SysResrceDao extends JpaRepository<SysResrce, Long>, JpaSpecificationExecutor<SysResrce> {

	public int countByBsCode(String bsCode);

	public List<SysResrce> findByBsCode(String bsCode);
}
