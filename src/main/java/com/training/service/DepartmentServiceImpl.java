package com.training.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.dao.DepartmentRepository;
import com.training.entities.Department;
import com.training.vo.DepartmentVO;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public List<DepartmentVO> getAllDepartments() {
		List<Department> departments = departmentRepository.findAll();
    	List<DepartmentVO> departmentVOList = departments.stream().map(DepartmentVO::new).collect(Collectors.toList());
    	return departmentVOList;
	}

	@Override
	public DepartmentVO createDepartment(DepartmentVO departmentVO) {
		Department department = departmentRepository.save(departmentVO.getDepartment());
		return department.getDepartmentVO();
	}

	@Override
	public DepartmentVO getDepartmentById(int departmentId) {
		Optional<Department> department = departmentRepository.findById(departmentId);
		return department.get().getDepartmentVO();
	}

	@Override
	public DepartmentVO updateDepartment(int departmentId,DepartmentVO departmentDetails) {
		Optional<Department> department = departmentRepository.findById(departmentId);
		Department modifiedDepartment = null;
		if(department.get() != null) {
	    	modifiedDepartment = department.get();
	    	modifiedDepartment.setDepartmentName(departmentDetails.getName());
	    	modifiedDepartment.setCreatedBy(departmentDetails.getCreatorName());
	    	modifiedDepartment = departmentRepository.save(modifiedDepartment);
	    }
		return modifiedDepartment.getDepartmentVO();
	}

	
	@Override
	public DepartmentVO deleteDepartment(int departmentId) {
		Optional<Department> department = departmentRepository.findById(departmentId);
		if(department.get() != null) {
			 departmentRepository.delete(department.get());
	    }
		return department.get().getDepartmentVO();
	}
	
	
}
