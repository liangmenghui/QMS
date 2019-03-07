package com.unind.qms.web.supplier.dao;

import com.unind.qms.web.supplier.entity.CustomerApprovedFile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 客户审核文件关联
 * @author Shen
 */
public interface CustomerApprovedFileDao extends CrudRepository<CustomerApprovedFile, Long>, JpaSpecificationExecutor<CustomerApprovedFile> {

    public List<CustomerApprovedFile> findByBsIsDelAndAndBsCustomerApprovedId(Integer bsIsDel, Long bsCustomerApprovedId);
}
