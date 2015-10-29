package com.zealot.boss.base.dao.authorize;

import java.util.List;

import com.zealot.boss.base.entity.authorize.Rights;
import com.zealot.boss.base.entity.authorize.SimpleRights;
import com.zealot.exception.AppException;

public interface RightsDao
{
	public Rights getRightByCode(String rightCode) throws AppException;
	
    public Integer saveRight(Rights right) throws AppException;
    
    public void updateRight(Rights right) throws AppException;
    
    public void deleteRight(String rightCode) throws AppException;
    
    public List<Rights> queryRightList(Rights right) throws AppException;
    
    public List<SimpleRights> querySimpleRightList(Integer state) throws AppException;
    
    public List<Rights> queryChilderenRight(String parentCode) throws AppException;
    
    public List<Rights> queryRightByRole(Integer roleId) throws AppException;
}
