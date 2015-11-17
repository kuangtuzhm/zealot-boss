package com.zealot.boss.base.controller.log4j;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zealot.boss.base.controller.BaseController;
import com.zealot.boss.base.vo.OperatorLogsBean;
import com.zealot.util.StringUtil;
import com.zealot.web.bean.Message;

@Controller
public class Log4jController extends BaseController {

	
	@RequestMapping(value = "/log4j/list")
	public String list(Model model, String name, OperatorLogsBean logsBean, HttpServletResponse response)
	{
		Enumeration<Logger> currentLoggers = LogManager.getCurrentLoggers(); 
		
		List<Logger> resultLoggers = new ArrayList<Logger>();
		
		if(StringUtil.isNotEmpty(name))
		{
	        while(currentLoggers.hasMoreElements())
	        {
	        	Logger l = currentLoggers.nextElement();
	        	if(l.getName().startsWith(name))
	        	{
	        		resultLoggers.add(l);
	        	}
	        }
	        model.addAttribute("currentLoggers", resultLoggers);
		}
		else
		{
			model.addAttribute("currentLoggers", currentLoggers);
		}
//		LoggerRepository d = LogManager.getLoggerRepository();
//		
//		Enumeration<Appender> s = d.getRootLogger().getAllAppenders();
//        String appender = "==";
//        while(s.hasMoreElements())
//        {
//        	Appender b = s.nextElement();
//        	appender += (","+b.getName());
//        }
//		System.out.println("==========="+appender);
//		
//		while (currentLoggers.hasMoreElements()) {  
//	        Logger logger = currentLoggers.nextElement();  
//	        System.out.println(logger.getName()+":"+logger.getLevel()+"::"+logger.getEffectiveLevel()); 
//	    } 
//		Level l = Level.DEBUG;
//		d.getRootLogger().setLevel(l);
//		
//		Enumeration<Logger> currentLoggers1 = LogManager.getCurrentLoggers(); 
//		while (currentLoggers1.hasMoreElements()) {  
//	        Logger logger = currentLoggers1.nextElement();  
//	        
//	        System.out.println(logger.getName()+":"+logger.getLevel()+"::"+logger.getEffectiveLevel()); 
//	    } 
		model.addAttribute("name", name);
		return "log4j/list";
	}
	
	@RequestMapping(value = "/log4j/edit_level")
	public String editLevel(Model model, String name, OperatorLogsBean logsBean, HttpServletResponse response)
	{
		Enumeration<Logger> currentLoggers = LogManager.getCurrentLoggers(); 

		if(StringUtil.isNotEmpty(name))
		{
	        while(currentLoggers.hasMoreElements())
	        {
	        	Logger l = currentLoggers.nextElement();
	        	if(l.getName().startsWith(name))
	        	{
	        		model.addAttribute("log", l);
	        	}
	        }
		}
		
		return "log4j/edit_level";
	}
	
	@RequestMapping(value = "/log4j/edit_rootlevel")
	public String editRootLevel(Model model, OperatorLogsBean logsBean, HttpServletResponse response)
	{
		Logger rootLogger = LogManager.getLoggerRepository().getRootLogger();

		model.addAttribute("log", rootLogger);
	    
		return "log4j/edit_rootLevel";
	}
	
	@RequestMapping(value = "/log4j/update_level")
	public String updateLevel(Model model, String name,String level, OperatorLogsBean logsBean, HttpServletResponse response)
	{
		Enumeration<Logger> currentLoggers = LogManager.getCurrentLoggers(); 

		if(StringUtil.isNotEmpty(name))
		{
	        while(currentLoggers.hasMoreElements())
	        {
	        	Logger l = currentLoggers.nextElement();
	        	if(l.getName().startsWith(name))
	        	{
	        		l.setLevel(Level.toLevel(level));
	        		return SUCCESS_AJAX;
	        	}
	        }
		}
		
		return ERROR_AJAX;
	}
	
	@RequestMapping(value = "/log4j/update_rootlevel")
	public @ResponseBody Message updateRootLevel(Model model, String level, OperatorLogsBean logsBean, HttpServletResponse response)
	{
		Logger rootLogger = LogManager.getLoggerRepository().getRootLogger();
		rootLogger.setLevel(Level.toLevel(level));
	    return SUCCESS_MESSAGE;
	}
}
