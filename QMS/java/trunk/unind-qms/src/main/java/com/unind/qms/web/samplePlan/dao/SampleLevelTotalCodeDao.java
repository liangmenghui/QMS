package com.unind.qms.web.samplePlan.dao;

import com.unind.qms.web.samplePlan.entity.SampleLevelTotalCode;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SampleLevelTotalCodeDao extends CrudRepository<SampleLevelTotalCode, Long>, JpaSpecificationExecutor<SampleLevelTotalCode> {

    public List<SampleLevelTotalCode> findByBsIsDel(Integer bsIsDel);
}