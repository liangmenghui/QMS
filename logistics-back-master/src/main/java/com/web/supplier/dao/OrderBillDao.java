package com.web.supplier.dao;

import com.web.supplier.entity.OrderBill;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface OrderBillDao extends CrudRepository<OrderBill, Long>, JpaSpecificationExecutor<OrderBill> {
}
