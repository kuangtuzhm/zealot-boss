<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>用户详情</title>
<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />

</head>

<body>
<!-- start of con_right_main -->
<div class="con_right_main">

	<form id="inputForm" method="post" action="update">
	
    <!-- start of con_search -->
	<div class="con_search">
	
    	<span class="con_box_BL"></span>
        
        <!-- start of add_list_table -->
        <table class="add_list_table input tabContent">
            <tr>
                <th class="padT20">用户名：</th>
                <td class="padT20">${(USER.loginName)!}</td>
            </tr>
            <tr>
                <th class="padT20">真实姓名：</th>
                <td class="padT20">${(USER.uname)!}</td>
            </tr>
            <tr>
                <th class="padT20">管理员：</th>
                <td class="padT20">
                	<#if USER.isAdmin == 0>否</#if>
                	<#if USER.isAdmin == 1>是</#if>
                </td>
            </tr>
            <tr>
                <th class="padT20">手机：</th>
                <td class="padT20">${(USER.phoneNum)!}</td>
            </tr>
            <tr>
                <th class="padT20">邮箱：</th>
                <td class="padT20">${(USER.email)!}</td>
            </tr>
            <tr>
                <th class="padT20">所属部门：</th>
                <td class="padT20">${(USER.department.name)!}</td>
            </tr>
            <tr>
                <th class="padT20">状态：</th>
                <td class="padT20">
                	<#if USER.state == 1>正常</#if>
                	<#if USER.state == 0>禁用</#if>
                </td>
            </tr>
        </table>
        <!-- end of add_list_table -->
        
        <span class="con_box_BR"></span>
    </div>
    <!-- end of con_search -->
    
    <!-- start of main_slide_con -->
    <div class="main_slide_con">
        <a class="btnD" href="#" onclick="location.href='list'">返回</a>
        
    </div>
    <!-- end of main_slide_con -->
    
    </form>
</div>
<!-- end of con_right_main -->




</body>
</html>