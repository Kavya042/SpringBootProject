package com.eci.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.eci.model.EmployeeDetails;
@Service
public interface EmployeeDetailsRepository extends PagingAndSortingRepository<EmployeeDetails, Long>{
	@Query("select empl from EmployeeDetails empl where " + "concat(empl.firstName,' ', empl.lastName,' ', empl.email) like %?1%")
	public Page<EmployeeDetails> findAll(String keyword, Pageable pageable);
}
