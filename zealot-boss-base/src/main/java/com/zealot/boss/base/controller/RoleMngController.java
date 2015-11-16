package com.zealot.boss.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zealot.boss.base.entity.authorize.Rights;
import com.zealot.boss.base.entity.authorize.Roles;
import com.zealot.boss.base.entity.authorize.SimpleRights;
import com.zealot.boss.base.entity.authorize.User;
import com.zealot.boss.base.service.authorize.RightsService;
import com.zealot.boss.base.service.authorize.RoleService;
import com.zealot.boss.base.service.authorize.UserService;
import com.zealot.common.CommonConsts;
import com.zealot.exception.AppException;
import com.zealot.exception.ResultException;
import com.zealot.orm.model.Pagination;
import com.zealot.util.JsonUtil;
import com.zealot.util.StringUtil;
import com.zealot.web.bean.Message;

@Controller
public class RoleMngController extends BaseController {
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private RightsService rightsService;

	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/role/add", method = RequestMethod.GET)
	public String add(Model model, HttpServletResponse response) throws Exception {
//		List<RtsMenu> menuList = menuService.findAllMenuRights();
//		List<Department> departmentList = departmentService.findAll();
//		model.addAttribute("DEPART_LIST", departmentList);
//		model.addAttribute("MENU_LIST", menuList);
		return "role/add";
	}
	
	@RequestMapping(value = "/role/save")
	public String save(Model model, Roles role, HttpServletRequest request, HttpServletResponse response) {
		try {
			role.setCreatedBy(this.getOperator(request).getId());
			role.setUpdatedBy(this.getOperator(request).getId());
			roleService.saveRole(role,null);
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			addErrorMessage(model, e.getMessage());
			return ERROR_VIEW;
		} catch (ResultException e)
		{
			addErrorMessage(model, e.getMessage());
			return ERROR_VIEW;
		}
		addSuccessMessage(model, "添加角色成功", "list");
		return SUCCESS_VIEW;
	}

	
	@RequestMapping(value = "/role/edit", method = RequestMethod.GET)
	public String edit(Model model, Integer id, HttpServletResponse response) throws Exception {
		Roles role = roleService.findRoleById(id);
        model.addAttribute("ROLE", role );
//        List<RtsMenu> menuList = menuService.findAllMenuRights();
//		List<Department> departmentList = departmentService.findAll();
//		List<RtsMenu> haveMenuRights = rtsRoleService.findHaveMenuRights(id);
//		model.addAttribute("HV_MENU_LIST", haveMenuRights);
//		model.addAttribute("ALL_MENU_LIST", menuList);
//		model.addAttribute("DEPART_LIST", departmentList);
		return "role/edit";
	}
	
	@RequestMapping(value = "/role/view", method = RequestMethod.GET)
	public String view(Model model, Integer id, HttpServletResponse response) throws Exception {
		Roles role = roleService.findRoleById(id);
        model.addAttribute("ROLE", role );
//        List<RtsMenu> menuList = menuService.findAllMenuRights();
//		model.addAttribute("ALL_MENU_LIST", menuList);
//		List<RtsMenu> haveMenuRights = rtsRoleService.findHaveMenuRights(id);
//		model.addAttribute("HV_MENU_LIST", haveMenuRights);
		return "role/view";
	}
	
	@RequestMapping(value = "/role/list_roleright", method = RequestMethod.GET)
	public String listRoleRight(Model model, Integer id, HttpServletResponse response) throws Exception {
//		List<SimpleRights> simpleRightList = rightsService.querySimpleRightList(CommonConsts.STATE_VALID);
//	    List<Rights> roleRights = rightsService.queryRightByRole(id);
//	    rightsService.setRoleRights(simpleRightList,roleRights);
	    
	    List<Roles> roleList = roleService.getRoles(1);
	    model.addAttribute("ROLE_LIST", roleList);
	    //model.addAttribute("MENU_LIST", simpleRightList);
		return "role/list_role_right";
	}
	
