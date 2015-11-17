<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>角色列表</title>

<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />

<script type="text/javascript">
	$().ready(function(){
		var $inputForm = $("#inputForm");
		$("#btn_pop_submitA").click(function() {
			$inputForm.submit();
			return false;
		});
	});
</script>
</head>

<body>
<form id="inputForm" method="post" action="update_level">
    <div id="auditTab" class="pop_main" style="width:600px;border: 0px solid;">
       <div class="pop_grouprmation_mod">
            <ul class="pop_list merchant_type_add">
            		<li class="clearfix">
                		<label class="tit">名称：</label>
                		<input class="c_input_text text" type="text" readonly="true" name="name" value="${log.name}">
               		</li> 
		            <li class="clearfix">
		            	<label class="tit">日志级别：</label>
		            	<select class="c_select" name="level" id="level">  
							<option value="DEBUG" <#if log.effectiveLevel=="DEBUG">selected</#if>>DEBUG</option>
							<option value="INFO" <#if log.effectiveLevel=="INFO">selected</#if>>INFO</option>
							<option value="WARN" <#if log.effectiveLevel=="WARN">selected</#if>>WARN</option>
							<option value="ERROR" <#if log.effectiveLevel=="ERROR">selected</#if>>ERROR</option>
							<option value="FATAL" <#if log.effectiveLevel=="FATAL">selected</#if>>FATAL</option>
		                </select>
		            </li>
            </ul>
        </div>
    </div>
    <div class="pop_footer">
        <a id="btn_pop_submitA" class="btn_pop_submitA" href="javascript:void(0)" onclick="formSub()">确定</a>
        <a id="btn_pop_submitB" class="btn_pop_submitB" href="javascript:void(0)" onclick="art.dialog.close();">取消</a>
    </div>
</form>
</body>
</html>