package com.unind.qms.web.shipment.dao;

import com.unind.qms.web.shipment.entity.ShipmentInspect;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

/**
 * 
 * @author chen
 *
 */
public interface ShipmentInspectDao extends CrudRepository<ShipmentInspect, Long>, JpaSpecificationExecutor<ShipmentInspect> {

//	public int countByBsSuppCode(String bsSuppCode);
//
//	public List<SampleInfo> findByBsSuppCode(String bsSuppCode);

    @Modifying
    @Query("update ShipmentInspect t set t.bsSampleId=?1 where t.id=?2")
    public void updateBsSampleIdByBsId(Long bsSampleId, Long id);

    public int countByBsOrderIdAndBsCreatedTimeIsLessThanEqual(Long bsOrderId, Date bsCreatedTime);
}
