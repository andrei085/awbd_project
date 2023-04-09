package net.andrei.awbd.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "projects")
public class Projects {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @NotNull(message = "cannot be empty")
    private String name;

    @ManyToMany
    private Set<Employee> workingEmployees;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getWorkingEmployees() {
        return workingEmployees;
    }

    public void setWorkingEmployees(Set<Employee> workingEmployees) {
        this.workingEmployees = workingEmployees;
    }
}
