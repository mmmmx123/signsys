package com.qfedu.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.entity.Student;
import com.qfedu.service.StudentService;
import com.qfedu.vo.JsonBean;

@Controller
public class StudentController {

	@Autowired
	private StudentService sSer;
	
	@RequestMapping("/studentlogin")
	@ResponseBody
	public JsonBean login(String account, String password) {
		JsonBean bean = new JsonBean();
		/*SimpleHash md5 = new SimpleHash("MD5", password, null, 1);*/
		UsernamePasswordToken token = new UsernamePasswordToken(account, password);
		Subject subject = SecurityUtils.getSubject();
		
		try {
			subject.login(token);
			bean.setCode(1);
			bean.setMsg(null);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public JsonBean register(Student stu) {
		JsonBean bean = new JsonBean();
		
		try {
			sSer.addStudent(stu);
			bean.setCode(1);
			bean.setMsg(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
	
	@RequestMapping("/forget")
	@ResponseBody
	public JsonBean forget(String stuid) {
		JsonBean bean = new JsonBean();
		String password = null;
		
		try {
			password = sSer.findMyPassword(stuid);
			bean.setCode(1);
			bean.setMsg(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public JsonBean query(String id) {
		JsonBean bean = new JsonBean();
		
		try {
			Student stu = sSer.findStuById(Integer.parseInt(id));
			bean.setCode(1);
			bean.setMsg(stu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public JsonBean update(Student stu) {
		JsonBean bean = new JsonBean();
		
		try {
			sSer.update(stu);
			bean.setCode(1);
			bean.setMsg(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
}
