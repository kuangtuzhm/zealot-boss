package com.zealot.boss.base.dao.authorize.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.boss.base.dao.authorize.UserDao;
import com.zealot.boss.base.entity.authorize.User;
import com.zealot.exception.AppException;
import com.zealot.orm.dao.BaseDAO;
import com.zealot.orm.model.Pagination;
import com.zealot.orm.util.Finder;
import com.zealot.util.StringUtil;
import com.zealot.util.ValidateUtil;


@Service("userDao")
public class UserDaoImpl implements UserDao
{

    @Resource  
    private BaseDAO<User> baseDAO;  
  
    public Integer saveUser(User user) throws AppException{  
        return (Integer)baseDAO.save(user);  
    }  
  
    public void updateUser(User user) throws AppException {  
        baseDAO.update(user);  
    }  
  
    public User findUserById(int id) throws AppException {  
        return baseDAO.get(User.class, id);  
    }  
  
    public void deleteUser(User user) throws AppException {  
        baseDAO.delete(user);  
    }  
     
    public User findUserByLoginName(String loginName)  throws AppException{  
        return baseDAO.get(" from User u where u.loginName = ?", new Object[] { loginName });  
    }

    public Pagination<User> queryPage(User user,int pageNo, int pageSize) throws AppException
    {
        Map<String, Object> param = new HashMap<String, Object>();
        StringBuilder hql = new StringBuilder("from User where 1=1");
        if(null != user)
        {
            if (ValidateUtil.isNotEmpty(user.getUname())) {
                hql.append(" and uname like '%")
                        .append(StringUtil.escapeSQL(user.getUname())).append("%'");
            }
            if (ValidateUtil.isNotEmpty(user.getLoginName())) {
                param.put("loginName", user.getLoginName());
                hql.append(" and loginName=:loginName");
            }
            if (ValidateUtil.isNotEmpty(user.getState())) {
                param.put("state", user.getState());
                hql.append(" and state=:state");
            }
        }
        hql.append(" order by uid desc ");
        Finder finder = new Finder(hql.toString());
        finder.setParams(param);
        return baseDAO.queryPage(finder,pageNo,pageSize);
    }  
    
    public Pagination<User> queryRoleUsers(Integer roleId,int pageNo, int pageSize) throws AppException
    {
    	String hql = "select u from User u, UserRole ur where u.uid = ur.uid and ur.roleId=:roleId order by u.uid desc";
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("roleId",roleId);
    	Finder finder = new Finder(hql.toString());
        finder.setParams(param);
        Pagination<User> users = (Pagination<User>) baseDAO.queryPage(finder,pageNo,pageSize);
    	return users;
    }
}
