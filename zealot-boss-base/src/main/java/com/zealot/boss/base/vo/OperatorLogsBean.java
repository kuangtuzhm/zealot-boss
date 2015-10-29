package com.zealot.boss.base.vo;

import com.zealot.boss.base.entity.logs.OperatorLogs;



/**
 * @desc 日志bean
 * @date 2015-01-12
 */
public class OperatorLogsBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/** 日志信息 **/
	private OperatorLogs operatorLogs;
	/** 开始日期 **/
	private String beginDate;
	/** 结束日期 **/
	private String endDate;

	public OperatorLogsBean() {
	}

	public OperatorLogs getOperatorLogs() {
		return operatorLogs;
	}

	public void setOperatorLogs(OperatorLogs operatorLogs) {
		this.operatorLogs = operatorLogs;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
