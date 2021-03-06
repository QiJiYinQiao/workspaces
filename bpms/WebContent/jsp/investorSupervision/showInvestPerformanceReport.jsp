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
	<jsp:include page="../../layout/script.jsp"></jsp:include>
	<style type="text/css">
		a{
			text-decoration:none;
		}
	</style>
	<script type="text/javascript">
	
			//声明一个全局变量row
			//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。
			var $dg;
			var $grid;
			$(function() {
 				//初始化组织机构
				$("#orgId").combotree({
					width:171,
					url:"orgz/organizationAction!findOrganizationList.action",
					idFiled:'id',
				 	textFiled:'name',
				 	parentField:'pid',
				 	onLoadSuccess:function(data){
				 		//加一个全部
				 	}
				}); 
				//加载投资客户的数据
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "investorderAndProducts/investorderAndProductsAction!findInvestPerformanceReportListByDate.action",
					width : 'auto',
					height : $(this).height()-83,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:true,
					nowrap:true,
					multiSort:false,
					border:false,
					fitColumns:true,
					columns : [ [ 
					              {field : 'orgName',  title : '机构名称',    width : parseInt($(this).width()*0.06), align:'center'},
					              {field : 'investMoneyDay',        title : '当日理财金额（元）',    width : parseInt($(this).width()*0.08), align:'center'},
					              {field : 'countDay',        title : '当日单数（笔）',    width : parseInt($(this).width()*0.08), align:'center' },
					              {field : 'investEduMonth',    title : '当月理财金额（元）',    width : parseInt($(this).width()*0.08), align:'center' },
					              {field : 'investEduMonthOfYear',      title : '当月年化金额（元）', width : parseInt($(this).width()*0.08), align:'center',
					            	  formatter:function(value,row){
					            		  return value.substr(0,value.indexOf('.')+6);
					            	  } },
					              ] ],
		              toolbar:'#tb',
		              onClickCell:function(rowIndex, field, value){
		            	  $(this).datagrid("selectRow","rowIndex");
		              }					              
				});				 				 				 
			});

		//执行高级查询
		function doSearch(){			
			var orgId=$("#orgId").combotree("getValue");
			var queryDate=$("#queryDate").datebox("getValue");
			//3、执行高级查询
			$("#dg").datagrid("load",{
				orgId:orgId,
				queryDate:queryDate
			});
		}

		//重置条件
		function clearAdvancedQueryConditions(){
			//1、清空高级查询各组件内容
			$("#searchForm").form("clear");
			//2、datagrid重新加载
			$("#dg").datagrid("load",{});
		}
		//导出理财业绩报表
		function report_export(){
			var orgId=$("#orgId").combotree("getValue");
			var orgName = $("#orgId").combotree("getText");
			var queryDate=$("#queryDate").datebox("getValue");
			//window.location.href = "investorderAndProducts/investorderAndProductsAction!doExportExcel.action?orgId="+orgId+"&queryDate="+queryDate;
			downFileByFormPost("investorderAndProducts/investorderAndProductsAction!doExportExcel.action", {"orgId":orgId,"queryDate":queryDate,"orgName":orgName});
		}
		
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      	<div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理  &gt; 财务监控管理  &gt; 投资客户监管 &gt; 理财业绩报表</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="" method="post">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
						<td>机构名称：&nbsp;&nbsp;</td>
						<td><input name="orgId" id="orgId" type="text" class="easyui-textbox easyui-validatebox" style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>查询日期：&nbsp;&nbsp;</td>
						<td><input name="queryDate" id="queryDate" class="easyui-datebox" editable="true"/></td>
												
						<td colspan="4" align="right">
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">条件重置</a>
						</td>						
					</tr>										
				</table>
			</form>			  			
		</div>
		

		<div id="tb" style="padding:2px 0">
			<a id="id4ExportReports" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-excel'" onclick="report_export()">导出业绩报表</a>  			
		</div>		

		<!-- 理财业绩数据表格区域 -->
		<table id="dg" ></table>	
  	</div>	
  </body>
</html>
