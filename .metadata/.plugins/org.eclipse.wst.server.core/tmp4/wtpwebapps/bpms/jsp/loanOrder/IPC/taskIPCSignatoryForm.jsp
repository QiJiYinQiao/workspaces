<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 签约人 -->
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
		height : 350,
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
		              {field : 'title',title : '审批简述',width :parseInt($(this).width()*0.1),align : 'left'},
		              {field : 'comment',title : '审批详情',width :parseInt($(this).width()*0.1),align : 'left'},
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
	
	//查看附件
	$("#checkAttachment").click(function(){
		checkAttachementDetail('noauditId',$row.loanOrderId,'');
	});
	
	//上传附件
	$("#upploadAttachment").click(function(){
		var attType = $("#attType").combobox("getValue");
		fileUploadsDlg(attType,$row.loanOrderId);
	});
	
	//查询此订单是否存在合同信息，如果有，保存合同ID
	$.ajax({
		url:'loanContract/loanContractAction!checkIsContractExist.action',
		data:{"loanOrderId":$row.loanOrderId},
		type:"post",
		success:function(data){
			if(data){
				$("#lcId").val(data.lcId);
				$("#contractNo").val(data.contractNo);
			}
		}
	});
});

	// 提交表单信息
	function  submitTask(result) {
			var data = {
				"comment" : $("#comment").val(),
				"title":$("#title").val(),
				"result" :result,
				"loanOrderId" : $row.loanOrderId,
				"taskId": $row.taskId,
				"processingResult":result=="SignatoryThrough"?"A":"B"
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
	
	//生成合同，向合同表插入数据
	function createContract(){
		var lcId = $("#lcId").val();
		var contractNo = $("#contractNo").val();
		$.ajax({
			url:"loanContract/loanContractAction!saveLoanContract.action",
			type:"post",
			data:{"loanOrderId":$row.loanOrderId,"lcId":lcId,"contractNo":contractNo},
			success:function(data){
				data = $.parseJSON(data);
				if(data.status){
					$.messager.show({
						title:'提示',
						msg:data.message,
						timeout:5000,
						showType:'slide'
					});
					exportContract();
					$("#lcId").val(data.data.lcId);
					$("#contractNo").val(data.data.contractNo);
				}else{
					$.messager.alert("提示",data.message,"error");
				}
			}
		});
	}
	
	// 导出合同
	function exportContract(){
		downFileByFormPost("loanContract/loanContractAction!downloadContract.action", 
				{"loanOrderId":$row.loanOrderId}
		);
	}
	
	// 完善合同信息弹窗框
	function saveContract(){
		var lcId = $("#lcId").val();
		if(lcId==""){
			$.messager.alert("提示","请先生成合同信息!","info");
			return false;
		}
		if(!isNotEmptyComment()) return;
		$(".txtDate").datebox({
            required: "true",
            editable:false,
        });
		$("#saveContract").css("display","block").dialog({
			title:"稽核信息记录表",
			width: 1000,    
		    height: 650,    
		    closed: false,    
		    cache: false,    
		    modal: true ,
		});	
	}
	
	// 完善合同并签约
	function saveContAndSinatoryThroughFun(result){
		if(!$("#contactForm").form("validate")) return ;
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if(r){
				$("#contactForm").form('submit',{
					url:"loanContract/loanContractAction!completeContract.action",
					onSubmit : function(param) {
						var isValid = $(this).form('validate');
						return isValid; // 返回false终止表单提交
				},
				success:function(data){
					data = $.parseJSON(data);
					if(data.status){
						$.messager.show({
							title:'提示',
							msg:data.message,
							timeout:5000,
							showType:'slide'
						});
						$("#contactForm").form("clear");
						$("#saveContract").dialog("close");
						submitTask(result);
					}else{
						$.messager.alert("提示",data.message,"info");
					}
				}
			});
		}});
	}
	
	// 导出车辆买卖协议书
	function createCarInfo(){
		downFileByFormPost("carInfo/carInfoAction!downloadCarInfo.action",{"loanOrderId":$row.loanOrderId});
	}
	
	// 客户拒绝签
	function sinatoryRefuseFun(result){
		if(!isNotEmptyComment()) return;
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if(r){	
					submitTask(result);
				}
			});
			
	}
	
	// 验证备注信息不为空时
	function isNotEmptyComment(){
		if($("#comment").val()=="" || $("#title").val()==""){
			$.messager.alert("提示","请填写完备注信息后再进行提交!","warning")
			return false;
		}
		return true;
	}
