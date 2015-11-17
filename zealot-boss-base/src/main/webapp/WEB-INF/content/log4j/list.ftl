<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>日志列表</title>

<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />

<script type="text/javascript">
	$().ready(function(){
		$("#listTable .btn_edit").bind("click", function(){
			var logname = $(this).attr("logname");
			art.dialog.open('/log4j/edit_level?name=' + logname, {
				id: 'edit_level',
				title: '日志级别设定',
				close: function () {
					callback();
				}
			}, false);
		});
	});
	
	function callback() {
	 	$("#searchButton").click();
	}
</script>
</head>

<body>
<!-- start of con_right_main -->
<div class="con_right_main">

	<form id="listForm" action="list" method="post">
	
    <!-- start of con_search -->
	<div class="con_search">
    	<span class="con_box_BL"></span>
        
        <!-- start of con_search_top -->
        <div class="con_search_top clearfix">
        	<div class="con_search_top_L left">
                <p>
                	<span class="group"><label>名称：</label>
                    	<input type="text" name="name" class="c_input_text" style="width:140px;" realValue="输入名称" value="${(name)!''}" />
                    </span>
                    <span class="group"><a id="searchButton" href="javascript:;" class="btn_search">搜索</a></span>
                </p>
            </div>
        </div>
        <!-- end of con_search_top -->
        
        <span class="con_box_BR"></span>
    </div>
    <!-- end of con_search -->
    
    <!-- start of table_list -->
    <table id="listTable" class="table_list list">
        <tr>
			<th width="15%" orderField="info.name">名称</th>
			<th width="10%" orderField="info.code">激活级别</th>
			<th width="10%" orderField="info.code">本身级别</th>
			<th width="5%">操作</th>
        </tr>
        <#list currentLoggers as logger>
        <tr class="even">
			<td>${logger.name}</td>
			<td>${(logger.effectiveLevel)!}</td>
			<td>${(logger.level)!}</td>
			<td>
				<a class="btn_icon btn_edit" href="javascript:void(0);" logname="${logger.name}" title="设定级别"></a>
			</td>
        </tr>
        </#list>
    </table>
    <!-- end of table_list -->
    
    
   
			
			
    </form>
</div>
<!-- end of con_right_main -->
</body>
</html>