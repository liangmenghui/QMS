package com.unind.qms.web.approved.dao;

import com.unind.qms.web.approved.entity.ApprovedTermsScoreFile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author chen
 *
 */
public interface ApprovedTermsScoreFileDao extends CrudRepository<ApprovedTermsScoreFile, Long>, JpaSpecificationExecutor<ApprovedTermsScoreFile> {

}
