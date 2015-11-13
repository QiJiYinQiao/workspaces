<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
	th {
		text-align: left;
	}
	textarea {
		width:100%;
	}
</style>
<script type="text/javascript">
	
	$(function(){
	   //查询贷款额度
	   $.ajax({
		   url : "microcreditOpinion/microcreditOpinionAction!findFinalLoanAmt.action",
		   type:"post",
		   data:{"loanOrderId":$row.loanOrderId},
		   success:function(data){
			  $("#oralAmt").val(data);
		   }
	   });
	   
	   //查询车抵额度
	   $.ajax({
			url:"carInfo/carInfoAction!findCarInfoByOrderId.action",
			type:"post",
			data:{"loanOrderId":$row.loanOrderId},
			success:function(data){
				if(data){
					$("#carAmt").val(data.vehicleMortgageAmt);
				}
			}
		});
	});
	
	function saveCarPledgeAmt(){
		var finalAmt = $("#finalAmt").val();
		$("#saveFinalAmtDateForm").form("submit",{
			url : "microcreditOpinion/microcreditOpinionAction!saveCarPledgeAmt.action",
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				if (isValid){
					param.loanOrderId = $row.loanOrderId;
				}
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
				carPledgeAmtDlg.dialog("close");
				data = $.parseJSON(data);
				$.messager.show({
					title:"提示",
					msg:data.message,
					showType:"slide",
					tiemout:1000
				});
			}
		});
	}
	
	function countFinalAMt(key){
		key.value= key.value.replace(/[^\d]/g,'');
		var carAmt = $("#carAmt").val();
		var finalAmt = parseInt(key.value==""?0:key.value)+parseInt(carAmt==""?0:carAmt);
		$("#finalAmt").val(finalAmt);
	}
	
</script>

	<div id="" >
		<form id="saveFinalAmtDateForm" method="post">
			<div style="width:92%;text-align: center;padding-left:20px;">
				<table cellpadding="8">
					<tr>
						<th>
							车抵额度
						</th>
						<td  colspan="2">
							<input id="carAmt" name="vehicleMortgageAmt" class="easyui-textbox" disabled="disabled"/>元
						</td>
					</tr>
				
					<tr>
						<th>
							原审批通过额度
						</th>
						<td  colspan="2">
							<input id="oralAmt" class="easyui-textbox" disabled="disabled"/>元
						</td>
					</tr>
					
					<tr>
						<th>
							最终通过额度
						</th>
						<td  colspan="2">
							<input id="finalAccrossAmt" name="finalAmt" onkeyup="countFinalAMt(this);" onblur="countFinalAMt(this);" class="easyui-textbox easyui-validatebox" data-options="validType:'mDouble',required:true"/>元
						</td>
					</tr>
					
					<tr>
						<th>
							总计贷款额度
						</th>
						<td colspan="2">
							<input id="finalAmt" class="easyui-textbox" readonly="readonly" />元
						</td>
					</tr>
				</table>
			</div>
		</form>
		<div id="upload_form" style="width: 90%; height: 30px; text-align: right;">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="saveCarPledgeAmt();">保存</a>
		</div> 
	</div>
