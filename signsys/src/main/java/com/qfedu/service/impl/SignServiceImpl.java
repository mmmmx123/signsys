package com.qfedu.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfedu.dao.SignDao;
import com.qfedu.dao.StudentDao;
import com.qfedu.entity.Sign;
import com.qfedu.entity.Student;
import com.qfedu.service.SignService;
import com.qfedu.vo.PageBean;

@Service
public class SignServiceImpl implements SignService {

	@Autowired
	private SignDao sDao;
	
	@Autowired
	private StudentDao stuDao;
	
	@Override
	public PageBean<Sign> findSignByPage(int page) {
		// TODO Auto-generated method stub
		PageBean<Sign> pageInfo = new PageBean<>();

		pageInfo.setCurrentPage(page);

		// 获取表中的记录总数
		int count = sDao.count();
		// 计算总页数
		if (count % pageInfo.getPageSize() == 0) {
			pageInfo.setTotalPage(count / pageInfo.getPageSize());
		} else {
			pageInfo.setTotalPage(count / pageInfo.getPageSize() + 1);
		}
		// 根据页码计算分页查询时的开始索引
		int index = (page - 1) * pageInfo.getPageSize();
		Map<String, Object> map = new HashMap<>();
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		List<Sign> list = sDao.findByIndexAndSize(map);
		pageInfo.setPageInfos(list);

		return pageInfo;
	}

	@Override
	public void sign() {
		// TODO Auto-generated method stub
		String account = (String) SecurityUtils.getSubject().getPrincipal();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-hh");
		Student stu = stuDao.findStudentByAccount(account);
		String name = stu.getRealname();
		
		Calendar now = Calendar.getInstance();
		
		int i = 0;
		if (i < 2) {
			Sign sign = new Sign();
			sign.setName(name);
			if (now.get(Calendar.HOUR_OF_DAY) >= 8 && now.get(Calendar.HOUR_OF_DAY) <= 9 ) {
				sign.setMorningsigntime(new Date());
				sign.setMorningsign("正常到校签到");
				sign.setEveningsigntime(null);
				sign.setEveningsign(null);
				sDao.addSign(sign);
				i++;
			}
			
			if (now.get(Calendar.HOUR_OF_DAY) >= 21 && now.get(Calendar.HOUR_OF_DAY) <= 22 ) {
				Sign sign1 = sDao.findSignByDate(sdf.format(new Date()));
				sign1.setEveningsigntime(new Date());
				sign1.setEveningsign("正常离校签到");
				sDao.updateSign(sign1);
				i++;
			}
			
			if (now.get(Calendar.HOUR_OF_DAY) > 9 && now.get(Calendar.HOUR_OF_DAY) <=10) {
				sign.setMorningsigntime(new Date());
				sign.setMorningsign("到校迟到");
				sign.setEveningsigntime(null);
				sign.setEveningsign(null);
				sDao.addSign(sign);
				i++;
			}
			
			if (now.get(Calendar.HOUR_OF_DAY) >= 20 && now.get(Calendar.HOUR_OF_DAY) < 21) {
				Sign sign1 = sDao.findSignByDate(sdf.format(new Date()));
				sign1.setEveningsigntime(new Date());
				sign1.setEveningsign("早退");
				sDao.updateSign(sign1);
				i++;
			}
			
			if (now.get(Calendar.HOUR_OF_DAY) > 0 && now.get(Calendar.HOUR_OF_DAY) < 8 ||
				now.get(Calendar.HOUR_OF_DAY) > 10 && now.get(Calendar.HOUR_OF_DAY) < 20 ||
				now.get(Calendar.HOUR_OF_DAY) > 22 && now.get(Calendar.HOUR_OF_DAY) < 24
					) {
				throw new RuntimeException("还不到签到时间，无法签到");
			}
			
		} else {
			
			throw new RuntimeException("一天只能签两次到");
		}
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		try {
			sDao.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
