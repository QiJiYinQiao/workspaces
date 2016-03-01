<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 车贷负责人 -->
<style type="text/css">
	#acceptTaskForm table input{border: none;}
	.table th{ text-align: right;}
	.table td{ text-align: left;}
</style>
<script type="text/javascript">
$(function(){
	if($row==null){
		$row = $grid.datagrid('getSelected');
	}
	// 渲染用户的信息的列表
	var assignUsersGrid = $("#assignUsersGrid").datagrid({
		url : "users/usersAction!findUsesrsByRoleCode.action",
		fit:true,
		fitColumns:true,
		queryParams: {"roleCode":$assignUserParam.roleCode},
		rownumbers:true,
		border:false,
		singleSelect:true,
		striped:true,
		columns : [ [ 
		              {field : 'companyName',title : '公司',width :$(window).height()*0.2,align : 'center' },
		              {field : 'deptName',title : '营业部',width :$(window).height()*0.2,align : 'center' },
		              {field : 'userName',title : '用户名',width : $(window).height()*0.1,align : 'center'},
		              {field : 'email',title : '邮箱',width : $(window).height()*0.3,align : 'center'},
		              {field : 'tel',title : '电话',width :$(window).height()*0.2,align : 'center'}
		              ] ],
		toolbar : [ {
			iconCls : 'icon-save',
			text : '指派审查员',
			handler : function(){
					// 修改处理人
					var user  = assignUsersGrid.datagrid("getSelected");
					if(user==null) {
						$.messager.alert('提示','请选择要指派的人！','warning');
						return;
					}
					// 确认是否提交
					$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
						if (r){
							var data = {
								"comment" : $("#comment").val(),
								"title" : $("#title").val(),
								"result" :$assignUserParam.result,
								"loanOrderId" : $row.loanOrderId,
								"taskId": $row.taskId,
								"processingResult":$assignUserParam.processingResult,
								"userId":user.userId
							}
							$.ajax({
								type : "POST",
								url : "loanOrder/loanOrderAction!submitTask.action",
								data : data,
								success : function(msg) {
									$grid.datagrid('reload');
									$taskFormDialog.dialog('close');
									$assignUsersDailog.dialog('close');
								}
							});
						}
					});
				}
			}],
			onLoadSuccess:function(data){
			 	var rows = data.rows;
	            var cMergeMap = {};
	            var dMergeMap = {};
	            if(rows){
	            	for(var i=0;i<rows.length;i++){
	            		var companyName = rows[i].companyName;
	            		if( companyName in cMergeMap ){
	            			cMergeMap[companyName].rowspan++;
	            		}else{
	            			cMergeMap[companyName]={"index":i,"rowspan":1}
	            		}
	            		
	            		var deptName = rows[i].deptName;
	            		if( deptName in dMergeMap ){
	            			dMergeMap[deptName].rowspan++;
	            		}else{
	            			dMergeMap[deptName]={"index":i,"rowspan":1}
	            		}
	            	}
	            }
	            for(var i in cMergeMap){
	                $(this).datagrid('mergeCells',{
	                    index: cMergeMap[i].index,
	                    field: 'companyName',
	                    rowspan: cMergeMap[i].rowspan
	                });
	            }
	            for(var i in dMergeMap){
	                $(this).datagrid('mergeCells',{
	                    index: dMergeMap[i].index,
	                    field: 'deptName',
	                    rowspan: dMergeMap[i].rowspan
	                });
	            }
		}
	});
})
</script>
<!-- 指派人的信息列表 S -->
<table id="assignUsersGrid"></table>
<!-- 指派人的信息列表 E -->