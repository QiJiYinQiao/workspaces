<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<style type="text/css">
	#acceptTaskForm table input{border: none;}
	.table th{ text-align: right;}
	.table td{ text-align: left;}
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
		height : 240,
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
	
	// 渲染是否通过
	$("#processingResult").combobox({
		valueField : 'code',
		textField : 'text',
		url:'common/commonAction!findTextArr.action?codeMyid=processing_result',
		onLoadSuccess : function(){
			var val = $(this).combobox("getData");
			if(!$.isEmptyObject(val)){
                $(this).combobox("select", val[0]["code"]);
			}
		}
    });

	// 渲染是否涉及车贷
	$("#isInvolvedLoanCar").combobox({
		valueField : 'code',
		textField : 'text',
		url:'common/commonAction!findTextArr.action?codeMyid=is_involved_loan_car',
		onLoadSuccess : function(){
			var val = $(this).combobox("getData");
			if(!$.isEmptyObject(val)){
                $(this).combobox("select", val[0]["code"]);
			}
		}
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
						"processingResult":$("#processingResult").combobox("getValue"),
						"isInvolvedLoanCar":$("#isInvolvedLoanCar").combobox("getValue")
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
			width : 970,
			height : 240,
			pagination:true,
			rownumbers:true,
			border:true,
			singleSelect:true,
			nowrap:true,
			queryParams:{"loanOrderId":row.loanOrderId,"userId":row.assignee},
			multiSort:false,
			fitColumns : true,
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
	
	//查看微贷业务呈报意见表
	function checkMic(){
		$("#microcreditOpinionDlg1").dialog("open");
		$("#microcreditOpinionForm").form("load","microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionByOid.action?loanOrderId="+$row.loanOrderId);
		// 组织机构的信息--进件城市
		$.ajax({
			type : "POST",
			url : "loanOrder/loanOrderAction!findLoanCityByOrderId.action",
			data : {"loanOrderId":$row.loanOrderId},
			success : function(data) {
				if(data){
					$("#microcreditOpinionForm input[name='loanCtiy']").val("IPC项目组-"+data.fullName);
				}
			}
		});
		$("#microcreditOpinionForm input[name='name']").val($row.name);
		$("#microcreditOpinionForm input[name='idNo']").val($row.idNo);
		$("#microcreditOpinionForm input[name='purpose']").val($row.purpose);
		
		$("#microcreditOpinionForm input").attr("readonly","readonly").css("background-color","#EBEBE4");
		$("#microcreditOpinionForm textarea").attr("readonly","readonly").css("background-color","#F5F5F5");
	}
</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">
	<div style="width: 986px;height: 265px;overflow: auto;">
			<form id="acceptTaskForm" method="post">
				 <input name="id" id="id"  type="hidden"/>
				 <input name="auditId" type="hidden" value="noauditId"/>
				 <table class="table" cellpadding="5px;">
					 <tr>
					    <th>客户姓名:</th>
						<td><input name="name" readonly="readonly" type="text"/></td>
						<th>身份证号:</th>
						<td><input name="idNo" readonly="readonly" type="text"/></td>
					</tr>
					<tr>
						<th>是否通过:</th>
						<td><input id="processingResult" name="processingResult" class="easyui-validatebox easyui-combobox" type="text"/></td>
						<th>是否涉及车贷:</th>
						<td><input id="isInvolvedLoanCar" name="isInvolvedLoanCar" class="easyui-validatebox easyui-combobox" type="text"/></td>
					</tr>
					<tr>
					 	<th>理由:</th>
						<td colspan="3">
							<textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:100%;height:70px;"></textarea>
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
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkMic();">查看微贷呈报意见表</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('LRC03Through',this);">提交</a>
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
<!-- 受理任务 E -->					

<!-- 微贷业务呈报意见表 S-->
<div id="microcreditOpinionDlg1" class="easyui-dialog" title=" " data-options="border:false,closed:true,modal:true" style="width:1000px;height:650px; overflow: hidden;">
	<div style="height: 600px;overflow: auto;">
		<form id="microcreditOpinionForm" method="post">
		<input name="mcbrId" type="hidden" />
		<div style="text-align:center;">
			<font size="4" style="font-weight: bold;">微贷业务呈报意见表</font>
		</div>
		<div>
			<table cellpadding="8px;">
				<tr>
					<th>借款人</th>
					<td>
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="name"  type="text" />
						<input name="loanOrderId" type="hidden" />
					</td>
					<th>身份证号</th>
					<td ><input readonly="readonly" class="easyui-validatebox easyui-textbox" name="idNo"  type="text" /></td>
					<th>咨询服务费</th>
					<td><input readonly="readonly" class="easyui-validatebox easyui-textbox" name="counselingRate"  type="text" />%</td>
				</tr>
				<tr>
					<th>共同借款人</th>
					<td><input class="easyui-validatebox easyui-textbox" name="coborrowerName"  type="text" /></td>
					<th>身份证号</th>
					<td ><input class="easyui-validatebox easyui-textbox" name="coborrowerIdno"  type="text" /></td>
					<th>收取方式</th>
					<td colspan="3"><input readonly="readonly" class="easyui-validatebox easyui-textbox" name="collectionMthd"  type="text" /></td>
				</tr>
				<tr>
					<th>建议金额(元)</th>
					<td><input class="easyui-validatebox easyui-textbox" name="adviceLoanAmt"  type="text"/></td>
					<th>期限(月)</th>
					<td><input class="easyui-validatebox easyui-textbox" name="adviceLoanPeriod"  type="text" /></td>
					<th>放款方式</th>
					<td><input readonly="readonly" class="easyui-validatebox easyui-textbox" name="loanMthd"  type="text" /></td>
				</tr>
				<tr>
					<th>贷款用途</th>
					<td><input class="easyui-validatebox easyui-textbox" name="purpose"  type="text" value=""/></td>
					<th>利率(年)</th>
					<td ><input readonly="readonly" class="easyui-validatebox easyui-textbox" name="loanRate"  type="text" />%</td>
					<th>还款方式</th>
					<td><input readonly="readonly" class="easyui-validatebox easyui-textbox" name="adviceRepayMthd"  type="text" /></td>
				</tr>
				<tr>
					<th>经办机构/部门</th>
					<td><input readonly="readonly" class="easyui-validatebox easyui-textbox" name="loanCtiy"  type="text"/></td>
					<th>经办人</th>
					<td colspan="3">
						A:<input id="useridA" class="easyui-validatebox easyui-textbox" name="operatorA"  type="text" />&nbsp;&nbsp;&nbsp;
						B:<input id="useridB" class="easyui-validatebox easyui-textbox" name="operatorB"  type="text" /> 
					</td>
				</tr>
			</table>
			<div style="width:99.5%;height:270px;">
				<div style="padding-left:10px;height:30px;">
						<span style="font-weight:bold;">风险控制措施:&nbsp;&nbsp;&nbsp;</span>
						<input class="easyui-textbox" name="riskCtrlMeasures" />
						<span style="padding-left:20px;font-weight:bold;">具体措施如下:</span>
				</div>
				<div style="height:230px;overflow:auto;">
						<textarea class="easyui-validatebox easyui-textbox" name="specificMeasures" style="width:99%;height:220px;resize: none;"></textarea>
					</div>
				</div>
				<div>
					<table cellpadding="8px;">
						<tr>
							<th rowspan="2">业务经办人</th>
							<td><input class="easyui-validatebox easyui-textbox" name="operatorA" type="text" /></td>
							<th rowspan="2">后台人员</th>
							<th>初次上会</th>
							<td><input class="easyui-validatebox easyui-textbox" name="firstMeeting" type="text" /></td>
							<th rowspan="2">部门负责人</th>
							<td rowspan="2"><input class="easyui-validatebox easyui-textbox" name="deptPrincipal" type="text" /></td>
						</tr>
						<tr>
							<td><input class="easyui-validatebox easyui-textbox" name="operatorB" type="text" /></td>
							<th>补调核实</th>
							<td><input class="easyui-validatebox easyui-textbox" name="verification" type="text" /></td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- 微贷业务呈报意见表 E-->