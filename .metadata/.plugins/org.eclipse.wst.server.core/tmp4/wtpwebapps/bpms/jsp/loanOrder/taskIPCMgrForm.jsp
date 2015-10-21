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
		editable:false ,
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
		},
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
	
	$("#microcreditOpinionForm input").css("background-color","#EBEBE4");
	$("#microcreditOpinionForm textarea").css("background-color","#F5F5F5");
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
						"processingResult":result=="IPCDeptLeaderThrough"?"A":"B",
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

	//微贷业务呈报意见表
	function microcreditOpinion(){
		//加载微贷业务表
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
		$("#microcreditOpinionForm input").attr("readonly","readonly");
		$("#microcreditOpinionForm textarea").attr("readonly","readonly");
		//渲染贷审委信息
		$.ajax({
				type : "POST",
				url : "loanOrderHis/loanOrderHisAction!findLoanOrderHis.action",
				data : {"loanOrderId":$row.loanOrderId},
				success : function(data) {
					if(data && data.length >0){
						$("#processingResult1").val(data[0].processingResult);
						$("#processingResult2").val(data[1].processingResult);
						$("#processingResult3").val(data[2].processingResult);
						$("#comment1").val(data[0].comment);
						$("#comment2").val(data[1].comment);
						$("#comment3").val(data[2].comment);
					}
				}
			});
		$("#microcreditOpinionDlg").dialog("open");	
	}
	
	// 查看附件
	function lookAttachment(index){
		var row = getRowData($datagrid,index);
		// 附件信息
		checkAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');
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

	
	// 填写最终通过金额
	function finalMicrocreditOpinionDg(){
		// 验证是否涉及车贷
		if($("#isInvolvedLoanCar").combobox("getValue")==""){
			$.messager.alert("提示","请选择是否涉及车贷!","warning")
			return false;
		}
		// 验证备注信息是否已经填写
		if($("#comment").val()==""){
			$.messager.alert("提示","请填写备注信息后再进行提交!","warning")
			return false;
		}
		// 最终还款方式
		$("#finalRepayMthd").val(checkSysParameter('loan_mthd'));
		//期限
		$("#finalLoanPeriod").combobox({
			valueField : 'code',
			textField : 'text',
			url:'common/commonAction!findTextArr.action?codeMyid=loan_period_type',
			onLoadSuccess : function(){
				var val = $(this).combobox("getData");
				if(!$.isEmptyObject(val)){
	                $(this).combobox("select", val[0]["code"]);
				}
			},
			editable:false ,
	    });
		$("#finalMicrocreditOpinionDg").dialog("open");

	}
	
	// 提交
	function finalMicrocreditFormSubmit(result,object){
		// 提交数据
		var data ={
				"loanOrderId" : $row.loanOrderId,
				"finalLoanAmt" : $("#finalLoanAmt").val(),
				"finalLoanPeriod" : $("#finalLoanPeriod").combobox("getValue"),
				"finalRepayMthd" : $("#finalRepayMthd").val()
		}
		
		// 判断
		if(data.finalLoanAmt=="" || data.finalLoanPeriod =="" ){
			$.messager.alert("提示","请填完整注信息后再进行提交!","warning")
			return false;
		}
		
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if (r){			//渲染贷审委信息
				$.ajax({
						type : "POST",
						url : "microcreditOpinion/microcreditOpinionAction!saveFinalMicrocreditOpinion.action",
						data : data,
						success : function(data) {
							if(data.status){
								$("#finalMicrocreditOpinionDg").dialog("close");
								var param = {
										"comment" : $("#comment").val(),
										"result" :result,
										"loanOrderId" : $row.loanOrderId,
										"taskId": $row.taskId,
										"processingResult":result=="IPCDeptLeaderThrough"?"A":"B",
										"isInvolvedLoanCar":$("#isInvolvedLoanCar").combobox("getValue")
								}
								$.ajax({
									type : "POST",
									url : "loanOrder/loanOrderAction!submitTask.action",
									data : param,
									success : function(msg) {
										$("#finalMicrocreditForm input[name='finalLoanAmt']").val("");
										$grid.datagrid('reload');
										$taskFormDialog.dialog('close');
									}
								});
							}
						}
					});
				}
			});
	}
	
	//查看稽核信息
	function checkAuditReportDetail(){
		window.open("jsp/loanOrder/auditInfoRecordDetail.jsp?loanOrderId="+$row.loanOrderId,
				"稽核信息详情", 'height=650, width=1000, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')
	}
