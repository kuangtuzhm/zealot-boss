package com.zealot.boss.base.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zealot.boss.base.entity.authorize.User;
import com.zealot.boss.base.filter.BossFilter;
import com.zealot.boss.base.service.authorize.UserService;
import com.zealot.web.bean.Message;

@Controller
public class PasswordController extends BaseController {
		
	@Resource
	private UserService userService;
		
	@RequestMapping(value = "/password/modify", method = RequestMethod.GET)
	public String modify(Model model, HttpServletRequest request) throws Exception {
		User user = (User)request.getSession().getAttribute(BossFilter.SESSION_USER_KEY);
		model.addAttribute("uid",user.getUid());
		return "password/modify";
	}
	

	@RequestMapping(value = "/password/update", method = RequestMethod.POST)
	public @ResponseBody Message update(Model model, User bo, HttpServletRequest request) throws Exception {
		try {
			userService.modifyUserPassWord(bo);
		} catch (Exception e) {
			return Message.error(e.getMessage());
		}
		return SUCCESS_MESSAGE;
	}
}
