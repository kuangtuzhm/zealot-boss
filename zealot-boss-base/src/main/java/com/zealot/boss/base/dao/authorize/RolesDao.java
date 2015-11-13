package com.zealot.boss.base.dao.authorize;

import java.util.List;

import com.zealot.boss.base.entity.authorize.Roles;
import com.zealot.exception.AppException;
import com.zealot.orm.model.Pagination;

public interface RolesDao
{
    public Integer saveRole(Roles role) throws AppException;
    
    public void updateRole(Roles role)  throws AppException;
    
    public Roles findRoleById(int id) throws AppException;
    
    public List<Roles> findRoleByName(String name) throws AppException;
    
    public Pagination<Roles> queryPage(Roles role,int pageNo, int pageSize) throws AppException;
    
    public List<Roles> getUserRoles(Integer uid) throws AppException;
    
    public List<Roles> getRoles(Integer state) throws AppException;
    
    public boolean isExistByName(String name) throws AppException;
}
