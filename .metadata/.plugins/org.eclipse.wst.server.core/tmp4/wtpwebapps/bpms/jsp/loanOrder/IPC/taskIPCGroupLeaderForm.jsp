<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 当地IPC组长 -->
<style type="text/css">
	#acceptTaskForm table input{border: none;}
	.table th{ text-align: right;}
	.table td{ text-align: left;}
</style>
<script type="text/javascript">
var $row;
var $datagrid;
var $assignUsersDailog;
var $assignUserParam={};
$(function(){
	// 查看申请状态
	$row = $grid.datagrid('getSelected');
	$datagrid = $("#lookLoanOrderdg").datagrid({
		url : "loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action",
		fit : true,
		fitColumns : true,
		pagination:false,
		rownumbers:true,
		border:true,
		singleSelect:true,
		nowrap:true,
		queryParams:{"loanOrderId":$row.loanOrderId},
		multiSort:false,
		fitColumns:true,
		columns : [ [ 
		              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),align : 'center'},
		              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1),align : 'center'},
		              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'center'},
		              {field : 'title',title : '审批简述',width :parseInt($(this).width()*0.1),align : 'center'},
		              {field : 'comment',title : '审批详情',width :parseInt($(this).width()*0.1),align : 'center'},
		              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.08),align : 'center',
			            	formatter:function(value,row,index){
			            		return "<a href='javascript:void(0);' onclick='lookAttachment("+index+");'>查看附件</a>　　" ;
			            	}  
		              }
		              ] ]
	});
	// 渲染姓名
	$("#acceptTaskForm input[name='name']").val($row.name);
	// 渲染身份证号
	$("#acceptTaskForm input[name='idNo']").val($row.idNo);


	$("#attType").combobox({
		valueField : 'code',
		textField : 'text',
		url:'common/commonAction!findTextArr.action?codeMyid=attachment_type',
		onLoadSuccess : function(){
			var val = $(this).combobox("getData");
			if(!$.isEmptyObject(val)){
				$attempData = val;
                $(this).combobox("select", $attempData[0]["code"]);
			}
		},
		editable:false
    });
	
	//查看附件
	$("#checkAttachment").click(function(){
		checkAttachementDetail('noauditId',$row.loanOrderId,'');
	});
	
	//上传附件
	$("#upploadAttachment").click(function(){
		var attType = $("#attType").combobox("getValue");
		fileUploadsDlg(attType,$row.loanOrderId,'');
	});	
	
});
	
	// 查看附件
	function lookAttachment(index){
		var row = getRowData($datagrid,index);
		// 附件信息
		checkAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');
	}
	
	//查看稽核信息
	function checkAuditReportDetail(){
		window.open("jsp/loanOrder/IPC/ipcAuditInfoRecordDetail.jsp?loanOrderId="+$row.loanOrderId,
				"稽核信息详情", "height="+($(window).height()*0.8)+", width=900, top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	}
	
	//查看信审报告 
	function checkApplicationReportDetail(){
		window.open("jsp/loanOrder/IPC/ipcApplicationReportDetail.jsp?loanOrderId="+$row.loanOrderId+"&loanerId="+$row.loanerId,
				"稽核信息详情", "height="+($(window).height()*0.8)+", width=900, top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	}
	
	// 弹出指定用户的列表的方法
	function throughAssignUsersDailog(){
		$assignUserParam.result = 'IPCGroupLeaderThrough';
		$assignUserParam.processingResult = 'A';
		$assignUserParam.roleCode = 'IPCDiaoChaZhuanYuan';
		// 验证备注信息是否已经填写
		$assignUsersDailog = $("<div></div>").dialog({
			title : '用户信息列表',
			width : 900,
			height : $(window).height()*0.8,
			modal:true,
			href : "jsp/loanOrder/IPC/ipcAssignUsers.jsp?t="+new Date(),
			onClose:function(){
				$(this).dialog("destroy");
			}
		}); 
	}
	
	// 弹出指定用户的列表的方法
	function rejectAssignUsersDailog(){
		$assignUserParam.result = 'IPCGroupLeaderReject';
		$assignUserParam.processingResult = 'A';
		$assignUserParam.roleCode = 'IPCXiaoEDiaoCha';
		// 验证备注信息是否已经填写
		$assignUsersDailog = $("<div></div>").dialog({
			title : '用户信息列表',
			width : 900,
			height : $(window).height()*0.8,
			modal:true,
			href : "jsp/loanOrder/IPC/ipcAssignUsers.jsp?t="+new Date(),
			onClose:function(){
				$(this).dialog("destroy");
			}
		}); 
	}
	
</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">
	<div style="width: 900px;height: 190px;overflow: auto;">
		<form id="acceptTaskForm" method="post">
				<input name="id" id="id"  type="hidden"/>
				<input name="auditId" type="hidden" value="noauditId"/>
				 <table cellpadding="5px;">
					<tr>
					    <th>客户姓名:</th>
						<td><input name="name" readonly="readonly" type="text"/></td>
						<th>身份证号:</th>
						<td><input name="idNo" readonly="readonly" type="text"/></td>
					</tr>
					<tr>
					 	<th>备注简述:</th>
						<td>
							<input id="title" name="title" class="easyui-validatebox easyui-textbox" style="border: 1px solid #DDDDDD;">
						</td>
					</tr>
					<tr>
					 	<th>备注详情</th>
						<td colspan="3">
							<textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:100%;height:70px;resize:none;"></textarea>
						</td>
					</tr>
					<tr>
						<th>
							附件类型:
						</th>
						<td>
							<input id="attType" class="easyui-textbox easyui-combobox" />
						</td>
						<td colspan="2">
							<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>	
							<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton" >上传附件</a>	
						</td>
					</tr>
				 </table>
		</form>
	</div>

	<div style="width:900px;height:30px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkApplicationReportDetail();">查看信审报告</a>
		<!-- <a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkAuditReportDetail();">查看稽核信息</a> -->
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="throughAssignUsersDailog();">IPC组长通过</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="rejectAssignUsersDailog();">IPC组长驳回</a>
	</div>
	
	<!-- 备注信息 -->
	<div id="lookInfo" class="easyui-accordion" style="height: 380px;width: 900px;overflow: hidden;">
		<div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true" >   
			<table id="lookLoanOrderdg" title="申请备注的信息"></table>
		</div>
	</div>
</div>   
<!-- 受理任务 E -->