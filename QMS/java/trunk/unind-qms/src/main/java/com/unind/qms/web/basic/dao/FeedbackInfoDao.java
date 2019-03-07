package com.unind.qms.web.basic.dao;

import com.unind.qms.web.basic.entity.FeedbackInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author chen
 *
 */
public interface FeedbackInfoDao extends CrudRepository<FeedbackInfo, Long>, JpaSpecificationExecutor<FeedbackInfo> {

//	public int countByBsSuppCode(String bsSuppCode);
//
//	public List<SampleInfo> findByBsSuppCode(String bsSuppCode);

//    @Modifying
//    @Query("update TodoInfo t set t.bsStatus='1' where t.bsUserId=?1 and t.bsExtend=?2")
//    public void closeByBsUserIdAndBsAndBsExtend(Long bsUserId, Long bsExtend);
}
