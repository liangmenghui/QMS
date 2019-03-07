package com.web.cost.dao;

import com.web.cost.entity.CustomerBom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 客户BOM表
 *
 */
public interface CustomerBomDao extends CrudRepository<CustomerBom, Long>, JpaSpecificationExecutor<CustomerBom> {

    public CustomerBom findById(long id);

	public List<CustomerBom> findByFileId(Long fileId);

    public List<CustomerBom> findByIsDelAndFileIdOrderByIdAsc(Integer isDel, Long fileId);

    public List<CustomerBom> findByIsDelAndFileIdAndBomType(Integer isDel, Long fileId, Integer bomType);
    
    @Modifying
	@Query(value = "update CustomerBom set mateCategory =?2 where id =?1")
	public void updateCategoryById(Long customId, String category);
}
