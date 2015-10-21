<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<style type="text/css">
	#acceptTaskForm table input{border: none;}
	.table th{ text-align: right;}
	.table td{ text-align: left;}
</style>
<script type="text/javascript">
var $row;
var $creditAuditReport;
var $datagrid;
var userData;
var $result;
var $object;
$(function(){
	// 查看申请状态
	$row = $grid.datagrid('getSelected');
	$datagrid = $("#lookLoanOrderdg").datagrid({
		url : "loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action",
		width : 'auto',
		height : 340,
		pagination:false,
		rownumbers:true,
		border:true,
		singleSelect:true,
		nowrap:true,
		queryParams:{"loanOrderId":$row.loanOrderId},
		multiSort:false,
		fitColumns:true,
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
	
   $("#microcreditOpinionDlg input[name='operatorA']").combobox({
		valueField : 'code',
		textField : 'text',
		required:true,
		url:'users/usersAction!findUsers.action',
		onLoadSuccess : function(){
			userData = $(this).combobox("getData");
			for (var item in userData[0]) {
                if (item == "code") {
                    $(this).combobox("select", userData[0][item]);
                }
            }
		},
		editable:false ,
		onSelect:function(record){
			$("input[name='operatorAS']").val(record.text);
		},
    }); 
   
	   $("#microcreditOpinionDlg input[name='operatorB']").combobox({
			valueField : 'code',
			textField : 'text',
			required:true,
			url:'users/usersAction!findUsers.action',
			onLoadSuccess : function(){
				var val = $(this).combobox("getData");
				if(!$.isEmptyObject(val)){
					userData = val;
	                $(this).combobox("select", val[0]["code"]);
				}
			},
			editable:false ,
			onSelect:function(record){
				$("input[name='operatorBS']").val(record.text);
			},
	   }); 
   
	   $("#microcreditOpinionRefuseDlg input[name='operatorA']").combobox({
			valueField : 'code',
			textField : 'text',
			required:true,
			url:'users/usersAction!findUsers.action',
			onLoadSuccess : function(){
				var val = $(this).combobox("getData");
				if(!$.isEmptyObject(val)){
					userData = val;
	                $(this).combobox("select", val[0]["code"]);
				}
			},
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
			onLoadSuccess : function(){
				var val = $(this).combobox("getData");
				if(!$.isEmptyObject(val)){
					userData = val;
	                $(this).combobox("select", val[0]["code"]);
				}
			},
			editable:false ,
			onSelect:function(record){
				$("input[name='operatorBR']").val(record.text);
			},
	  }); 
	
	//还款期限
	$("#microcreditOpinionDlg input[name='adviceLoanPeriod']").combobox({
		valueField : 'code',
		textField : 'text',
		required:true,
		url:'common/commonAction!findTextArr.action?codeMyid=loan_period_type',
		editable:false ,
		onLoadSuccess : function(){
			var val = $(this).combobox("getData");
			if(!$.isEmptyObject(val)){
				//userData = val;
                $(this).combobox("select", val[0]["code"]);
			}
		},
    });
	
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
		var data = {
			"comment" : $("#comment").val(),
			"result" :result,
			"loanOrderId" : $row.loanOrderId,
			"taskId" : $row.taskId,
			"processingResult":result=="IPCInvestigationDeptThrough"?"A":"B"
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

	// 查看附件
	function lookAttachment(index){
		var row = getRowData($datagrid,index);
		// 附件信息
		checkAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');
	}
	
	//微贷业务呈报意见表
	function microcreditOpinion(result){
		$result = result;
		if($("#comment").val()==""){
			$.messager.alert("提示","请填写备注信息后再进行提交!","warning")
			return false;
		}
		$("#microcreditOpinionForm").form("clear");
		//加载微贷业务表
		$("#microcreditOpinionForm").form("load","microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionByOid.action?loanOrderId="+$row.loanOrderId);
		$("#microcreditOpinionForm :input").removeAttr("disabled");
		
		$("#microcreditOpinionForm input[name='adviceLoanAmt']").val("");
		// 组织机构的信息--进件城市
		$.ajax({
			type : "POST",
			url : "loanOrder/loanOrderAction!findLoanCityByOrderId.action",
			data : {"loanOrderId":$row.loanOrderId},
			success : function(data) {
				if(data){
					$("#microcreditOpinionDlg input[name='loanCtiy']").val("IPC项目组-"+data.fullName);
				}
			}
		});
		$("#microcreditOpinionDlg input[name='name']").val($row.name);
		$("#microcreditOpinionDlg input[name='loanOrderId']").val($row.loanOrderId);
		$("#microcreditOpinionDlg input[name='idNo']").val($row.idNo);
		$("#microcreditOpinionDlg input[name='purpose']").val($row.purpose);
		$("#microcreditOpinionDlg input[name='adviceLoanAmt']").val($row.loanAmount);
		//checkSysParameter
		$("#microcreditOpinionDlg input[name='loanMthd']").val(checkSysParameter('loan_mthd'));
		$("#microcreditOpinionDlg input[name='adviceRepayMthd']").val(checkSysParameter('repay_mthd'));
		$("#microcreditOpinionDlg input[name='loanRate']").val(checkSysParameter('loan_rate'));
		$("#microcreditOpinionDlg input[name='counselingRate']").val(checkSysParameter('counseling_rate'));
		$("#microcreditOpinionDlg input[name='collectionMthd']").val(checkSysParameter('collection_mthd'));
		
		$("#operatorA").combobox("select",userData[0].code);
		$("#operatorB").combobox("select",userData[0].code);
		$("input[name='operatorAS']").val($("#operatorA").combobox('getText'));
		$("input[name='operatorBS']").val($("#operatorB").combobox('getText'));
		//根据订单ID 共同贷款人 名称 ID
		$.ajax({
			url : "loanerJoint/loanerJointAction!findLoanerJointByOrderId.action",
			data : {
				loanOrderId : $row.loanOrderId
			},
			async: false,
			type : "post",
			success : function(data) {
				if(data){
					$("#microcreditOpinionDlg input[name='coborrowerName']").val(data.name);
					$("#microcreditOpinionDlg input[name='coborrowerIdno']").val(data.idNo);
				}
			},
			error:function(){
				
			}
		});
		
		$("#microcreditOpinionDlg").dialog({
			closed:false,
			onClose:function(){
				$("#microcreditOpinionForm :input").attr("disabled","disabled");
			}
		});	
		
	}
	
	//拒绝决议表
	function microcreditOpinionRefuse(result){
		//加载拒绝决议表
		$("#microcreditOpinionRefuseForm").form("load","microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionByOid.action?loanOrderId="+$row.loanOrderId);
		$result = result;
		if($("#comment").val()==""){
			$.messager.alert("提示","请填写备注信息后再进行提交!","warning")
			return false;
		}
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
		$("#microcreditOpinionRefuseDlg input[name='name']").val($row.name);
		$("#microcreditOpinionRefuseDlg input[name='loanOrderId']").val($row.loanOrderId);
		$("#microcreditOpinionRefuseDlg input[name='idNo']").val($row.idNo);
		$("#microcreditOpinionRefuseDlg input[name='purpose']").val($row.purpose);
		$("#microcreditOpinionRefuseDlg input[name='loanAmount']").val($row.loanAmount);
		
		$("#microcreditOpinionRefuseDlg input[name='operatorAR']").val($("#operatorAR").combobox('getText'));
		$("#microcreditOpinionRefuseDlg input[name='operatorBR']").val($("#operatorBR").combobox('getText'));
		
		$("#microcreditOpinionRefuseDlg").dialog({
			closed:false,
			onClose:function(){
				$("#surveyDate").datebox("setValue","");
				$("#microcreditOpinionRefuseForm textarea[name='rejectCause']").val("");
				$("#microcreditOpinionRefuseForm input[name='deptPrincipal']").val("");
			}
		});
	}
	
	//微带呈报意见表保存
	function microcreditOpinionSubmit(formId,dlgId){
		var isCheck = false;
		$("#checkBoxDiv input[type='checkbox']").each(function() {
			if($(this).attr("checked")){
				isCheck = true;
			}
		});
		if(isCheck==false){
			$.messager.alert("提示","至少选择一条风险控制措施","info");
			return false;
		}
		
		saveMicrocreditOpinion(formId,dlgId);
	}
	
	//拒绝意见表提交保存
	function microcreditOpinionRefuseSubmit(formId,dlgId){
		saveMicrocreditOpinion(formId,dlgId);
	}

	// 保存微保意见
	function saveMicrocreditOpinion(formId,dlgId){
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
						data = $.parseJSON(data);
						$("#"+formId+" input[name='mcbrId']").val(data.data);
						$("#"+dlgId).dialog("close");
						submitTask($result);
					}
				});
			}
		});
	}
	
	// IPC放弃处理，跳入到执行外访
	function ipcAuditBackOut(result){
		$result = result;
		// 判断备注是否已经填写
		if($("#comment").val()==""){
			$.messager.alert("提示","请填写备注信息后再进行提交!","warning")
			return false;
		}
		// 确认是否提交
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if (r){
					submitTask($result);
				}
		});
	}
	
	//查询系统参数
	function checkSysParameter(paramCode){
		var datas = "";
		$.ajax({
			url:"sysParameter/sysParameterAction!findSysParameter.action",
			type:"post",
			async:false,
			data:{"parmCode":paramCode},
			success:function(data){
				datas = data.parmValue;
			},
			error:function(){
			}
		});
		return datas;
	}
	
	//查看稽核信息
	function checkAuditReportDetail(){
		window.open("jsp/loanOrder/auditInfoRecordDetail.jsp?loanOrderId="+$row.loanOrderId,
				"稽核信息详情", 'height=650, width=1000, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')
	}
		
