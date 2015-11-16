package com.zealot.boss.base.controller;

import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.appleframework.config.core.PropertyConfigurer;
import com.zealot.boss.base.entity.authorize.Department;
import com.zealot.boss.base.entity.authorize.Roles;
import com.zealot.boss.base.entity.authorize.User;
import com.zealot.boss.base.entity.logs.OperatorLogs;
import com.zealot.boss.base.service.authorize.DepartmentService;
import com.zealot.boss.base.service.authorize.RoleService;
import com.zealot.boss.base.service.authorize.UserService;
import com.zealot.common.CommonConsts;
import com.zealot.exception.AppException;
import com.zealot.exception.ResultException;
import com.zealot.orm.model.Pagination;
import com.zealot.util.StringUtil;
import com.zealot.web.bean.Message;

import freemarker.template.Template;

@Controller
public class UserMngController extends BaseController {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private UserService userService;
	
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private JavaMailSender mailSender;
	
	@Resource
	private FreeMarkerConfigurer freemarkerConfig;
	
	@RequestMapping(value = "/user/list")
	public String list(Model model, Pagination<User> page, User user, HttpServletResponse response) throws Exception {
		page = userService.queryUserList(user, page.getPageNo(), page.getPageSize());
		model.addAttribute("page", page);
		model.addAttribute("user", user);
		return "user/list";
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String add(Model model, HttpServletResponse response) throws Exception {
		List<Department> departmentList = departmentService.findAll();
		model.addAttribute("DEPART_LIST", departmentList);
		return "user/add";
	}
	
	@RequestMapping(value = "/user/save")
	public String save(Model model, User user, HttpServletRequest request) {
		
		user.setState(1);
		user.setIsAdmin(0);
		Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        user.setCreatedBy(this.getOperator(request).getId());
        user.setUpdatedBy(this.getOperator(request).getId());
        
        OperatorLogs operatorLogs = new OperatorLogs();
        operatorLogs.setOperatorType(CommonConsts.operObject.USER);
        operatorLogs.setOperAction(CommonConsts.operator.OPERATOR_MODIFY);
        operatorLogs.setUid(this.getOperator(request).getId());
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
            addErrorMessage(model, e.getMessage());
            return ERROR_VIEW;
        }
		addSuccessMessage(model, "添加用户成功", "list");
		return SUCCESS_VIEW;
	}

	
	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public String edit(Model model, Integer id, HttpServletResponse response) throws Exception {
        User user = userService.findUserByUid(id);
        model.addAttribute("USER", user);
		List<Department> departmentList = departmentService.findAll();
		model.addAttribute("DEPART_LIST", departmentList);
		return "user/edit";
	}
	
	@RequestMapping(value = "/user/view", method = RequestMethod.GET)
	public String view(Model model, Integer id, HttpServletResponse response) throws Exception {
        User user = userService.findUserByUid(id);
        model.addAttribute("USER", user);
		return "user/view";
	}
	
	@RequestMapping(value = "/user/update")
	public String update(Model model, User user, HttpServletRequest request, HttpServletResponse response) {
		try {
			user.setUpdatedBy(this.getOperator(request).getId());
			userService.updateUser(user);
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			addErrorMessage(model, e.getMessage());
			return ERROR_VIEW;
		}
		addSuccessMessage(model, "修改用户成功", "list");
		return SUCCESS_VIEW;
	}
	
	@RequestMapping(value = "/user/rolelist")
	public String rolelist(Model model, Integer id, HttpServletResponse response) throws Exception {
		List<Roles> allRole = roleService.getRoles(1);
		List<Roles> userRole = roleService.getUserRoles(id);
		setUserRole(allRole,userRole);
		model.addAttribute("ROLE_LIST", allRole);
		model.addAttribute("uid", id);
		return "user/list_role";
	}
	
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer id, HttpServletRequest request) {
		
		try {
			userService.delete(id, this.getOperator(request));
			return SUCCESS_MESSAGE;
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			return Message.error(e.getMessage());
		} catch (ResultException e) {
			return Message.error(e.getMessage());
		}
	}
	
	
	
	@RequestMapping(value = "/user/add_role", method = RequestMethod.POST)
	public String addRole(Integer id, Integer [] roleIds, HttpServletRequest request) {
		
		try {
			roleService.addUserRole(id, roleIds);
			return SUCCESS_AJAX;
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			return ERROR_AJAX;
		}
	}
	
