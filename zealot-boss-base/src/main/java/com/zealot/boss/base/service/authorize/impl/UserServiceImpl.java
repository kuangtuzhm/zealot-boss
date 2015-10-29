package com.zealot.boss.base.service.authorize.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.boss.base.dao.authorize.UserDao;
import com.zealot.boss.base.entity.authorize.User;
import com.zealot.boss.base.service.authorize.UserService;
import com.zealot.exception.AppException;
import com.zealot.exception.ResultException;
import com.zealot.exception.message.MessageCode;
import com.zealot.orm.model.Pagination;
import com.zealot.util.MD5;

@Service("userService")
public class UserServiceImpl implements UserService
{

    @Resource(name="userDao")
    private UserDao userDao;
    
    public User login(User user) throws AppException,ResultException
    {
        String pwd = MD5.convert32(user.getPwd());
        User u = userDao.findUserByLoginName(user.getLoginName());
        if(u==null || !pwd.equalsIgnoreCase(u.getPwd()))
        {
            throw new ResultException(MessageCode.USER_LOGIN_ERR);
        }
        if(u.getState() == 0)
        {
        	throw new ResultException(MessageCode.USER_STOPED);
        }
        return u;
    }
    
    public boolean userExits(User user) throws AppException
    {
        User u = userDao.findUserByLoginName(user.getLoginName());
        if(null == u)
        {
            return false;
        }
        return true;
    }

    public boolean validUserAdd(User user) throws AppException, ResultException
    {
        if(userExits(user))
        {
            throw new ResultException(MessageCode.USER_LOGINNAME_EXITS);
        }
        return true;
    }

    public Integer addUser(User user) throws AppException
    {
        user.setPwd(MD5.convert32(user.getPwd()));
        return userDao.saveUser(user);
    }

    public void updateUser(User user) throws AppException
    {
        User u = findUserByUid(user.getUid());
        if(u!=null)
        {
            u.setUname(user.getUname());
            u.setUpdateTime(user.getUpdateTime());
            
            userDao.updateUser(u);
        }
    }
    
    public void updateUserState(User user) throws AppException
    {
        User u = findUserByUid(user.getUid());
        if(u!=null)
        {
            u.setState(user.getState());
            u.setUpdateTime(user.getUpdateTime());
            userDao.updateUser(u);
        }
    }

    public User findUserByUid(int uid) throws AppException
    {
        return userDao.findUserById(uid);
    }

    public void modifyUserPassWord(User user) throws AppException, ResultException
    {
        String pwd = MD5.convert32(user.getOldPwd());
        User u = findUserByUid(user.getUid());
        if(u==null)
        {
            throw new ResultException(MessageCode.USER_NOT_EXITS);
        }
        if(!pwd.equalsIgnoreCase(u.getPwd()))
        {
            throw new ResultException(MessageCode.USER_OLDPWD_ERROR);
        }
        u.setPwd(MD5.convert32(user.getPwd()));
        userDao.updateUser(u);
    }

    public Pagination<User> queryUserList(User user, int pageNo, int pageSize) throws AppException
    {
        return userDao.queryPage(user,pageNo,pageSize);
    }
    
    public Pagination<User> queryRoleUsers(Integer roleId,int pageNo, int pageSize) throws AppException
    {
    	return userDao.queryRoleUsers(roleId,pageNo,pageSize);
    }
}
