package com.vietlong.sandbox.dto.keysearch;

import com.vietlong.sandbox.dto.keysearch.specification.FilterQuery;
import com.vietlong.sandbox.model.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EmployeeFilterQuery extends FilterQuery<Employee> {

    @Schema(description = "Từ khóa tìm kiếm chung (mã nhân viên, họ, tên, địa chỉ)")
    private String keyword;

    @Schema(description = "Mã nhân viên")
    private String empId;

    @Schema(description = "Tuổi tối thiểu")
    private Integer minAge;

    @Schema(description = "Tuổi tối đa")
    private Integer maxAge;

    @Schema(description = "ID nhân viên cấp trên")
    private Long superiorEmployeeId;

    @Override
    public Specification<Employee> toSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.isFalse(root.get("isDeleted")));

            if (keyword != null && !keyword.isBlank()) {
                String pattern = "%" + keyword.trim().toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("empId")), pattern),
                        cb.like(cb.lower(root.get("firstName")), pattern),
                        cb.like(cb.lower(root.get("lastName")), pattern),
                        cb.like(cb.lower(root.get("adress")), pattern)
                ));
            }

            if (empId != null && !empId.isBlank()) {
                predicates.add(cb.equal(root.get("empId"), empId.trim()));
            }

            if (minAge != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("age"), minAge));
            }

            if (maxAge != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("age"), maxAge));
            }

            if (superiorEmployeeId != null) {
                predicates.add(cb.equal(root.get("superiorEmployee").get("id"), superiorEmployeeId));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
