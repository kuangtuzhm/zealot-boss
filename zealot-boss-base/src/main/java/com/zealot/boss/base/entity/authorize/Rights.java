package com.zealot.boss.base.entity.authorize;

import java.io.Serializable;

import com.zealot.model.entity.BaseEntity;

/**
 * 权限功能
 * @version 	1.0 2015-7-2
 * @author		zhm
 * @history	
 *
 */
public class Rights extends BaseEntity implements Serializable 
{
	private static final long serialVersionUID = 1629018043539614522L;
	
	/**权限号**/
	private String rightCode;
	
	private String parentCode;
	
	/**
	 * 所属系统的编号
	 */
	private String sysCode;
	
	/**排列顺序**/
	private Integer iorder;
	/**1:菜单 2:button**/
	private Integer type;
	
	/**
	 * 0.当前页面打开 1 新建页面打开
	 */
	private Integer openStyle;
	
	/**权限描述**/
	private String rightDesc;
	
	/**
	 * 权限对应路径
	 * 
	 */
	private String uri;
	
	/**
	 * 图标
	 */
	private String icon;
	
	/**状态**/
	private Integer state;
	
	/**
	 * 路径
	 */
	private String path;
	
	/**
	 * 根目录访问URL
	 */
	private String baseUrl;
	
	/**
	 * 右菜单是否隐藏 =1是 =0否
	 */
	private Integer isHidden;
	
	public String getRightCode() {
		return rightCode;
	}
	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}
	public String getRightDesc() {
		return rightDesc;
	}
	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public Integer getIorder() {
		return iorder;
	}
	public void setIorder(Integer iorder) {
		this.iorder = iorder;
	}

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Integer getOpenStyle() {
		return openStyle;
	}
	public void setOpenStyle(Integer openStyle) {
		this.openStyle = openStyle;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public Integer getIsHidden() {
		return isHidden;
	}
	public void setIsHidden(Integer isHidden) {
		this.isHidden = isHidden;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	@Override
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append("rightCode : ").append(rightCode);
		buffer.append(" rightDesc : ").append(rightDesc);
		buffer.append(" parentCode : ").append(parentCode);
		buffer.append(" type : ").append(type);
		buffer.append(" uri : ").append(uri);
		return buffer.toString();
	}
	
}
