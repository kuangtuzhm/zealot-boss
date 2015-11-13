package com.zealot.boss.base.entity.authorize;

import java.io.Serializable;
import java.util.List;

import com.zealot.model.entity.BaseEntity;

/**
 * 角色(组)
 * @version 	1.0 2012-11-7
 * @author		tangl
 * @history	
 *
 */
public class Roles extends BaseEntity implements Serializable 
{
	private static final long serialVersionUID = -7985316023382235349L;
	
	/**角色号**/
	private Integer id;
	/**角色(组)名**/
	private String name;
	
	/**
	 * 描述
	 */
	private String description;
	
	private Integer root;
	
	/**状态**/
	private Integer state;
    
	
	/**角色(组)下的权限**/
	private List<Rights> rights;
	
	/**
	 * 用户分配角色时标志用户是否具有角色
	 */
	private boolean hasRole = false;

	public boolean isHasRole() {
		return hasRole;
	}
	public void setHasRole(boolean hasRole) {
		this.hasRole = hasRole;
	}
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

    public List<Rights> getRights() {
		return rights;
	}
	public void setRights(List<Rights> rights) {
		this.rights = rights;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append("roleId : ").append(id);
		buffer.append(" roleName : ").append(name);
		buffer.append(" description : ").append(description);
		return buffer.toString();
	}
}
