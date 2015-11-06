package com.zealot.boss.base.controller;

import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.zealot.boss.base.entity.authorize.User;
import com.zealot.boss.base.entity.logs.OperatorLogs;
import com.zealot.boss.base.service.authorize.UserService;
import com.zealot.common.CommonConsts;
import com.zealot.exception.AppException;
import com.zealot.exception.ResultException;
import com.zealot.model.entity.Search;
import com.zealot.orm.model.Pagination;

@Controller
public class UserMngController extends BaseController {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private UserService userService;
	
//	@Resource
//	private JavaMailSender mailSender;
//	
//	@Resource
//	private FreeMarkerConfigurer freemarkerConfig;
	
	@RequestMapping(value = "/user/list")
	public String list(Model model, Pagination<User> page, User user, HttpServletResponse response) throws Exception {
		page = userService.queryUserList(user, page.getPageNo(), page.getPageSize());
		model.addAttribute("page", page);
		model.addAttribute("user", user);
		return "user/list";
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String add(Model model, HttpServletResponse response) throws Exception {
		return "user/add";
	}
	
	@RequestMapping(value = "/user/save")
	public String save(Model model, User user, HttpServletRequest request) {
		
		user.setState(1);
		user.setIsAdmin(0);
		Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        
        OperatorLogs operatorLogs = new OperatorLogs();
        operatorLogs.setOperatorType(CommonConsts.operObject.USER);
        operatorLogs.setOperAction(CommonConsts.operator.OPERATOR_MODIFY);
        operatorLogs.setUid(this.getOperator(request).getUid());
        try
        {   //判断用户登陆名是否存在
            userService.validUserAdd(user);
            
            Integer uid = userService.addUser(user);
            operatorLogs.setOperObject(uid);
            operatorLogs.setOperatorDesc("新增用户:"+user.getUname()
                    + "["+uid+"]成功");
        }
        catch (AppException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            
            operatorLogs.setOperObject(0);
            operatorLogs.setOperatorDesc("新增用户:"+user.getUname()
                    + "[0]失败");
            addErrorMessage(model, e.getMessage());
			return ERROR_VIEW;
        }
        catch (ResultException e)
        {
            operatorLogs.setOperObject(0);
            operatorLogs.setOperatorDesc("新增用户:"+user.getUname()
                    + "[0]失败,"+e.getMessage());
        }
		addSuccessMessage(model, "添加用户成功", "list");
		return SUCCESS_VIEW;
	}

	
//	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
//	public String edit(Model model, Integer id, HttpServletResponse response) throws Exception {
//        User user = userService.get(id);
//        model.addAttribute("USER", user);
//        List<RtsRole> roleList = rtsRoleService.findAll();
//		List<Department> departmentList = departmentService.findAll();
//		model.addAttribute("ROLE_LIST", roleList);
//		model.addAttribute("DEPART_LIST", departmentList);
//		return "user/edit";
//	}
//	
//	@RequestMapping(value = "/user/view", method = RequestMethod.GET)
//	public String view(Model model, Integer id, HttpServletResponse response) throws Exception {
//        User user = userService.get(id);
//        model.addAttribute("USER", user);
//		return "user/view";
//	}
//	
//	@RequestMapping(value = "/user/update")
//	public String update(Model model, User user, String[] roleIds, HttpServletResponse response) {
//		try {
//			userService.update(user, roleIds);
//		} catch (ServiceException e) {
//			addErrorMessage(model, e.getMessage());
//			return ERROR_VIEW;
//		}
//		addSuccessMessage(model, "修改用户成功", "list");
//		return SUCCESS_VIEW;
//	}
//	
//	
//	
//	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
//	public @ResponseBody Message delete(Integer id) {
//		try {
//			userService.delete(id);
//			return SUCCESS_MESSAGE;
//		} catch (ServiceException e) {
//			return Message.error(e.getMessage());
//		}
//	}
//	
//	@RequestMapping(value = "/user/deletes", method = RequestMethod.POST)
//	public @ResponseBody Message deletes(String[] ids) {
//		try {
//			for (int i = 0; i < ids.length; i++) {
//				Integer id = Integer.parseInt(ids[i]);
//				userService.delete(id);
//			}
//			return SUCCESS_MESSAGE;
//		} catch (Exception e) {
//			return Message.error(e.getMessage());
//		}
//	}
//	
//	@RequestMapping(value = "/user/enable", method = RequestMethod.POST)
//	public @ResponseBody Message enable(Integer id) {
//		try {
//			userService.state(id, 1);
//			return SUCCESS_MESSAGE;
//		} catch (ServiceException e) {
//			return Message.error(e.getMessage());
//		}
//	}
//	
//	@RequestMapping(value = "/user/disabled", method = RequestMethod.POST)
//	public @ResponseBody Message disabled(Integer id) {
//		try {
//			userService.state(id, 0);
//			return SUCCESS_MESSAGE;
//		} catch (ServiceException e) {
//			return Message.error(e.getMessage());
//		}
//	}
//	
//	// AJAX唯一验证
//	@RequestMapping(value = "/user/check_username", method = RequestMethod.GET)
//	public @ResponseBody String checkUsername(String oldUsername, String username) {
//		if (userService.isUniqueByUsername(oldUsername, username)) {
//			return ajax("true");
//		} else {
//			return ajax("false");
//		}
//	}
//	
//	@RequestMapping(value = "/user/upload", method = RequestMethod.GET)
//	public String upload(Model model, HttpServletResponse response) throws Exception {
//		return "user/upload";
//	}
//	
//	@RequestMapping(value = "/user/mail", method = RequestMethod.POST)
//	public @ResponseBody
//	Message mail(Integer id) {
//		User user = userService.get(id);
//		if(null == user.getEmail()) {
//			return Message.error("该用户还未填写邮件地址！");
//		}
//		String password = DES.decrypt(user.getPassword());
//		user.setPassword(password);
//		try {
//			MimeMessage mailMessage = mailSender.createMimeMessage();
//			// 设置utf-8或GBK编码，否则邮件会有乱码
//			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
//
//			messageHelper.setTo(user.getEmail()); // 接受者
//			messageHelper.setFrom(PropertyConfigurer.getValue("mail.username")); // 发送者
//			messageHelper.setSubject("运营平台账号开通"); // 主题
//			// 邮件内容，注意加参数true，表示启用html格式
//			String htmlString = this.buildString(user);
//			messageHelper.setText(htmlString, true);
//			mailSender.send(mailMessage);
//			return SUCCESS_MESSAGE;
//		} catch (Exception e) {
//			return Message.error("邮件发送失败！");
//		}
//
//	}
//	
//	public String buildString(User user) {
//		try {
//			String templatePath = "/content/user/mail.ftl";
//			Map<String, Object> data = new HashMap<String, Object>();
//			data.put("user", user);
//			data.put("serverName", PropertyConfigurer.getValue("serverName"));
//			//Configuration configuration = new Configuration();
//			Template template = freemarkerConfig.getConfiguration().getTemplate(templatePath);
//			//FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
//			StringWriter out = new StringWriter();
//			template.process(data, out);
//			return out.getBuffer().toString();
//		} catch (Exception e) {
//			return null;
//		}
//	}
	
}
