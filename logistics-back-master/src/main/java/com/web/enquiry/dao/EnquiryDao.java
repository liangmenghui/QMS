package com.web.enquiry.dao;

import com.web.enquiry.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * 新料询价表
 */
public interface EnquiryDao extends CrudRepository<Enquiry, Long>, JpaSpecificationExecutor<Enquiry> {

    public Enquiry findById(long id);

    public int countByIsDelAndEqStatus(Integer isDel, Integer eqStatus);
}
