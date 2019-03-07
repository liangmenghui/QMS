package com.unind.qms.web.risk.dao;

import com.unind.qms.web.risk.entity.RiskLevelManagement;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 风险等级管理（审核周期和检验水平）
 * @author Shen
 */
public interface RiskLevelManagementDao extends CrudRepository<RiskLevelManagement,Long>, JpaSpecificationExecutor<RiskLevelManagement> {

    public List<RiskLevelManagement> findByBsIsDelAndBsRiskLevel(Integer bsIsDel, Integer bsRiskLevel);
}
