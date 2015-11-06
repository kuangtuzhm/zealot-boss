package com.zealot.boss.base.service.authorize.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zealot.boss.base.dao.authorize.RightsDao;
import com.zealot.boss.base.dao.authorize.RoleRightDao;
import com.zealot.boss.base.entity.authorize.Rights;
import com.zealot.boss.base.entity.authorize.SimpleRights;
import com.zealot.boss.base.service.authorize.RightsService;
import com.zealot.exception.AppException;
import com.zealot.exception.ResultException;
import com.zealot.exception.message.MessageCode;


@Service("rightService")
public class RightsServiceImpl implements RightsService
{
	private Logger logger = Logger.getLogger(this.getClass());
	
    @Resource(name="rightDao")
    private RightsDao rightDao;
    
    @Resource(name="roleRightDao")
    private RoleRightDao roleRightDao;
    
    @Override
	public List<Rights> queryRootMenu(Rights right) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	public Rights getRightByCode(String rightCode) throws AppException
    {
    	return rightDao.getRightByCode(rightCode);
    }
    
    public Integer saveRight(Rights right) throws AppException,ResultException
    {
    	Rights r = getRightByCode(right.getRightCode());
    	if(r!=null)
    	{
    		StringBuffer buffer = new StringBuffer();
			buffer.append("权限代码[").append(r.getRightCode()).append("]已经存在");
			logger.error(buffer.toString());
			throw new ResultException(MessageCode.RIGHTS_CODE_EXITS);
    	}
    	Integer rid = rightDao.saveRight(right);
        return rid;
    }

    public void updateRight(Rights right) throws AppException
    {
        rightDao.updateRight(right);
    }

    public void deleteRights(String rightCode) throws AppException
	{
	    //删除本节点
	    deleteRight(rightCode);
	    //递归删除子节点
	    List<Rights> list = queryChilderenRight(rightCode);
	    if(list!=null && list.size()>0)
	    {
	        for(Rights right : list)
	        {
	            deleteRights(right.getRightCode());
	        }
	    }
	}

	private void deleteRight(String rightCode) throws AppException{
	    
		roleRightDao.deleteByRight(rightCode);

		rightDao.deleteRight(rightCode);
	}
	
	private List<Rights> queryChilderenRight(String parentCode) throws AppException
	{
	    return rightDao.queryChilderenRight(parentCode);
	}

	public List<Rights> queryRightList(Rights right) throws AppException
    {
        return rightDao.queryRightList(right);
    }

    public List<SimpleRights> querySimpleRightList(Integer state) throws AppException
    {
        return rightDao.querySimpleRightList(state);
    }

    public List<Rights> queryRightByRole(Integer roleId) throws AppException
    {
        return rightDao.queryRightByRole(roleId);
    }
    
    public void setRoleRights(List<SimpleRights> allRights,List<Rights> roleRights )
    {
    	Map<String,Integer> map = new HashMap<String,Integer>();
	    if(roleRights!=null && roleRights.size()>0 && allRights!=null && allRights.size()>0)
	    {
	        for(Rights right : roleRights)
	        {
	            map.put(right.getRightCode(),1);
	        }
	        for(SimpleRights sr : allRights)
	        {
	            if(map.containsKey(sr.getId()))
	            {
	                sr.setChecked(true);
	            }
	        }
	    }
    }
    
    public List<SimpleRights> groupRights(List<SimpleRights> allRights)
    {
    	List<SimpleRights> resultList = new ArrayList<SimpleRights>();
    	
    	SimpleRights parent = null;
    	
    	//首先把根目录先查找到并放入作为最顶层
    	for(SimpleRights rights : allRights)
    	{
    		if(rights.getType()==-1)
    		{
    			parent = rights;
    			allRights.remove(rights);
    			break;
    		}
    	}
    	if(parent!=null)
    	{
    		build(parent,allRights);
    		resultList = parent.getChildList();
    	}
    	return resultList;
    }
    
    @Override
	public List<Rights> findChildList(Rights right, List<Rights> list) {
		List<Rights> childList = new ArrayList<Rights>();
		if(list!=null)
		{
			for(Rights r : list)
			{
				if(right.getRightCode().equals(r.getParentCode()))
				{
					childList.add(r);
				}
			}
		}
		return childList;
	}
    
    public List<Rights> findByPath(String path) throws AppException
    {
    	StringBuilder sb = new StringBuilder("");
    	String [] pathArr = path.split(",");
    	for(String pathId : pathArr)
    	{
    		if(sb.length()!=0)
    		{
    			sb.append(",");
    		}
    		sb.append("'").append(pathId).append("'");
    	}
    	return rightDao.findByPath(sb.toString());
    }

	private void build(SimpleRights node,List<SimpleRights> allRights){  
        List<SimpleRights> children = getChildren(node,allRights);
        if (!children.isEmpty()) {
        	node.setChildList(children);
            for (SimpleRights child : children) { 
                build(child,allRights);  
            }  
        }   
    }  
      
    private List<SimpleRights> getChildren(SimpleRights node,List<SimpleRights> allRights){  
        List<SimpleRights> children = new ArrayList<SimpleRights>();  
        String id = node.getId();  
        for (SimpleRights child : allRights) {  
            if (id.equals(child.getPid())) {  
                children.add(child);  
            }  
        }  
        return children;  
    }  
}
