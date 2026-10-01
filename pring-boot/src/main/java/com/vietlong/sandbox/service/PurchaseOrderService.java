package com.vietlong.sandbox.service;

import com.vietlong.sandbox.dto.PurchaseOrderDto;
import com.vietlong.sandbox.model.PurchaseOrder;
import com.vietlong.sandbox.repository.PurchaseOrderRepository;
import com.vietlong.sandbox.repository.base.BaseRepository;
import com.vietlong.sandbox.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService extends BaseService<PurchaseOrder, PurchaseOrderDto> {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderDto purchaseOrderDtoHelper = new PurchaseOrderDto();

    @Override
    protected BaseRepository<PurchaseOrder> getRepository() {
        return purchaseOrderRepository;
    }

    @Override
    protected PurchaseOrderDto getDtoHelper() {
        return purchaseOrderDtoHelper;
    }
}
