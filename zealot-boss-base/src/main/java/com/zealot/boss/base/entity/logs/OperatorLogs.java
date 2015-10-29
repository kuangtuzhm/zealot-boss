package com.zealot.boss.base.entity.logs;

import java.io.Serializable;

/**
 * @desc 操作日志
 * @author zhangliang
 * @date 2015-01-12
 */
public class OperatorLogs implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	/** 生成操作日志的客户编号 **/
	private Integer uid;

	/** 请求IP地址 **/
	private String ip;
	
	/** 操作内容的id
	 * 
	 *  **/
    private Integer operObject;
	
    /** 操作内容
     * 1:用户
     *  **/
	private Integer operatorType;
	
	/** 操作日志类型 1-->登陆 2-->添加 3-->修改 4-->删除(除软删除) **/
	private Integer operAction;
	
	/** 日志描述:修改渠道等 **/
	private String operatorDesc;
	
	/** 创建日期 **/
	private String createTime;
	
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public Integer getUid()
    {
        return uid;
    }
    public void setUid(Integer uid)
    {
        this.uid = uid;
    }
    public String getIp()
    {
        return ip;
    }
    public void setIp(String ip)
    {
        this.ip = ip;
    }
    public Integer getOperatorType()
    {
        return operatorType;
    }
    public void setOperatorType(Integer operatorType)
    {
        this.operatorType = operatorType;
    }
    
    public String getOperatorDesc()
    {
        return operatorDesc;
    }
    public void setOperatorDesc(String operatorDesc)
    {
        this.operatorDesc = operatorDesc;
    }
    public String getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    public Integer getOperObject()
    {
        return operObject;
    }
    public void setOperObject(Integer operObject)
    {
        this.operObject = operObject;
    }
    public Integer getOperAction()
    {
        return operAction;
    }
    public void setOperAction(Integer operAction)
    {
        this.operAction = operAction;
    }

}
