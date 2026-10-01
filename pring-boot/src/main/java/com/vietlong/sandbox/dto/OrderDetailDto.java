package com.vietlong.sandbox.dto;

import com.vietlong.sandbox.dto.base.BaseDto;
import com.vietlong.sandbox.model.OrderDetail;
import com.vietlong.sandbox.model.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto implements BaseDto<OrderDetail, OrderDetailDto> {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private Boolean isDeleted;

    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private Long purchaseOrderId;

    @Override
    public OrderDetail toEntity() {
        return OrderDetail.builder()
                .id(id)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .isDeleted(isDeleted != null ? isDeleted : false)
                .productName(productName)
                .quantity(quantity)
                .price(price)
                .purchaseOrder(purchaseOrderId != null ? PurchaseOrder.builder().id(purchaseOrderId).build() : null)
                .build();
    }

    @Override
    public OrderDetailDto fromEntity(OrderDetail entity) {
        if (entity == null) {
            return null;
        }
        return OrderDetailDto.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .isDeleted(entity.getIsDeleted())
                .productName(entity.getProductName())
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .purchaseOrderId(entity.getPurchaseOrder() != null ? entity.getPurchaseOrder().getId() : null)
                .build();
    }
}
