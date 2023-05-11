package net.andrei.awbd.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name")
	@Pattern(regexp = "[A-Za-z -]*", message = "only letters spaces or -")
	@NotEmpty(message = "cannot be empty")
	private String firstName;
	
	@Column(name = "last_name")
	@Pattern(regexp = "[A-Za-z -]*", message = "only letters spaces or -")
	@NotEmpty(message = "cannot be empty")
	private String lastName;
	
	@Column(name = "email")
	@NotEmpty(message = "cannot be empty")
	@Email(message = "invalid email address")
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	@NotNull(message = "cannot be empty")
	private Department department;

	@OneToMany(mappedBy = "employee")
	private Set<Reviews> reviews;

	@ManyToMany
	private Set<Projects> projects;

	public Set<Reviews> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Reviews> reviews) {
		this.reviews = reviews;
	}

	public Set<Projects> getProjects() {
		return projects;
	}

	public void setProjects(Set<Projects> projects) {
		this.projects = projects;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
