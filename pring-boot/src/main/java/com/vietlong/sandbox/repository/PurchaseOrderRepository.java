package com.vietlong.sandbox.repository;

import com.vietlong.sandbox.model.PurchaseOrder;
import com.vietlong.sandbox.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends BaseRepository<PurchaseOrder> {
}
