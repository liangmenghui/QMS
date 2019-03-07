package com.unind.qms.web.risk.dao;

import com.unind.qms.web.risk.entity.ProductPpm;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 产品风险ppm的不良品数
 * @author Shen
 */
public interface ProductPpmDao extends CrudRepository<ProductPpm, Long>, JpaSpecificationExecutor<ProductPpm> {

    public List<ProductPpm> findByBsPrIdAndBsYear(Long bsPrId, Integer bsYear);
}
