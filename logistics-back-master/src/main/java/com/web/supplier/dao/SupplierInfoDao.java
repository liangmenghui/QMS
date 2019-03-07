package com.web.supplier.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.web.supplier.entity.SupplierInfo;

import java.util.List;

public interface SupplierInfoDao extends CrudRepository<SupplierInfo, Long>, JpaSpecificationExecutor<SupplierInfo>{

    public int countByIsDelAndSuppCode(Integer isDel, String suppCode);

    public List<SupplierInfo> findByIsDelAndLoginName(Integer isDel, String loginName);

    public SupplierInfo findById(long id);
    
//    @Query(value = "select  from  "+SupplierInfo.TABLE_NAME+" p where p.", nativeQuery = true)
//    public List<SupplierInfo> findSupplierInfoByK3CodeIsNotNull();
    
    public List<SupplierInfo> findBySuppK3CodeIsNullAndIsDelAndSuppGrade(Integer isDel, Integer suppGrade);

}
