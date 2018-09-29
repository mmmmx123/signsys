package com.qfedu.service;

import com.qfedu.entity.Sign;
import com.qfedu.vo.PageBean;

public interface SignService {

	public PageBean<Sign> findSignByPage(int page);
	
	public void sign();
	
	public void delete(int id);
}
