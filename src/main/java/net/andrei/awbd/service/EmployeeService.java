package net.andrei.awbd.service;

import java.util.List;

import net.andrei.awbd.model.City;
import net.andrei.awbd.model.Department;
import net.andrei.awbd.model.Projects;
import org.springframework.data.domain.Page;

import net.andrei.awbd.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	void saveProject(Projects project);
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	void assignProject(long id, long projectID);
	List<City> getCities();
	List<Department> getDepartments();
	List<Projects> getProjects();
}
