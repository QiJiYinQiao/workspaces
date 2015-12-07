<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<style type="text/css">
	#acceptTaskForm table input{border: none;}
	.table th{ text-align: left;width:120px;height:20px;padding-left: 8px;padding-top: 8px;font-weight: bold;}
	.table td{ text-align: left;width:200px;height:20px;padding-left: 8px;padding-top: 8px;}
</style>
<script type="text/javascript">
var $row;
var $datagrid;
$(function(){
	// 查看申请状态
	$row = $grid.datagrid('getSelected');
	$datagrid = $("#lookLoanOrderdg").datagrid({
		url : "loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action",
		width : 'auto',
		height : 350,
		pagination:false,
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
		              {field : 'title',title : '审批简述',width :parseInt($(this).width()*0.1),align : 'left'},
		              /* {field : 'comment',title : '审批详情',width :parseInt($(this).width()*0.1),align : 'left'}, */
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

	$("#attType").combobox({
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

	// 渲染附件列表
	//查看附件
	$("#checkAttachment").click(function(){
		checkAttachementDetail('noauditId',$row.loanOrderId,'');
	});
	
	//上传附件
	$("#upploadAttachment").click(function(){
		var attType = $("#attType").combobox("getValue");
		fileUploadsDlg(attType,$row.loanOrderId);
	});

});
		

	// 提交表单信息
	function  submitTask(result) {
		// 验证备注简述不能为空
		if($("#title").val() =="" || $("#comment").val()==""){
			$.messager.alert("提示","请填写完备注信息后在进行提交!","warning");
			return false;
		}
		// 确认是否提交
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if (r){
				var data = {
					"comment" : $("#comment").val(),
					"title" : $("#title").val(),
					"result" :result,
					"loanOrderId" : $row.loanOrderId,
					"taskId": $row.taskId,
					"processingResult":result=="DataPostThrough"?"A":"B"
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
		checkAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');
	}
	
	//查看相关文档信息
	function checkRelativeInfo() {
		$("<div></div>").dialog({
			title:"相关文档信息",
			width:1000,
			height:680,
			closed:false,
			closeable:true,
			modal:true,
			href:"jsp/loanOrder/settlement/relativeWrodInfo.jsp",
			onClose:function() {
				$(this).dialog("destroy");
			}
		});
	}
	
	//修改信访费用
	function modifyVisitFee() {
		$("<div></div>").dialog({
			title:"修改信访费用",
			width:1000,
			height:200,
			closed:false,
			closeable:true,
			modal:true,
			href:"jsp/loanOrder/settlement/modifyVisitFee.jsp",
			onClose:function() {
				$(this).dialog("destroy");
			}
		});
	}
	
	//修改银行卡信息
	function modifyBankAccount() {
		$("<div></div>").dialog({
			title:"修改开户行信息",
			width:1100,
			height:250,
			closed:false,
			closeable:true,
			modal:true,
			href:"jsp/loanOrder/settlement/modifyBankAccountInfo.jsp",
			onClose:function() {
				$(this).dialog("destroy");
			}
		});
	}
</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">
	<div style="width: 980px;height: 190px;overflow: auto;">
	<form id="acceptTaskForm" method="post">
		 <input name="id" id="id"  type="hidden"/>
		 <input name="auditId" type="hidden" value="noauditId"/>
		 <table class="table">
			<tr>
			    <th>客户姓名:</th>
				<td><input name="name" readonly="readonly" type="text"/></td>
				<th>身份证号:</th>
				<td><input name="idNo" readonly="readonly" type="text"/></td>
			</tr>
			<tr>
			 	<th>备注简述:</th>
				<td>
					<input id=title name="title" class="easyui-validatebox easyui-textbox" style="border: 1px solid #DDDDDD;">
				</td>
			</tr>
			<tr>
			 	<th>备注详情:</th>
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
	
	<div style="width: 980px;height:30px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="modifyVisitFee();">修改信访费用</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="modifyBankAccount();">修改银行卡信息</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkRelativeInfo();">查看相关文档信息</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('ShuJuZhuanYuanThrough');">数据专员通过</a>
	</div>
	
	<div id="lookInfo" class="easyui-accordion" style="height: 380px;width: 980px;overflow: hidden;">
	    <div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true" >   
			<table id="lookLoanOrderdg" title="申请备注的信息"></table>
		</div>
	</div>
</div>   
<!-- 受理任务 E -->					

			