package com.zealot.boss.base.entity;

import java.util.List;

/**
 * 客户表
 * 
 * @author tangl
 * @date 2012-7-13
 * @since 1.0
 */
public class User implements java.io.Serializable {
	private static final long serialVersionUID = -2636778397956119662L;

	/**
	 * 客户编号
	 */
	private Integer uid;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 名字
	 */
	private String uname;
	/**
	 * 登陆密码
	 */
	private String pwd;
	
	/**
     * 修改密码时的旧密码
     */
	private String oldPwd;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
     * 创建时间
     */
    private String updateTime;

	/**
	 * 状态 1 有效状态 0 无效状态
	 */
	private Integer state;

	/** 用户所有角色(组)信息 **/
	private List<Roles> roles;

    public Integer getUid()
    {
        return uid;
    }

    public void setUid(Integer uid)
    {
        this.uid = uid;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getUname()
    {
        return uname;
    }

    public void setUname(String uname)
    {
        this.uname = uname;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

    public List<Roles> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Roles> roles)
    {
        this.roles = roles;
    }

    public String getOldPwd()
    {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd)
    {
        this.oldPwd = oldPwd;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

}
