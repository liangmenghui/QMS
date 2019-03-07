package com.web.cost.dao;

import com.web.cost.entity.CustomerBom;
import com.web.cost.entity.CustomerBomMatch;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springside.modules.persistence.SearchFilter.Operator;

import java.util.List;

public interface CustomerBomMatchDao extends CrudRepository<CustomerBomMatch, Long>, JpaSpecificationExecutor<CustomerBomMatch> {

    public List<CustomerBomMatch> findByIsDelAndAndCusBomIdOrderByRatioDesc(Integer isDel, Long cusBomId);
    
    public CustomerBomMatch findById(long id);
    
    public List<CustomerBomMatch> findByIsDelAndCheckStatusAndCusBomId(Integer isDel, int checkStatus, Long cusBomId);

    public List<CustomerBomMatch> findByIsDelAndFileId(Integer isDel, Long fileId);
}
