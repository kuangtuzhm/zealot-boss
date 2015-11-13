<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>角色列表</title>

<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />

<script type="text/javascript">

</script>
</head>

<body>
<!-- start of con_right_main -->
 <div class="pop_main" id="popOwnersCollectionTab" style="width:600px;border: 0px solid;">
        <div class="pop_ownersCollection_con">
            <div class="pop_detailTable_show">

			<form id="inputForm" method="post" action="add_role">
			<input type="hidden" name="id" value="${uid}" />
	        <table class="add_list_table input tabContent">
	        	<#assign num=0>
	            <#list ROLE_LIST as role>
	            <#if num%3==0>
	            	<tr>
	            </#if>
	                <td>
	                	<input id="userRole${role.id}" name="roleIds" <#if role.hasRole>checked</#if> value='${role.id}' type='checkbox' >${role.name}
	                </td>
				<#if num%3==2>
					</tr>
	            </#if>
	            <#assign num=num+1>
				</#list>
	        </table>
        <!-- end of add_list_table -->
        
        <span class="con_box_BR"></span>
    
    <!-- end of con_search -->
    
    <!-- start of main_slide_con -->
    <div class="main_slide_con">
    	<input type="submit" class="btnC" value="确定" />
        <a class="btnD" href="#" onclick="art.dialog.close();">取消</a>
    </div>
    </div>
    </form>
</div>
</div>
</body>
</html>