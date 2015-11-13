package com.zealot.boss.base.dao.authorize;

import com.zealot.boss.base.entity.authorize.UserRole;
import com.zealot.exception.AppException;


public interface UserRoleDao
{
    public void saveUserRole(UserRole userRole) throws AppException;  
      
    public void deleteUserRole(UserRole userRole) throws AppException;
    
    /**
     * 根据用户删除用户所有权限
     * @param uid
     * @throws AppException
     */
    public void deleteUserRole(Integer uid) throws AppException;
}