//	@RequestMapping(value = "/user/deletes", method = RequestMethod.POST)
//	public @ResponseBody Message deletes(String[] ids, HttpServletRequest request) {
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
	
	@RequestMapping(value = "/user/enable", method = RequestMethod.POST)
	public @ResponseBody Message enable(Integer id, HttpServletRequest request) {
		try {
			User user = new User();
			user.setState(1);
			user.setUid(id);
			user.setUpdatedBy(this.getOperator(request).getId());
			userService.updateUserState(user);
			return SUCCESS_MESSAGE;
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			return Message.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/user/disabled", method = RequestMethod.POST)
	public @ResponseBody Message disabled(Integer id, HttpServletRequest request) {
		try {
			User user = new User();
			user.setState(0);
			user.setUid(id);
			user.setUpdatedBy(this.getOperator(request).getId());
			userService.updateUserState(user);
			return SUCCESS_MESSAGE;
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			return Message.error(e.getMessage());
		}
	}
	
	// AJAX唯一验证
	@RequestMapping(value = "/user/check_username", method = RequestMethod.GET)
	public @ResponseBody String checkUsername(String oldUsername, String uname) {
		try {
			if (userService.isUniqueByUsername(oldUsername, uname)) {
				return ajax("true");
			} else {
				return ajax("false");
			}
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			return ajax("true");
		}
	}
	
//	@RequestMapping(value = "/user/upload", method = RequestMethod.GET)
//	public String upload(Model model, HttpServletResponse response) throws Exception {
//		return "user/upload";
//	}
//	
	@RequestMapping(value = "/user/mail", method = RequestMethod.POST)
	public @ResponseBody
	Message mail(Integer id) {
		try {
			User user = userService.findUserByUid(id);
			if(StringUtil.isEmpty(user.getEmail())) {
				return Message.error("该用户还未填写邮件地址！");
			}
			//String password = MD5..(user.getPwd());
			//user.setPassword(password);
			
			MimeMessage mailMessage = mailSender.createMimeMessage();
			// 设置utf-8或GBK编码，否则邮件会有乱码
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");

			messageHelper.setTo(user.getEmail()); // 接受者
			messageHelper.setFrom(PropertyConfigurer.getValue("mail.username")); // 发送者
			messageHelper.setSubject("运营平台账号开通"); // 主题
			// 邮件内容，注意加参数true，表示启用html格式
			String htmlString = this.buildString(user);
			messageHelper.setText(htmlString, true);
			mailSender.send(mailMessage);
			return SUCCESS_MESSAGE;
		} catch (AppException e) {
			logger.error(e.getMessage(),e);
			return Message.error("邮件发送失败！");
		}
		catch (Exception e) {
			logger.error(e.getMessage(),e);
			return Message.error("邮件发送失败！");
		}

	}
	
	public String buildString(User user) {
		try {
			String templatePath = "/content/user/mail.ftl";
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("user", user);
			data.put("serverName", PropertyConfigurer.getValue("serverName"));
			//Configuration configuration = new Configuration();
			Template template = freemarkerConfig.getConfiguration().getTemplate(templatePath);
			//FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
			StringWriter out = new StringWriter();
			template.process(data, out);
			return out.getBuffer().toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	private void setUserRole(List<Roles> allRoles,List<Roles> userRoles)
	{
		if(allRoles!=null && userRoles!=null && allRoles.size()>0 && userRoles.size()>0)
		for(Roles role : allRoles)
		{
			for(Roles urole : userRoles)
			{
				if(role.getId()==urole.getId())
				{
					role.setHasRole(true);
					break;
				}
			}
		}
	}
}
