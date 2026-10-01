package com.vietlong.sandbox.dto.keysearch;

import com.vietlong.sandbox.dto.keysearch.specification.FilterQuery;
import com.vietlong.sandbox.model.OrderDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDetailFilterQuery extends FilterQuery<OrderDetail> {

    @Schema(description = "Tên sản phẩm")
    private String productName;

    @Schema(description = "Giá tối thiểu")
    private BigDecimal minPrice;

    @Schema(description = "Giá tối đa")
    private BigDecimal maxPrice;

    @Schema(description = "ID đơn đặt hàng")
    private Long purchaseOrderId;

    @Override
    public Specification<OrderDetail> toSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.isFalse(root.get("isDeleted")));

            if (productName != null && !productName.isBlank()) {
                String pattern = "%" + productName.trim().toLowerCase() + "%";
                predicates.add(cb.like(cb.lower(root.get("productName")), pattern));
            }

            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            if (purchaseOrderId != null) {
                predicates.add(cb.equal(root.get("purchaseOrder").get("id"), purchaseOrderId));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
