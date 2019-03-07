package com.unind.qms.web.quality.dao;

import com.unind.qms.web.quality.entity.QualityInspect;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author chen
 *
 */
public interface QualityInspectDao extends CrudRepository<QualityInspect, Long>, JpaSpecificationExecutor<QualityInspect> {

//	public int countByBsSuppCode(String bsSuppCode);
//
//	public List<SampleInfo> findByBsSuppCode(String bsSuppCode);

    @Modifying
    @Query("update QualityInspect t set t.bsSampleId=?1 where t.id=?2")
    public void updateBsSampleIdByBsId(Long bsSampleId, Long id);
}
