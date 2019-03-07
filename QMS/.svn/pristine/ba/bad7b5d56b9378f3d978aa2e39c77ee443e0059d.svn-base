package com.unind.base.dao.admin.agg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unind.base.domain.admin.agg.SysResrcePermAgg;

public interface SysResrcePermAggDao extends JpaRepository<SysResrcePermAgg, Long>, JpaSpecificationExecutor<SysResrcePermAgg> {

	@Modifying
	@Query("delete SysResrcePermAgg rp where rp.pkResrce=:pkResrce")
	public void deleteByPkResrce(@Param("pkResrce") Long PkResrce);
	
}
