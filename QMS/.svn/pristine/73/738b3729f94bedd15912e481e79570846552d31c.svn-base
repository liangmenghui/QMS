package com.unind.qms.web.risk.dao;

import com.unind.qms.web.risk.entity.RiskApprovedRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 风险批准记录表
 * @author Shen
 */
public interface RiskApprovedRecordDao  extends CrudRepository<RiskApprovedRecord, Long>, JpaSpecificationExecutor<RiskApprovedRecord> {

    public List<RiskApprovedRecord> findByBsPrIdAndBsStatus(Long bsPrId, Integer bsStatus);

    public List<RiskApprovedRecord> findByBsSuppIdAndBsStatus(Long bsSuppId, Integer bsStatus);
}
