package com.unind.qms.web.approved.dao;

import com.unind.qms.web.approved.entity.ApprovedTerms;
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
public interface ApprovedTermsDao extends CrudRepository<ApprovedTerms, Long>, JpaSpecificationExecutor<ApprovedTerms> {

	public int countByBsNo(String bsNo);

	public List<ApprovedTerms> findByBsNo(String bsNo);
}
