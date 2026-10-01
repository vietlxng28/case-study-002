package com.vietlong.sandbox.service;

import com.vietlong.sandbox.dto.OrderDetailDto;
import com.vietlong.sandbox.model.OrderDetail;
import com.vietlong.sandbox.repository.OrderDetailRepository;
import com.vietlong.sandbox.repository.base.BaseRepository;
import com.vietlong.sandbox.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailService extends BaseService<OrderDetail, OrderDetailDto> {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailDto orderDetailDtoHelper = new OrderDetailDto();

    @Override
    protected BaseRepository<OrderDetail> getRepository() {
        return orderDetailRepository;
    }

    @Override
    protected OrderDetailDto getDtoHelper() {
        return orderDetailDtoHelper;
    }
}
