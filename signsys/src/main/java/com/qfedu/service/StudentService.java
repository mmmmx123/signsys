package com.qfedu.service;

import com.qfedu.entity.Student;

public interface StudentService {

	public void addStudent(Student stu);
	
	public String findMyPassword(String stuid);
	
	public Student findStuById(int id);
	
	public void update(Student stu);
	
}
