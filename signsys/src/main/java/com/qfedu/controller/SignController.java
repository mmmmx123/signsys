package com.qfedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.entity.Sign;
import com.qfedu.service.SignService;
import com.qfedu.vo.JsonBean;
import com.qfedu.vo.PageBean;

@Controller
public class SignController {

	@Autowired
	private SignService sSer;
	
	@RequestMapping(value="/sign/page/{page}", method=RequestMethod.GET)
	@ResponseBody
	public JsonBean findAllSign(@PathVariable("page") int page) {
		JsonBean bean = new JsonBean();
		PageBean<Sign> pageInfo = null;
		try {
			pageInfo = sSer.findSignByPage(page);
			bean.setCode(1);
			bean.setMsg(pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
	
	@RequestMapping("/sign")
	@ResponseBody
	public JsonBean sign() {
		JsonBean bean = new JsonBean();
		
		try {
			sSer.sign();
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
	
	@RequestMapping("/delete")
	@ResponseBody
	public JsonBean delete(int id) {
		JsonBean bean = new JsonBean();
		
		try {
			sSer.delete(id);
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
