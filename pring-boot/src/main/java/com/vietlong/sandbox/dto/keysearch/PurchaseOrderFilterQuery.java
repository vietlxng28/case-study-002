package com.vietlong.sandbox.dto.keysearch;

import com.vietlong.sandbox.dto.keysearch.specification.FilterQuery;
import com.vietlong.sandbox.model.PurchaseOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PurchaseOrderFilterQuery extends FilterQuery<PurchaseOrder> {

    @Schema(description = "Mã đơn hàng")
    private String orderCode;

    @Schema(description = "Tên khách hàng")
    private String customerName;

    @Schema(description = "Từ ngày đặt hàng")
    private LocalDate fromOrderDate;

    @Schema(description = "Đến ngày đặt hàng")
    private LocalDate toOrderDate;

    @Schema(description = "ID nhân viên quản lý")
    private Long managerEmployeeId;

    @Override
    public Specification<PurchaseOrder> toSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.isFalse(root.get("isDeleted")));

            if (orderCode != null && !orderCode.isBlank()) {
                predicates.add(cb.equal(root.get("orderCode"), orderCode.trim()));
            }

            if (customerName != null && !customerName.isBlank()) {
                String pattern = "%" + customerName.trim().toLowerCase() + "%";
                predicates.add(cb.like(cb.lower(root.get("customerName")), pattern));
            }

            if (fromOrderDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("orderDate"), fromOrderDate));
            }

            if (toOrderDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("orderDate"), toOrderDate));
            }

            if (managerEmployeeId != null) {
                predicates.add(cb.equal(root.get("managerEmployee").get("id"), managerEmployeeId));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
