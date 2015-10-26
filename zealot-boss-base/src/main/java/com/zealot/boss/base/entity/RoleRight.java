package com.zealot.boss.base.entity;

import java.io.Serializable;

/**
 * 角色权限
 * @version 	1.0 2012-11-7
 * @author		赵海明
 * @history	
 *
 */
public class RoleRight implements Serializable 
{
	private static final long serialVersionUID = 7442859656299220827L;
	/**角色号**/
	private Integer roleId;
	/**权限号**/
	private String rightCode;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRightCode() {
		return rightCode;
	}
	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}
}
