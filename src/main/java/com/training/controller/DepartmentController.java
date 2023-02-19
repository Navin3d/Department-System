package com.training.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.exceptions.ClientErrorException;
import com.training.service.DepartmentService;
import com.training.vo.DepartmentVO;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	//private static final org.slf4j.Logger log = 
	//	    org.slf4j.LoggerFactory.getLogger(DepartmentController.class);
	
    
    /*@GetMapping("/departments")
    public List<DepartmentVO> getAllDepartments() {
    	List<DepartmentVO> departments = service.getAllDepartments();
    	return departments;
    }*/
    
    @GetMapping("/departments")
    public List<DepartmentVO> getAllDepartments() {
    	log.debug("getAllDepartments begin ");

    	List<DepartmentVO> departments = service.getAllDepartments();
    	
    	log.debug("getAllDepartments end");
    	return departments;
    }
    
    
    
    @GetMapping("/departments/{id}")
    public ResponseEntity<?> getDepartmentById(@Positive @PathVariable("id") int departmentId) {
    	log.info("getDepartmentById begin");
    	DepartmentVO department = null;
  		try {
  			department = service.getDepartmentById(departmentId);
  			return new ResponseEntity<DepartmentVO>(department,HttpStatus.OK);
  		} catch(NoSuchElementException ex) {
  			//log.error("Exception caught at getting the department by Id" + departmentId);
  			
  			log.error("Exception caught at getting the department by Id {} ", departmentId);
  			throw new ClientErrorException("Deparment Not Found For ID : " + departmentId);
  		}
  		
    }
   
    /* @PostMapping("/departments")
    public DepartmentVO createDepartment(@RequestBody DepartmentVO departmentVO) {
    	DepartmentVO department = service.createDepartment(departmentVO);
		return department;
	}
    */
    
    @PostMapping("/departments")
    public ResponseEntity<DepartmentVO> createDepartment(@Valid @RequestBody DepartmentVO departmentVO) {
    	System.out.println("createDepartment begin");
    	DepartmentVO department = service.createDepartment(departmentVO);
    	HttpHeaders responseHeaders = new HttpHeaders();
   	    responseHeaders.set("APPNAME", "DMS");
   	    System.out.println("createDepartment end");
	 	return new ResponseEntity<DepartmentVO>(department,responseHeaders,HttpStatus.CREATED);
	}
    
    
    @PutMapping("/departments/{departmentId}")
    public DepartmentVO updateDepartment(@PathVariable int departmentId,@Valid @RequestBody DepartmentVO departmentDetails) {
    	DepartmentVO department =  service.updateDepartment(departmentId, departmentDetails);
		return department;
	}
    
    @DeleteMapping("/departments/{departmentId}")
    public ResponseEntity<DepartmentVO> deleteDepartment(@PathVariable int departmentId) {
    	DepartmentVO department = service.deleteDepartment(departmentId);
    	return new ResponseEntity<DepartmentVO>(department,HttpStatus.NO_CONTENT);
    }
    
    /*@ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchElementException() {
    	System.out.println("I am from DepartmentController");
    	ErrorResponse error = new ErrorResponse();
    	error.setCode("404");
    	error.setReason("Department Not Found");
    	error.setStatus("404");
    	return error;
    }*/
    
    /*@ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException() {
    	ErrorResponse error = new ErrorResponse();
    	error.setCode("404");
    	error.setReason("Department Not Found");
    	error.setStatus("404");
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
	*/
   
}
