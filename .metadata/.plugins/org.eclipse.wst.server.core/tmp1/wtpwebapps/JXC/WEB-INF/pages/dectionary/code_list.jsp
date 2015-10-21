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
	<script type="text/javascript" src="../media/js/WdatePicker.js"></script>
	<script type="text/javascript" src="../media/js/jquery.min.js"></script>
	<script type="text/javascript" src="../media/js/jquery.easyui.min.js"></script>
	<style type="text/css">
		.datagrid-view{
				background-color: #F8F8FF;
				font-size: 16px;
			}
		div{
			background-color: #F8F8FF;
				font-size: 16px;
		}
		table tr td input{
			border: 1px solid #95b8e7;border-radius: 5px;height: 20px;
		}
		select {
			border: 1px solid #95b8e7;border-radius: 5px;height: 24px;width:140px;
		}
	</style>
</head>
<body class="easyui-layout">
	<script type="text/javascript">
		$(function(){
			$("#dcode").datagrid({
				url:'getList',
			});
		});
	</script>
	<div data-options="region:'center',title:'字典管理'"> 
		<div style="width: 30%;float: left;height: 100%">
				<div align="right" style="height:25px;width: 100%;margin-right: 10px;overflow: hidden;">
						<a onclick="addDCode();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">新增</a>
						<a onclick="editDCode();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">修改</a>
						<a onclick="startDCode();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">启用</a>
						<a onclick="stopDCode();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">禁用</a>
				</div>
				<!-- 表单开始 -->
				<table id="dcode" style="height: 90%;width: 98%;" class="easyui-datagrid"  
				data-options="rownumbers:true,pagination:false,singleSelect:true,method:'post',remoteSort:false,multiSort:true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'code',width:140,align:'left'">代号</th>
						<th data-options="field:'name',width:140,align:'left'">名称</th>
						<th data-options="field:'status',width:40,align:'lfet'">状态</th>
					</tr>
				</thead>
				</table>
				<!-- 表单结束 -->
		</div>
		<div id="test" style="height: 100%;width:70%;display: none;float: right;overflow: hidden;">
			<div align="right" style="height:25px;width: 100%;">
				<a onclick="addDCodeLine();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">新增</a>
				<a onclick="editDCodeLine();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">修改</a>
				<a onclick="startDCodeLine();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">启用</a>
				<a onclick="stopDCodeLine();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">禁用</a>
			</div>
			<table id="dcodeline" style="height: 95%;width: 100%;" class="easyui-datagrid"  
			data-options="rownumbers:true,pagination:false,singleSelect:true,method:'post',remoteSort:false,multiSort:true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'item_value',width:160,align:'left'">代号</th>
					<th data-options="field:'item_text',width:320,align:'left'">名称</th>
					<th data-options="field:'description',width:300,align:'left'">描述</th>
					<th data-options="field:'status',width:40,align:'left'">状态</th>
				</tr>
			</thead>
			</table>
		</div>
		
		<!-- 添加主 -->
			<div id="addcode"  class="easyui-dialog" modal="true" title="添加"  closed="true" style="width:400px;height:300px;overflow:hidden;text-align: center;">
				<form id="addCodeForm" style="text-align: center;width: 100%;margin-top: 20px;">
					<table style="text-align: center;width:100%;">
					<tr>
						<td>代号</td><td><input name="code" value=""></td>
					</tr>
					<tr>
						<td>名称</td><td><input name="name" value=""></td>
					</tr>
					<tr>
						<td>描述</td><td><input name="description" value=""></td>
					</tr>
					<tr>
						<td>代码类型</td>
						<td>
							<select name="code_type">
								<option value="0" selected="selected">单级</option>
								<option value="1">多级</option>
							</select>
						</td>
					</tr>
					<tr><td><input name="create_user" value="${id }" type="hidden"></td><td><input name="modify_user" value="${id }" type="hidden"></td></tr>
				</table>
				</form>
				<a onclick="submitAddCode();" class="easyui-linkbutton" style="width: 60px;height: 25px;margin-right: 5px;">提交</a>
				<a onclick="cancelAddCode();" class="easyui-linkbutton" style="width: 60px;height: 25px;">取消</a>
			</div>
		<!-- 添加主 -->
		
		<!-- 编辑主 -->
			<div id="editcode"  class="easyui-dialog" modal="true" title="添加"  closed="true" style="width:400px;height:300px;overflow:hidden;text-align: center;">
				<form id="editCodeForm"  style="text-align: center;width: 100%;margin-top: 20px;">
					<input name="id" value="" id="codeid" type="hidden">
					<table style="text-align: center;width:100%;">
					<tr>
						<td>代号</td><td><input id="code" name="code" value=""></td>
					</tr>
					<tr>
						<td>名称</td><td><input id="name" name="name" value=""></td>
					</tr>
					<tr>
						<td>描述</td><td><input id="description"  name="description" value=""></td>
					</tr>
					<tr>
						<td>代码类型</td>
						<td>
							<select  id="code_type" name="code_type">
								<option value="0" selected="selected">单级</option>
								<option value="1">多级</option>
							</select>
						</td>
					</tr>
					<tr><td><input name="modify_user" value="${id }" type="hidden"></td></tr>
				</table>
				</form>
				<a onclick="submitEditCode();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">提交</a>
				<a onclick="cancelEditCode();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">取消</a>
			</div>
		<!-- 编辑主 -->
		
			<!-- 添加副-->
			<div id="addcodeline"  class="easyui-dialog" modal="true" title="添加"  closed="true" style="width:400px;height:300px;overflow:hidden;text-align: center;">
				<form id="addCodeLineForm" style="text-align: center;width: 100%;margin-top: 20px;">
					<input id="pid" name="codeid" value="" type="hidden">
					<table style="text-align: center;width:100%;">
					<tr>
						<td>代号</td><td><input name="item_value" value=""></td>
					</tr>
					<tr>
						<td>名称</td><td><input name="item_text" value=""></td>
					</tr>
					<tr>
						<td>描述</td><td><input name="description" value=""></td>
					</tr>
					<tr>
						<td>代码级数</td>
						<td>
							<select name="item_level">
								<option value="0" selected="selected">单级</option>
								<option value="1">多级</option>
							</select>
						</td>
					</tr>
					<tr><td><input name="create_user" value="${id }" type="hidden"></td><td><input name="modify_user" value="${id }" type="hidden"></td></tr>
				</table>
				</form>
				<a onclick="submitAddCodeLine();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">提交</a>
				<a onclick="cancelAddCodeLine();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">取消</a>
			</div>
		<!-- 添加副-->
		
		<!-- 编辑副 -->
			<div id="editcodeline"  class="easyui-dialog" modal="true" title="添加"  closed="true" style="width:400px;height:300px;overflow:hidden;text-align: center;">
				<form id="editCodeLineForm" style="text-align: center;width: 100%;margin-top: 20px;">
					<input name="id" value="" id="ids" type="hidden">
					<table style="text-align: center;width:100%;">
					<tr>
						<td>代号</td><td><input id="item_value" name="item_value" value=""></td>
					</tr>
					<tr>
						<td>名称</td><td><input id="item_text" name="item_text" value=""></td>
					</tr>
					<tr>
						<td>描述</td><td><input id="descriptionline"  name="description" value=""></td>
					</tr>
					<tr>
						<td>代码级数</td>
						<td>
							<select  id="item_level" name="item_level">
								<option value="0" selected="selected">单级</option>
								<option value="1">多级</option>
							</select>
						</td>
					</tr>
					<tr><td><input name="modify_user" value="${id }" type="hidden"></td></tr>
				</table>
				</form>
				<a onclick="submitEditCodeLine();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">提交</a>
				<a onclick="cancelEditCodeLine();" class="easyui-linkbutton" style="margin-right: 5px;width: 60px;height: 25px;">取消</a>
			</div>
		<!-- 编辑副 -->
		
	</div>
	<script type="text/javascript">
		$(function(){
				$('#dcode').datagrid({
					onClickRow:function(index,data){
					var row=$('#dcode').datagrid('getSelected');
					var codeid = row.id;
						if(row)
						{
							$("#dcodeline").datagrid({
								url:'getCodeLineList',
								queryParams:{
									codeid:codeid,
								}
							});
							$("#pid").val(codeid);
							$("#test").css("display","block");
						}
					}
				})
			}); 
		
		function addDCode(){
			$("#addcode").dialog("open");
		}
		
		function cancelAddCode(){
			$("#addcode").dialog("close");
		}
		
		function submitAddCode(){
			//alert("sub");
			jQuery.ajax({
				url:"saveCode",
				type:"post",
				data:$("#addCodeForm").serialize(),
				error:function(){},
				success:function(data){
					alert("保存成功!")
					$("#addcode").dialog("close");
					$("#dcode").datagird("reload");
				},
			})
		}
		
		function editDCode(){
			var row=$('#dcode').datagrid('getSelected');
			if(row==null){
				alert("请选择一条数据！");
				return false;
			}
			$("#code").val(row.code);
			$("#name").val(row.name);
			$("#description").val(row.description);
			$("#code_type").val(row.code_type);
			$("#codeid").val(row.id);
			
			$("#editcode").dialog("open");
		}
		
		function cancelEditCode(){
			$("#editcode").dialog("close");
		}
		
		function submitEditCode(){
			jQuery.ajax({
				url:"editCode",
				type:"post",
				data:$("#editCodeForm").serialize(),
				error:function(){},
				success:function(data){
					alert("修改成功!")
					$("#editcode").dialog("close");
					$("#dcode").datagird("reload");
				},
			})
		}
		
		function startDCode(){
			var row=$('#dcode').datagrid('getSelected');
			if(row==null){
				alert("请选择一条数据！");
				return false;
			}
			var status = row.status;
			if(status==1){
				alert("此条已被启用!")
				return false;
			}
			var id = row.id;
			jQuery.ajax({
				url:"startcode",
				type:"post",
				data:{id:id,type:1},
				error:function(){},
				success:function(data){
					alert("启用成功!")
					$("#dcode").datagird("reload");
				}
			})
		}
		
		function stopDCode(){
			var row=$('#dcode').datagrid('getSelected');
			if(row==null){
				alert("请选择一条数据！");
				return false;
			}
			var status = row.status;
			if(status==0){
				alert("此条已被禁用!")
				return false;
			}
			var id = row.id;
			jQuery.ajax({
				url:"stopcode",
				type:"post",
				data:{id:id,type:1},
				error:function(){},
				success:function(data){
					alert("禁用成功!")
					$("#dcode").datagird("reload");
				}
			})
		}
		
		function addDCodeLine(){
			$("#addcodeline").dialog("open");
		}
		
		function cancelAddCodeLine(){
			$("#addcodeline").dialog("close");
		}
		
		function submitAddCodeLine(){
			jQuery.ajax({
				url:"saveCodeLine",
				type:"post",
				data:$("#addCodeLineForm").serialize(),
				error:function(){},
				success:function(data){
					alert("添加成功!")
					$("#addcodeline").dialog("close");
					$("#dcodeline").datagrid("reload");
				},
			})
		}
		
		function editDCodeLine(){
			var row=$('#dcodeline').datagrid('getSelected');
			if(row==null){
				alert("请选择一条数据！");
				return false;
			}
			$("#ids").val(row.id);
			$("#item_value").val(row.item_value);
			$("#item_text").val(row.item_text);
			$("#descriptionline").val(row.description);
			$("#item_level").val(row.item_level);
			
			$("#editcodeline").dialog("open");
		}
		
		function cancelEditCodeLine(){
			$("#editcodeline").dialog("close");
		}
		
		function submitEditCodeLine(){
			jQuery.ajax({
				url:"editCodeLine",
				type:"post",
				data:$("#editCodeLineForm").serialize(),
				error:function(){},
				success:function(data){
					alert("修改成功!")
					$("#editcodeline").dialog("close");
					$("#dcodeline").datagrid("reload");
				},
			})
		}
		
		function startDCodeLine(){
			var row=$('#dcodeline').datagrid('getSelected');
			if(row==null){
				alert("请选择一条数据！");
				return false;
			}
			if(row.status==1){
				alert("此已经启用！");
				return false;				
			}
			var id = row.id;
			jQuery.ajax({
				url:"startcode",
				type:"post",
				data:{id:id,type:2},
				error:function(){},
				success:function(data){
					alert("启用成功!");
					$("#dcodeline").datagrid("reload");
				}
			})
		}
		
		function stopDCodeLine(){
			var row=$('#dcodeline').datagrid('getSelected');
			if(row==null){
				alert("请选择一条数据！");
				return false;
			}
			if(row.status==0){
				alert("此已经禁用！");
				return false;				
			}
			var id = row.id;
			jQuery.ajax({
				url:"stopcode",
				type:"post",
				data:{id:id,type:2},
				error:function(){},
				success:function(data){
					alert("禁用成功!");
					$("#dcodeline").datagrid("reload");
				}
			})
		}
	</script>
</body>
 
</html>