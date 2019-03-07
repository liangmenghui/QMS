package com.unind.qms.web.approved.dao;

import com.unind.qms.web.approved.entity.ApprovedFlowRecord;
import com.unind.qms.web.approved.entity.ApprovedItems;
import com.unind.qms.web.approved.entity.ApprovedTerms;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 
 * @author chen
 *
 */
public interface ApprovedItemsDao extends CrudRepository<ApprovedItems, Long>, JpaSpecificationExecutor<ApprovedItems> {

	public int countByBsName(String bsName);

	public List<ApprovedItems> findByBsName(String bsName);

	@Modifying
	@Query("select t from ApprovedItems t where t.bsFlowId = ?1 and t.bsIsDel = '0'")
	public List<ApprovedItems> findByBsFlowId(Long bsFlowId);
}
