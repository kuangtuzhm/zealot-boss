package com.zealot.boss.base.entity.authorize;

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
	private String rightCode;
	/**父节点号**/
	private String parentCode;
	
	/**
	 * 系统代码
	 */
	private String sysCode;
	
	/**权限描述**/
	private String rightDesc;
	
	private Integer type;
	
	private Integer iorder;
	
	private boolean checked;

	private List<SimpleRights> childList;
	
	public String getRightCode() {
		return rightCode;
	}


	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}


	public String getParentCode() {
		return parentCode;
	}


	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}


	public String getSysCode() {
		return sysCode;
	}


	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}


	public String getRightDesc() {
		return rightDesc;
	}


	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIorder() {
		return iorder;
	}


	public void setIorder(Integer iorder) {
		this.iorder = iorder;
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
		buffer.append("rightCode : ").append(rightCode);
		buffer.append(" rightDesc : ").append(rightDesc);
		buffer.append(" parentCode : ").append(parentCode);
		return buffer.toString();
	}

}
