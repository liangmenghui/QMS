package com.unind.qms.web.supplier.dao;

import com.unind.qms.web.supplier.entity.Promote;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author Shen
 */
public interface PromoteDao extends CrudRepository<Promote, Long>, JpaSpecificationExecutor<Promote> {
    public List<Promote> findByBsFlowRecordIdOrderByIdAsc(Long bsFlowRecordId);

    public int countByBsItemsRecordIdAndBsTermsId(Long bsItemsRecordId, Long bsTermsId);

    public Promote findByBsItemsRecordIdAndBsTermsId(Long bsItemsRecordId, Long bsTermsId);

    public void deleteAllByIdNotInAndBsItemsRecordId(Collection<Long> ids, Long bsItemsRecordId);

    public void deleteAllByBsItemsRecordId(Long bsItemsRecordId);
}
