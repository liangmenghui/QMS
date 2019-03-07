package com.unind.qms.web.approved.dao;

import com.unind.qms.web.approved.entity.ApprovedFlowRecord;
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
public interface ApprovedFlowRecordDao extends CrudRepository<ApprovedFlowRecord, Long>, JpaSpecificationExecutor<ApprovedFlowRecord> {

//	public int countByBsName(String bsName);
//
//	public List<ApprovedFlow> findByBsName(String bsName);
    @Modifying
    @Query("select t from ApprovedFlowRecord t where t.bsSuppId = ?1 and t.bsStatus <> '2'")
    public List<ApprovedFlowRecord> findByBsSuppIdAndAndBsStatus(Long bsSuppId);

    @Modifying
    @Query("select t from ApprovedFlowRecord t where t.bsPrId = ?1 and t.bsStatus <> '2'")
    public List<ApprovedFlowRecord> findByBsPrIdAndAndBsStatus(Long bsPrId);
}
