package com.unind.qms.web.shipment.dao;

import com.unind.qms.web.shipment.entity.OrderInspect;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author chen
 *
 */
public interface OrderInspectDao extends CrudRepository<OrderInspect, Long>, JpaSpecificationExecutor<OrderInspect> {

}
