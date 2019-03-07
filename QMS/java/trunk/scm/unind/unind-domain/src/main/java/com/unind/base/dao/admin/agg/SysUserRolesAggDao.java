package com.unind.base.dao.admin.agg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unind.base.domain.admin.agg.SysUserRolesAgg;

public interface SysUserRolesAggDao extends JpaRepository<SysUserRolesAgg, Long>, JpaSpecificationExecutor<SysUserRolesAgg> {

	@Modifying
	@Query("delete SysUserRolesAgg ur where ur.pkUser=:pkUser")
	public void deleteByPkUser(@Param("pkUser") Long pkUser);
}
