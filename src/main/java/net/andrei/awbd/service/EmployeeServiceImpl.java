package net.andrei.awbd.service;

import java.util.List;
import java.util.Optional;

import net.andrei.awbd.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;

import net.andrei.awbd.model.City;
import net.andrei.awbd.model.Department;
import net.andrei.awbd.model.Employee;
import net.andrei.awbd.model.Projects;
import net.andrei.awbd.repository.CityRepository;
import net.andrei.awbd.repository.DepartmentRepository;
import net.andrei.awbd.repository.EmployeeRepository;
import net.andrei.awbd.repository.ProjectsRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ProjectsRepository projectsRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
		logger.info("Employee saved with id " + employee.getId());
	}

	@Override
	public void saveProject(Projects project){
		this.projectsRepository.save(project);
		logger.info("Project saved with id " + project.getId());
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			logger.error(" Employee not found for id :: " + id);
			throw new ResourceNotFoundException("employee " + id + " not found");
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
		logger.info(" Employee with the id: " + id + " was deleted.");
	}

	@Override
	public void assignProject(long id, long projectID) {
		Optional<Employee> optional = this.employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		}
		else {
			logger.error(" Employee not found for id :: " + id);
			throw new ResourceNotFoundException("employee " + id + " not found");
		}
		Optional<Projects> projectsOptional = this.projectsRepository.findById(projectID);
		Projects project = null;
		if(projectsOptional.isPresent()){
			project = projectsOptional.get();
			employee.getProjects().add(project);
			this.employeeRepository.save(employee);
			project.getWorkingEmployees().add(employee);
			this.projectsRepository.save(project);
			logger.info(" The project with ID: " + project.getId() + " was assigned to the employee with the id " + employee.getId());
		}
		else {
			logger.error(" Project not found for id :: " + projectID);
			throw new ResourceNotFoundException("project " + projectID + " not found");
		}
	}

	@Override
	public List<City> getCities(){
		return this.cityRepository.findAll();
	}

	@Override
	public List<Department> getDepartments(){
		return this.departmentRepository.findAll();
	}

	@Override
	public List<Projects> getProjects() {return this.projectsRepository.findAll();}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.employeeRepository.findAll(pageable);
	}
}
