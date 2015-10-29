package com.zealot.boss.base.service.logs;

import com.zealot.boss.base.entity.logs.OperatorLogs;
import com.zealot.boss.base.vo.OperatorLogsBean;
import com.zealot.exception.AppException;
import com.zealot.orm.model.Pagination;



/**
 * @desc 日志业务
 * @date 2015-01-12
 */
public interface OperatorLogsService {
	
	/**
	 * 添加日志信息
	 * @param operatorLogs
	 * @return
	 * @throws ServiceException
	 */
	public void addOperatorLogs(OperatorLogs operatorLogs) throws  AppException;
	
	/**
	 * 获取日志列表信息
	 * @param logsBean
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public Pagination<OperatorLogs> getOperatorLos(OperatorLogsBean logsBean, int pageNo, int pageSize) 
	        throws  AppException;
	
}
