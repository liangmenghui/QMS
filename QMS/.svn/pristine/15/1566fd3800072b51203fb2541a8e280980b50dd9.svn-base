package com.unind.qms.web.approved.dao;

import com.unind.qms.web.approved.entity.ApprovedEHSTerms;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 
 * @author fyx
 *
 */
public interface ApprovedEHSTermsDao extends CrudRepository<ApprovedEHSTerms, Long>, JpaSpecificationExecutor<ApprovedEHSTerms> {

	public int countByBsNo(String bsNo);

	public List<ApprovedEHSTerms> findByBsNo(String bsNo);
}
