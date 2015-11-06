package com.zealot.boss.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zealot.boss.base.entity.authorize.Rights;
import com.zealot.boss.base.service.authorize.RightsService;
import com.zealot.util.StringUtil;

@Controller
public class MenuController {
	
	@Resource
	private RightsService rightsService;
	
	@RequestMapping(value = "/menu_main", method = RequestMethod.GET)
	public String main(Model model, String id, HttpServletResponse response) throws Exception {
		model.addAttribute("MENU_ID", id);
		return "menu_main";
	}
	
	@RequestMapping(value = "/menu_container", method = RequestMethod.GET)
	public String container(Model model, String id, HttpServletResponse response) throws Exception {
		model.addAttribute("MENU_ID", id);
		return "menu_container";
	}
	

	@RequestMapping(value = "/menu_header", method = RequestMethod.GET)
	public String index(Model model, String id, HttpServletResponse response) throws Exception {
		//查询子系统
		Rights right = new Rights();
		right.setState(1);
		right.setType(0);
		List<Rights> list = rightsService.queryRightList(right);
		//控制面板选择系统
		model.addAttribute("ROOT_MENU_LIST", list);
		
		Rights rightExample = new Rights();
		rightExample.setParentCode(id);
		List<Rights> secondList = rightsService.queryRightList(rightExample);
		model.addAttribute("SECOND_MENU_LIST", secondList);
		return "menu_header";
	}
	
	@RequestMapping(value = "/menu_left", method = RequestMethod.GET)
	public String left(Model model, String id, HttpServletResponse response) throws Exception {
		if(!StringUtil.isEmpty(id)) {
			List<Rights> leftMenuList = null;
			Rights rightExample = new Rights();
			rightExample.setParentCode(id);
			leftMenuList = rightsService.queryRightList(rightExample);
			
			if(leftMenuList!=null && leftMenuList.size()>0)
			{
				Map<String, List<Rights>> childMenuMap = new HashMap<String, List<Rights>>();
				//同已系统下所有菜单的sysCode相同
				String sysCode = leftMenuList.get(0).getSysCode();
				//本系统下所有菜单项
				Rights example = new Rights();
				example.setSysCode(sysCode);
				List <Rights> allMenuList = rightsService.queryRightList(example);
		        //左侧树形菜单一级菜單
				for (Rights leftMenu : leftMenuList) {
					List<Rights> childMenuList = rightsService.findChildList(leftMenu, allMenuList);
					childMenuMap.put(leftMenu.getRightCode()+ "", childMenuList);
				}

				model.addAttribute("LEFT_MENU_LIST", leftMenuList);
				model.addAttribute("CHILD_MAP_MENU_LIST", childMenuMap);
			}
		}
		else {
			//leftMenuList = new ArrayList<RtsMenu>();
			return null;
		}
		return "menu_left";
	}
	
	@RequestMapping(value = "/menu_footer", method = RequestMethod.GET)
	public String footer(Model model, HttpServletResponse response) throws Exception {
		return "menu_footer";
	}
	
	@RequestMapping(value = "/menu_right", method = RequestMethod.GET)
	public String right(Model model, String id, HttpServletResponse response) throws Exception {
		if(!StringUtil.isEmpty(id)) {
			List<Rights> rightMenuList = null;
			Rights rightExample = new Rights();
			rightExample.setParentCode(id);
			rightMenuList = rightsService.queryRightList(rightExample);
			model.addAttribute("RIGHT_MENU_LIST", rightMenuList);
			
			
			Rights menu = rightsService.getRightByCode(id);
			List<Rights> pathMenuList = rightsService.findByPath(menu.getPath());
			model.addAttribute("PATH_MENU_LIST", pathMenuList);
		}
		else {
			return null;
		}
		return "menu_right";
	}

	@RequestMapping(value = "/menu_content", method = RequestMethod.GET)
	public String content(Model model, HttpServletResponse response) throws Exception {
		return null;
	}
}
