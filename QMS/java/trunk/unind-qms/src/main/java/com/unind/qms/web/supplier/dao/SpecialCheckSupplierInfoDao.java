package com.unind.qms.web.supplier.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.unind.qms.web.supplier.entity.SpecialCheckSupplierInfo;

/**
 * 
 * @author fyx
 *
 */
public interface SpecialCheckSupplierInfoDao extends CrudRepository<SpecialCheckSupplierInfo, Long>, JpaSpecificationExecutor<SpecialCheckSupplierInfo> {

}
