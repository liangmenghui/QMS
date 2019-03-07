package com.web.supplier.dao;

import com.web.supplier.entity.InvoiceBill;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceBillDao extends CrudRepository<InvoiceBill, Long>, JpaSpecificationExecutor<InvoiceBill> {
}
