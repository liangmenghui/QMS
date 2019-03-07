package com.unind.qms.web.shipment.dao;

import com.unind.qms.web.shipment.entity.ShipmentInspectRecordFile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author chen
 *
 */
public interface ShipmentInspectRecordFileDao extends CrudRepository<ShipmentInspectRecordFile, Long>, JpaSpecificationExecutor<ShipmentInspectRecordFile> {

}
