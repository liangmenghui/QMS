package com.unind.qms.web.sample.dao;

import com.unind.qms.web.sample.entity.SampleRegular;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author chen
 *
 */
public interface SampleRegularDao extends CrudRepository<SampleRegular, Long>, JpaSpecificationExecutor<SampleRegular> {

//	public int countByBsSuppCode(String bsSuppCode);
//
//	public List<SampleInfo> findByBsSuppCode(String bsSuppCode);
}
