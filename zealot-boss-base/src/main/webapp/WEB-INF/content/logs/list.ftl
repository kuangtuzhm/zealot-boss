<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>日志列表</title>

<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
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
                    <span class="group">
                    	<label>操作类型：</label>
                    	<select class="c_select" name="operatorLogs.operAction" id="operAction" style="width:140px;">  
                    		<option value="">全部</option>
                    		<#if operActionMap?exists>	            		
		            		<#list operActionMap.keySet() as key> 
								<option value="${key}" <#if (logsBean.operatorLogs.operAction)?? && logsBean.operatorLogs.operAction == key>selected</#if>>${(operActionMap.get(key))!}</option>  
							</#list>
							</#if>
		                </select>
                    </span>
                    <span class="group"><label>操作日期：</label>
                    	<input name="beginDate" id="beginDate" value="${((logsBean.beginDate))!''}" class="c_input_text" type="text" realValue="输入开始时间"
                    	onFocus="WdatePicker({dt:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'})" style="width:140px;"/>
                    	-
                    	<input name="endDate" id="endDate" value="${((logsBean.endDate))!''}" class="c_input_text" type="text" realValue="输入结束时间" 
                    	onFocus="WdatePicker({dt:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'beginDate\')}'})" style="width:140px;" />
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
			<th width="6%" orderField="info.name">用户id</th>
			<th width="10%" orderField="info.code">操作时间</th>
			<th width="6%"  orderField="info.code">IP地址</th>
			<th width="15%" orderField="info.code">操作类型</th>
			<th width="10%" orderField="info.code">操作信息</th>
        </tr>
        
        <#list page.list as logs>
        <tr class="even">
			<td>${logs.uid}</td>
			<td>${(logs.createTime)!}</td>
			<td>${(logs.ip)!}</td>
			<td>
				${(operActionMap.get(logs.operAction))!}
			</td>
			<td>${logs.operatorDesc}</td>
        </tr>
        </#list>
        
    </table>
    <!-- end of table_list -->
    
    
    <#if (page.list?size > 0)>
    
    	<!-- start of table_bottom -->
	    <div class="table_bottom clearfix">
	    	<!--
	    	<div class="table_bottom_checkbox left">
	    		<input id="selectAll" name="" type="checkbox" value=""><a class="btn" href="#" id="deleteAll">删除选中</a>
	    	</div>
	        -->
	   		<!-- start of 分页 -->
	   		<@paging pageNumber = page.pageNo totalPages = page.totalPage>
				<#include "../commons/pager.ftl">
			</@paging>
	        <!-- end of 分页 -->
	    </div>
	    <!-- end of table_bottom -->
			
	<#else>
		<div class="noRecord">没有找到任何记录!</div>
	</#if>
			
			
    </form>
</div>
<!-- end of con_right_main -->
</body>
</html>