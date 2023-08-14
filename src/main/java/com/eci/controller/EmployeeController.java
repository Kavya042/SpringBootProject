package com.eci.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.eci.model.EmployeeDetails;
import com.eci.service.EmployeeService;

@Controller
public class EmployeeController {
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping("/employeesdetail")
	public String listEmployees(Model model) {
		String keyword = null;
		return listByPage(model, 1, "firstName", "asc", keyword);
	}

	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword) {
		Page<EmployeeDetails> page = employeeService.getAllEmployeeDetails(currentPage, sortField, sortDir, keyword);
		List<EmployeeDetails> listEmp = page.getContent();
		long totalEmp = page.getTotalElements();
		int totalPages =page.getTotalPages();
		model.addAttribute("employeeslist", listEmp);
		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);

		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
		return "employees";
	}

	@GetMapping("/employees/new")
	public String createEmployeeForm(Model model) {
		EmployeeDetails emp = new EmployeeDetails();
		model.addAttribute("employeeAdd",emp);
		return "createEmployee";
	}

	@PostMapping("/employeessave")
	public String saveEmployee(@ModelAttribute("employeeAdd") EmployeeDetails employeeAdd) {
		employeeService.saveEmployee(employeeAdd);
		return "redirect:/employeesdetail";
	}

	@GetMapping("/employees/edit/{id}")
	public String editEmployeeForm(@PathVariable Long id, Model model) {
		model.addAttribute("employeeEdit", employeeService.getEmployeeById(id));
		return "editEmployee";
	}

	@PostMapping("employeesupdate/{id}")
	public String updateEmployee(@PathVariable Long id, @ModelAttribute("employeeUpdate") EmployeeDetails employeeUpdate, Model model) {
		EmployeeDetails existingEmp = employeeService.getEmployeeById(id);
		existingEmp.setId(id);
		existingEmp.setFirstName(employeeUpdate.getFirstName());
		existingEmp.setLastName(employeeUpdate.getLastName());
		existingEmp.setEmail(employeeUpdate.getEmail());
		employeeService.updateEmployee(existingEmp);
		return "redirect:/employeesdetail";
	}

	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/employeesdetail";
	}
}
