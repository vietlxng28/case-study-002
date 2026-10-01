package com.vietlong.sandbox.repository;

import com.vietlong.sandbox.model.Employee;
import com.vietlong.sandbox.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee> {
}
