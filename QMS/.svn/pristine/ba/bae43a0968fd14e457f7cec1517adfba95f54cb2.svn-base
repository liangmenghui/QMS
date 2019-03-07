package com.unind.qms.web.samplePlan.dao;

import com.unind.qms.web.samplePlan.entity.SampleLevelCodeNum;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SampleLevelCodeNumDao extends CrudRepository<SampleLevelCodeNum, Long>, JpaSpecificationExecutor<SampleLevelCodeNum> {

    public List<SampleLevelCodeNum> findByBsIsDelAndBsCodeNo(Integer bsIsDel, Integer bsCodeNo);
}
