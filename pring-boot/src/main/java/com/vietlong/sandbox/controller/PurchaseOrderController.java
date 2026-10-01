package com.vietlong.sandbox.controller;

import com.vietlong.sandbox.controller.base.BaseController;
import com.vietlong.sandbox.dto.PurchaseOrderDto;
import com.vietlong.sandbox.dto.keysearch.PurchaseOrderFilterQuery;
import com.vietlong.sandbox.model.PurchaseOrder;
import com.vietlong.sandbox.service.PurchaseOrderService;
import com.vietlong.sandbox.service.base.BaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PurchaseOrder", description = "Purchase Order Management APIs")
@RestController
@RequestMapping("/api/v1/purchase-orders")
@RequiredArgsConstructor
public class PurchaseOrderController extends BaseController<PurchaseOrder, PurchaseOrderDto, PurchaseOrderFilterQuery> {

    private final PurchaseOrderService purchaseOrderService;

    @Override
    protected BaseService<PurchaseOrder, PurchaseOrderDto> getService() {
        return purchaseOrderService;
    }
}
