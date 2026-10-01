package com.vietlong.sandbox.service;

import com.vietlong.sandbox.dto.EmployeeDto;
import com.vietlong.sandbox.model.Employee;
import com.vietlong.sandbox.repository.EmployeeRepository;
import com.vietlong.sandbox.repository.base.BaseRepository;
import com.vietlong.sandbox.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService extends BaseService<Employee, EmployeeDto> {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDto employeeDtoHelper = new EmployeeDto();

    @Override
    protected BaseRepository<Employee> getRepository() {
        return employeeRepository;
    }

    @Override
    protected EmployeeDto getDtoHelper() {
        return employeeDtoHelper;
    }
}
