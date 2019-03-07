package com.unind.qms.web.shipment.dao;

import com.unind.qms.web.shipment.entity.ShipmentInspect;
import com.unind.qms.web.shipment.entity.ShipmentInspectRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 
 * @author chen
 *
 */
public interface ShipmentInspectRecordDao extends CrudRepository<ShipmentInspectRecord, Long>, JpaSpecificationExecutor<ShipmentInspectRecord> {

//	public int countByBsSuppCode(String bsSuppCode);
//
//	public List<SampleInfo> findByBsSuppCode(String bsSuppCode);

    public List<ShipmentInspectRecord> findByBsShipmentIdOrderByIdAsc(Long bsShipmentId);
}
