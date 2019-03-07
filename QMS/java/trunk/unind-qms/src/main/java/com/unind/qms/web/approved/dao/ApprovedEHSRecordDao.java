package com.unind.qms.web.approved.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.unind.qms.web.approved.entity.ApprovedEHSRecord;
import com.unind.qms.web.approved.entity.ApprovedFlow;
import com.unind.qms.web.approved.entity.ApprovedTermsScore;

/**
 * 
 * @author fyx
 *
 */
public interface ApprovedEHSRecordDao extends CrudRepository<ApprovedEHSRecord, Long>, JpaSpecificationExecutor<ApprovedEHSRecord> {
	public List<ApprovedEHSRecord> findByMapIdAndBsEHSItemsId(Long mapId, Long itemsId);

	public List<ApprovedEHSRecord> findByMapIdAndEhsEval(Long mapId, String ehsEval);
}
