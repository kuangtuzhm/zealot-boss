<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>更改总日志级别</title>

<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />

<script type="text/javascript">
	$().ready(function(){
		$("#btn_pop_submitA").click(function() {
			var level = $("#level").val();
			$.ajax({
						type : "post",
						url : "update_rootlevel?level="+level,
						dataType: "json",
						cache : false,
						success: function(data){
							console.log("return data of munu delete:%s",data);
							if(data.type == 'success') {
								pop_succeed("操作成功", "更改总日志级别成功。", function() {
									location.reload();
								}, false);
							}
							else {
								pop_error("操作失败", data.content,function() {
								} ,false);
							}
						}					
					});
		});
	});
</script>
</head>

<body>
<div class="con_right_main">

	<form id="inputForm" method="post" action="update_rootlevel">
	
    <!-- start of con_search -->
	<div class="con_search">
	
    	<span class="con_box_BL"></span>
        
        <!-- start of add_list_table -->
        <table class="add_list_table input tabContent">
            <tr>
                <th class="padT20">名称：</th>
                <td class="padT20">
                	<input class="c_input_text text" type="text" readonly="true" name="name" value="${log.name}">
                </td>
            </tr>
            <tr>
                <th class="padT20">日志级别：</th>
                <td class="padT20">
					<select class="c_select" name="level" id="level">  
						<option value="DEBUG" <#if log.effectiveLevel=="DEBUG">selected</#if>>DEBUG</option>
						<option value="INFO" <#if log.effectiveLevel=="INFO">selected</#if>>INFO</option>
						<option value="WARN" <#if log.effectiveLevel=="WARN">selected</#if>>WARN</option>
						<option value="ERROR" <#if log.effectiveLevel=="ERROR">selected</#if>>ERROR</option>
						<option value="FATAL" <#if log.effectiveLevel=="FATAL">selected</#if>>FATAL</option>
		            </select>
				</td>
            </tr>
        </table>
        <!-- end of add_list_table -->
        
        <span class="con_box_BR"></span>
    </div>
    <!-- end of con_search -->
    
    <!-- start of main_slide_con -->
    <div class="main_slide_con">
    	<input id="btn_pop_submitA" type="button" class="btnC" value="确定" />
    </div>
    <!-- end of main_slide_con -->
    
    </form>
</div>
<!-- end of con_right_main -->

<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:200px;"></ul>
</div>

</body>
</html>