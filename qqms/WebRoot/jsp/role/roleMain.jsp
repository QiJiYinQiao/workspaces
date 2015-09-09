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
    <title>角色管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../layout/script.jsp"></jsp:include>
	<script type="text/javascript">
		var $role;
		var $grid;
		$(function() {
			$role = $("#role");
			$grid=$role.datagrid({
					url : "permission/permissionAssignmentAction!findAllRoleList.action",
					width : 'auto',
					height : $(this).height()-76,
					pageSize:30,
					pagination:true,
					border:false,
					rownumbers:true,
					singleSelect:true,
					fitColumns:true,
					striped:true,
					columns : [ [ {field : 'name',title : '角色名称',width :parseInt($(this).width()*0.1),align : 'center',editor : {type:'validatebox',options:{required:true}}},
					              {field : 'roleCode',title : '角色编码',width :parseInt($(this).width()*0.1),align : 'center',editor : "numberbox"},
					              {field : 'sort',title : '排序',width :parseInt($(this).width()*0.1),align : 'center',editor : "numberbox"},
					              {field : 'description',title : '角色描述',width : parseInt($(this).width()*0.1),align : 'center',editor : "text"}
					              ] ],toolbar:'#tbRole'
				});
			//搜索框
			$("#searchbox").searchbox({ 
				 menu:"#mm", 
				 prompt :'模糊查询',
			    searcher:function(value,name){   
			    	var str="{\"searchName\":\""+name+"\",\"searchValue\":\""+value+"\"}";
		            var obj = eval('('+str+')');
		            $role.datagrid('reload',obj); 
			    }
			   
			});
		});
		function endEdit(){
			var flag=true;
			var rows = $role.datagrid('getRows');
			for ( var i = 0; i < rows.length; i++) {
				$role.datagrid('endEdit', i);
				var temp=$role.datagrid('validateRow', i);
				if(!temp){flag=false;}
			}
			return flag;
		}
		function addRows(){
			$role.datagrid('appendRow', {});
			var rows = $role.datagrid('getRows');
			$role.datagrid('beginEdit', rows.length - 1);
		}
		function editRows(){
			var rows = $role.datagrid('getSelections');
			$.each(rows,function(i,row){
				if (row) {
					var rowIndex = $role.datagrid('getRowIndex', row);
					$role.datagrid('beginEdit', rowIndex);
				}
			});
		}
		function removeRows(){
			var rows = $role.datagrid('getSelections');
			$.each(rows,function(i,row){
				if (row) {
					var rowIndex = $role.datagrid('getRowIndex', row);
					$role.datagrid('deleteRow', rowIndex);
				}
			});
		}
		function saveRows(){
			if(endEdit()){
				if ($role.datagrid('getChanges').length) {
					var inserted =$role.datagrid('getChanges', "inserted");
					var deleted =$role.datagrid('getChanges', "deleted");
					var updated = $role.datagrid('getChanges', "updated");
					
					var effectRow = new Object();
					if (inserted.length) {
						effectRow["inserted"] = JSON.stringify(inserted);
					}
					if (deleted.length) {
						effectRow["deleted"] = JSON.stringify(deleted);
					}
					if (updated.length) {
						effectRow["updated"] = JSON.stringify(updated);
					}
					$.post("permission/permissionAssignmentAction!persistenceRole.action", effectRow, function(rsp) {
						if(rsp.status){
							$role.datagrid('acceptChanges');
						}
						$.messager.alert(rsp.title, rsp.message);
					}, "JSON").error(function() {
						$.messager.alert("提示", "提交错误了！");
					});
				}
			}else{
				$.messager.alert("提示", "字段验证未通过!请查看");
			}
		}
		function delRows(){
			var row = $role.datagrid('getSelected');
			if(row){
				$.ajax({
					url:"permission/permissionAssignmentAction!delRole.action",
					data: "roleId="+row.roleId,
					success: function(rsp){
						if(rsp.status){//如果删除成功，在删除页面上的数据
							var rowIndex = $role.datagrid('getRowIndex', row);
							$role.datagrid('deleteRow', rowIndex);
						}
						parent.$.messager.show({
							title : rsp.title,
							msg : rsp.message,
							timeout : 3000 * 2
						});
					}
				});
			}else{
				parent.$.messager.show({
					title : "提示",
					msg : "请选择行数据!",
					timeout : 1000 * 2
				});
			}
		}
		//弹窗修改
		function updRowsOpenDlg() {
			var row = $role.datagrid('getSelected');
			if (row) {
				parent.$.modalDialog({
					title : "编辑角色",
					width : 600,
					height : 400,
					href : "jsp/role/roleEditDlg.jsp",
					onLoad:function(){
						var f = parent.$.modalDialog.handler.find("#form");
						f.form("load", row);
					},			
					buttons : [ {
						text : '编辑',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find("#form");
							f.submit();
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							parent.$.modalDialog.handler.dialog('destroy');
							parent.$.modalDialog.handler = undefined;
						}
					}
					]
				});
			}else{
				parent.$.messager.show({
					title :"提示",
					msg :"请选择一行记录!",
					timeout : 1000 * 2
				});
			}
		}
		//弹窗增加
		function addRowsOpenDlg() {
			parent.$.modalDialog({
				title : "添加角色",
				width : 600,
				height : 400,
				href : "jsp/role/roleEditDlg.jsp",
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find("#form");
						f.submit();
					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						parent.$.modalDialog.handler.dialog('destroy');
						parent.$.modalDialog.handler = undefined;
					}
				}
				]
			});
		}
	</script>
	</head>
  <body>
		<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false" title="" style="height: 82px; overflow: hidden; padding: 5px;">
			<div class="well well-small">
				<span class="badge">提示</span>
				<p>
					在此你可以对<span class="label-info"><strong>角色</strong></span>进行编辑!
				</p>
			</div>
		</div>
			<div data-options="region:'center',border:false">
				<div id="tbRole" style="padding:2px 0">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td style="padding-left:4px;padding-bottom:4px;">
<%-- 								<shiro:hasPermission name="perConfig">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-config" plain="true" onclick="savePermission();">保存设置</a>
								</shiro:hasPermission> --%>
								<shiro:hasPermission name="roleAdd">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
								</shiro:hasPermission>
								<shiro:hasPermission name="roleEdit">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">编辑</a>
								</shiro:hasPermission>
								<shiro:hasPermission name="roleDel">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRows();">删除</a>
								</shiro:hasPermission>
								<!--<shiro:hasPermission name="roleEndEdit">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="endEdit();">结束编辑</a>
								</shiro:hasPermission>
								<shiro:hasPermission name="roleSave">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveRows();">保存</a>
								</shiro:hasPermission>-->
							</td>
							<td style="padding-left:4px;padding-bottom:4px;">
								<input id="searchbox" type="text"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="mm">
						<div name="name">角色名称</div>
						<div name="description">角色描述</div>
				</div>
				<table id="role" title="角色管理"></table>
			</div>
		</div>
  </body>
</html>
