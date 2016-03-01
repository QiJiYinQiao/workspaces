<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
	
</style>
<script type="text/javascript">
	$(function(){
		$("#contractDetailForm input").attr("disabled","disabled");
		$("#contractStatus").combobox({
			valueField : 'code',
			textField : 'text',
			editable:false ,
			url:"common/commonAction!findTextArr.action?codeMyid=contract_status",
		});
		
		$("#obliMatchStatus").combobox({
			valueField : 'code',
			textField : 'text',
			editable:false ,
			url:"common/commonAction!findTextArr.action?codeMyid=obli_match_status",
		});
		
		$.ajax({
    		url:"loanContract/loanContractAction!findLoanContract.action",
    		type:"post",
    		data:{"lcId":$lcid},
    		success:function(data){
    			$("#contractDetailForm").form("load",data);
    		}
    	});
	});
</script>
<!-- 合同详情 -->
		<div>
			<form id="contractDetailForm">
				<table cellpadding="8px;" style="width:100%;height:100%;">
					<tr>
						<th>贷款合同编号</th>
						<td><input name="contractNo" class="easyui-textbox" /></td>
						<th>合同签署地</th>
						<td><input name="contractSignSite" class="easyui-textbox" /></td>
						<th>合同签署日期</th>
						<td><input name="contractSignDate" class="easyui-textbox" /></td>
					</tr>
					
					<tr>
						<th>贷款人姓名</th>
						<td><input name="loaner" class="easyui-textbox" /></td>
						<th>贷款人身份证号</th>
						<td><input name="loanerIdno" class="easyui-textbox" /></td>
						<th>贷款人通讯地址</th>
						<td><input name="loanerPostalAddr" class="easyui-textbox" /></td>
					</tr>
					
					<tr>
						<th>贷款人联系电话</th>
						<td><input name="loanerTel" class="easyui-textbox" /></td>
						<th>贷款人签字日期</th>
						<td><input name="loanerSignDate" class="easyui-textbox" /></td>
						<th>贷款期数</th>
						<td><input name="loanPeriods" class="easyui-textbox" /></td>
					</tr>
					
					<tr>
						<th>债权匹配状态</th>
						<td><input id="obliMatchStatus" name="obliMatchStatus" disabled="disabled" /></td>
						<th>出借人</th>
						<td><input name="lender" class="easyui-textbox" /></td>
						<th>出借人身份证号</th>
						<td><input name="lenderIdno" class="easyui-textbox" /></td>
					</tr>
					
					<tr>
						<th>出借人签字日期</th>
						<td><input name="lenderSignDate" class="easyui-textbox" /></td>
						<th>贷款用途</th>
						<td><input name="purpose" class="easyui-textbox" /></td>
						<th>贷款额度</th>
						<td><input name="loanEdu" class="easyui-textbox" /></td>
					</tr>
					
					<tr>
						<th>月利率</th>
						<td><input name="monthlyRate" class="easyui-textbox" /></td>
						<th>贷款利息</th>
						<td><input name="loanInterest" class="easyui-textbox" /></td>
						<th>月还款额度</th>
						<td><input name="monthlyRepayment" class="easyui-textbox" /></td>
					</tr>
					
					<tr>
						<th>月还款日</th>
						<td><input name="monthlyRepaymentDate" class="easyui-textbox" /></td>
						<th>贷款开始日期</th>
						<td><input name="loanBgDate" class="easyui-textbox" /></td>
						<th>贷款结束日期</th>
						<td><input name="loanEdDate" class="easyui-textbox" /></td>
					</tr>
					
					<tr>
						<th>还款开始日期</th>
						<td><input name="repaymentBgDate" class="easyui-textbox" /></td>
						<th>还款结束日期</th>
						<td><input name="repaymentEdDate" class="easyui-textbox" /></td>
						<th>贷款人开户银行全称</th>
						<td><input name="loanerBankName" class="easyui-textbox" /></td>
					</tr>
					
					<tr>
						<th>贷款人开户行户名</th>
						<td><input name="loanerActName" class="easyui-textbox" /></td>
						<th>贷款人开户行账号</th>
						<td><input name="loanerActNum" class="easyui-textbox" /></td>
						<th>合同状态</th>
						<td><input id="contractStatus" name="contractStatus" disabled="disabled" /></td>
					</tr>
					
					<tr>
						<th>委托代理人姓名</th>
						<td><input name="agent" class="easyui-textbox" /></td>
						<th>委托代理人身份证号</th>
						<td><input name="agentIdno" class="easyui-textbox" /></td>
						<th>委托代理人签字日期</th>
						<td><input name="agentSignDate" class="easyui-textbox" /></td>
					</tr>
					
					<tr>
						<th>共同贷款人身份证号</th>
						<td><input name="ljIdno" class="easyui-textbox" /></td>
						<th>共同贷款人通讯地址</th>
						<td><input name="ljPostalAddr" class="easyui-textbox" /></td>
						<th>共同贷款人联系电话</th>
						<td><input name="ljTel" class="easyui-textbox" /></td>
					</tr>
					
					<tr>
						<th>共同贷款人姓名</th>
						<td><input name="loanerJoint" class="easyui-textbox" /></td>
						<th>共同贷款人签字日期</th>
						<td><input name="ljSignDate" class="easyui-textbox" /></td>
					</tr>
					
					<tr>
						<th>备注信息</th>
						<td colspan="5"><textarea name="remark" class="easyui-textbox" style="width:99%;height:70px;resize:none;" disabled="disabled"></textarea></td>
					</tr>
				</table>
			</form>
		</div>