package com.zealot.boss.base.controller;

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
import com.zealot.boss.base.entity.authorize.SimpleRights;
import com.zealot.boss.base.service.authorize.RightsService;
import com.zealot.exception.AppException;
import com.zealot.exception.ResultException;
import com.zealot.util.StringUtil;
import com.zealot.web.bean.Message;

@Controller
public class MenuMngController extends BaseController {
	
	@Resource
	private RightsService rightsService;

	@RequestMapping(value = "/menu/add", method = RequestMethod.GET)
	public String add(Model model, String rightCode, HttpServletResponse response) throws Exception {
		if(StringUtil.isNotEmpty(rightCode)) {
			Rights menu = rightsService.getRightByCode(rightCode);
			model.addAttribute("PARENT_MENU", menu);
			if(menu.getType() >= 4) {
				addErrorMessage(model, "最高只能添加5级菜单");
				return ERROR_VIEW;
			}
		}
		else {
			rightCode = "0";
		}
		model.addAttribute("parentCode", rightCode);
		return "menu/add";
	}
	
	@RequestMapping(value = "/menu/edit", method = RequestMethod.GET)
	public String edit(Model model, String rightCode, HttpServletResponse response) throws Exception {
		Rights menu = rightsService.getRightByCode(rightCode);
		String parentCode = menu.getParentCode();
		if(StringUtil.isNotEmpty(parentCode)) {
			Rights parentMenu = rightsService.getRightByCode(parentCode);
			model.addAttribute("PARENT_MENU", parentMenu);
		}
		else {
			parentCode = "0";
		}
		model.addAttribute("parentCode", parentCode);
		model.addAttribute("MENU", menu);
		return "menu/edit";
	}
	
	@RequestMapping(value = "/menu/save")
	public String save(Model model, Rights menu, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (!"0".equals(menu.getParentCode())) {
				Rights parent = rightsService.getRightByCode(menu.getParentCode());
				menu.setType(parent.getType() + 1);
				menu.setBaseUrl(parent.getBaseUrl());
			} else {
				menu.setType(0);
			}
			if(StringUtil.isNotEmpty(menu.getPath()))
			{
				menu.setPath(menu.getPath()+","+menu.getRightCode());
			}
			else
			{
				menu.setPath(menu.getRightCode());
			}
			menu.setIsHidden(0);
			menu.setState(1);
			menu.setCreatedBy(this.getOperator(request).getId());
			menu.setUpdatedBy(this.getOperator(request).getId());
			rightsService.saveRight(menu);
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			addErrorMessage(model, e.getMessage());
			return ERROR_VIEW;
		}
		catch (ResultException e) {
			addErrorMessage(model, e.getMessage());
			return ERROR_VIEW;
		}
		addSuccessMessage(model, "添加菜单成功", "list");
		return SUCCESS_VIEW;

	}
	
	@RequestMapping(value = "/menu/update")
	public String update(Model model, Rights menu, HttpServletRequest request, HttpServletResponse response) {
		try {			
			menu.setUpdatedBy(this.getOperator(request).getId());
			rightsService.updateRight(menu);
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			addErrorMessage(model, e.getMessage());
			return ERROR_VIEW;
		}
		addSuccessMessage(model, "修改菜单成功", "list");
		return SUCCESS_VIEW;
	}
	
	@RequestMapping(value = "/menu/list")
	public String list(Model model, HttpServletResponse response) throws Exception {
		List<SimpleRights> list = rightsService.querySimpleRightList(1);
		model.addAttribute("MENU_LIST", list);
		return "menu/list";
	}
	
	/*@RequestMapping(value = "/menu/delete", method = RequestMethod.GET)
	public String delete(Model model, Integer id, HttpServletResponse response) {
		try {
			RtsMenu menu = menuService.get(id);
			menuService.delete(menu);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		addSuccessMessage(model, "修改菜单成功", "list");
		return SUCCESS_VIEW;
	}*/
	
	@RequestMapping(value = "/menu/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(String rightCode) {
		try {
			rightsService.deleteRights(rightCode);
			return SUCCESS_MESSAGE;
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			return Message.error(e.getMessage());
		}
		
	}

}
