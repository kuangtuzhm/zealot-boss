package com.zealot.boss.base.dao.logs.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zealot.boss.base.dao.logs.OperatorLogsDao;
import com.zealot.boss.base.entity.logs.OperatorLogs;
import com.zealot.boss.base.vo.OperatorLogsBean;
import com.zealot.exception.AppException;
import com.zealot.orm.dao.BaseDAO;
import com.zealot.orm.model.Pagination;
import com.zealot.orm.util.Finder;
import com.zealot.util.ValidateUtil;

@Repository("operatorLogsDao")
public class OperatorLogsDaoImpl implements OperatorLogsDao {
    
    @Resource(name="baseDAO")
    private BaseDAO<OperatorLogs> baseDAO;
	
	public Pagination<OperatorLogs> queryPage(OperatorLogsBean logsBean,
			int pageNo, int pageSize) throws AppException {

	    Map<String, Object> param = new HashMap<String, Object>();
        StringBuilder hql = new StringBuilder("from OperatorLogs where 1=1");
		if(null != logsBean)
		{
		    if(null != logsBean.getOperatorLogs())
		    {
		        if(ValidateUtil.isNotEmpty(logsBean.getOperatorLogs().getOperatorDesc()))
	            {
	                hql.append(" and operatorDesc like '%")
	                .append(logsBean.getOperatorLogs().getOperatorDesc())
	                .append("%'");
	            }
		        if(ValidateUtil.isNotEmpty(logsBean.getOperatorLogs().getOperatorType()))
                {
                    param.put("operatorType", logsBean.getOperatorLogs().getOperatorType());
                    hql.append(" and operatorType=:operatorType");
                }
		        if(ValidateUtil.isNotEmpty(logsBean.getOperatorLogs().getOperAction()))
                {
                    param.put("operAction", logsBean.getOperatorLogs().getOperAction());
                    hql.append(" and operAction=:operAction");
                }
		        if(ValidateUtil.isNotEmpty(logsBean.getOperatorLogs().getUid()))
		        {
		            param.put("uid", logsBean.getOperatorLogs().getUid());
	                hql.append(" and uid=:uid");
		        }
		    }
		    if(ValidateUtil.isNotEmpty(logsBean.getBeginDate()))
		    {
		        param.put("beginDate", logsBean.getBeginDate());
                hql.append(" and createTime>=:beginDate");
		    }
		    if(ValidateUtil.isNotEmpty(logsBean.getEndDate()))
		    {
		        param.put("endDate", logsBean.getEndDate());
                hql.append(" and createTime<=:endDate");
		    }
		}
		hql.append(" order by id desc ");
		Finder finder = new Finder(hql.toString());
        finder.setParams(param);
        return baseDAO.queryPage(finder,pageNo,pageSize);
	}
	
	public void saveOperatorLogs(OperatorLogs operatorLogs) throws AppException {
	    baseDAO.save(operatorLogs);
	}

}
