package com.zealot.boss.base.dao.authorize.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.boss.base.dao.authorize.UserRoleDao;
import com.zealot.boss.base.entity.authorize.UserRole;
import com.zealot.exception.AppException;
import com.zealot.orm.dao.BaseDAO;

@Service("userRoleDao")
public class UserRoleDaoImpl implements UserRoleDao
{

	@Resource(name="baseDAO") 
    private BaseDAO<UserRole> baseDAO;

	public void saveUserRole(UserRole userRole) throws AppException {
		
		baseDAO.save(userRole);
	}

	public void deleteUserRole(UserRole userRole) throws AppException {
		
		String hql = "delete from UserRole where uid= ? and roleId= ?";
    	List<Object> param = new ArrayList<Object>();
        param.add(userRole.getUid());
        param.add(userRole.getRoleId());
        baseDAO.executeHql(hql, param);
	}
	
	public void deleteUserRole(Integer uid) throws AppException {
		
		String hql = "delete from UserRole where uid= ?";
    	List<Object> param = new ArrayList<Object>();
        param.add(uid);
        baseDAO.executeHql(hql, param);
	}
}
