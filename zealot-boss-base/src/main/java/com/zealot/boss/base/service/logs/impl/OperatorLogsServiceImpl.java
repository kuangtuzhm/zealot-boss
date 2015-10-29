package com.zealot.boss.base.service.logs.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.boss.base.dao.logs.OperatorLogsDao;
import com.zealot.boss.base.entity.logs.OperatorLogs;
import com.zealot.boss.base.service.logs.OperatorLogsService;
import com.zealot.boss.base.vo.OperatorLogsBean;
import com.zealot.exception.AppException;
import com.zealot.orm.model.Pagination;



@Service("operatorLogsService")
public class OperatorLogsServiceImpl implements OperatorLogsService {
	@Resource
	private OperatorLogsDao operatorLogsDao;

	public void addOperatorLogs(OperatorLogs operatorLogs)
			throws AppException {
		operatorLogsDao.saveOperatorLogs(operatorLogs);
	}

	public Pagination<OperatorLogs> getOperatorLos(OperatorLogsBean logsBean,
			int pageNo, int pageSize) throws AppException {
		
		return operatorLogsDao.queryPage(logsBean, pageNo, pageSize);
	}
	
}
