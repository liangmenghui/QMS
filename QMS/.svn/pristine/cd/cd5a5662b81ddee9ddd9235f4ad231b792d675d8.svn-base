package com.unind.qms.web.basic.dao;

import com.unind.qms.web.basic.entity.ExcelTemp;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author chen
 *
 */
public interface ExcelTempDao extends CrudRepository<ExcelTemp, Long>, JpaSpecificationExecutor<ExcelTemp> {

    public int countByBsType(int bsType);

    public ExcelTemp findFirstByBsType(int bsType);
}
