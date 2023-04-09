package net.andrei.awbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.andrei.awbd.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
