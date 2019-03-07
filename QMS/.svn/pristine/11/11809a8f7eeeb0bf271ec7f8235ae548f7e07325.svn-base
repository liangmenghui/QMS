package com.unind.qms.web.approved.dao;

import com.unind.qms.web.approved.entity.ApprovedItemsMap;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author chen
 *
 */
public interface ApprovedItemsMapDao extends CrudRepository<ApprovedItemsMap, Long>, JpaSpecificationExecutor<ApprovedItemsMap> {

//	public int countByBsName(String bsName);
//
//	public List<ApprovedItems> findByBsName(String bsName);

//	@Modifying
//	@Query("delete from ApprovedItemsMap t where t.bsItemsId=?")
//	public void deleteByBsItemsId(Long bsItemsId);
//
//	@Modifying
//	@Query("delete from ApprovedItemsMap t where t.bsTermsId=?")
//	public void deleteByBsTermsId(Long bsTermsId);

	@Modifying
	@Query("update ApprovedItemsMap t set t.bsIsDel='1' where t.bsItemsId=?1")
	public void deleteByBsItemsId(Long bsItemsId);

	@Modifying
	@Query("update ApprovedItemsMap t set t.bsIsDel='1' where t.bsTermsId=?1")
	public void deleteByBsTermsId(Long bsTermsId);
}
