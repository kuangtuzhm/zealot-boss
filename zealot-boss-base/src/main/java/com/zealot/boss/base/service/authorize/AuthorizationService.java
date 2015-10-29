package com.zealot.boss.base.service.authorize;

import com.zealot.exception.AppException;


/**
 * 权限接口
 * 
 * @version 1.0 2012-11-07
 * @author ltang
 * @history
 */
public interface AuthorizationService 
{
	/**
	 * 判断用户是否具有某功能的权限
	 * @param roles 用户角色
	 * @param rightCode 权限信息
	 * @return true/false
	 */
	//public boolean hasRight(Integer [] roles, String rightCode) throws AppException;
	public boolean hasRight(Integer uid, String rightCode) throws AppException;
	
	/**
	 * 判断用户是否具有某功能的权限
	 * @param roles 用户角色
	 * @param uri uri信息
	 * @return true/false
	 */
	//public boolean hasUriRight(Integer [] roles, String uri) throws AppException;
	public boolean hasUriRight(Integer uid, String uri) throws AppException;
	
	/**
	 * 清空权限信息
	 */
	public void reInit();
}
