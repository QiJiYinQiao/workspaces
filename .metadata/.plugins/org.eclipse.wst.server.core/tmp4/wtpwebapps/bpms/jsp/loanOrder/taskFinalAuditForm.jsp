<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<style type="text/css">
	#acceptTaskForm table input{border: none;}
	.table th{
		text-align: right;
	}
	.table td{
		text-align: left;
	}
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
				$attempData = val;
                $(this).combobox("select", $attempData[0]["code"]);
			}
		},
		editable:false
    });
	

	loadAttachmentList('attachmentList','noauditId',$row.loanOrderId);
	
});

	// 提交表单信息
	function  submitTask(result) {
		var data = {
			"comment" : $("#comment").val(),
			"result" :result,
			"loanOrderId" : $row.loanOrderId,
			"taskId": $row.taskId,
			"processingResult":result=="FinalAuditThrough"?"A":"B"
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
	
	// 渲染贷款期限
   $("input[name='loanPeriodType']").combobox({
		url : "common/commonAction!findTextArr.action?codeMyid=loan_period_type",
		valueField : 'code',
		textField : 'text',
		onSelect:function(){
			 calculate();
		},
		onLoadSuccess : function(){
            var val = $(this).combobox("getData");
            for (var item in val[0]) {
                if (item == "code") {
                    $(this).combobox("select", val[0][item]);
                }
            }
			},
   });

	// 渲染月服务费率
   $("input[name='monthServiceFeeRate']").combobox({
		url : "common/commonAction!findTextArr.action?codeMyid=month_service_fee_rate",
		valueField : 'code',
		textField : 'text',
		onSelect:function(){
			 calculate();
		},
		onLoadSuccess : function(){
            var val = $(this).combobox("getData");
            for (var item in val[0]) {
                if (item == "code") {
                    $(this).combobox("select", val[0][item]);
                }
            }
			},
   });

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
	$("input[name='loanInterestRate']").numberbox({    
	    value:checkSysParameter('loan_rate'),
	    disabled:true
	});  


	// 终审资质分析
	function applicationReport(){
		// 验证备注信息是否已经填写
		if($("#comment").val()==""){
			$.messager.alert("提示","请填写备注信息后再进行提交!","warning")
			return false;
		}
		$("#applicationReportDialog").dialog("open");
		$("#visitFee").numberbox("setValue",'');
		$("#contractLoanAmount").numberbox('setValue','');
		$("#actualLoanAmount").numberbox("setValue", 0);
		$("#monthRepay").numberbox("setValue",0);
	}
	// 加载信息
	function loadFinalAuditReport(data){
		if(!$.isEmptyObject(data)){
			$("#finalAuditReport-form").form("load",data);
		}
	}

	// 保存资质分析的信息
	function saveFinalAuditReport(result,object){
		// 验证备注信息是否已经填写
		if($("#comment").val()==""){
			$.messager.alert("提示","请填写备注信息后再进行提交!","warning")
			return false;
		}
		// 确认是否提交
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if (r){
					$(object).parents("form:first").form('submit', {
						url : "finalAuditReportAction/finalAuditReportAction!saveFinalAuditReport.action",
						onSubmit : function(param) {
							var isValid = $(this).form('validate');
							if (isValid){
								param.loanOrderId = $row.loanOrderId;
							}
							return isValid; // 返回false终止表单提交
						},
						success : function(data) {
							data = $.parseJSON(data);
							loadFinalAuditReport(data.data);
							alertMsg(data);
							$.messager.progress('close'); // 如果提交成功则隐藏进度条
							$("#applicationReportDialog").dialog("close");
							// 确认是否提交
							submitTask(result);
						}
				});
			}
		});
	}
	
	//驳回
	function finalAuditRefuse(result){
		// 验证备注信息是否已经填写
		if($("#comment").val()==""){
			$.messager.alert("提示","请填写备注信息后再进行提交!","warning")
			return false;
		}
		
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
		if (r) {
				// 确认是否提交
				submitTask(result);
			}
		});
	}

	// 提示信息
	function alertMsg(data) {
		if (data.status) {
			$.messager.show({
				title : data.title,
				msg : data.message,
				timeout : 5000,
				showType : 'slide'
			})
		} else {
			$.messager.alert(data.title, data.message, 'error');
		}

	}

	// 计算金额
	function calculate() {
		// 合同金额
		var contractLoanAmount = Number($("#contractLoanAmount").val());
		// 贷款期限
		var loanPeriodType = Number($("#loanPeriodType").combobox("getText"));
		// 月服务汇率
		var monthServiceFeeRate = Number(parseFloat($("#monthServiceFeeRate")
				.combobox("getText")) / 100.0);
		// 利息
		/* var loanInterestRate = Number($("#loanInterestRate")
				.combobox("getText")); */
		var loanInterestRate = Number($("#loanInterestRate").numberbox(
				'getValue'));
		// 信访费用
		var visitFee = Number($("#visitFee").val());
		// 计算金额
		if (contractLoanAmount != "" && loanPeriodType != ""
				&& monthServiceFeeRate != "" && loanInterestRate != ""
				&& visitFee != "") {
			// 实放金额 = 合同金额-(合同金额*(服务汇率*贷款期限)+信访费用)
			var actualLoanAmount = contractLoanAmount
					- (contractLoanAmount
							* (monthServiceFeeRate * loanPeriodType) + visitFee);
			// 月还款额 = (合同金额/贷款期限)+合同金额*利息
			var monthRepay = (contractLoanAmount / loanPeriodType)
					+ contractLoanAmount * loanInterestRate;
			$("#actualLoanAmount").numberbox("setValue", actualLoanAmount);
			$("#monthRepay").numberbox("setValue", monthRepay);
		}
	}

	// 查看附件
	function lookAttachment(index) {
		var row = getRowData($datagrid,index);
		// 附件信息
		$("#lookAttachmentList").datagrid({
				url : "attachment/attachmentAction!findAttachmentListByUserIdAndOrderId.action",
				width : 'auto',
				height : 240,
				pagination : true,
				rownumbers : true,
				border : true,
				singleSelect : true,
				nowrap : true,
				queryParams : {
					"loanOrderId" : row.loanOrderId,
					"userId" : row.assignee
				},
				multiSort : false,
				columns : [ [
						{field : 'attName',title : '附件名称',width : parseInt($(this).width() * 0.1),sortable : true},
						{field : 'attTypeName',title : '附件类型',width : parseInt($(this).width() * 0.1)},
						{field : 'creatorName',title : '创建者',width : parseInt($(this).width() * 0.1),align : 'left'},
			            {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.1),align : 'left',
			            	formatter:function(value,row,index){
			            		var result = "<a target='_blank' href='jsp/openoffice/documentView.jsp?attId="+row.attId+"'>在线预览</a>　　" ;
			            			result += "<a target='_blank' href='javascript:void(0);' onclick=\"downloadAttachment('"+row.attId+"');\">下载</a>　　" ;
			            		return result;
			            	}  
		                }
						] ]
		});
		$('#lookInfo').accordion("select", "附件信息");
	}
