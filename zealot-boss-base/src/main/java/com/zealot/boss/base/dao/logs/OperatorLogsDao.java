package com.zealot.boss.base.dao.logs;

import com.zealot.boss.base.entity.logs.OperatorLogs;
import com.zealot.boss.base.vo.OperatorLogsBean;
import com.zealot.exception.AppException;
import com.zealot.orm.model.Pagination;


/**
 * @desc 日志操作
 * @author zhangliang
 *
 */
public interface OperatorLogsDao {
	
	/**
	 * 根据条件查询日志信息
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws DAOException
	 */
	public Pagination<OperatorLogs> queryPage(OperatorLogsBean logsBean, int pageNo, int pageSize) throws AppException;
	
	/**
	 * 保存日志信息
	 * @param operatorLos
	 * @return
	 * @throws DAOException
	 */
	public void saveOperatorLogs(OperatorLogs operatorLogs) throws AppException;
	
}
