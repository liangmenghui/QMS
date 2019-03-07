package com.unind.qms.web.shipment.dao;

import com.unind.qms.web.shipment.entity.ShipmentInspectFile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author chen
 *
 */
public interface ShipmentInspectFileDao extends CrudRepository<ShipmentInspectFile, Long>, JpaSpecificationExecutor<ShipmentInspectFile> {

}