</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">
	<div style="width: 980px;height: 280px;overflow: auto;">
	<form id="acceptTaskForm" method="post">
		 <input name="id" id="id"  type="hidden"/>
		 <input name="auditId" type="hidden" value="noauditId"/>
		 <table class="table" cellpadding="5px;">
			 <tr>
			    <th>客户姓名:</th>
				<td><input name="name" readonly="readonly" type="text"/></td>
			</tr>
			<tr>
				<th>身份证号:</th>
				<td><input name="idNo" readonly="readonly" type="text"/></td>
			</tr>
			<tr>
			 	<th>备注:</th>
				<td colspan="3">
					<textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:300px;height:70px;"></textarea>
				</td>
			</tr>
		 </table>
		<div id="attachmentList" style="width:100%;display:block;float:left;">
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
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="applicationReport()">填写申请报告并通过</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="finalAuditRefuse('FinalAuditRefuse');">终审拒贷</a>
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

<!-- 终审资质分析 -->	
<div id="applicationReportDialog" class="easyui-dialog" title="申请报告" data-options="border:false,closed:true,modal:true"  style="width: 1000px;height: 650px;overflow: auto;">
	<div class="easyui-tabs" style="fit:true;">
		<div title="资质分析">
			<form id="finalAuditReport-form" method="post">
				<input name="finaId" hidden="true" class="easyui-validatebox">
				<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
					<tr>
						<td colspan="6"><span style="font-weight: bold;font-size: 14px;width:60px;">[终审资质分析详情]</span></td>
					</tr>
					<tr>
						<th>合同金额:</th>
						<td><input id="contractLoanAmount" name="contractLoanAmount"  class="easyui-validatebox easyui-numberbox" data-options="min:0,precision:2,required:true"  onblur="calculate();"/>元</td>
						<th>贷款期限:</th>
						<td><input id="loanPeriodType" name="loanPeriodType"  class="easyui-validatebox easyui-textbox easyui-combobox"  onchange="calculate();" />月</td>
						<th>月服务汇率:</th>
						<td><input id="monthServiceFeeRate" name="monthServiceFeeRate" class="easyui-validatebox easyui-combobox"  onchange="calculate();"/></td>
					</tr>
					<tr>
						<th>利息:</th>
						<!-- <td><input id="loanInterestRate" name="loanInterestRate"  class="easyui-validatebox easyui-combobox"  onchange="calculate();"/></td> -->
						<td><input id="loanInterestRate" name="loanInterestRate"  class="easyui-validatebox easyui-numberbox" data-options="min:0,precision:2,required:true" onchange="calculate();"/></td>
						<th>信访费用:</th>
						<td ><input id="visitFee" name="visitFee"  class="easyui-validatebox easyui-numberbox"  data-options="required:true,min:0,precision:2,required:true" onblur="calculate();"/>元</td>
					</tr>
					<tr>
						<th>实放金额:</th>
						<td><input id="actualLoanAmount" name="actualLoanAmount"  class="easyui-validatebox easyui-textbox easyui-numberbox" data-options="disabled:true,min:0,precision:2" />元</td>
						<th>月还款额:</th>
						<td><input id="monthRepay" name="monthRepay"  class="easyui-validatebox easyui-textbox easyui-numberbox" data-options="disabled:true,min:0,precision:2" /></td>
					</tr>
					<tr>
						<th>终审人:</th>
						<td>
							<input class="easyui-validatebox easyui-textbox"  value="<shiro:principal property='name'/>" disabled="disabled"/>
							<input id="finaPersonnel" name="finaPersonnel" hidden="true"  class="easyui-validatebox easyui-textbox" value="<shiro:principal property="userId"/>"/>
						</td>
						<th>终审日期</th>
						<td><input id="finaDate" name="finaDate"  class="easyui-validatebox easyui-datetimebox" /></td>
					</tr>
					<tr>
						<th>终审人员意见:</th>
						<td colspan="6"><textarea id="finaPersSugg" name="finaPersSugg" style="width:600px;height:70px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
					</tr>
					<tr>
						<th>终审资质分析说明:</th>
						<td colspan="6"><textarea id="description" name="description" style="width:600px;height:70px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
					</tr>
					<tr>
						<td colspan="6" style="text-align: right;">
							<a href="javascript:void(0);" onclick="saveFinalAuditReport('FinalAuditThrough',this);"	class="easyui-linkbutton" iconCls="icon-save">保存</a>
						</td>
					</tr>
				</table>
			</form>
		</div>	
	</div>	
</div>	
<!-- 终审资质分析 -->				

			
