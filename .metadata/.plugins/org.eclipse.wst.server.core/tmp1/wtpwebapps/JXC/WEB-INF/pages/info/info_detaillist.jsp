<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		.datagrid-view{
			background-color: #F8F8FF;
			font-size: 16px;
		}
	</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',title:'SIM卡操作记录'"> 
		<!-- 表单开始 -->
				<table id="dg" style="height: 100%;" class="easyui-datagrid"  
				data-options="rownumbers:true,pagination:true,singleSelect:true,url:'getSimChildList?id=${id }',method:'post',onDblClickRow:doubleclick">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'operation_content',width:80,align:'center'">操作内容</th>
				<th data-options="field:'operation_user',width:120,align:'center'">操作员</th>
				<th data-options="field:'operation_time',width:200,align:'center'">操作时间</th>
				<th data-options="field:'remark',width:600,align:'center'">备注</th>
				</tr>
				</thead>
				
				</table>
		<!-- 表单结束 -->
	</div>
	 <script type="text/javascript">
		$(function(){
		var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
		pager.pagination({
		beforePageText: '第',//页数文本框前显示的汉字  
	    afterPageText: '页    共 {pages} 页',  
	    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		/* buttons:[{
		iconCls:'icon-search',
		handler:function(){
		alert('search');
		}
		},{
		iconCls:'icon-add',
		handler:function(){
		alert('add');
		}
		},{
		iconCls:'icon-edit',
		handler:function(){
		alert('edit');
		}
		}] */
		});
		})
		//双击事件
		function doubleclick(index,field){
			var row = $("#dg").datagrid("getRows",index);
			//alert(row);
			document.getElementById("detail").src = "detail?id="+field.id;
			document.getElementById("detaillist").src = "detaillist?id="+field.id;
			$("#detaildiv").dialog("open");
		}
	</script>
</body>
 
</html>