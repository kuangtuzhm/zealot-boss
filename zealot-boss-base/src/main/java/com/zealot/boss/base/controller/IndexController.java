package com.zealot.boss.base.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.util.CasPropertiesConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.appleframework.config.core.PropertyConfigurer;
import com.zealot.boss.base.entity.authorize.Rights;
import com.zealot.boss.base.filter.BossFilter;
import com.zealot.boss.base.service.authorize.RightsService;

@Controller
public class IndexController {
	
	@Resource
	private RightsService rightsService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletResponse response) {
		return "index";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model, HttpServletResponse response) throws Exception {
		//查询子系统
		Rights right = new Rights();
		right.setState(1);
		right.setType(0);
		List<Rights> list = rightsService.queryRightList(right);
		model.addAttribute("ROOT_MENU_LIST", list);
		return "main";
	}
	
	@RequestMapping(value = "/panel", method = RequestMethod.GET)
	public String panel(Model model, HttpServletResponse response) throws Exception {
//		List<RtsMenu> list = menuService.findRootMenuList();
//		model.addAttribute("ROOT_MENU_LIST", list);
		return "panel";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) throws Exception {
		String casServer = PropertyConfigurer.getValue(CasPropertiesConfig.CAS_SERVER);
		String serverName = PropertyConfigurer.getValue(CasPropertiesConfig.SERVER_NAME);
		String logoutUrl = casServer + "/logout?service=" + serverName;
		
		request.getSession().removeAttribute(BossFilter.SESSION_USER_KEY);
//		request.getSession().removeAttribute(BossFilter.SESSION_RTS_KEY);
		request.getSession().removeAttribute(BossFilter.SESSION_CAS_KEY);
		model.addAttribute("LOGOUT_URL", logoutUrl);
		return "logout";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model, HttpServletRequest request) throws Exception {
		return "index";
	}
	
	@RequestMapping(value = "/boss", method = RequestMethod.GET)
	public String boss(Model model, HttpServletRequest request) throws Exception {
		return "index";
	}

}
