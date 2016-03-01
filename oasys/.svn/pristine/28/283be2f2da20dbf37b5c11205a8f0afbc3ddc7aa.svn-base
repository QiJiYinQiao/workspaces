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
	<script type="text/javascript" src="jsp/pd/empSalPositionChgApp/empSalJS.js"></script>
	<style type="text/css">
		a{
			text-decoration:none;
		}
	</style>
	<script type="text/javascript">
			//声明一个全局变量row
			//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。
			<%String definitionKey=request.getParameter("key");%>//流程变量
		 	$(function() {
				//加载员工薪资岗位变动申请的数据
				 $dg = $("#dg");
				$(window).resize(function(){  
		            $("#dg").datagrid({  
		            	height: $(window).height()-100,
		            	width : 'auto'
		            });                
		        });
				 $grid=$dg.datagrid({
					url : "empSalPositionChgAppController/findEmpSalTask.do?definitionKey=<%=definitionKey%>",
					title: "员工薪资岗位变动申请",
					width : 'auto',
					height : $(this).height()-83,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:false,
					nowrap:true,
					multiSort:false,
					border:false,
					fitColumns:false,
					pageSize:30,
					pageList:[10,20,30,40],
					columns : [ [
								{field : 'ck',        title : 'ck',    width : $(this).width * 0.1, align:'center',checkbox:true},
								{field : 'efaId',        title : '编号',    width : 0, align:'center'},
							  	{field : 'taskID',        title : 'TASK_ID',    width : 0, align:'center'},
				 	 			{field : 'formKey',        title : 'FORM_KEY',    width : 0, align:'center' },
			    				{field : 'curDeptName',        title : '目前部门名称',    width : parseInt($(this).width() * 0.1), align:'center' },
								{field : 'appNo',        title : '申请编号',    width : parseInt($(this).width() * 0.08), align:'center'},
								{field : 'userName',        title : '申请人',    width : parseInt($(this).width() * 0.06), align:'center'},
								{field : 'appDate',        title : '申请时间',    width : parseInt($(this).width() * 0.06), align:'center'},
								{field : 'curDept',        title : '目前部门名称',    width : parseInt($(this).width() * 0.1), align:'center',
									formatter:function(value,row,index){
										return row.curDeptName;
								}},
			    				{field : 'curPosition',      title : '目前所在职位', width : parseInt($(this).width() * 0.07), align:'center'}, 
			    				{field : 'curSal',    title : '目前薪资(元)',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'entryDate',    title : '入职日期',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'chgTypeName',    title : '异动事由',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'aftDeptName',    title : '调整后部门名称',    width : parseInt($(this).width() * 0.08), align:'center' },
			    				{field : 'aftPosition',    title : '调整后所在职位',    width : parseInt($(this).width() * 0.08), align:'center' },
			    				{field : 'aftSal',    title : '调整后薪资',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'chgResonName',    title : '变动理由',    width : parseInt($(this).width() * 0.08), align:'center',
			    					formatter:function(value,row,index){
			    						//判断如果为其他 则显示 其他变动理由 字段
			    						if(row.chgReson == "4"){
			    							return row.chgResonOther;
			    						}else{
			    							return row.chgResonName;
			    						}
			    					} },
			    				{field : 'salChgTypeName',    title : '薪资调整形势',    width : parseInt($(this).width() * 0.08), align:'center' },
			    				{field : 'effectiveDate',    title : '申请生效日期',    width : parseInt($(this).width() * 0.08), align:'center' },
			    				{field : 'trialPeriods',    title : '试岗期限(月)',    width : parseInt($(this).width() * 0.06), align:'center' },
			    				{field : 'trialSal',    title : '试岗月薪(元)',    width : parseInt($(this).width() * 0.06), align:'center' },
						         {field : 'sqzt',    title : '申请状态',    width : parseInt($(this).width() * 0.05), align:'center',
						        	 formatter: function(value,row,index){
						        		 var result = "";
											if (row.procStatus == 1) {
												result = "初始状态";
											} else if (row.procStatus == 3) {
												result = "审批完成";
											} else if (row.procStatus == 5) {
												result = "审批撤销";
											} else {
												result = "审批中";
											}
											return result;
					      				}
						        	 },
							          {field : 'caozuo',    title : '操作',    width : parseInt($(this).width() * 0.18), align:'center',
							        	 	formatter: function(value,row,index){
							        		   	var result = "";
												if (row.assistant == null || row.assistant == "") {
													result += "<a href='javascript:void(0);' onclick='singForTask("+row.taskID+");'>签收任务</a>　";
												}else{
													result += "<a href='javascript:void(0);' onclick='saveTask("+index+");'>受理任务</a>　";
												}
							        		   	result += "<a href='javascript:void(0);' onclick='showInvestProductDetails("+ index + ")';>查看审批意见</a>　"
							        		   	result += "<a href='javascript:void(0);' onclick='showImage("+index+");'>查看当前流程图</a>"
												return result; 
						      				}
							          }						        	 
					              ] ],
		              toolbar:'#tb',
		              onClickCell:function(rowIndex, field, value){
		            	  $(this).datagrid("selectRow","rowIndex");
		              },onLoadSuccess:function(data){
		            	  
		            	  var mergeMap = getMergeMap( $(this).datagrid("getRows"),'curDeptName','');
		            	  mergeFunc($(this),mergeMap,'curDeptName');
		            	  /* 
		            	  var paramObj = {rowStr:'curDeptName',//按照哪列进行分组
  			  					rowGroup:'ck,curDept'};//对应rowStr的需要合并单元格的字段
  			  					/* hejiColumn:'ppeGross',//需要计算的合计值的所在列
  			  					hejiResult:'heji'};//计算后将合计值更新到该列中 
					  	  dataGirdSumMergeFunc($(this),paramObj); */
					    }
				});
				$('#dg').datagrid("doCellTip",{'max-width':'100px'});
				$('#dg').datagrid('hideColumn','formKey');
				$('#dg').datagrid('hideColumn','taskID');
				$('#dg').datagrid('hideColumn','efaId');
			});  
	
			
		
		//批量处理任务
		function signTask(){
			var rows = $grid.datagrid("getSelections");
			var businessID = new Array();
			var formKey = "";
			var taskID = new Array();
			var appNo = new Array;
			if(rows==null || rows.length<=0){
				$.messager.alert("提示","请选择至少一条记录!","warning");
				return false;
			}
			for(var i=0;i<rows.length;i++){
				if($.inArray(rows[i].efaId,businessID)==-1){
					businessID.push(rows[i].efaId);
					formKey = rows[i].formKey;
					taskID.push(rows[i].taskID);
					appNo.push(rows[i].appNo);
				}
			}
			var data1 = "appNo="+appNo.join(",")+"&businessID="+businessID.join(",")+"&taskID="+taskID.join(",")+"&formKey="+formKey;
			ajaxLoading("正在处理 请稍后...");
		 	  $.ajax({
				type: "POST",
				url:"empSalPositionChgAppController/saveTaskEmpSalBatch.do",
				data:data1,
			    success: function(iJson) {
			    	$("#dg").datagrid("load",{});
			    	ajaxLoadEnd();
		 	    	$.messager.alert(iJson.title,iJson.message,'info');
			    }
			});  
		}

	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      	<div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理 -> 人力资源规划 -> 员工薪资岗位变动申请</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="empSalPositionChgAppController/findAllEmpSalApp.do" method="post">
			<input type="hidden"  name="appNoShow" id="appNoShow" />
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
						<td>申请日期：&nbsp;&nbsp;</td>
						<td><input name="appDateBefore" id="appDateBefore" class="easyui-datebox" editable="false" style="width:174px;" value=""  title="开始日期" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;到：&nbsp;&nbsp;</td>
						<td><input name="appDateAfter" id="appDateAfter" class="easyui-datebox" editable="false" style="width:174px;" value="" title="结束日期"/></td>
						<td width="70px"></td>
						<td colspan="4" align="right">
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">条件重置</a>
						</td>	
					</tr>	
				</table>
			</form>			  			
		</div>
		<div id="tb" style="padding:2px 0">
			<a id="id4ExportReports" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="signTask();">批量受理任务</a>
		</div>
		<!-- 表格区域 -->
		<table id="dg"  width="100%"></table>	
  	</div>	
  	<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
	<img id="image" src="" >
	</div>
  </body>
</html>
