package com.zealot.boss.base.entity.authorize;

import java.io.Serializable;
import java.util.List;

/**
 * 角色(组)
 * @version 	1.0 2012-11-7
 * @author		tangl
 * @history	
 *
 */
public class Roles implements Serializable 
{
	private static final long serialVersionUID = -7985316023382235349L;
	
	/**角色号**/
	private Integer id;
	/**角色(组)名**/
	private String name;
	
	private Integer root;
	
	/**状态**/
	private Integer state;
	/**创建时间**/
	private String createTime;
	
	/**修改时间**/
    private String updateTime;
	
	/**角色(组)下的权限**/
	private List<Rights> rights;
	
	
	
	public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Integer getRoot() {
		return root;
	}
	public void setRoot(Integer root) {
		this.root = root;
	}
	public Integer getState()
    {
        return state;
    }
    public void setState(Integer state)
    {
        this.state = state;
    }
    public String getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    public String getUpdateTime()
    {
        return updateTime;
    }
    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }
    public List<Rights> getRights() {
		return rights;
	}
	public void setRights(List<Rights> rights) {
		this.rights = rights;
	}
	
	@Override
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append("roleId : ").append(id);
		buffer.append(" roleName : ").append(name);
		return buffer.toString();
	}
}
