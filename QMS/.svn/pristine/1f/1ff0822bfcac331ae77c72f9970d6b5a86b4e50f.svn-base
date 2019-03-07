package com.unind.qms.web.samplePlan.dao;

import com.unind.qms.web.samplePlan.entity.SampleLevelRisk;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SampleLevelRiskDao extends CrudRepository<SampleLevelRisk, Long>, JpaSpecificationExecutor<SampleLevelRisk> {

    public List<SampleLevelRisk> findByBsIsDelAndAndBsRiskLevel(Integer bsIsDel, Integer bsRiskLEvel);
}
