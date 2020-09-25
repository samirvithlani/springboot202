package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.StudentBean;
import com.services.StudentServices;

@Repository
public class StudentDao implements StudentServices {

	@Autowired
	JdbcTemplate JdbcTemplate;

	@Override
	public int addStudent(StudentBean studentBean) {

		return JdbcTemplate.update("insert into student(sname,semail,spassword,sage)values(?,?,?,?)",
				studentBean.getsName(), studentBean.getsEmail(), studentBean.getsPassword(), studentBean.getsAge());
	}

	@Override
	public List<StudentBean> viewStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateStudent(int id, StudentBean studentBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteStudent(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
