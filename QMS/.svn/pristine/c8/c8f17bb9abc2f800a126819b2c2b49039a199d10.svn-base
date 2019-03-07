package com.unind.qms.web.supplier.dao;

import com.unind.qms.web.supplier.entity.CustomerApprovedRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 客户审核记录
 * @author Shen
 */
public interface CustomerApprovedRecordDao extends CrudRepository<CustomerApprovedRecord, Long>, JpaSpecificationExecutor<CustomerApprovedRecord> {

    public List<CustomerApprovedRecord> findByBsIsDelAndBsSuppIdOrderByIdDesc(Integer bsIsDel, Long bsSuppId);
}
