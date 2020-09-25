package com.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bean.StudentBean;

@Service
public interface StudentServices {

	
	public int addStudent(StudentBean studentBean);
	public List<StudentBean> viewStudent();
	public int updateStudent(int id,StudentBean studentBean);
	public int deleteStudent(int id);
	
	
	
	
}
