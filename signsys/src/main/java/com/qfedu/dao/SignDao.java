package com.qfedu.dao;

import java.util.List;
import java.util.Map;

import com.qfedu.entity.Sign;

public interface SignDao {
	
	public List<Sign> findByIndexAndSize(Map<String, Object> info);

	public int count();
	
	public void addSign(Sign sign);
	
	public void updateSign(Sign sign);
	
	public void delete(int id);
	
	public Sign findSignByDate(String date);
	
}