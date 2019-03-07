package com.unind.qms.web.approved.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.unind.qms.web.approved.entity.ApprovedEHSMap;
import com.unind.qms.web.approved.entity.ApprovedEHSRecord;
import com.unind.qms.web.approved.entity.ApprovedTermsScore;

import java.util.List;

/**
 * 
 * @author fyx
 *
 */
public interface ApprovedEHSMapDao extends CrudRepository<ApprovedEHSMap, Long>, JpaSpecificationExecutor<ApprovedEHSMap> {

    public List<ApprovedEHSMap> findByBsIsDelAndSupplierIdOrderByBsCreatedTimeDesc(Integer bsIsDel, Long supplierId);
}
