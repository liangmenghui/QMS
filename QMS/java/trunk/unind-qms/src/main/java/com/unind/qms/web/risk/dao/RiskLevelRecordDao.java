package com.unind.qms.web.risk.dao;

import com.unind.qms.web.risk.entity.RiskLevelRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Shen
 */
public interface RiskLevelRecordDao extends CrudRepository<RiskLevelRecord, Long>, JpaSpecificationExecutor<RiskLevelRecord> {

    public List<RiskLevelRecord> findByBsPrIdAndBsTypeAndBsResult(Long bsPrId, Integer bsType, Integer bsResult);

    public List<RiskLevelRecord> findByBsSuppIdAndBsTypeAndBsResult(Long bsSuppId, Integer bsType, Integer bsResult);
}
