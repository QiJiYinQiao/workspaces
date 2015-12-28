<%@page import="com.oasys.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<jsp:include page="../../../layout/script.jsp"></jsp:include>
	<style type="text/css">
		a{
			text-decoration:none;
		}
	</style>
	<script type="text/javascript">
			//声明一个全局变量row
			//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。
			var $$row
			var $dg;
			var $dialog;
			var $grid;
			var procStateJson = [{ 
				"id":"", 
				"text":"全部状态", 
				"selected":true 
				},{ 
				"id":1, 
				"text":"初始状态" 
				},{ 
				"id":2, 
				"text":"审批中" 
				},{ 
				"id":3, 
				"text":"已完成"
				},{ 
				"id":4, 
				"text":"已失效" 
				},{ 
				"id":5, 
				"text":"已撤销" 
				},{ 
				"id":6, 
				"text":"已拒绝" 
				}] ;
		 	$(function() {
		 		
		 		$(window).resize(function(){  
		 	        $("#dg").datagrid({  
		 	        	height: $(window).height()-85
		 	        });                
		 	    });
		 		<%
			    String definitionKey=request.getParameter("key");
			    %>
				//加载固定资产报废申请的数据
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "businessTripApp/findBusinessTripAppTask.do?definitionKey=<%=definitionKey %>",
					width : 'auto',
					title: "出差申请",
					height : $(this).height()-85,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:false,
					nowrap:true,
					multiSort:false,
					border:false,
					fitColumns:true,
					columns : [ [
								{field : 'ck',title:'ck',checkbox:true},
							    {field : 'btaId',        title : '编号',    width : $(this).width * 0.1, align:'center'},
								 {field : 'taskID',        title : 'TASK_ID',    width : $(this).width * 0.1, align:'center'},
							 	 {field : 'formKey',        title : 'FORM_KEY',    width : 60, align:'center'},
			      				{field : 'appNo',title : '申请编号',width :120,align : 'center'},
			      				{field : 'appDate',title : '申请日期',width : 100,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.appDate.split(" ")[0];
					      				}},
				                {field : 'userName',title : '申请人',width : 120,align : 'center'},
					            {field : 'deptName',title : '申请部门',width : 120,align : 'center'},
					            {field : 'btReason',title : '出差事由',width : 120,align : 'center'},
					            {field : 'agentName',title : '职务代理人',width : 120,align : 'center'},
					            {field : 'btOrig',title : '出差始发地',width : 120,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.businessTripAttach.btOrig;
					      				}},
			      				{field : 'btDest',title : '出差目的地',width : 120,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.businessTripAttach.btDest;
					      				}},
			      				{field : 'planBgDtime',title : '计划开始时间',width : 120,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.businessTripAttach.planBgDtime;
					      				}},
			      				{field : 'planEdDtime',title : '计划结束时间',width : 120,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.businessTripAttach.planEdDtime;
					      				}},
			      				{field : 'planBtDays',title : '计划天数',width : 120,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.businessTripAttach.planBtDays;
					      				}},
			      				{field : 'realBgDtime',title : '实际开始时间',width : 120,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.businessTripAttach.realBgDtime;
					      				}},
			      				{field : 'realEdDtime',title : '实际结束时间',width : 120,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.businessTripAttach.realEdDtime;
					      				}},
			      				{field : 'realBtDays',title : '实际天数',width : 120,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.businessTripAttach.realBtDays;
					      				}},
					      				{field : 'vehicle',title : '交通工具',width : 120,align : 'center',
								        	 formatter: function(value,row,index){
								        		 if(row.businessTripAttach.vehicle=="1"){
								        			 return "出租车";
								        		 }else if(row.businessTripAttach.vehicle=="2"){
								        			 return "公共汽车";
								        		 }else if(row.businessTripAttach.vehicle=="3"){
								        			 return "火车";
								        		 }else if(row.businessTripAttach.vehicle=="4"){
								        			 return "飞机";
								        		 }else if(row.businessTripAttach.vehicle=="4"){
								        			 return "船只";
								        		 }else{
								        			 return "其他";
								        		 }
							      				}},
			      				{field : 'vehicleOther',title : '其它交通工具',width : 120,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.businessTripAttach.vehicleOther;
					      				}},
				                {field : 'procStatus',title : '流程状态',width : 80,align : 'center',formatter:function(value,row,index){
				                	if(row.procStatus == "1"){
				                		return "初始状态";
				                	}else if(row.procStatus == "2"){
				                		return "审批中";
				                	}else if(row.procStatus == "3"){
				                		return "已完成";
				                	}else if(row.procStatus == "4"){
				                		return "已失效";
				                	}else if(row.procStatus == "5"){
				                		return "已撤销";
				                	}else{
				                		return "已拒绝";
				                	}
				                }},
				                {field : 'remark',        title : '备注',    width : 120, align:'center'},
			      				{field : 'aa',    title : '操作',    width : $(this).width * 0.1, align:'center',
					        	 	formatter: function(value,row,index){
					        		   	var result = ""; 
										if (row.assistant == null || row.assistant == "") {
											result += "<a href='javascript:void(0);' onclick='singForTask("+row.taskID+");'>签收任务</a>　　";
										}else{
											result += "<a href='javascript:void(0);' onclick='saveTask("+index+");'>办理任务</a>　　";
										}
										return result; 
				      				}
					          }
				              ] ],
		              toolbar:'#tb',
		              onClickCell:function(rowIndex, field, value){
		            	  $(this).datagrid("selectRow","rowIndex");
		              },onLoadSuccess:function(data){
		                  $(this).datagrid("doCellTip",{'max-width':'100px'});
		              }
				});
				$('#dg').datagrid('hideColumn', 'btaId');
				$('#dg').datagrid('hideColumn','taskID');
 				$('#dg').datagrid('hideColumn','formKey');
				$('#procStatus').combobox({
					data:procStateJson,
					valueField:'id',
					textField:'text'
				});
			}); 
		
		 	//批量处理任务
			function signTask(){
				var rows = $grid.datagrid("getSelections");
				if(rows==null || rows.length<=0){
					$.messager.alert("提示","请选择至少一条记录!","warning");
					return false;
				}
				ajaxLoading("正在处理 请稍后...");
				var businessID = new Array();
				var formKey = "";
				var taskID = new Array();
				var appNo = new Array;
				
				for(var i=0;i<rows.length;i++){
					if($.inArray(rows[i].btaId,businessID)==-1){
						businessID.push(rows[i].btaId);
						formKey=rows[i].formKey;
						taskID.push(rows[i].taskID);
						appNo.push(rows[i].appNo);
					}
				}
				var data1 = "appNo="+appNo.join(",")+"&businessID="+businessID.join(",")+"&taskID="+taskID.join(",")+"&formKey="+formKey;
			 	  $.ajax({
					type: "POST",
					url:"businessTripApp/saveTaskBusinessTripAppBatch.do",
					data:data1,
				    success: function(iJson) {
				    	$("#dg").datagrid("load",{});
				    	ajaxLoadEnd();
			 	    	$.messager.alert(iJson.title,iJson.message,'info');
				    }
				});  
			}
		 	
		//执行高级查询
		function doSearch(){			
			$("#dg").datagrid("load",{
				appDept:$("#appDept").val(),
				procStatus:$("#procStatus").combobox("getValue"),
				appDateBefore:$('#appDateBefore').datebox('getValue'),
				appDateAfter:$('#appDateAfter').datebox('getValue')
			}); 
		}

		//重置条件
		function clearAdvancedQueryConditions(){
			//1、清空高级查询各组件内容
			$("#searchForm").form("clear");
			//2、datagrid重新加载
			$("#dg").datagrid("load",{});
		}
		
		
		// 根据索引获取每一行的基本信息
		function getRowData (index) {
	        if (!$.isNumeric(index) || index < 0) { return undefined; }
	        var rows = $grid.datagrid("getRows");
	        return rows[index];
	    }

		function saveTask(index){
			var selectedRow = getRowData(index);
			$selRow = selectedRow;
			$$row = $selRow;
			var formKey = selectedRow.formKey;
			$dialog=$("<div></div>").dialog({
				/* 动态显示Dialog的标题	*/
				width : 850,
				height : 450,					
				title : '受理任务',
				href : formKey,
			    onLoad:function(){
			    	var saveOrUpdateForm = $("#taskForm");
			    	saveOrUpdateForm.form("load",$selRow);
			    	saveOrUpdateForm.form("load",$selRow.businessTripAttach);
			    	$("#taskForm #businessID").val(selectedRow.btaId);
			    	$("#taskForm #taskID").val(selectedRow.taskID);
			    },
			    modal:true,
				resizable:false,
				iconCls:'icon-add',
				closed: false,
			    buttons : [{
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							$dialog.dialog('close');
					}
				}],onClose:function(){
			    	//刷新列表
			    	$("#dg").datagrid("reload");
			    	//关闭弹窗
			    	$(this).dialog('destroy');
			    }
			});
		}
		
		//签收任务
		function singForTask(taskID){
			$.messager.confirm('签收任务', '是否确认签收任务?', function(d) {
	    		if (d) {
	    			$.ajax( {
	    				type : "POST",
	    				url : 'businessTripApp/signForTask.do',
	    				data : "taskID="+taskID,
	    				dataType:'JSON',
	    				success : function(iJson) {		    					    				
	    					if(iJson.status){
	    						//刷新列表		    						
	    						$("#dg").datagrid("load",{});
	    					}
	    					parent.$.messager.alert(iJson.title,iJson.message,'info');
	    				}
		    			});
	    		}
	    	});		
		}		
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      	<div class="position" style="margin-top: 5px;">您当前所在位置： 任务管理-固定资产报废申请任务列表</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="businessTripApp/findBusinessTripAppTask" method="post">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
<!-- 						<td>所属部门：&nbsp;&nbsp;</td>
						<td><input name="appDept" id="appDept" type="text" class="easyui-textbox easyui-validatebox"  style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
						<td>申请状态：&nbsp;&nbsp;</td>
						<td><select id="procStatus" class="easyui-combobox" name="procStatus"  style="width: 170px;"></select>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>申请日期：&nbsp;&nbsp;</td>
						<td><input name="appDateBefore" id="appDateBefore" class="easyui-datebox" editable="true" style="width:174px;" value=""  title="开始日期" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;到：&nbsp;&nbsp;</td>
						<td><input name="appDateAfter" id="appDateAfter" class="easyui-datebox" editable="true" style="width:174px;" value="" title="结束日期"/></td>
						<td width="70px"></td>
						<td colspan="4" align="right">
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">条件重置</a>
						</td>	
					</tr>	
				</table>
			</form>			  			
		</div>
		<!-- 理财业绩数据表格区域 -->
		<table id="dg"  width="100%"></table>	
		<div id="tb" style="padding:2px 0">
			<a id="id4ExportReports" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="signTask();">批量受理任务</a>
		</div>
		<!-- 增加或修改理财对话框区域 -->
		<div id="saveOrUpdateInvestProductDialog"></div>	
  	</div>	
  </body>
</html>