</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">
	<div style="width: 980px;height: 195px;overflow: auto;">
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
						<th>是否涉及车贷:</th>
						<td><input id="isInvolvedLoanCar" name="isInvolvedLoanCar" class="easyui-validatebox easyui-combobox" type="text"/></td>
					</tr>
					<tr>
					 	<th>理由:</th>
						<td colspan="3">
							<textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:100%;height:70px;"></textarea>
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
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="microcreditOpinion();">查看微贷业务呈报意见表</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="finalMicrocreditOpinionDg();">IPC部门经理通过</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('IPCDeptLeaderReject',this);">IPC部门经理拒贷</a>
		</div>
		<div id="lookInfo" class="easyui-accordion" style="height: 370px;width: 980px;overflow: hidden;">
		    <div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true" >   
				<table id="lookLoanOrderdg" title="申请备注的信息"></table>
			</div>
		</div>
</div>  
<!-- 受理任务 E -->
	
<!-- 微贷业务呈报意见表E -->				
<div id="microcreditOpinionDlg" class="easyui-dialog" title="微贷业务呈报意见表" data-options="border:false,closed:true,modal:true" style="width: 1000px; height: 650px; overflow: auto;">
	<form id="microcreditOpinionForm" method="post" >
		<input name="mcbrId" type="hidden" />
		<div >
			<table cellpadding="5px;" style="widht:100%;">
				<tr>
					<th>借款人</th>
					<td>
						<input readonly="readonly" class="easyui-textbox" name="name"  type="text" />
					</td>
					<th>身份证号</th>
					<td >
						<input readonly="readonly" class="easyui-textbox" name="idNo"  type="text" />
					</td>
					<th>贷款用途</th>
					<td><input class="easyui-textbox" name="purpose"  type="text" value=""/></td>
				</tr>
				<tr>
					<th>共同借款人</th>
					<td><input class="easyui-textbox" name="coborrowerName"  type="text" /></td>
					<th>身份证号</th>
					<td ><input class="easyui-textbox"   name="coborrowerIdno" type="text" /></td>
					<th>咨询服务费</th>
					<td><input readonly="readonly" class="easyui-textbox" name="counselingRate"  type="text" />%</td>
				</tr>
				<tr>
					<th>建议金额(元)</th>
					<td><input class="easyui-textbox" name="adviceLoanAmt"  type="text"/></td>
					<th>期限(月)</th>
					<td><input class="easyui-textbox" name="adviceLoanPeriod"  type="text" /></td>
					<th>利率(年)</th>
					<td colspan="3"><input readonly="readonly" class="easyui-textbox" name="loanRate"  type="text" />%</td>
					<!-- <th>放款方式</th>
					<td><input readonly="readonly" class="easyui-textbox" name="loanMthd"  type="text" /></td> -->
				</tr>
				<!-- <tr>
					<th>贷款用途</th>
					<td><input class="easyui-textbox" name="purpose"  type="text" value=""/></td>
					<th>利率(年)</th>
					<td colspan="3"><input readonly="readonly" class="easyui-textbox" name="loanRate"  type="text" />%</td>
				</tr> -->
				<tr>
					<th>放款方式</th>
					<td><input readonly="readonly" class="easyui-textbox" name="loanMthd"  type="text" /></td>
					<th>还款方式</th>
					<td><input readonly="readonly" class="easyui-textbox" name="adviceRepayMthd"  type="text" /></td>
					<th>收取方式</th>
					<td><input readonly="readonly" class="easyui-textbox" name="collectionMthd"  type="text" /></td>
				</tr>
				<tr>
					<!-- <th>咨询服务费</th>
					<td><input readonly="readonly" class="easyui-textbox" name="counselingRate"  type="text" />%</td> -->
					<!-- <th>收取方式</th>
					<td colspan="3"><input readonly="readonly" class="easyui-textbox" name="collectionMthd"  type="text" /></td> -->
				</tr>
				<tr>
					<th>经办机构/部门</th>
					<td><input readonly="readonly" class="easyui-textbox" name="loanCtiy"  type="text"/></td>
					<th>经办人</th>
					<td >
						A:<input class="easyui-textbox" name="operatorA"  type="text" />&nbsp;&nbsp;&nbsp;
					</td>
					<td colspan="2">	
						B:<input class="easyui-textbox" name="operatorB"  type="text" /> 
					</td>
				</tr>
				<tr>
					<th>风险控制措施</th>
					<td><input class="easyui-textbox" name="riskCtrlMeasures" /></td>
				</tr>
				<tr>
					<th>具体措施</th>
					<td colspan="5"><textarea class="easyui-textbox" name="specificMeasures" style="width:99%;height:70px;resize: none;"></textarea></td>
				</tr>
			</table>
			<div>
				<table cellpadding="5px;">
					<tr>
						<th rowspan="2">业务经办人</th>
						<td>A:<input class="easyui-textbox" name="operatorA"  type="text" /></td>
						<th rowspan="2">后台人员</th>
						<th>初次上会</th>
						<td><input class="easyui-textbox" name="firstMeeting" type="text" /></td>
						<th rowspan="2">部门负责人</th>
						<td rowspan="2"><input class="easyui-textbox" name="deptPrincipal" type="text" /></td>
					</tr>
					<tr>
						<td>B:<input class="easyui-textbox" name="operatorB"  type="text" /> </td>
						<th>补调核实</th>
						<td><input class="easyui-textbox" name="verification" type="text" /></td>
					</tr>
					<tr>
						<th >贷审委1</th>
						<td colspan="7"><input id="processingResult1"  name="processingResult" class="easyui-textbox" readonly="readonly"/><textarea class="easyui-textbox" id="comment1" style="width:99%;height:70px;resize: none;" type="text" readonly="readonly"></textarea></td>
					</tr>
					<tr>
						<th >贷审委2</th>
						<td colspan="7"><input id="processingResult2" name="processingResult" class="easyui-textbox" type="text" readonly="readonly"/><textarea class="easyui-textbox" id="comment2"  style="width:99%;height:70px;resize: none;" readonly="readonly"></textarea></td>
					</tr>
					<tr>
						<th>贷审委3</th>
						<td colspan="7"><input id="processingResult3" name="processingResult" class="easyui-textbox" type="text" readonly="readonly"/><textarea class="easyui-textbox" id="comment3" style="width:99%;height:70px;resize: none;" readonly="readonly"></textarea></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</div>
<!-- 微贷业务呈报意见表E -->	
			
<div id="finalMicrocreditOpinionDg" class="easyui-dialog" title="微贷业务呈报意见表" data-options="border:false,closed:true,modal:true"  style="width: 1000px; height: 650px; overflow: auto;text-align: center;">
	<form id="finalMicrocreditForm" method="post">
		<table cellpadding="8px;" style="text-align: center;">
			<tr>
				<th>最总通过金额(元)</th>
				<td><input class="easyui-validatebox easyui-textbox" data-options="validType:'mDouble1',required:true" id="finalLoanAmt" name="finalLoanAmt"  /></td>
				<th>最终通过期限(月)</th>
				<td><input class="easyui-validatebox" id="finalLoanPeriod" name="finalLoanPeriod" class="easyui-validatebox easyui-combobox" type="text" /></td>
			</tr>
			<tr>
				<th>最终放款方式</th>
				<td><input readonly="readonly" class="easyui-validatebox" id="finalRepayMthd" name="finalRepayMthd"  type="text" /></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: right;">
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="finalMicrocreditFormSubmit('IPCDeptLeaderThrough',this)">提交</a>
				</td>
			</tr>
		</table>
	</form>
</div>
