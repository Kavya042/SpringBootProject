package com.eci.service;

import org.springframework.data.domain.Page;
import com.eci.model.EmployeeDetails;

public interface EmployeeService {

	Page<EmployeeDetails> getAllEmployeeDetails(int pageNumber, String sortField, String sortDir, String keyword);

	EmployeeDetails saveEmployee(EmployeeDetails employee);

	EmployeeDetails getEmployeeById(Long id);

	EmployeeDetails updateEmployee(EmployeeDetails employee);

	void deleteEmployeeById(Long id);
}
