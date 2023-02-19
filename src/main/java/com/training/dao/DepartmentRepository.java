package com.training.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	
}
