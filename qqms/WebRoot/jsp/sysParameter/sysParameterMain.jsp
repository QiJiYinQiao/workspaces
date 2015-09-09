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
    <title>系统参数</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../layout/script.jsp"></jsp:include>
	<script type="text/javascript">
			var $dg;
			$(function() {
				$dg=$("#dg");
				$dg.datagrid({
					width:'auto',
					height:$(this).height()-90,
					pagination:true,
					rownumbers:true,
					striped:true,
					//scrollbarSize:0,
					toolbar:"#tb",
					fitColumns:true,
					singleSelect:true,
					url:"systemParameter/systemParameterAction!findParameterList.action",
					columns: [[
					    		{field:'parmName',title:'参数名称',align : 'center',width:parseInt($(this).width()*0.1),editor:"text"},
					    		{field:'parmCode',title:'参数编码',align : 'center',width:parseInt($(this).width()*0.1),editor:"text"},
					    		{field:'status',title:'是否启用',align : 'center',width:parseInt($(this).width()*0.05),
					    			formatter:function(value,row){
				            		  if("1"==row.status)
											return "<font color=green>是<font>";
					            		  else
					            			return "<font color=red>否<font>";  
										},
										editor:{type:'checkbox',options:{on:'1',off:'0'}}
					    		},
					   		    {field:'parmValue',title:'参数值',align : 'center',width:parseInt($(this).width()*0.05),editor:"text"},
					    		{field:'description',title:'参数描述',align : 'center',width:parseInt($(this).width()*0.2),editor:"text"}
					        ]],
					 onDblClickRow:function(index,data){
							$dg.datagrid('beginEdit', index); 
					 }
/* 			        onBeforeEdit:function(index,row){  
			            row.editing = true;  
			            $dg.datagrid('refreshRow', index);  
			        },  
			        onAfterEdit:function(index,row){  
			            row.editing = false;  
			            $dg.datagrid('refreshRow', index);  
			        },  
			        onCancelEdit:function(index,row){  
			            row.editing = false;  
			            $dg.datagrid('refreshRow', index);  
			        }   */
				});
			});
			
			function endEdit(){
				var flag=true;
				var rows = $dg.datagrid('getRows');
				for ( var i = 0; i < rows.length; i++) {
					$dg.datagrid('endEdit', i);
					var temp=$dg.datagrid('validateRow', i);
					if(!temp){flag=false;}
				}
				return flag;
			}
			function addRows(){
				$dg.datagrid('appendRow', {});
				var rows = $dg.datagrid('getRows');
				$dg.datagrid('beginEdit', rows.length - 1);
			}
			function editRows(){
				var rows = $dg.datagrid('getSelections');
				if(rows){
				$.each(rows,function(i,row){
					if (row) {
						var rowIndex = $dg.datagrid('getRowIndex', row);
						$dg.datagrid('beginEdit', rowIndex);
					}
				});
				}else{
					parent.$.messager.show({
						title :"提示",
						msg :"请选择一行记录!",
						timeout : 1000 * 2
					});
				}
			}
			function removeRows(){
				var rows = $dg.datagrid('getSelections');
				$.each(rows,function(i,row){
					if (row) {
						var rowIndex = $dg.datagrid('getRowIndex', row);
						$dg.datagrid('deleteRow', rowIndex);
					}
				});
			}
			function saveRows(){
				if(endEdit()){
					if ($dg.datagrid('getChanges').length) {
						var inserted = $dg.datagrid('getChanges', "inserted");
						var deleted = $dg.datagrid('getChanges', "deleted");
						var updated = $dg.datagrid('getChanges', "updated");
						
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
						$.post("systemParameter/systemParameterAction!persistenceCompanyInfo.action", effectRow, function(rsp) {
							if(rsp.status){
								$dg.datagrid('acceptChanges');
							}
							parent.$.messager.show({
								title :rsp.title,
								msg :rsp.message,
								timeout : 1000 * 2
							});
						}, "JSON").error(function() {
							parent.$.messager.show({
								title :"提示",
								msg :"提交错误了！",
								timeout : 1000 * 2
							});
						});
					}
				}else{
					parent.$.messager.show({
						title :"提示",
						msg :"字段验证未通过!请查看",
						timeout : 1000 * 2
					});
				}
			}
			
		</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
				<span class="badge">提示</span>
				<p>
					在此你可以对<span class="label-info"><strong>系统参数</strong></span>进行设置和编辑,双击<span class="label-info"><strong>某一参数所在行</strong></span>对其进行设置和编辑!
				</p>
		</div>
		<div id="tb" style="padding:2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left:2px">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRows();">增加</a>
						<shiro:hasPermission name="parAdd">
						</shiro:hasPermission>
<%-- 						<shiro:hasPermission name="parEndEdit">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-end" plain="true" onclick="endEdit();">结束编辑</a>
						</shiro:hasPermission> --%>
						<shiro:hasPermission name="parDel">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeRows();">删除</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="parSave">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveRows();">保存修改</a>
						</shiro:hasPermission>
					</td>
				</tr>
			</table>
		</div>
		<table id="dg" title="参数编辑"></table>
  	</div>	
  </body>
</html>
