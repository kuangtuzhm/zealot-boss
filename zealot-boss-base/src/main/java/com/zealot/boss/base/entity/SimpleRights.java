package com.zealot.boss.base.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 权限功能
 * @version 	1.0 2015-7-7
 * @author		zhm
 * @history	
 *
 */
public class SimpleRights implements Serializable 
{
	private static final long serialVersionUID = 1629018043539614522L;
	/**权限号**/
	private String id;
	/**父节点号**/
	private String pid;
	/**权限描述**/
	private String name;
	
	private Integer type;
	
	private Integer indexNum;
	
	private boolean open = true;
	
	private boolean checked = false;
	
	private List<SimpleRights> childList;
	
	
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    

    public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIndexNum()
    {
        return indexNum;
    }


    public void setIndexNum(Integer indexNum)
    {
        this.indexNum = indexNum;
    }


    public boolean isOpen()
    {
        return open;
    }

    public void setOpen(boolean open)
    {
        this.open = open;
    }

    public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<SimpleRights> getChildList() {
		return childList;
	}

	public void setChildList(List<SimpleRights> childList) {
		this.childList = childList;
	}

	@Override
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append("rightCode : ").append(id);
		buffer.append(" rightDesc : ").append(name);
		buffer.append(" parentCode : ").append(pid);
		return buffer.toString();
	}

}