	@RequestMapping(value = "/role/role_right", method = RequestMethod.POST)
	public @ResponseBody String roleRight(Model model, Integer id, HttpServletResponse response) {
		String s = "";
		try {
			List<SimpleRights> simpleRightList = rightsService.querySimpleRightList(CommonConsts.STATE_VALID);
			List<Rights> roleRights = rightsService.queryRightByRole(id);
			rightsService.setRoleRights(simpleRightList,roleRights);
			//model.addAttribute("MENU_LIST", simpleRightList);
			s = JsonUtil.toJson(simpleRightList);
			return ajax(s);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			s="{\"stats\":\"error\",\"msg\":\"后台处理出错\"}";
			return ajax(s);
		}
	}
	
	@RequestMapping(value = "/role/save_role_right", method = RequestMethod.POST)
	public @ResponseBody String saveRoleRight(Model model, Integer id, String rightCode, HttpServletResponse response) {
		String s = "";
		try {
			if (StringUtil.isEmpty(rightCode) || id==null) {
				s="{\"stats\":\"error\",\"msg\":\"添加或删除权限缺少参数\"}";
				return ajax(s);
			}
			List<String> list = new ArrayList<String>();
			String[] codes = rightCode.split(",");
			if (StringUtil.isNotEmpty(rightCode)
					&& codes!=null) 
			{
				for (String code : codes) {
					list.add(code);
				}
			}
			rightsService.updateRoleRights(id, list);
			s="{\"stats\":\"ok\"}";
			return ajax(s);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			s="{\"stats\":\"error\",\"msg\":\"后台处理出错\"}";
			return ajax(s);
		}
	}
	
	@RequestMapping(value = "/role/update")
	public String update(Model model, Roles role, String[] ckd, HttpServletRequest request, HttpServletResponse response) {
		try {
			role.setUpdatedBy(this.getOperator(request).getId());
			roleService.updateRole(role, null);
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			addErrorMessage(model, e.getMessage());
			return ERROR_VIEW;
		} catch (ResultException e)
		{
			addErrorMessage(model, e.getMessage());
			return ERROR_VIEW;
		}
		addSuccessMessage(model, "修改角色成功", "list");
		return SUCCESS_VIEW;
	}
	
	@RequestMapping(value = "/role/list")
	public String list(Model model, Pagination<Roles> page, Roles role, HttpServletResponse response) throws Exception {
		page = roleService.queryPage(role, page.getPageNo(), page.getPageSize());
		
		model.addAttribute("page", page);
		model.addAttribute("role", role);
		return "role/list";
	}
	
	@RequestMapping(value = "/role/userlist")
	public String listUser(Model model, Pagination<User> page, Integer id, HttpServletResponse response) throws Exception {
		page = userService.queryRoleUsers(id, page.getPageNo(), page.getPageSize());
		model.addAttribute("page", page);
		model.addAttribute("roleId", id);
		return "role/list_user";
	}
	
	@RequestMapping(value = "/role/userdelete", method = RequestMethod.POST)
	public @ResponseBody
	Message deleteUser(Integer uid, Integer roleId) {
		try {
			List<Integer> roleList = new ArrayList<Integer>();
			roleList.add(roleId);
			roleService.deleteUserRole(uid, roleList);
			return SUCCESS_MESSAGE;
		} catch (Exception e) {
			return Message.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/role/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Roles role, HttpServletRequest request) {
			try {
				role.setState(0);
				role.setUpdatedBy(this.getOperator(request).getId());
				roleService.updateRoleState(role);
			} catch (AppException e) {
				logger.error(e.getMessage(),e);
				return Message.error(e.getMessage());
			} catch (ResultException e) {
				return Message.error(e.getMessage());
			}
			return SUCCESS_MESSAGE;
		
	}
	
	// AJAX唯一验证
	@RequestMapping(value = "/role/check_rolename", method = RequestMethod.GET)
	public @ResponseBody String checkRoleName(String oldRoleName, String name) {
		try {
			if (roleService.isUniqueByName(oldRoleName, name)) {
				return ajax("true");
			} else {
				return ajax("false");
			}
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			return ajax("true");
		}
	}

}
