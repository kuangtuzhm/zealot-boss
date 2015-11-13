package com.zealot.boss.base.service.authorize.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.zealot.boss.base.dao.authorize.RolesDao;
import com.zealot.boss.base.dao.authorize.UserRoleDao;
import com.zealot.boss.base.entity.authorize.Roles;
import com.zealot.boss.base.entity.authorize.UserRole;
import com.zealot.boss.base.service.authorize.AuthorizationService;
import com.zealot.boss.base.service.authorize.RoleService;
import com.zealot.exception.AppException;
import com.zealot.exception.ResultException;
import com.zealot.exception.message.MessageCode;
import com.zealot.orm.model.Pagination;
import com.zealot.util.ValidateUtil;


@Service("roleService")
public class RoleServiceImpl implements RoleService
{

	//private Logger logger = Logger.getLogger(this.getClass());
	
    @Resource(name="roleDao")
    private RolesDao roleDao;
    
    @Resource(name="userRoleDao")
    private UserRoleDao userRoleDao;
    
    @Resource(name="authorizationService")
    private AuthorizationService authorizationService;
    
    public Integer saveRole(Roles role,List<String> list) throws AppException,ResultException
    {
    	role.setState(1);
    	role.setRoot(0);
    	Date date = new Date();
    	role.setCreateTime(date);
    	role.setUpdateTime(date);
    	validRoleAdd(role);
    	Integer roleId = roleDao.saveRole(role);
        return roleId;
        
    }

    public void updateRole(Roles role,List<String> list) throws AppException,ResultException
    {
        Roles r = findRoleById(role.getId());
        if(!isUniqueByName(r.getName(),role.getName()))
        {
        	throw new ResultException(MessageCode.ROLE_NAME_EXITS);
        }
        if(null != r)
        {
            if(ValidateUtil.isNotEmpty(role.getName()))
            {
                r.setName(role.getName());
            }
            if(ValidateUtil.isNotEmpty(role.getDescription()))
            {
            	r.setDescription(role.getDescription());
            }
            if(ValidateUtil.isNotEmpty(role.getState()))
            {
                r.setState(role.getState());
            }
            r.setUpdatedBy(role.getUpdatedBy());
            r.setUpdateTime(role.getUpdateTime());
            roleDao.updateRole(r);
        }
    }
    
    public void updateRoleState(Roles role) throws AppException,ResultException
    {
        Roles r = findRoleById(role.getId());
        if(r.getRoot()==1 && role.getState()==0)
        {
        	throw new ResultException(MessageCode.ADMIN_CANNOT_STOP);
        }
        if(null != r)
        {
            if(ValidateUtil.isNotEmpty(role.getState()))
            {
                r.setState(role.getState());
            }
            r.setUpdateTime(role.getUpdateTime());
            r.setUpdatedBy(role.getUpdatedBy());
            roleDao.updateRole(r);
        }
    }

    public boolean validRoleAdd(Roles role) throws AppException, ResultException
    {
    	boolean f = roleDao.isExistByName(role.getName());
        if(f)
        {
            throw new ResultException(MessageCode.ROLE_NAME_EXITS);
        }
        return true;
    }

    public Roles findRoleById(int id) throws AppException
    {
        return roleDao.findRoleById(id);
    }

    public Pagination<Roles> queryPage(Roles role, int pageNo, int pageSize) throws AppException
    {
        return roleDao.queryPage(role,pageNo,pageSize);
    }

    /**
     * 获取用户的角色
     */
	public List<Roles> getUserRoles(Integer uid) throws AppException {
		
		return roleDao.getUserRoles(uid);
	}
	
	/**
     * 获取角色
     */
	public List<Roles> getRoles(Integer state) throws AppException {
		
		return roleDao.getRoles(state);
	}
	
	public void addUserRole(Integer userId, Integer [] roleIds) throws AppException
    {
		userRoleDao.deleteUserRole(userId);
		UserRole ur = null;
		for (Integer roleId : roleIds) {
//			if (userHasRole(userId, roleId)) {
//				continue;
//			}
			ur = new UserRole();
			ur.setRoleId(roleId);
			ur.setUid(userId);
			userRoleDao.saveUserRole(ur);
		}
		authorizationService.clearUserRoles(userId);
	}

	public void deleteUserRole(Integer userId, List<Integer> roleIds)
			throws AppException 
	{
		
		UserRole ur = null;
		for (Integer roleId : roleIds) {
			ur = new UserRole();
			ur.setRoleId(roleId);
			ur.setUid(userId);
			userRoleDao.deleteUserRole(ur);
		}
		authorizationService.clearUserRoles(userId);
	}
	
	/**
	 * 用户是否已经在此角色下
	 * 
	 * @param userId
	 *            用户编号
	 * @param roleId
	 *            角色编号
	 * @return true/false(有/无)
	 */
	private boolean userHasRole(Integer uid, Integer roleId) throws AppException{
		Assert.notNull(uid);
		Assert.notNull(roleId);
		boolean has = false;
		// 数据量不是很大，直接得出所有权限再做判断
		List<Roles> roles = this.getUserRoles(uid);
		if (ValidateUtil.isEmpty(roles)) {
			return has;
		}

		for (Roles role : roles) {
			if (roleId.equals(role.getId())) {
				has = true;
				break;
			}
		}
		return has;
	}
	
	public boolean isUniqueByName(String oldName, String newName) throws AppException{
		if (StringUtils.equalsIgnoreCase(oldName, newName)) {
			return true;
		} else {
			if (roleDao.isExistByName(newName)) {
				return false;
			} else {
				return true;
			}
		}
	}
}
