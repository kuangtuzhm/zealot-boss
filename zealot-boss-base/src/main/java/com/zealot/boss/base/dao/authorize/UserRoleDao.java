package com.zealot.boss.base.dao.authorize;

import com.zealot.boss.base.entity.authorize.UserRole;
import com.zealot.exception.AppException;


public interface UserRoleDao
{
    public void saveUserRole(UserRole userRole) throws AppException;  
      
    public void deleteUserRole(UserRole userRole) throws AppException;
}
