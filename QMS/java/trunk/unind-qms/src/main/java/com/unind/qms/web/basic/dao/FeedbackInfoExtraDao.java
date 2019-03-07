package com.unind.qms.web.basic.dao;

import com.unind.qms.web.basic.entity.FeedbackInfoExtra;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 客诉附加信息
 */
public interface FeedbackInfoExtraDao extends CrudRepository<FeedbackInfoExtra, Long>, JpaSpecificationExecutor<FeedbackInfoExtra> {

    public List<FeedbackInfoExtra> findByBsIsDelAndBsPrIdOrderByBsCreatedTimeDesc(Integer bsIsDel, Long bsPrId);

    public List<FeedbackInfoExtra> findByBsIsDelAndBsSuppIdOrderByBsCreatedTimeDesc(Integer bsIsDel, Long bsSuppId);

    public List<FeedbackInfoExtra> findByBsIsDelAndBsFeedbackIdOrderByIdDesc(Integer bsIsDel, Long bsFeedbackId);
}
