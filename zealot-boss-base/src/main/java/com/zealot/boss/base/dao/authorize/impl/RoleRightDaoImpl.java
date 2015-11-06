package com.zealot.boss.base.dao.authorize.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.boss.base.dao.authorize.RoleRightDao;
import com.zealot.boss.base.entity.authorize.RoleRight;
import com.zealot.exception.AppException;
import com.zealot.orm.dao.BaseDAO;

@Service("roleRightDao")
public class RoleRightDaoImpl implements RoleRightDao {
	
	@Resource(name="baseDAO") 
    private BaseDAO<RoleRight> baseDAO;

	public void saveRoleRight(RoleRight roleRight) throws AppException {
		 baseDAO.save(roleRight);
	}

	public void deleteByRight(String rightCode) throws AppException {
		
		String hql = "delete from RoleRight where rightCode = ?";
    	List<Object> param = new ArrayList<Object>();
        param.add(rightCode);
        baseDAO.executeHql(hql, param);
	}

	public void deleteByRole(Integer roleId) throws AppException {
		String hql = "delete from RoleRight where roleId = ?";
    	List<Object> param = new ArrayList<Object>();
        param.add(roleId);
        baseDAO.executeHql(hql, param);
	}
}
