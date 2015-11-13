<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>角色详情</title>
<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />
</head>

<body>
<!-- start of con_right_main -->
<div class="con_right_main">

	<form id="inputForm" method="post" action="update">
	<input type="hidden" name="id" value="${ROLE.id}" />
	
    <!-- start of con_search -->
	<div class="con_search">
	
    	<span class="con_box_BL"></span>
        
        <!-- start of add_list_table -->
        <table class="add_list_table input tabContent">
            <tr>
                <th class="padT20">角色名称：</th>
                <td class="padT20">${(ROLE.name)!}</td>
            </tr>            
            <tr>
                <th class="padB56">角色描述：</th>
                <td class="padB56">${(ROLE.description)!}</td>
            </tr>  
        </table>
        <!-- end of add_list_table -->
        
        <span class="con_box_BR"></span>
    </div>
    <!-- end of con_search -->
    
    <!-- start of main_slide_con -->
    <div class="main_slide_con">
        <a class="btnD" href="#" onclick="location.href='list'">取消</a>
    </div>
    <!-- end of main_slide_con -->
    
    </form>
</div>
<!-- end of con_right_main -->




</body>
</html>