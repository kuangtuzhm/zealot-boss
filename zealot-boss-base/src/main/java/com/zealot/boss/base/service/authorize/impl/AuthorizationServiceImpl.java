package com.zealot.boss.base.service.authorize.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.boss.base.entity.authorize.Rights;
import com.zealot.boss.base.entity.authorize.Roles;
import com.zealot.boss.base.service.authorize.AuthorizationService;
import com.zealot.boss.base.service.authorize.RightsService;
import com.zealot.boss.base.service.authorize.RoleService;
import com.zealot.exception.AppException;
import com.zealot.util.StringUtil;


@Service("authorizationService")
public class AuthorizationServiceImpl implements AuthorizationService {

	@Resource(name="roleService")
    private RoleService roleService;
	
	@Resource(name="rightService")
    private RightsService rightService;
	
	private static Map<Integer,List<Rights>> map = new HashMap<Integer,List<Rights>>();
	
	public List<Rights> getRoleRights(Integer roleId) throws AppException
	{
		if(map.isEmpty())
		{
			synchronized(this)
			{
				List<Roles> roles = roleService.getRoles(1);
				for(Roles role : roles)
				{
					List<Rights> rights = rightService.queryRightByRole(role.getId());
					map.put(role.getId(), rights);
				}
			}
		}
		return map.get(roleId);
	}
	
	//public boolean hasRight(Integer [] roles, String rightCode) throws AppException
	public boolean hasRight(Integer uid, String rightCode) throws AppException
	{
		List<Roles> roles = roleService.getUserRoles(uid);
		if(roles != null && roles.size()>0)
		{
			for(Roles role : roles)
			{
				List<Rights> rights = getRoleRights(role.getId());
				if(rights!=null && rights.size()>0)
				{
					for(Rights r : rights)
					{
						if(rightCode.equals(r.getRightCode()))
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	//public boolean hasUriRight(Integer [] roles, String uri) throws AppException
	public boolean hasUriRight(Integer uid, String uri) throws AppException
	{
		List<Roles> roles = roleService.getUserRoles(uid);
		if(roles != null && !StringUtil.isEmpty(uri))
		{
			for(Roles role : roles)
			{
				List<Rights> rights = getRoleRights(role.getId());
				if(rights!=null && rights.size()>0)
				{
					for(Rights r : rights)
					{
						if(r.getUri().indexOf(uri)!=-1)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void reInit()
	{
		map.clear();
	}
}
