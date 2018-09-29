package com.qfedu.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.qfedu.dao.StudentDao;
import com.qfedu.entity.Student;

public class MyRealm extends AuthorizingRealm {
	
	@Autowired
	private StudentDao sDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String account = (String) token.getPrincipal();
		Student stu = sDao.findStudentByAccount(account);
		
		if (stu == null) {
			throw new UnknownAccountException();
		}
		
		String password = stu.getPassword();
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account, password, this.getName());
		
		return info;
	}

}
