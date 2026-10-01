package com.vietlong.sandbox.dto;

import com.vietlong.sandbox.dto.base.BaseDto;
import com.vietlong.sandbox.model.Employee;
import com.vietlong.sandbox.model.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDto implements BaseDto<PurchaseOrder, PurchaseOrderDto> {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private Boolean isDeleted;

    private String orderCode;
    private String customerName;
    private LocalDate orderDate;
    private Long managerEmployeeId;

    @Override
    public PurchaseOrder toEntity() {
        return PurchaseOrder.builder()
                .id(id)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .isDeleted(isDeleted != null ? isDeleted : false)
                .orderCode(orderCode)
                .customerName(customerName)
                .orderDate(orderDate)
                .managerEmployee(managerEmployeeId != null ? Employee.builder().id(managerEmployeeId).build() : null)
                .build();
    }

    @Override
    public PurchaseOrderDto fromEntity(PurchaseOrder entity) {
        if (entity == null) {
            return null;
        }
        return PurchaseOrderDto.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .isDeleted(entity.getIsDeleted())
                .orderCode(entity.getOrderCode())
                .customerName(entity.getCustomerName())
                .orderDate(entity.getOrderDate())
                .managerEmployeeId(entity.getManagerEmployee() != null ? entity.getManagerEmployee().getId() : null)
                .build();
    }
}
