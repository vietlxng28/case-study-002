package com.vietlong.sandbox.controller;

import com.vietlong.sandbox.controller.base.BaseController;
import com.vietlong.sandbox.dto.EmployeeDto;
import com.vietlong.sandbox.dto.keysearch.EmployeeFilterQuery;
import com.vietlong.sandbox.model.Employee;
import com.vietlong.sandbox.service.EmployeeService;
import com.vietlong.sandbox.service.base.BaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Employee", description = "Employee Management APIs")
@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController extends BaseController<Employee, EmployeeDto, EmployeeFilterQuery> {

    private final EmployeeService employeeService;

    @Override
    protected BaseService<Employee, EmployeeDto> getService() {
        return employeeService;
    }
}
