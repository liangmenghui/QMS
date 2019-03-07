package com.unind.qms.web.approved.dao;

import com.unind.qms.web.approved.entity.ApprovedFlow;
import com.unind.qms.web.approved.entity.ApprovedTermsScore;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author chen
 *
 */
public interface ApprovedTermsScoreDao extends CrudRepository<ApprovedTermsScore, Long>, JpaSpecificationExecutor<ApprovedTermsScore> {

//	public int countByBsName(String bsName);
//
//	public List<ApprovedFlow> findByBsName(String bsName);

	public List<ApprovedTermsScore> findByBsItemsRecoreIdOrderByIdAsc(Long bsItemsRecordId);

    //    select decode(count(*),0,'',sum(decode(a.bs_score,-1,0,a.bs_score)))||'/'||(sum(decode(a.bs_score,-1,0,1))*5) as bsScoreNum from t_approved_terms_score a where a.bs_items_recore_id = '3822';
    //返回当前项目记录的条款得分
    @Query(value = "select decode(count(*),0,'',sum(decode(a.bs_score,-1,0,a.bs_score)))||'/'||(sum(decode(a.bs_score,-1,0,1))*5) as bsScoreNum from t_approved_terms_score a where a.bs_items_recore_id = ?1",nativeQuery = true)
    public String findScoreNumByBsItemsRecoreId(Long bsItemsRecoreId);
}
