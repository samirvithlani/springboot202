package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.StudentBean;
import com.services.StudentServices;

@RestController
@RequestMapping("student")
public class StudentController {

	@Autowired
	StudentServices studentServices;

	@PostMapping(value = "/addstudent")
	public String addStudent(StudentBean studentBean) {

		int status = studentServices.addStudent(studentBean);
		if (status > 0) {

			return "data added";
		}

		return "data not added";

	}

	@PostMapping(value = "/addstudent1")
	public ResponseEntity addStudent1(StudentBean studentBean) {

		int status = studentServices.addStudent(studentBean);
		System.out.println(status);

		if (status > 0) {

			return new ResponseEntity<>("Data added..", HttpStatus.CREATED);
		}

		return new ResponseEntity<>("data not added..", HttpStatus.PAYMENT_REQUIRED);

	}
	
	

}
