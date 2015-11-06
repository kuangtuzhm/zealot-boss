package com.zealot.boss.base.dao.authorize.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.boss.base.dao.authorize.RolesDao;
import com.zealot.boss.base.entity.authorize.Roles;
import com.zealot.exception.AppException;
import com.zealot.orm.dao.BaseDAO;
import com.zealot.orm.model.Pagination;
import com.zealot.orm.util.Finder;
import com.zealot.util.ValidateUtil;

@Service("roleDao")
public class RolesDaoImpl implements RolesDao
{

	@Resource(name="baseDAO")
    private BaseDAO<Roles> baseDAO;
    
    public Integer saveRole(Roles role) throws AppException
    {
        return (Integer)baseDAO.save(role);
    }

    public void updateRole(Roles role) throws AppException
    {
        baseDAO.update(role);
    }

    public Roles findRoleById(int id) throws AppException
    {
        return baseDAO.get(Roles.class,id);
    }

    public Roles findRoleByName(String name) throws AppException
    {
        String hql = " from Roles where name= ?";
        return baseDAO.get(hql,new Object[] { name });
    }

    public Pagination<Roles> queryPage(Roles role, int pageNo, int pageSize) throws AppException
    {
        Map<String, Object> param = new HashMap<String, Object>();
        StringBuilder hql = new StringBuilder("from Roles where 1=1");
        if(null != role)
        {
            if(ValidateUtil.isNotEmpty(role.getName()))
            {
                hql.append(" and name like '%").append(role.getName()).append("%'");
            }
            if(ValidateUtil.isNotEmpty(role.getState()))
            {
                param.put("state", role.getState());
                hql.append(" and state=:state");
            }
        }
        hql.append(" order by id desc ");
        Finder finder = new Finder(hql.toString());
        finder.setParams(param);
        return baseDAO.queryPage(finder,pageNo,pageSize);
    }

    public List<Roles> getUserRoles(Integer uid) throws AppException {
		String hql = "select r from UserRole as ur,Roles as r where ur.roleId = r.id and ur.uid = ? and r.state = 1";
    	List<Object> param = new ArrayList<Object>();
        param.add(uid);
        List<Roles> list = baseDAO.find(hql, param);
        return list;
	}
    
    public List<Roles> getRoles(Integer state) throws AppException {
		String hql = "from Roles where state = ?";
    	List<Object> param = new ArrayList<Object>();
        param.add(state);
        List<Roles> list = baseDAO.find(hql, param);
        return list;
	}
}