</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">
	<div style="width: 986px;height: 190px;overflow: auto;">
			<form id="acceptTaskForm" method="post">
				 <input name="id" id="id"  type="hidden"/>
				 <input name="auditId" type="hidden" value="noauditId"/>
				 <table class="table" cellpadding="5px;">
					 <tr>
					    <th>客户姓名:</th>
						<td><input name="name" readonly="readonly" style="background-color: #EBEBE4" type="text"/></td>
					</tr>
					<tr>
						<th>身份证号:</th>
						<td><input name="idNo" readonly="readonly" style="background-color: #EBEBE4" type="text"/></td>
					</tr>
					<tr>
					 	<th>备注:</th>
						<td colspan="3">
							<textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:300px;height:70px;"></textarea>
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
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkAuditReportDetail();">查看稽核信息</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="microcreditOpinion('IPCInvestigationDeptThrough');">填写呈报意见表并通过</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="microcreditOpinionRefuse('IPCInvestigationDeptReject');">填写拒绝决议表并驳回</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="ipcAuditBackOut('IPCInvestigationDeptBackOut');">IPC放弃处理</a>
		</div>
		
		<div id="lookInfo" class="easyui-accordion" style="height: 370px;width: 980px;overflow: hidden;">
		    <div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true" >   
				<table id="lookLoanOrderdg" title="申请备注的信息"></table>
			</div>
		</div>
