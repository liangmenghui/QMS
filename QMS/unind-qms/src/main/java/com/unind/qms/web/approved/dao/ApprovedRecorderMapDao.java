package com.unind.qms.web.approved.dao;

import com.unind.qms.web.approved.entity.ApprovedItemsMap;
import com.unind.qms.web.approved.entity.ApprovedRecorderMap;
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
public interface ApprovedRecorderMapDao extends CrudRepository<ApprovedRecorderMap, Long>, JpaSpecificationExecutor<ApprovedRecorderMap> {

//	public int countByBsName(String bsName);
//
//	public List<ApprovedItems> findByBsName(String bsName);

	@Modifying
	@Query("update ApprovedRecorderMap t set t.bsIsDel='1' where t.bsItemsId=?1")
	public void deleteByBsItemsId(Long bsItemsId);

	//返回当前审批人数据
	@Query(value = "select a from ApprovedRecorderMap a where a.bsItemsId = ?1 and a.bsPriority = ?2 and a.bsIsDel='0'")
	public List<ApprovedRecorderMap> findByBsItemsIdAndBsPriority(Long bsItemsId, int bsStep);
}
