<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>添加菜单</title>
<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />

<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
		
	// 表单验证
	$inputForm.validate({
		rules: {
			"rightCode": "required",
			"rightDesc": "required",
			"sysCode" : "required",
			"iorder": {
				required: true,
				digits: true
			}
		},
		messages: {
			"iorder": {
				required: "必填",
				digits: "只允许输入正整数"
			}
		},
		submitHandler:function(form){
            form.submit();
        }
	});
		
	
});
</script>

</head>

<body>
<!-- start of con_right_main -->
<div class="con_right_main">

	<form id="inputForm" method="post" action="save">
	
    <!-- start of con_search -->
	<div class="con_search">
	
    	<span class="con_box_BL"></span>
        
        <!-- start of add_list_table -->
        <table class="add_list_table input tabContent">
        	<tr>
                <th class="padT20">系统编码：</th>
                <td class="padT20"><input class="c_input_text text" <#if parentCode != '0'>readonly="true"</#if> type="text" name="sysCode" value="${(PARENT_MENU.sysCode)!}"></td>
            </tr>
            <tr>
                <th class="padT20">父菜单编码：</th>
                <td class="padT20"><input class="c_input_text text" type="text" name="parentCode" value="${parentCode}" readonly="true"></td>
                <input type="hidden" name="path" value="${(PARENT_MENU.path)!}" />
            </tr>
        	<tr>
                <th class="padT20">菜单编码：</th>
                <td class="padT20"><input class="c_input_text text" type="text" name="rightCode" value=""></td>
            </tr>
            <tr>
                <th class="padT20">菜单名称：</th>
                <td class="padT20"><input class="c_input_text text" type="text" name="rightDesc" value=""></td>
            </tr>
            <tr>
                <th>打开模式：</th>
                <td>
                	<input name="openStyle" value='0' type='radio' checked="checked" >本系统
                	<input name="openStyle" value='1' type='radio' >新系统
                </td>
            </tr>
            
          	<#if parentCode == '0'>
          	<tr>
                <th>ICON地址：</th>
                <td><input class="c_input_text text" type="text" name="icon" value=""></td>
            </tr>
            <tr>
                <th>顶级菜单地址：</th>
                <td><input class="c_input_text text" type="text" name="baseUrl" value=""></td>
            </tr>
            <#else>
	            <#if PARENT_MENU.type == 3 >
	            <tr>
	                <th>菜单地址：</th>
	                <td>${(PARENT_MENU.baseUrl)!}<input class="c_input_text text" type="text" name="uri" value=""></td>
	            </tr>
	            <tr>
	                <th>是否隐藏左侧菜单：</th>
	                <td>
	                	<input name="isHidden" value='0' type='radio' checked="checked" >否
                		<input name="isHidden" value='1' type='radio' >是
	                </td>
	            </tr>
	            </#if>
            </#if>
            
            <tr>
                <th>排序：</th>
                <td><input class="c_input_text text" type="text" name="iorder" value="1"></td>
            </tr>
        </table>
        <!-- end of add_list_table -->
        
        <span class="con_box_BR"></span>
    </div>
    <!-- end of con_search -->
    
    <!-- start of main_slide_con -->
    <div class="main_slide_con">
    	<input type="submit" class="btnC" value="确定" />
        <a class="btnD" href="#" onclick="location.href='list'">取消</a>
        
    </div>
    <!-- end of main_slide_con -->
    
    </form>
</div>
<!-- end of con_right_main -->




</body>
</html>