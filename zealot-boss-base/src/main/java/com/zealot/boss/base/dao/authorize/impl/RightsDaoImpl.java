package com.zealot.boss.base.dao.authorize.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.boss.base.dao.authorize.RightsDao;
import com.zealot.boss.base.entity.authorize.Rights;
import com.zealot.boss.base.entity.authorize.SimpleRights;
import com.zealot.exception.AppException;
import com.zealot.orm.dao.BaseDAO;
import com.zealot.util.ValidateUtil;

@Service("rightDao")
public class RightsDaoImpl implements RightsDao
{

    @Resource  
    private BaseDAO<Rights> baseDAO;
    
    @Resource(name="baseDAO")  
    private BaseDAO<SimpleRights> simpleBaseDAO;
    
    public Rights getRightByCode(String rightCode) throws AppException
    {
    	String hql = "from Rights where rightCode= ?";
    	List<Object> param = new ArrayList<Object>();
        param.add(rightCode);
        Rights rights = baseDAO.get(hql, param);
        return rights;
    }
    
    public Integer saveRight(Rights right) throws AppException
    {
        return (Integer)baseDAO.save(right);
    }

    public void updateRight(Rights right) throws AppException
    {
        baseDAO.update(right);
    }
    
    public void deleteRight(String rightCode) throws AppException {

    	String hql = "update Rights set state=0 where rightCode= ?";
    	List<Object> param = new ArrayList<Object>();
        param.add(rightCode);
        baseDAO.executeHql(hql, param);
	}

	public List<Rights> queryRightList(Rights right) throws AppException
    {
        // TODO Auto-generated method stub
        return null;
    }
	
	public List<Rights> queryRightByRole(Integer roleId) throws AppException
	{
		String hql = "select r from RoleRight rr, Rights r where rr.rightCode = r.rightCode"
				+ " and r.state=1 and rr.roleId=?";
    	List<Object> param = new ArrayList<Object>();
    	param.add(roleId);
    	List<Rights> rights = (List<Rights>) baseDAO.find(hql, param);
    	return rights;
	}

    public List<SimpleRights> querySimpleRightList(Integer state) throws AppException
    {
    	String hql = "from SimpleRights where 1=1";
    	List<Object> param = new ArrayList<Object>();
        if (ValidateUtil.isNotEmpty(state)) {
            hql += " and  state=?";
            param.add(state);
        }
        hql += " order by pid,index_num";
        List<SimpleRights> rights = (List<SimpleRights>) simpleBaseDAO.find(hql, param);
        if (rights == null) {
            rights = new ArrayList<SimpleRights>();
        }
        return rights;
    }

	public List<Rights> queryChilderenRight(String parentCode) throws AppException{
		String hql = "from Rights where parentCode = ?";
    	List<Object> param = new ArrayList<Object>();
    	param.add(parentCode);
        List<Rights> rights = (List<Rights>) baseDAO.find(hql, param);
        if (rights == null) {
            rights = new ArrayList<Rights>();
        }
        return rights;
	}

}
