package com.unind.base.dao.admin.agg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unind.base.domain.admin.agg.SysRolePermsAgg;

public interface SysRolePermsAggDao extends JpaRepository<SysRolePermsAgg, Long>, JpaSpecificationExecutor<SysRolePermsAgg>{

	@Modifying
	@Query("delete SysRolePermsAgg t where t.pkRole=:pkRole")
	public void deleteByPkRole(@Param("pkRole") Long pkRole);
	
}
