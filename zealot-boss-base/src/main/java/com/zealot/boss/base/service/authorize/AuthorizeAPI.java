package com.zealot.boss.base.service.authorize;

import java.util.List;

import com.zealot.exception.AppException;

/**
 * 获取用户权限验证的信息数据，可以有不同实现，比如查数据库，查缓存等
 * @author Administrator
 *
 */
public interface AuthorizeAPI {

	/**
	 * 获取用户角色的编号
	 * @param uid
	 * @return
	 * @throws AppException
	 */
	public List<Integer> getUserRoles(Integer uid) throws AppException;
	
	/**
	 * 获得某个right所对应可以有访问权限的角色编号
	 * @param rightCode
	 * @return
	 * @throws AppException
	 */
	public List<Integer> getRightRoles(String rightCode) throws AppException;
	
	/**
	 * 用户角色授权变更后改变缓存
	 * @param uid
	 */
	public void clearUserRoles(Integer uid);
	
	/**
	 * 角色权限变更后改变缓存
	 * @param rightCode
	 */
	public void clearRightRoles(String rightCode);
}