</script>
	<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">
	<div style="width: 980px;height: 190px;overflow: auto;">
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
				 	<th>备注简述:</th>
					<td colspan="3">
						<textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:100%;height:15px;"></textarea>
					</td>
				</tr>
				<tr>
				 	<th>备注详情:</th>
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
	
	<div style="width:980px;height:30px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="createCarInfo();">导出车辆买卖协议书</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="createContract();">生成并导出合同</a>
		<!-- <a href="javascript:void(0);" class="easyui-linkbutton" onclick="downloadContract();">导出合同信息</a> -->
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="saveContract();">完善合同并签约</a>
		<!-- <a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('SignatoryThrough',this);">客户签约</a> -->
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="sinatoryRefuseFun('IPCSignatoryRefuse');">客户拒签</a>
	</div>
	
	<div id="lookInfo" class="easyui-accordion" style="height: 390px;width: 980px;overflow: hidden;">
	    <div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true" >   
			<table id="lookLoanOrderdg" title="申请备注的信息"></table>
		</div>
	</div>
</div>   
<!-- 受理任务 E -->		

<div id="saveContract" style="display:none;">
	 <form id="contactForm" method="post">
		<input id="lcId" name="lcId" type="hidden" /><!-- 合同id -->
		<input id="contractNo" name="contractNo" type="hidden" /><!-- 合同编号 -->
		<table class="table">
			<tr>
				<th>合同签署地:</th>
				<td><input name="contractSignSite" class="easyui-validatebox easyui-textbox" type="text" data-options="required:true"/></td>
			    <th>合同签署日期:</th><!-- class="easyui-validatebox easyui-datebox" data-options="required:true" -->
				<td><input class="txtDate" name="contractSignDate" type="text" /></td>
				<th>共同贷款人签字日期:</th>
				<td><input name="ljSignDate"
					type="text" class="easyui-validatebox easyui-datebox" data-options="editable:false"/></td>
			</tr>
			<tr>
				<th>委托代理人姓名:</th>
				<td><input name="agent" type="text"
					class="easyui-validatebox easyui-textbox"/></td>
				<th>委托代理人身份证号:</th>
				<td><input name="agentIdno"
					type="text" class="easyui-validatebox easyui-textbox" data-options="validType:'idcard'"/></td>
				<th>委托代理人签字日期:</th>
				<td><input name="agentSignDate" type="text"
					class="easyui-validatebox easyui-datebox " data-options="editable:false"/></td>
			</tr>
			<tr>
			    <th>还款开始日期:</th>
			    <td>
			       <input class="txtDate" name="repaymentBgDate" />
			    </td>
			    <th>还款结束日期:</th>
				<td>
				   <input class="txtDate" name="repaymentEdDate" type="text"
					/>
				</td>
				<th>贷款结束日期:</th>
			    <td>
			       <input class="txtDate" name="loanEdDate" />
			    </td>
			</tr>
			<!-- <tr>
				<th>月还款日:</th>
			    <td>
			       <input name="monthlyRepaymentDate" class="easyui-combobox" data-options="
					required:true,
					valueField: 'label',
					textField: 'value',
					data: [{
						label: '15',
						value: '15'
					},{
						label: '30',
						value: '30'
					}]" />
			    </td>
			</tr> -->
			<tr>
				<th>备注信息:</th>
				<td colspan="5"><textarea name="remark" type="text" style="width:100%;height:75px;"
					class="easyui-textbox easyui-validatebox" data-options="required:true"/></textarea></td>
			</tr>
			<tr>
			   <td colspan="6" style="text-align: right;">
			      <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="saveContAndSinatoryThroughFun('IPCSignatoryThrough');">保存</a>
			   </td>
			</tr>
		</table>
	</form>
</div>			

			
