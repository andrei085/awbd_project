package net.andrei.awbd.controller;

import java.util.List;

import net.andrei.awbd.exceptions.ResourceNotFoundException;
import net.andrei.awbd.model.City;
import net.andrei.awbd.model.Department;
import net.andrei.awbd.model.Employee;
import net.andrei.awbd.model.Projects;
import net.andrei.awbd.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView handlerNotFoundException(Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.getModel().put("exception",exception);
		modelAndView.setViewName("notFoundException");
		return modelAndView;
	}
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);		
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		List<City> cities = employeeService.getCities();
		List<Department> departments = employeeService.getDepartments();
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("cities",cities);
		model.addAttribute("departments",departments);
		return "new_employee";
	}

	@GetMapping("/showAddNewProject")
	public String showAddNewProject(Model model) {
		Projects project = new Projects();
		model.addAttribute("project", project);
		return "add_project";
	}

	@PostMapping("/saveProject")
	public String saveProject(@Valid @ModelAttribute("project") Projects project,
							  BindingResult bindingResult) {
		if (bindingResult.hasErrors()){return "add_project";}
		employeeService.saveProject(project);
		return "redirect:/";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
							   BindingResult bindingResult) {
		if (bindingResult.hasErrors()){return "new_employee";}
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}

	@PostMapping("/updateEmployee")
	public String updateEmployee(@Valid @ModelAttribute("employee") Employee employee,
							   BindingResult bindingResult) {
		if (bindingResult.hasErrors()){return "update_employee";}
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

		Employee employee = employeeService.getEmployeeById(id);
		List<City> cities = employeeService.getCities();
		List<Department> departments = employeeService.getDepartments();
		model.addAttribute("employee", employee);
		model.addAttribute("cities",cities);
		model.addAttribute("departments",departments);
		return "update_employee";
	}

	@GetMapping("/showEmployeeInfo/{id}")
	public String showEmployeeInfo(@PathVariable ( value = "id") long id, Model model) {

		Employee employee = employeeService.getEmployeeById(id);
		List<City> cities = employeeService.getCities();
		List<Department> departments = employeeService.getDepartments();
		model.addAttribute("employee", employee);
		model.addAttribute("cities",cities);
		model.addAttribute("departments",departments);
		return "info_employee";
	}

	@PostMapping("/assignProject/{empid}/{projectid}")
	public String assignProject(@PathVariable ( value = "empid") long employeeID, @PathVariable (value = "projectid") long projectID) {
		this.employeeService.assignProject(employeeID,projectID);
		return "redirect:/";
	}

	@GetMapping("/assignForm/{id}")
	public String showAssignForm(@PathVariable ( value = "id") long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		List<Projects> projects = employeeService.getProjects();
		Projects project = new Projects();
		model.addAttribute("employee", employee);
		model.addAttribute("projects",projects);
		model.addAttribute("project",project);
		return "assign_project";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {

		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
}
