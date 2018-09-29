package com.qfedu.dao;

import com.qfedu.entity.Student;

public interface StudentDao {

	public Student findStudentByAccount(String account);
	
	public void addStudent(Student stu);
	
	public Student findStudentByStuId(String stuid);
	
	public Student query(int id);
	
	public void update(Student stu);
}