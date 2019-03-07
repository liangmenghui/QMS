package com.unind.qms.web.approved.dao;

import com.unind.qms.web.approved.entity.ApprovedFlow;
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

    @Query(value = "select count(1) from "+ApprovedFlowRecord.TABLE_NAME+" a LEFT JOIN "+ ApprovedFlow.TABLE_NAME+" b on a.bs_flow_id = b.id where a.bs_supp_id = ?1 and b.bs_type = ?2 and a.bs_status <> '2'",nativeQuery = true)
    public int countByBsSuppIdAndBsType(Long bsSuppId, int bsType);

    @Query(value = "select count(1) from "+ApprovedFlowRecord.TABLE_NAME+" a LEFT JOIN "+ApprovedFlow.TABLE_NAME+" b on a.bs_flow_id = b.id where a.bs_pr_id = ?1 and b.bs_type = ?2 and a.bs_status <> '2'",nativeQuery = true)
    public int countByBsPrIdAndBsType(Long bsSuppId, int bsType);

    @Query(value = "select a.* from " + ApprovedFlowRecord.TABLE_NAME + " a left join " + ApprovedFlow.TABLE_NAME +
            " b on a.bs_flow_id = b.id where a.bs_supp_id = ?1 and b.bs_type = ?2 and a.bs_status = 2 order by a.id desc", nativeQuery = true)
    public List<ApprovedFlowRecord> findByBsSuppIdAndBsType(Long bsSuppId, Integer bsType);
}