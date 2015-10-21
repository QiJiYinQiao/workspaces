<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登陆首页</title>
	<link rel="stylesheet" type="text/css" href="../media/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../media/css/icon.css">
	<link rel="stylesheet" type="text/css" href="../media/css/demo.css">
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<script>
		function submitForm(){
		$('#ff').submit();
		}
		function clearForm(){
		$('#ff').form('clear');
		}
		$("#dg").datagrid({
				
	                columns: [{
	                    field: 'id',
	                    width: 100,
	                    rowspan: 2
	                },{
	                	field:'username',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'name',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'sex',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'idnumber',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'birth',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'mobile',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'counterid',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'headid',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'contactsname',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'contactstel',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'entrydate',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'address',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'gradetype',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'isvalid',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'createuser',
	                	width: 100,
	                    rowspan: 2
	                },{
	                	field:'createtime',
	                	width: 100,
	                    rowspan: 2
	                }]}
		);
</script>
	<!-- <div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">龙在天下</div>
	<div data-options="region:'west',split:true,title:'菜单栏'" style="width:150px;padding:10px;">
		<div id="orders">订单管理
			<div style="display:none;" id="order">
				<a href="order/add">增加订单</a>
			</div>
		</div>
		<dl>
			<dt>人员管理</dt>
			<dd>人员列表</dd>
		</dl>
		<dl>
			<dt>柜台管理</dt>
			<dd>柜台列表</dd>
		</dl>
		<dl>
			<dt>设备管理</dt>
			<dd>设备管理列表</dd>
		</dl>
		<dl>
			<dt>国际漫游卡</dt>
			<dd>国际漫游卡列表</dd>
		</dl>
		
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'通知'" style="width:100px;padding:10px;">通知</div>
	<div data-options="region:'south',border:false" style="height:5px;background:#A9FACD;padding:10px;"></div>
	<div data-options="region:'center',title:'列表'"> -->
		<!-- 表单开始 -->
				<table id="dg" class="easyui-datagrid"  
				data-options="rownumbers:true,pagination:true,singleSelect:true,url:'getList',method:'get'">
				<thead>
				<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',width:80">用户ID</th>
				<th data-options="field:'username',width:100">用户名</th>
				<th data-options="field:'name',width:80,align:'right'">真实姓名</th>
				<th data-options="field:'sex',width:80,align:'right'">性别</th>
				<th data-options="field:'idnumber',width:80">身份证号</th>
				<th data-options="field:'birth',width:110,align:'center'">出生日期</th>
				<th data-options="field:'mobile',width:80,align:'center'">手机号</th>
				<th data-options="field:'counterid',width:80,align:'center'">柜台ID</th>
				<th data-options="field:'headid',width:80,align:'center'">主管ID</th>
				<th data-options="field:'contactsname',width:80,align:'center'">联系人姓名</th>
				<th data-options="field:'contactstel',width:80,align:'center'">联系人电话</th>
				<th data-options="field:'entrydate',width:80,align:'center'">入职时间</th>
				<th data-options="field:'address',width:80,align:'center'">地址</th>
				<th data-options="field:'gradetype',width:80,align:'center'">级别标识</th>
				<th data-options="field:'isvalid',width:80,align:'center'">是否有效</th>
				<th data-options="field:'createuser',width:80,align:'center'">创建人</th>
				<th data-options="field:'createtime',width:80,align:'center'">创建时间</th>
				</tr>
				</thead>
				<tbody>
					<%-- <c:if test="${ss!=null}">
						<c:forEach items="${ss}" var="list">
							<tr><td>${list.id}</td></tr>
						</c:forEach>
					</c:if> --%>
					<tr><td></td></tr>
					<tr><td></td></tr>
					<tr><td></td></tr>
				</tbody>
				</table>
				<div style="margin:10px 0;">
				<span>Selection Mode: </span>
				<select onchange="$('#dg').datagrid({singleSelect:(this.value==0)})">
				<option value="0">Single Row</option>
				<option value="1">Multiple Rows</option>
				</select><br/>
				SelectOnCheck: <input type="checkbox" checked onchange="$('#dg').datagrid({selectOnCheck:$(this).is(':checked')})"><br/>
				CheckOnSelect: <input type="checkbox" checked onchange="$('#dg').datagrid({checkOnSelect:$(this).is(':checked')})">
			  </div>
		<!-- 表单结束 -->
	</div>
	 <script type="text/javascript">
		$(function(){
		var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
		pager.pagination({
		buttons:[{
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
		}]
		});
		})
	</script>
</body>
 
</html>