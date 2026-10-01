package com.vietlong.sandbox.controller;

import com.vietlong.sandbox.controller.base.BaseController;
import com.vietlong.sandbox.dto.OrderDetailDto;
import com.vietlong.sandbox.dto.keysearch.OrderDetailFilterQuery;
import com.vietlong.sandbox.model.OrderDetail;
import com.vietlong.sandbox.service.OrderDetailService;
import com.vietlong.sandbox.service.base.BaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "OrderDetail", description = "Order Detail Management APIs")
@RestController
@RequestMapping("/api/v1/order-details")
@RequiredArgsConstructor
public class OrderDetailController extends BaseController<OrderDetail, OrderDetailDto, OrderDetailFilterQuery> {

    private final OrderDetailService orderDetailService;

    @Override
    protected BaseService<OrderDetail, OrderDetailDto> getService() {
        return orderDetailService;
    }
}
