package net.andrei.awbd.services;

import net.andrei.awbd.model.Department;
import net.andrei.awbd.model.Employee;
import net.andrei.awbd.repository.EmployeeRepository;
import net.andrei.awbd.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Test
    public void getEmployeeById(){
        Employee employee = new Employee();
        employee.setFirstName("Nume");
        employee.setLastName("Prenume");
        employee.setId(1);
        employee.setEmail("aaa@bbb.com");
        Department dept = new Department();
        dept.setId(1);
        dept.setName("Departament");
        employee.setDepartment(dept);
        Set<Employee> emps = new HashSet<>();
        emps.add(employee);
        dept.setEmployeeSet(emps);

        when(employeeRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(employee));
        Employee result = employeeService.getEmployeeById(1);
        assertEquals(result,employee);
        verify(employeeRepository,times(1)).findById(Long.valueOf(1));
    }

}
