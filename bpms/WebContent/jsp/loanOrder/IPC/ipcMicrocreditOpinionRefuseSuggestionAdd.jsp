<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript">
$(function(){
	  
	   $("#microcreditOpinionRefuseDlg input[name='operatorA']").combobox({
			valueField : 'code',
			textField : 'text',
			required:true,
			url:'users/usersAction!findUsers.action',
			editable:false ,
			onSelect:function(record){
				$("input[name='operatorAR']").val(record.text);
			},
	  }); 
	   
	   $("#microcreditOpinionRefuseDlg input[name='operatorB']").combobox({
			valueField : 'code',
			textField : 'text',
			required:true,
			url:'users/usersAction!findUsers.action',
			editable:false ,
			multiple:true,
			width:250,
			onSelect:function(record){
				$("input[name='operatorBR']").val(record.text);
			},
	  }); 
	  
	//加载拒绝决议表   
    $.ajax({
		url : "microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionByOid.action",
		data : {"loanOrderId":$row.loanOrderId},
		type : "POST",
		async:false,
		success : function(data) {
			if(data){
				data.operatorB = data.operatorB.replace("/\s/g","").split(",");
				$("#microcreditOpinionRefuseForm").form("load",data);
			}
		}
	});   
	   
	//加载拒绝决议表
	//$("#microcreditOpinionRefuseForm").form("load","microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionByOid.action?loanOrderId="+$row.loanOrderId);
	// 组织机构的信息--进件城市
	$.ajax({
		type : "POST",
		url : "loanOrder/loanOrderAction!findLoanCityByOrderId.action",
		data : {"loanOrderId":$row.loanOrderId},
		success : function(data) {
			if(data){
				$("#microcreditOpinionRefuseDlg input[name='loanCtiy']").val(data.fullName);
			}
		}
	});
	
	// 渲染审查评议表其他的属性值
	$("#microcreditOpinionRefuseDlg input[name='name']").val($row.name);
	$("#microcreditOpinionRefuseDlg input[name='loanOrderId']").val($row.loanOrderId);
	$("#microcreditOpinionRefuseDlg input[name='idNo']").val($row.idNo);
	$("#microcreditOpinionRefuseDlg input[name='purpose']").val($row.purpose);
	$("#microcreditOpinionRefuseDlg input[name='loanAmount']").val($row.loanAmount);
	setTimeout("loadOperator()",300);
	
});

function loadOperator() {
	$("#microcreditOpinionRefuseDlg input[name='operatorAR']").val($("#operatorAR").combobox('getText'));
	$("#microcreditOpinionRefuseDlg input[name='operatorBR']").val($("#operatorBR").combobox('getText'));
}

// 保存微保意见
function saveMicrocreditOpinion(formId){
	// 确认是否提交
	$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
		if (r){
			$("#"+formId).form('submit', {
				url : "microcreditOpinion/microcreditOpinionAction!saveMicrocreditOpinion.action",
				onSubmit : function(param) {
					var isValid = $("#"+formId).form('validate');
					return isValid; // 返回false终止表单提交
				},
				success : function(data) {
					console.info(data);
					data = $.parseJSON(data);
					$("#"+formId+" input[name='mcbrId']").val(data.data);
					submitTask("IPCCensorReject");
				}
			});
		}
	});
}


</script>
<!-- 拒绝决议表S -->
<div id="microcreditOpinionRefuseDlg">
	<form id="microcreditOpinionRefuseForm" method="post"> 
		<input name="mcbrId" type="hidden" />
		<div style="text-align:center;">
			<font size="4" style="font-weight: bold;">拒绝决议表</font>
		</div>
		<div>
			<table cellpadding="5" style="width:100%;height:100%;">
				<tr>
					<th>
						客户姓名
					</th>
					<td>
						<input name="name" class="easyui-textbox" readonly="readonly" style="background-color: #EBEBE4" type="text" value="韩冰"/>
						<input name="loanOrderId" type="hidden" />
					</td>
					<th>
						身份证号
					</th>
					<td >
						<input readonly="readonly" style="background-color: #EBEBE4" style="background-color: #EBEBE4" class="easyui-validatebox easyui-textbox" name="idNo"  type="text" />
					</td>
					<th>
						申请金额(元)
					</th>
					<td>
						<input name="loanAmount" readonly="readonly" style="background-color: #EBEBE4" class="easyui-validatebox easyui-textbox" type="text" value=""/>
					</td>
				</tr>
				
				<tr>
					<th>
						贷款目的
					</th>
					<td>
						<input name="purpose"  readonly="readonly" style="background-color: #EBEBE4" class="easyui-validatebox easyui-textbox"/>
					</td>
					<th>
						调查日期
					</th>
					<td >
						<input id="surveyDate" name="surveyDate" class="easyui-textbox easyui-datebox" data-options="editable:false"/>
					</td>
					<th>
						所在地区
					</th>
					<td><input readonly="readonly" style="background-color: #EBEBE4" class="easyui-validatebox easyui-textbox" name="loanCtiy"  type="text"/></td>
				</tr>
				
				<tr>
					<th>
						调查人员
					</th>
					<td colspan="5">
						A:<input id="operatorAR" name="operatorA" />&nbsp;&nbsp;&nbsp;
						B:<input id="operatorBR" name="operatorB"  /> 
					</td>
				</tr>
				<tr>
					<th>拒绝原因:</th>
				</tr>
				<tr>
					<td colspan="6">
						<textarea class="easyui-validatebox easyui-textbox" name="rejectCause" style="width:99%;height:320px;resize: none;"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div id="upload_form" style="width: 100%; height: 30px; text-align: right;">
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="saveMicrocreditOpinion('microcreditOpinionRefuseForm');">提交</a>
						</div> 
					</td>
				</tr>
			</table>
			<!-- <div style="width:99.5%;height:400px;">
				<div style="height:30px;">
						<span style="font-weight:bold;padding-left:10px;">拒绝原因：</span>
				</div>
				<div style="padding:20px 0 20px 20px;height:330px;overflow:auto;">
					<textarea class="easyui-validatebox easyui-textbox" name="rejectCause" style="width:99%;height:320px;resize: none;"></textarea>
				</div>
			</div> -->
			<!-- <div style="height:40px;">
				<table cellpadding="8px;">
					<tr>
						<th>
							业务经办人
						</th>
						<td>
							<input readonly="readonly" style="background-color: #EBEBE4" type="text" name="operatorAR" class="easyui-validatebox easyui-textbox" />
						</td>
						<td>
							<input readonly="readonly" style="background-color: #EBEBE4" type="text" name="operatorBR" class="easyui-validatebox easyui-textbox" />
						</td>
						<th >
							部门负责人
						</th>
						<td >
							<input class="easyui-validatebox easyui-textbox" name="deptPrincipal" data-options="required:true"/>
						</td>
					</tr>
				</table>
			</div> -->
		</div>
	</form>	
 </div>
<!-- 决绝决议表E -->