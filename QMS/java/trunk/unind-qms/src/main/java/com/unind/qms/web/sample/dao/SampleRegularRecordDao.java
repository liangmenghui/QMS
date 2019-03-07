package com.unind.qms.web.sample.dao;

import com.unind.qms.web.sample.entity.SampleRegularRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 
 * @author chen
 *
 */
public interface SampleRegularRecordDao extends CrudRepository<SampleRegularRecord, Long>, JpaSpecificationExecutor<SampleRegularRecord> {

//	public int countByBsSuppCode(String bsSuppCode);
//
//	public List<SampleInfo> findByBsSuppCode(String bsSuppCode);

    public List<SampleRegularRecord> findByBsShipmentInspectIdAndAndBsIsDelOrderByIdAsc(Long bsShipmentInspectId, int bsIsDel);
}
