package com.qfedu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfedu.dao.StudentDao;
import com.qfedu.entity.Student;
import com.qfedu.service.StudentService;
import com.qfedu.vo.PageBean;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao sDao;
	
	@Override
	public void addStudent(Student stu) {
		// TODO Auto-generated method stub
		if (stu.getAccount() == null || stu.getAccount().trim() == "") {
			throw new RuntimeException("账号不能为空");
		}
		
		if (stu.getPassword() == null || stu.getPassword().trim() == "") {
			throw new RuntimeException("密码不能为空");
		}
		
		if (stu.getRealname() == null || stu.getRealname().trim() == "") {
			throw new RuntimeException("真实姓名不能为空");
		}
		
		if (stu.getStuid() == null || stu.getStuid().trim() == "") {
			throw new RuntimeException("账号不能为空");
		}
		
		Student stu1 = sDao.findStudentByAccount(stu.getAccount());
		
		if (stu1 != null) {
			throw new RuntimeException("存在相同用户名");
		}
		
		Student stu2 = sDao.findStudentByStuId(stu.getStuid());
		
		if (stu2 != null) {
			throw new RuntimeException("学生id不能重复");
		}
		
		/*SimpleHash md5 = new SimpleHash("md5", stu.getPassword(), null, 1);*/
		
		try {
			/*stu.setPassword(md5.toString().trim());*/
			sDao.addStudent(stu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public String findMyPassword(String stuid) {
		// TODO Auto-generated method stub
		if (stuid == null) {
			throw new RuntimeException("学生ID不能为空");
		}
		
		Student stu = sDao.findStudentByStuId(stuid);
		
		if (stu == null) {
			throw new RuntimeException("请输入正确的学生ID");
		}
		
		String password = null;
		
		try {
			password = stu.getPassword();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return password;
	}

	@Override
	public Student findStuById(int id) {
		// TODO Auto-generated method stub
		Student stu = sDao.query(id);
		return stu;
	}

	@Override
	public void update(Student stu) {
		// TODO Auto-generated method stub
		
		try {
			sDao.update(stu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