</div>  
<!-- 受理任务 E -->		

<!-- 微贷业务呈报意见表E -->				
<div id="microcreditOpinionDlg" class="easyui-dialog" title=" " data-options="border:false,closed:true,modal:true" style="width:900px;height:700px; overflow: hidden;">
	<form id="microcreditOpinionForm" method="post">
		<input name="mcbrId" type="hidden" />
		<div style="text-align:center;">
			<font size="4" style="font-weight: bold;">微贷业务呈报意见表</font>
		</div>
		<div>
			<table cellpadding="8px;">
				<tr>
					<th>
						借款人
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="name"  type="text" />
						<input name="loanOrderId" type="hidden" />
					</td>
					<th>
						身份证号
					</th>
					<td colspan="3">
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="idNo"  type="text" />
					</td>
				</tr>
				
				<tr>
					<th>
						共同借款人
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="coborrowerName"  type="text" />
					</td>
					<th>
						身份证号
					</th>
					<td colspan="3">
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" data-options="validType:'idcard'" name="coborrowerIdno"  type="text" />
					</td>
				</tr>
				
				<tr>
					<th>
						建议金额(元)
					</th>
					<td>
						<input class="easyui-validatebox easyui-textbox" data-options="validType:'mDouble',required:true" name="adviceLoanAmt" />
					</td>
					<th>
						期限(月)
					</th>
					<td>
						<input class="easyui-validatebox easyui-textbox easyui-combobox" name="adviceLoanPeriod" />
					</td>
					<th>
						放款方式
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="loanMthd"  type="text" />
					</td>
				</tr>
				
				<tr>
					<th>
						贷款用途
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="purpose"  type="text" value=""/>
					</td>
					<th>
						利率(年)
					</th>
					<td colspan="3">
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="loanRate"  type="text" />%
					</td>
				</tr>
				
				<tr>
					<th>
						还款方式
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="adviceRepayMthd"  type="text" />
					</td>
					<td>
						
					</td>
					<td colspan="3">
						
					</td>
				</tr>
				
				<tr>
					<th>
						咨询服务费
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="counselingRate"  type="text" />%
					</td>
					<th>
						收取方式
					</th>
					<td colspan="3">
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="collectionMthd"  type="text" />
					</td>
				</tr>
				
				<tr>
					<th>
						经办机构/部门
					</th>
					<td><input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="loanCtiy"  type="text"/></td>
					<th>
						经办人
					</th>
					<td colspan="3">
						A:<input id="operatorA" class="easyui-validatebox easyui-textbox easyui-combobox" name="operatorA"  />&nbsp;&nbsp;&nbsp;
						B:<input id="operatorB" class="easyui-validatebox easyui-textbox easyui-combobox" name="operatorB" /> 
					</td>
				</tr>
			</table>
			<div style="width:99.5%;height:270px;">
				<div id="checkBoxDiv" style="padding-left:10px;height:30px;">
						<span style="font-weight:bold;">风险控制措施:&nbsp;&nbsp;&nbsp;</span>
						<input class="checkbox" id="baozhengren" type="checkbox" name="riskCtrlMeasures" checked="checked" value="保证人" /><label for="baozhengren">保证人</label>
						<input class="checkbox" id="diya" type="checkbox" name="riskCtrlMeasures" value="抵押" /><label for="diya">抵押</label>
						<input class="checkbox" id="zhiya" type="checkbox" name="riskCtrlMeasures" value="质押" /><label for="zhiya">质押</label>
						<input class="checkbox" id="baozhengjin" type="checkbox" name="riskCtrlMeasures" value="保证金" /><label for="baozhengjin">保证金</label>
						
						<span style="padding-left:20px;font-weight:bold;">具体措施如下:</span>
				</div>
				<div style="height:230px;overflow:auto;">
						<textarea class="easyui-validatebox easyui-textbox" data-options="required:true" name="specificMeasures" style="width:99%;height:220px;resize: none;">
						
						</textarea>
				</div>
			</div>
			<div>
				<table cellpadding="8px;">
					<tr>
						<th rowspan="2">
							业务经办人
						</th>
						<td>
							<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="operatorAS" type="text" />
						</td>
						<th rowspan="2">
							后台人员
						</th>
						<th>
							初次上会
						</th>
						<td>
							<input class="easyui-validatebox easyui-textbox" name="firstMeeting" type="text" />
						</td>
						<th rowspan="2">
							部门负责人
						</th>
						<td rowspan="2">
							<input class="easyui-validatebox easyui-textbox" name="deptPrincipal" data-options="required:true" />
						</td>
					</tr>
					<tr>
						<td>
							<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="operatorBS" type="text" />
						</td>
						<th>
							补调核实
						</th>
						<td>
							<input class="easyui-validatebox easyui-textbox" name="verification" type="text" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div id="upload_form" style="width: 100%; height: 30px; text-align: right;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="microcreditOpinionSubmit('microcreditOpinionForm','microcreditOpinionDlg');">提交</a>
	</div> 
