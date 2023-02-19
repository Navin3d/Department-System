package com.training.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.training.vo.DepartmentVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="DEPARTMENTS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	@Id
	@Column(name="DEPARTMENT_ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int departmentId;
	
	@Column(name="DEPARTMENT_NAME")
	private String departmentName;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	public Department(DepartmentVO vo) {
		this.departmentId = vo.getNumber();
		this.departmentName = vo.getName();
		this.createdBy = vo.getCreatorName();
	}
	
	public DepartmentVO getDepartmentVO() {
		DepartmentVO vo = new DepartmentVO();
		vo.setNumber(this.departmentId);
		vo.setName(this.departmentName);
		vo.setCreatorName(this.createdBy);
		return vo;
	}



	
}
	
	

