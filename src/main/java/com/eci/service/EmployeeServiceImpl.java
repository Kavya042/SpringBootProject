package com.eci.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eci.model.EmployeeDetails;
import com.eci.repository.EmployeeDetailsRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeDetailsRepository employeeDetailsRepositoy;
	
	public EmployeeServiceImpl(EmployeeDetailsRepository employeeDetailsRepositoy) {
		super();
		this.employeeDetailsRepositoy = employeeDetailsRepositoy;
	}

	/*@Override
	public List<EmployeeDetails> getAllEmployeeDetails(String keyword) {
		if(keyword != null) {
			return employeeDetailsRepositoy.findAll(keyword);
		}
		return employeeDetailsRepositoy.findAll();
	}*/
	
	@Override
	public Page<EmployeeDetails> getAllEmployeeDetails(int pageNumber, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNumber-1, 5, sort);
		if(keyword != null) {
			return employeeDetailsRepositoy.findAll(keyword,pageable);
		}
		return employeeDetailsRepositoy.findAll(pageable);
	}

	@Override
	public EmployeeDetails saveEmployee(EmployeeDetails employee) {
		return employeeDetailsRepositoy.save(employee);
	}

	@Override
	public EmployeeDetails getEmployeeById(Long id) {
//		return employeeDetailsRepositoy.getById(id);
		return employeeDetailsRepositoy.findById(id).get();
	}

	@Override
	public EmployeeDetails updateEmployee(EmployeeDetails employee) {
		return employeeDetailsRepositoy.save(employee);
	}

	@Override
	public void deleteEmployeeById(Long id) {
		employeeDetailsRepositoy.deleteById(id);		
	}

}
