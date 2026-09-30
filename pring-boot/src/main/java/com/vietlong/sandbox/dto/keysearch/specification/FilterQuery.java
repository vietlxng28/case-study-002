package com.vietlong.sandbox.dto.keysearch.specification;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class FilterQuery<T> {

    @Schema(description = "Số trang (bắt đầu từ 0)", example = "0")
    private Integer page = 0;

    @Schema(description = "Số lượng bản ghi mỗi trang", example = "10")
    private Integer size = 10;

    @Schema(description = "Danh sách thuộc tính sắp xếp (định dạng: property,asc|desc)", example = "[\"id,desc\"]")
    private List<String> sort;

    public abstract Specification<T> toSpecification();

    public Pageable toPageable() {
        int p = (page != null && page >= 0) ? page : 0;
        int s = (size != null && size > 0) ? size : 10;

        if (sort != null && !sort.isEmpty()) {
            List<Sort.Order> orders = new ArrayList<>();
            for (String sortStr : sort) {
                if (sortStr != null && !sortStr.isBlank()) {
                    String[] parts = sortStr.split(",");
                    String property = parts[0].trim();
                    if (!property.isEmpty() && property.matches("^[a-zA-Z0-9._]+$")) {
                        Sort.Direction direction = Sort.Direction.ASC;
                        if (parts.length > 1 && "desc".equalsIgnoreCase(parts[1].trim())) {
                            direction = Sort.Direction.DESC;
                        }
                        orders.add(new Sort.Order(direction, property));
                    }
                }
            }
            if (!orders.isEmpty()) {
                return PageRequest.of(p, s, Sort.by(orders));
            }
        }

        return PageRequest.of(p, s);
    }
}
