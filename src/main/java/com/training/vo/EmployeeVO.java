package com.training.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.training.entities.Department;
import com.training.entities.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeVO {

	private int employeeId;
	
	private String firstName;
	
	private String lastName;
	
	private String gender;
	
	private int age;
	
	private String email;
	
	private String phoneNumber;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate hireDate;
	
	private Integer salary;
	
	private String createdBy;
	
	private DepartmentVO departmentId;
	
	public EmployeeVO(Employee employee) {
		this.employeeId = employee.getEmployeeId();
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.gender = employee.getGender();
		this.age = employee.getAge();
		this.email = employee.getEmail();
		this.hireDate = employee.getHireDate();
		this.salary = employee.getSalary();
		this.createdBy = employee.getCreatedBy();
		DepartmentVO departmentVO = new DepartmentVO(employee.getDepartmentId().getDepartmentId(),employee.getDepartmentId().getDepartmentName(),employee.getDepartmentId().getCreatedBy());
		this.departmentId = departmentVO;
	}
	
	@JsonIgnore
	public Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setGender(gender);
		employee.setAge(age);
		employee.setEmail(email);
		employee.setPhoneNumber(phoneNumber);
		employee.setHireDate(hireDate);
		employee.setSalary(salary);
		
		Department department = new Department(this.getDepartmentId().getNumber(),this.getDepartmentId().getName(),this.getDepartmentId().getCreatorName());
		employee.setDepartmentId(department);
		return employee;
	}
	
	
	
}
