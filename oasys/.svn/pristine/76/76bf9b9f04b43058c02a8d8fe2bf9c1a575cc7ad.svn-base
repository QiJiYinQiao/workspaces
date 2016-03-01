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
	<style type="text/css">
		a{
			text-decoration:none;
		}
	</style>
	<jsp:include page="../../../layout/script.jsp"></jsp:include>
	<script type="text/javascript" src="jsp/ad/ppeScrap/ppeScrapJs.js"></script>
	<script type="text/javascript">
			//声明一个全局变量row
			//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。
			<%String definitionKey=request.getParameter("key");%>//流程变量
		 	$(function() {
				//加载固定资产报废申请的数据
				 $dg = $("#dg");
				$(window).resize(function(){  
		            $("#dg").datagrid({  
		            	height: $(window).height()-100,
		            	width : 'auto'
		            });                
		        });
				 $grid=$dg.datagrid({
					url : "ppeScrapAppController/findPPEScrapTask.do?definitionKey=<%=definitionKey%>",
					width : 'auto',
					title: "固定资产报废申请",
					height : $(window).height()-83,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:true,
					nowrap:true,
					multiSort:false,
					border:false,
					fitColumns:true,
					columns : [ [
							  {field : 'psaId',        title : '编号',    width : 0, align:'center',
								        	 formatter: function(value,row,index){
								        		 return row.ppeApp.psaId;
								        	 }},
							  {field : 'taskID',        title : 'TASK_ID',    width : 0, align:'center',
							        	 formatter: function(value,row,index){
							        		 return row.ppeApp.taskID;
						      				}},
						 	 {field : 'formKey',        title : 'FORM_KEY',    width : 0, align:'center' ,
								        	 formatter: function(value,row,index){
								        		 return row.ppeApp.formKey;
							      				}},
							      				 {field : 'appNo',        title : '申请编号',    width : parseInt($(this).width() * 0.1), align:'center',
										        	 formatter: function(value,row,index){
										        		 return row.ppeApp.appNo;
									      				}},
									  {field : 'userName',        title : '申请人',    width : parseInt($(this).width() * 0.04), align:'center',
										        	 formatter: function(value,row,index){
										        		 return row.ppeApp.userName;
									      				}},
									  {field : 'orgName',        title : '申请部门',    width : parseInt($(this).width() * 0.06), align:'center',
										        	 formatter: function(value,row,index){
										        		 return row.ppeApp.orgName;
									      				}},
									  {field : 'appDate',        title : '申请时间',    width : parseInt($(this).width() * 0.07), align:'center',
												        	 formatter: function(value,row,index){
												        		 return row.ppeApp.appDate;
											      				}},
				    				{field : 'ppeNo',        title : '资产编号',    width : parseInt($(this).width() * 0.06), align:'center' },
				    				{field : 'ppeName',      title : '资产名称', width : parseInt($(this).width() * 0.07), align:'center'},
				    				{field : 'ppeModel',    title : '资产规格',    width : parseInt($(this).width() * 0.06), align:'center' },
				    				{field : 'buyDate',    title : '购买时间',    width : parseInt($(this).width() * 0.06), align:'center' },
				    				{field : 'usedYear',    title : '使用年限(年)',    width : parseInt($(this).width() * 0.07), align:'center' },
				    				{field : 'ppeGross',    title : '资产原值(元)',    width : parseInt($(this).width() * 0.07), align:'center' },
				    				{field : 'ppeNet',    title : '资产净值(元)',    width : parseInt($(this).width() * 0.07), align:'center' },
				    				{field : 'ppeSalvageVal',    title : '资产残值(元)',    width : parseInt($(this).width() * 0.07), align:'center' },
				    				{field : 'scrapReson',    title : '资产报废原因',    width : parseInt($(this).width() * 0.075), align:'center' },												      				
							         {field : 'sqzt',    title : '申请状态',    width : parseInt($(this).width() * 0.055), align:'center',
							        	 formatter: function(value,row,index){
							        		 var result = "";
												if (row.ppeApp.procStatus == 1) {
													result = "初始状态";
												} else if (row.ppeApp.procStatus == 3) {
													result = "审批完成";
												} else if (row.ppeApp.procStatus == 5) {
													result = "审批撤销";
												} else {
													result = "审批中";
												}
												return result;
						      				}
							        	 },
					          {field : 'caozuo',    title : '操作',    width : parseInt($(this).width() * 0.21), align:'center',
					        	 	formatter: function(value,row,index){
					        		   	var result = "";
										if (row.ppeApp.assistant == null || row.ppeApp.assistant == "") {
											result += "<a href='javascript:void(0);' onclick='singForTask("+row.ppeApp.taskID+");'>签收任务</a>		";
										}else{
											result += "<a href='javascript:void(0);' onclick='saveTask("+index+");'>受理任务</a>		";
										}
					        		   	result += "<a href='javascript:void(0);' onclick='showInvestProductDetails("+ index + ")';>查看审批意见</a>		"
					        		   	result += "<a href='javascript:void(0);' onclick='showImage("+index+");'>查看流程图</a>"
										return result; 
				      				}
					          }
					              ] ],
		              toolbar:'#tb',
		              onClickCell:function(rowIndex, field, value){
		            	  $(this).datagrid("selectRow","rowIndex");
		              },onLoadSuccess:function(data){
		            	  var rows = data.rows;
		                  var mergeMap = {};
		                  if(rows){
		                  	for(var i=0;i<rows.length;i++){
		                  		var appNo = rows[i].appNo
		                  		if( appNo in mergeMap ){
		                  			mergeMap[appNo].rowspan++;
		                  		}else{
		                  			mergeMap[appNo]={"index":i,"rowspan":1}
		                  		}
		                  	}
		                  }
		                  for(var i in mergeMap){
		                  	$(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'appNo',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                  	$(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'userName',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'orgName',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'appDate',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'caozuo',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'sqzt',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                  }
		              }
				});
				$('#dg').datagrid("doCellTip",{'max-width':'100px'});
				$('#dg').datagrid('hideColumn', 'psaId');
				$('#dg').datagrid('hideColumn','taskID');
 				$('#dg').datagrid('hideColumn','formKey');
			}); 
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      	<div class="position" style="margin-top: 5px;">您当前所在位置： 任务管理-固定资产报废申请任务列表</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="ppeScrapAppController/findAllppeScrap.do" method="post">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
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
		<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
		<img id="image" src="" >
  	</div>	
  </body>
</html>
