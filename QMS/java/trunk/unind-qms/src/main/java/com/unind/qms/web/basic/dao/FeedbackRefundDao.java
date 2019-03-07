package com.unind.qms.web.basic.dao;

import com.unind.qms.web.basic.entity.FeedbackRefund;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Shen
 */
public interface FeedbackRefundDao extends CrudRepository<FeedbackRefund, Long>, JpaSpecificationExecutor<FeedbackRefund> {

    public List<FeedbackRefund> findByBsIsDelAndBsFeedbackId(Integer bsIsDel, Long bsFeedbackId);

}
