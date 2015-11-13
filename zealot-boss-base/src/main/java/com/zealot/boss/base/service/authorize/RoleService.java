package com.zealot.boss.base.service.authorize;

import java.util.List;

import com.zealot.boss.base.entity.authorize.Roles;
import com.zealot.exception.AppException;
import com.zealot.exception.ResultException;
import com.zealot.orm.model.Pagination;


public interface RoleService
{

    public Integer saveRole(Roles role,List<String> list) throws AppException,ResultException;
    
    public void updateRole(Roles role,List<String> list) throws AppException,ResultException;
    
    public void updateRoleState(Roles role) throws AppException,ResultException;
    
    public boolean validRoleAdd(Roles role) throws AppException,ResultException;
    
    public Roles findRoleById(int id) throws AppException;
    
    public Pagination<Roles> queryPage(Roles role,int pageNo, int pageSize) throws AppException;
    
    public List<Roles> getUserRoles(Integer uid) throws AppException;
    
    public List<Roles> getRoles(Integer state) throws AppException;
    
    public void addUserRole(Integer userId, Integer [] roleIds) throws AppException;
    
    public boolean isUniqueByName(String oldName, String newName) throws AppException;
    
    public void deleteUserRole(Integer userId, List<Integer> roleIds)
			throws AppException;
}
