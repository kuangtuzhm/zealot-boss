package com.zealot.boss.base.entity.authorize;

import java.io.Serializable;

/**
 * 用户角色
 * @version 	1.0 2015-7-2
 * @author		zhm
 * @history	
 *
 */
public class UserRole implements Serializable 
{
	private static final long serialVersionUID = -3040321779770164293L;

	/**用户**/
	private Integer uid;
	/**角色**/
	private Integer roleId;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
