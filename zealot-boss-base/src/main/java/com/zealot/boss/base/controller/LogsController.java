package com.zealot.boss.base.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zealot.boss.base.constant.OperAction;
import com.zealot.boss.base.entity.logs.OperatorLogs;
import com.zealot.boss.base.service.logs.OperatorLogsService;
import com.zealot.boss.base.vo.OperatorLogsBean;
import com.zealot.exception.AppException;
import com.zealot.orm.model.Pagination;

@Controller
public class LogsController extends BaseController {

	@Resource
	private OperatorLogsService operatorLogsService;
	
	@RequestMapping(value = "/logs/list")
	public String list(Model model, Pagination<OperatorLogs> page, OperatorLogsBean logsBean, HttpServletResponse response)
	{
		try {
			page = operatorLogsService.getOperatorLos(logsBean, page.getPageNo(), page.getPageSize());
			model.addAttribute("logsBean",logsBean);
			model.addAttribute("page", page);
			model.addAttribute("operActionMap", OperAction.map());
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			addErrorMessage(model, e.getMessage());
			return ERROR_VIEW;
		}
	    //model.addAttribute("MENU_LIST", simpleRightList);
		return "logs/list";
	}
}