</div>
<!-- 微贷业务呈报意见表E -->		
		
<!-- 拒绝决议表S -->
<div id="microcreditOpinionRefuseDlg" class="easyui-dialog" title=" " data-options="border:false,closed:true,modal:true" style="width:800px;height:700px;">
	<form id="microcreditOpinionRefuseForm" method="post"> 
		<input name="mcbrId" type="hidden" />
		<div style="text-align:center;">
			<font size="4" style="font-weight: bold;">拒绝决议表</font>
		</div>
		<div>
			<table cellpadding="8px;">
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
				</tr>
				
				<tr>
					<th>
						申请金额(元)
					</th>
					<td>
						<input name="loanAmount" readonly="readonly" style="background-color: #EBEBE4" class="easyui-validatebox easyui-textbox" type="text" value=""/>
					</td>
					<th>
						调查日期
					</th>
					<td >
						<input id="surveyDate" name="surveyDate" class="easyui-textbox easyui-datebox" data-options="editable:false"/>
					</td>
				</tr>
				
				<tr>
					<th>
						贷款目的
					</th>
					<td colspan="3">
						<input name="purpose"  readonly="readonly" style="background-color: #EBEBE4" class="easyui-validatebox easyui-textbox"/>
					</td>
				</tr>
				
				<tr>
					<th>
						所在地区
					</th>
					<td><input readonly="readonly" style="background-color: #EBEBE4" class="easyui-validatebox easyui-textbox" name="loanCtiy"  type="text"/></td>
					<th>
						调查人员
					</th>
					<td>
						A:<input id="operatorAR" class="easyui-validatebox easyui-textbox easyui-combobox"  name="operatorA" />&nbsp;&nbsp;&nbsp;
						B:<input id="operatorBR" class="easyui-validatebox easyui-textbox easyui-combobox" name="operatorB"  /> 
					</td>
				</tr>
			</table>
			<div style="width:99.5%;height:400px;">
				<div style="height:30px;">
						<span style="font-weight:bold;padding-left:10px;">拒绝原因：</span>
				</div>
				<div style="padding:20px 0 20px 20px;height:330px;overflow:auto;">
					<textarea class="easyui-validatebox easyui-textbox" name="rejectCause" style="width:99%;height:320px;resize: none;"></textarea>
				</div>
			</div>
			<div style="height:40px;">
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
			</div>
		</div>
	</form>	
	<div id="upload_form" style="width: 100%; height: 30px; text-align: right;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="microcreditOpinionRefuseSubmit('microcreditOpinionRefuseForm','microcreditOpinionRefuseDlg');">提交</a>
	</div> 
 </div>
<!-- 决绝决议表E -->	
