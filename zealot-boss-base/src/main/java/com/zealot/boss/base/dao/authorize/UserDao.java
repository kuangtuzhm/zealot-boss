package com.zealot.boss.base.dao.authorize;

import com.zealot.boss.base.entity.authorize.User;
import com.zealot.exception.AppException;
import com.zealot.orm.model.Pagination;

public interface UserDao
{
    public Integer saveUser(User user) throws AppException;  
    
    public void updateUser(User user) throws AppException;  
      
    public User findUserById(int id) throws AppException;  
      
    public void deleteUser(User user) throws AppException;
    
    public User findUserByLoginName(String username) throws AppException;

    /**
     * 查询用户
     * @param user
     * @param pageNo
     * @param pangeSize
     * @return
     * @throws AppException
     * @作者 赵海明
     * @创建时间 2015-3-4 上午11:37:07
     */
    public Pagination<User> queryPage(User user,int pageNo, int pageSize) throws AppException;
    
    public Pagination<User> queryRoleUsers(Integer roleId,int pageNo, int pageSize) throws AppException;
}
