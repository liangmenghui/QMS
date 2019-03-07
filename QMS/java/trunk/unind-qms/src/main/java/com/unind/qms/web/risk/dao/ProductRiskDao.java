package com.unind.qms.web.risk.dao;

import com.unind.qms.web.risk.entity.ProductRisk;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 
 * @author chen
 *
 */
public interface ProductRiskDao extends CrudRepository<ProductRisk, Long>, JpaSpecificationExecutor<ProductRisk> {

    public List<ProductRisk> findByBsIsDel(Integer bsIsDel);

    public List<ProductRisk> findByBsIsDelAndBsPrIdOrderByBsCreatedTimeDesc(Integer bsIsDel, Long bsPrId);

    public int countByBsPrId(Long bsPrId);

//	public int countByBsSuppCode(String bsSuppCode);
//
//	public List<SampleInfo> findByBsSuppCode(String bsSuppCode);

//    @Modifying
//    @Query("update ProductRisk t set t.bsSampleId=?1 where t.id=?2")
//    public void updateBsSampleIdByBsId(Long bsSampleId, Long id);
}