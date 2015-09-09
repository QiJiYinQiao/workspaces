<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<style type="text/css">
	#acceptTaskForm table input{border: none;}
	.table th{ text-align: right;}
	.table td{ text-align: left;}
</style>
<script type="text/javascript">
var $row;
var $paramRow;
var $datagrid;
$(function(){
	// 查看申请状态
	$row = $grid.datagrid('getSelected');
	$paramRow = $row;
	// 备注信息
	$datagrid = $("#lookLoanOrderdg").datagrid({
		url : "loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action",
		width : 'auto',
		height: 240,
		pagination:true,
		rownumbers:true,
		border:true,
		singleSelect:true,
		nowrap:true,
		queryParams:{"loanOrderId":$row.loanOrderId},
		multiSort:false,
		columns : [ [ 
		              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),sortable:true},
		              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1)},
		              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'left'},
		              {field : 'comment',title : '审批意见',width :parseInt($(this).width()*0.1),align : 'left'},
		              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.09),align : 'left',
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
	
	$("#upload_form_div input:first").combobox({
		valueField : 'code',
		textField : 'text',
		url:'common/commonAction!findTextArr.action?codeMyid=attachment_type',
		onLoadSuccess : function(){
			var val = $(this).combobox("getData");
			if(!$.isEmptyObject(val)){
                $(this).combobox("select", val[0]["code"]);
			}
		},
		editable:false
    });
	
	loadAttachmentList('attachmentLists','noauditId',$row.loanOrderId);
	
});

	// 提交表单信息
	function  submitTask(result,object) {
		// 验证备注信息是否已经填写
		if($("#comment").val()==""){
			$.messager.alert("提示","请填写备注信息后再进行提交!","warning")
			return false;
		}
		// 确认是否提交
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if (r){
				var data = {
					"comment" : $("#comment").val(),
					"result" :result,
					"loanOrderId" : $row.loanOrderId,
					"taskId": $row.taskId,
					"processingResult":result=="OrderResubmitThrough"?"A":"B"
				}
				$.ajax({
					type : "POST",
					url : "loanOrder/loanOrderAction!submitTask.action",
					data : data,
					success : function(msg) {
						$grid.datagrid('reload');
						$taskFormDialog.dialog('close');
					}
				});
			}
		});
	}

	// 查看附件
	function lookAttachment(index){
		var row = getRowData($datagrid,index);
		// 附件信息
		$("#lookAttachmentList").datagrid({
			url : "attachment/attachmentAction!findAttachmentListByUserIdAndOrderId.action",
			width : 'auto',
			height : 240,
			pagination:true,
			rownumbers:true,
			border:true,
			singleSelect:true,
			nowrap:true,
			queryParams:{"loanOrderId":row.loanOrderId,"userId":row.assignee},
			multiSort:false,
			columns : [ [ 
			              {field : 'attName',title : '附件名称',width : parseInt($(this).width()*0.1),sortable:true},
			              {field : 'attTypeName',title : '附件类型',width : parseInt($(this).width()*0.1)},
			              {field : 'creatorName',title : '创建者',width : parseInt($(this).width()*0.1),align : 'left'},
				          {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.1),align : 'left',
				            	formatter:function(value,row,index){
				            		var result = "<a target='_blank' href='jsp/openoffice/documentView.jsp?attId="+row.attId+"'>在线预览</a>　　" ;
				            			result += "<a target='_blank' href='javascript:void(0);' onclick=\"downloadAttachment('"+row.attId+"');\">下载</a>　　" ;
				            		return result;
				            	}  
			              }
			              ] ]
		});
		$('#lookInfo').accordion("select","附件信息"); 
	}
	
	//订单信息修改
	function updRowsOpenDlg(index) {
		$("<div></div>").dialog({
			title:"编辑",
			iconCls:'icon-edit',
			resizable:true,
			modal:true,
			width: 900,
			height:650,
			href: "jsp/loanOrder/loanOrderEditForm.jsp",
			onClose:function(){
				$(this).dialog("destroy");
			}
		});
	}
</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">
<div style="width: 980px;height: 267px;overflow: auto;">
<form id="acceptTaskForm" method="post">
		<input name="id" id="id"  type="hidden"/>
		<input name="auditId" type="hidden" value="noauditId"/>
		 <table cellpadding="5px;">
			 <tr>
			    <th>客户姓名</th>
				<td><input name="name" readonly="readonly" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<th>身份证号</th>
				<td><input name="idNo" readonly="readonly" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'idcard'"/></td>
			</tr>
			<tr>
			 	<th>备注</th>
				<td colspan="3">
					<textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:300px;height:70px;"></textarea>
				</td>
			</tr>
		 </table>
<div id="attachmentLists" style="width:100%;display:block;float:left;">
</div>
<div id="upload_form_div_add">
	<div id="upload_form_father_idDiv" style="width:100%;">
		<div id="upload_form_div">
			<font size="2" style="font-weight: bold;">　上传附件:&nbsp;</font>
			<input class="easyui-textbox easyui-combobox" type="text" />
			<input name="fileName" type="text" placeholder="请输入附件名">
			<input id="file" name="file" type="file"  onchange="fileChange(this);" > 
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addACredential(this);">添加</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeACredential(this);">删除</a> 
		</div>
	</div>
</div>
<div id="upload_form" style="width: 100%; height: 30px; text-align: right;">
	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="fileUploads(this)">上传附件</a>
</div> 
</form>
</div>


<div style="width: 980px;height:30px;">
<a href="javascript:void(0);" class="easyui-linkbutton" onclick="updRowsOpenDlg();">订单调整</a>
<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('OrderResubmitThrough',this);">重新提交</a>
<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('OrderResubmitCancle',this);">订单取消</a>
</div>

<div id="lookInfo" class="easyui-accordion" style="height: 300px;width: 980px;overflow: hidden;">
	    <div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true" >   
			<table id="lookLoanOrderdg" title="申请备注的信息"></table>
		</div>
	    <div title="附件信息" data-options="iconCls:'icon-cstbase'" >   
			<table id="lookAttachmentList" title="申请附件的信息"></table>
		</div>
	</div>
</div>
					
