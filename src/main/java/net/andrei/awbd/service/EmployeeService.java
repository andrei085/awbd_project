package net.andrei.awbd.service;

import java.util.List;

import net.andrei.awbd.model.*;
import org.springframework.data.domain.Page;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	void saveProject(Projects project);
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	void assignProject(long employeeID, long projectID);
	void saveReview(long employeeID,Reviews review);
	List<City> getCities();
	List<Department> getDepartments();
	List<Projects> getProjects();
}
