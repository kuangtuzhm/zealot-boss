<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>添加角色</title>
<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />

<link rel="StyleSheet" href="/css/dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="/js/dtree/dtree.js"></script>

<link rel="stylesheet" type="text/css" href="/css/zTreeStyle/zTreeStyle.css" >
<script type="text/javascript" src="/js/zTreeJs/jquery.ztree.core-3.5.js"></script>

<style type="text/css">
    
    ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4; width:250px; height:260px;overflow-y:scroll;overflow-x:auto;}
    
</style>

<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
		
	// 表单验证
	$inputForm.validate({
		rules: {
			"name": {
				required: true,
				remote: "check_rolename?oldRoleName=${(ROLE.name)!}"
			},
			"depart": "required"
		},
		messages: {
			"name": {
				remote: "角色已存在"
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

	<form id="inputForm" method="post" action="update">
	<input type="hidden" name="id" value="${ROLE.id}" />
	
    <!-- start of con_search -->
	<div class="con_search">
	
    	<span class="con_box_BL"></span>
        
        <!-- start of add_list_table -->
        <table class="add_list_table input tabContent">
            <tr>
                <th class="padT20">角色名称：</th>
                <td class="padT20"><input class="c_input_text text" type="text" name="name" value="${(ROLE.name)!}" maxlength="20"></td>
            </tr>            
            <tr>
                <th class="padB56">角色描述：</th>
                <td class="padB56">
                <textarea class="c_textarea auto_hint" name="description" cols="" rows="" realValue="输入文本...">${(ROLE.description)!}</textarea>
                <span class="in_num_text">0/200</span></td>
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