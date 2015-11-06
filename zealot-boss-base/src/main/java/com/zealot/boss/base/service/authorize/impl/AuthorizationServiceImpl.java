package com.zealot.boss.base.service.authorize.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.boss.base.service.authorize.AuthorizationService;
import com.zealot.boss.base.service.authorize.AuthorizeAPI;
import com.zealot.exception.AppException;


@Service("authorizationService")
public class AuthorizationServiceImpl implements AuthorizationService {
	
	@Resource(name="authorizeAPI4DB")
	private AuthorizeAPI defaultAuthorizeAPI;
	
	private AuthorizeAPI authorizeAPI;
	
	public boolean hasRight(Integer uid, String rightCode) throws AppException
	{
		List<Integer> roleList = null;
		List<Integer> rightRoleList = null;
		if(authorizeAPI!=null)
		{
			roleList = authorizeAPI.getUserRoles(uid);
			rightRoleList = authorizeAPI.getRightRoles(rightCode);
		}
		else
		{
			roleList = defaultAuthorizeAPI.getUserRoles(uid);
			rightRoleList = defaultAuthorizeAPI.getRightRoles(rightCode);
		}
		if(roleList != null && roleList.size()>0 
				&& rightRoleList != null && rightRoleList.size()>0)
		{
			for(Integer roleId : roleList)
			{
				for(Integer r : rightRoleList)
				{
					if(roleId == r)
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//public boolean hasUriRight(Integer [] roles, String uri) throws AppException
	public boolean hasUriRight(Integer uid, String uri) throws AppException
	{
		return false;
	}
	
	@Override
	public void clearUserRoles(Integer uid) {
		if(authorizeAPI!=null)
		{
			authorizeAPI.clearUserRoles(uid);
		}
		else
		{
			defaultAuthorizeAPI.clearUserRoles(uid);
		}
	}

	@Override
	public void clearRightRoles(String rightCode) {
		if(authorizeAPI!=null)
		{
			authorizeAPI.clearRightRoles(rightCode);
		}
		else
		{
			defaultAuthorizeAPI.clearRightRoles(rightCode);
		}
	}

	public void setAuthorizeAPI(AuthorizeAPI authorizeAPI) {
		this.authorizeAPI = authorizeAPI;
	}
}
