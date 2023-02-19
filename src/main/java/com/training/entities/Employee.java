package com.training.entities;



import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.training.vo.DepartmentVO;
import com.training.vo.EmployeeVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="EMPLOYEES")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Employee {

	@Id
	@Column(name="EMPLOYEE_ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int employeeId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="AGE")
	private int age;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="HIRE_DATE")
	private LocalDate hireDate;
	
	@Column(name="SALARY")
	private Integer salary;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID_FK", referencedColumnName="DEPARTMENT_ID", nullable = false)
	private Department departmentId;
	
	
	
	public Employee(EmployeeVO vo) {
		this.employeeId = vo.getEmployeeId();
		this.firstName = vo.getFirstName();
		this.lastName = vo.getLastName();
		this.gender = vo.getGender();
		this.age = vo.getAge();
		this.email = vo.getEmail();
		this.hireDate = vo.getHireDate();
		this.salary = vo.getSalary();
		this.createdBy = vo.getCreatedBy();
		Department department = new Department(vo.getDepartmentId().getNumber(),vo.getDepartmentId().getName(),vo.getDepartmentId().getCreatorName());
		this.departmentId = department;
	}

	
	
	public EmployeeVO getEmployeeVO() {
		EmployeeVO vo = new EmployeeVO();
		vo.setEmployeeId(employeeId);
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setGender(gender);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhoneNumber(phoneNumber);
		vo.setHireDate(hireDate);
		vo.setSalary(salary);
		
		DepartmentVO departmentVO = new DepartmentVO(this.getDepartmentId());
		vo.setDepartmentId(departmentVO);
		return vo;
	}
	
	
}
