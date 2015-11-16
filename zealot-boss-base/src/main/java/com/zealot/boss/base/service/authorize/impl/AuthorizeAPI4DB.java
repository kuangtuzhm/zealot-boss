package com.zealot.boss.base.service.authorize.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.boss.base.service.authorize.AuthorizeAPI;
import com.zealot.exception.AppException;
import com.zealot.orm.dao.BaseDAO;

@Service("authorizeAPI4DB")
public class AuthorizeAPI4DB implements AuthorizeAPI {

	@Resource(name="baseDAO")
    private BaseDAO<Integer> baseDAO;
	
	private static Map<Integer,List<Integer>> userRoleMap = new HashMap<Integer,List<Integer>>();
	
	private static Map<String,List<Integer>> rightRoleMap = new HashMap<String,List<Integer>>();
	
	@Override
	public List<Integer> getUserRoles(Integer uid) throws AppException {
		List<Integer> list = userRoleMap.get(uid);
		if(list==null || list.isEmpty())
		{
			synchronized(this)
			{
				String hql = "select ur.roleId from UserRole as ur,Roles as r where ur.roleId = r.id and ur.uid = ? and r.state = 1";
		    	List<Object> param = new ArrayList<Object>();
		        param.add(uid);
		        list = baseDAO.find(hql, param);
		        userRoleMap.put(uid, list);
			}
		}
		return userRoleMap.get(uid);
	}

	@Override
	public List<Integer> getRightRoles(String rightCode) throws AppException {
		
		List<Integer> list = rightRoleMap.get(rightCode);
		if(list==null ||list.isEmpty())
		{
			synchronized(this)
			{
				String hql = "select roleId from RoleRight where rightCode = ?";
		    	List<Object> param = new ArrayList<Object>();
		        param.add(rightCode);
		        list = baseDAO.find(hql, param);
		        rightRoleMap.put(rightCode, list);
			}
		}
		return rightRoleMap.get(rightCode);
	}

	public void clearUserRoles(Integer uid)
	{
		userRoleMap.remove(uid);
	}
	
	public void clearRightRoles(String rightCode)
	{
		rightRoleMap.remove(rightCode);
	}

	@Override
	public void clearRightRoles() {
		rightRoleMap.clear();
	}
}
