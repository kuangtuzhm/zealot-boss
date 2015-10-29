package com.zealot.boss.base.service.authorize;

import java.util.List;

import com.zealot.boss.base.entity.authorize.Rights;
import com.zealot.boss.base.entity.authorize.SimpleRights;
import com.zealot.exception.AppException;
import com.zealot.exception.ResultException;


public interface RightsService
{
	public Rights getRightByCode(String rightCode) throws AppException;
	
	public Integer saveRight(Rights right) throws AppException,ResultException;
    
    public void updateRight(Rights right) throws AppException;
    
    public void deleteRights(String rightCode) throws AppException;
    
    public List<Rights> queryRightList(Rights right) throws AppException;
    
    public List<SimpleRights> querySimpleRightList(Integer state) throws AppException;
    
    public List<Rights> queryRightByRole(Integer roleId) throws AppException;
    
    public void setRoleRights(List<SimpleRights> allRights,List<Rights> roleRights );
    
    public List<SimpleRights> groupRights(List<SimpleRights> allRights);
}
