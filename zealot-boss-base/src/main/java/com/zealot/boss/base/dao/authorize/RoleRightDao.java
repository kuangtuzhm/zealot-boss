package com.zealot.boss.base.dao.authorize;

import com.zealot.boss.base.entity.authorize.RoleRight;
import com.zealot.exception.AppException;

public interface RoleRightDao
{
    public void saveRoleRight(RoleRight roleRight) throws AppException;
    
    public void deleteByRight(String rightCode)  throws AppException;
    
    public void deleteByRole(Integer roleId) throws AppException;
}
