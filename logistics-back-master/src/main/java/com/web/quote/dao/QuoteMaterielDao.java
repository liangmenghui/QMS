package com.web.quote.dao;

import com.web.quote.entity.QuoteMateriel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuoteMaterielDao extends CrudRepository<QuoteMateriel, Long>, JpaSpecificationExecutor<QuoteMateriel> {

    public QuoteMateriel findById(long id);

    public List<QuoteMateriel> findByIsDelAndQtIdOrderByIdAsc(Integer isDel, Long qtId);

    public List<QuoteMateriel> findByIsDelAndQtIdAndMateNameAndMateModelAndQtMateNum(Integer isDel, Long qtId, String mateName, String mateModel, Integer qtMateNum);
}
