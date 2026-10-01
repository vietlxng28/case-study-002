package com.vietlong.sandbox.dto;

import com.vietlong.sandbox.dto.base.BaseDto;
import com.vietlong.sandbox.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto implements BaseDto<Employee, EmployeeDto> {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private Boolean isDeleted;

    private String empId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String adress;
    private Long superiorEmployeeId;

    @Override
    public Employee toEntity() {
        return Employee.builder()
                .id(id)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .isDeleted(isDeleted != null ? isDeleted : false)
                .empId(empId)
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .adress(adress)
                .superiorEmployee(superiorEmployeeId != null ? Employee.builder().id(superiorEmployeeId).build() : null)
                .build();
    }

    @Override
    public EmployeeDto fromEntity(Employee entity) {
        if (entity == null) {
            return null;
        }
        return EmployeeDto.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .isDeleted(entity.getIsDeleted())
                .empId(entity.getEmpId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .age(entity.getAge())
                .adress(entity.getAdress())
                .superiorEmployeeId(entity.getSuperiorEmployee() != null ? entity.getSuperiorEmployee().getId() : null)
                .build();
    }
}
