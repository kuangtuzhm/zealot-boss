<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>添加角色</title>
<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />

<link rel="stylesheet" type="text/css" href="/css/zTree/zTreeStyle.css" >
<script type="text/javascript" src="/js/zTreeJs/jquery.ztree.all-3.5.js"></script>

<style type="text/css">    
    ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4; width:250px; height:260px;overflow-y:scroll;overflow-x:auto;}
    
</style>


<script type="text/javascript">
		var setting = {
			check: {
				enable: true
			},
			view: {
				dblClickExpand: false
			},
			data: {
				key: {
					name: "rightDesc"
				},
				simpleData: {
					enable: true,
					idKey: "rightCode",
					pIdKey: "parentCode"
				}
			}
		};
		
	var id='';
	function getGroupRole(roleId,obj)
	{
		id='';
		$("#roles li").removeClass();
		$.ajax({ 
           type: "post", 
           url: "role_right?id="+roleId, 
           dataType: "json", 
           success: function (data) { 
           		if(data.msg)
           		{
           			pop_error("操作失败", data.msg,function() {
								} ,false);
           		}
           		else
           		{
                	$.fn.zTree.init($("#tree_roleRight"), setting, data);
                	$(obj).parent("li").addClass("ball_blue");
                	id=roleId;
                }
           }, 
           error: function (XMLHttpRequest, textStatus, errorThrown) { 
           		pop_error("操作失败", errorThrown,function() {
								} ,false);
           }
       });
	}
	
	function addOrDeleteRight(){
		if(id==''){
			alert('请选择角色');
			return;
		}			
		 var str="";
         var zTree=$.fn.zTree.getZTreeObj("tree_roleRight");
         var nodes = zTree.getCheckedNodes(true);
         var len = nodes.length;
         for(var i=0; i<len; i++) 
         {
         	str += nodes[i].rightCode+",";
         }
         
     	 var rightCode=str.substr(0,str.lastIndexOf(','));
     	 if(rightCode.length==0){
			alert('请选择权限');
			return;
        }
        $.ajax({ 
           type: "post", 
           url: "/role/save_role_right", 
           dataType: "json", 
           data: {"id":id,"rightCode":rightCode},
           success: function (data) { 
           		if(data.stats=="ok")
           		{
           			pop_succeed("操作成功", "角色权限设置成功。", function() {
								}, false);
           		}
           		else
           		{
           			pop_error("操作失败", data.msg,function() {
								} ,false);
                }
           }, 
           error: function (XMLHttpRequest, textStatus, errorThrown) { 
                pop_error("操作失败", errorThrown,function() {
								} ,false);
           }
       });
	}
</script>

</head>

<body>
<!-- start of con_right_main -->
<div class="con_right_main">

	<form id="inputForm" method="post" action="save">
	<input type="hidden" name="state" value="1" />
	
    <!-- start of con_search -->
	<div class="con_search">
	
    	<span class="con_box_BL"></span>
        
        <!-- start of add_list_table -->
        <table class="add_list_table input tabContent">
            <tr>
            	<td>
            		<ul id="roles" class="ztree">
						<#list ROLE_LIST as role>
							<div><li><a href="#" onclick="getGroupRole('${role.id}',this)">${role.name }</a></li></div>
						</#list>
					</ul>
            	</td>
                <td>
               		<ul id="tree_roleRight" class="ztree"></ul>
                </td>
            </tr>
            
        </table>
        <!-- end of add_list_table -->
        
        <span class="con_box_BR"></span>
    </div>
    <!-- end of con_search -->
    
    <!-- start of main_slide_con -->
    <div class="main_slide_con">
    	<input type="button" class="btnC" onclick="addOrDeleteRight()" value="保存" />
    </div>
    <!-- end of main_slide_con -->
    
    </form>
</div>
<!-- end of con_right_main -->

</body>
</html>