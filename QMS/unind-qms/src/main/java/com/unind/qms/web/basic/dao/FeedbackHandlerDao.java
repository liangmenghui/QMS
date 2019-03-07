package com.unind.qms.web.basic.dao;

import com.unind.qms.web.basic.entity.FeedbackHandler;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 
 * @author chen
 *
 */
public interface FeedbackHandlerDao extends CrudRepository<FeedbackHandler, Long>, JpaSpecificationExecutor<FeedbackHandler> {

    public List<FeedbackHandler> findByBsIsDelAndBsFeedbackIdAndBsType(Integer bsIdDel, Long bsFeedbackId, Integer bsType);

//	public int countByBsSuppCode(String bsSuppCode);
//
//	public List<SampleInfo> findByBsSuppCode(String bsSuppCode);

//    @Modifying
//    @Query("update TodoInfo t set t.bsStatus='1' where t.bsUserId=?1 and t.bsExtend=?2")
//    public void closeByBsUserIdAndBsAndBsExtend(Long bsUserId, Long bsExtend);
}
