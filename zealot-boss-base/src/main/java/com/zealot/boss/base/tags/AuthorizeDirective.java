package com.zealot.boss.base.tags;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zealot.boss.base.service.authorize.AuthorizationService;
import com.zealot.exception.AppException;
import com.zealot.web.freemarker.tags.BaseDirective;

import freemarker.core.Environment;
import freemarker.ext.beans.NumberModel;
import freemarker.ext.beans.StringModel;
import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("authorizeDirective")
public class AuthorizeDirective extends BaseDirective {

	private static final String VARIABLE_NAME = "hasRights";
	
	@Resource(name="authorizationService")
	private AuthorizationService authorizationService;

	@SuppressWarnings({ "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		StringModel menu = (StringModel)params.get("menuId");
		NumberModel adminNumber = (NumberModel)params.get("isAdmin");
		NumberModel uidNumber = (NumberModel)params.get("uid");
		String menuId = menu.toString();
		Integer isAdmin = adminNumber.getAsNumber().intValue();
		Integer uid = uidNumber.getAsNumber().intValue();
		boolean hasRights = false;
		if(isAdmin == 1) {
			hasRights = true;
		}
		else {
			try {
				hasRights = authorizationService.hasRight(uid, menuId);
			} catch (AppException e) {
				e.printStackTrace();
			}
		}
		setLocalVariable(VARIABLE_NAME, hasRights, env, body);
	}

}