<%@page import="com.bpms.util.Constants"%>
<%@page import="com.bpms.shiro.ShiroUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
 ShiroUser shiroUser = Constants.getCurrendUser();
%>
<style type="text/css">
</style>
<script type="text/javascript">
	$(function(){
		$.ajax({
			type : "POST",
			url : "loanContract/loanContractAction!findBankAccountInfo.action",
			data : {"loanOrderId":$row.loanOrderId},
			success : function(data) {
				if (data) {
					var htmlC = "";
					$.each(data,function(i,item) {
						if (item.primaryBackup=="A") {
							htmlC += "<tr><th>主卡</th></tr>";
						} else {
							htmlC += "<tr><th>副卡</th></tr>";
						}
						htmlC += "<tr><th>贷款人开户银行全称</th>";
						htmlC += "<td><input name='accountIds' value='"+item.accountId+"' type='hidden'/><input name='bankNames' class='easyui-textbox' value='"+item.bankName+"'/></td>";
						htmlC += "<th>贷款人开户行户名</th>";
						htmlC += "<td><input name='accountNames' class='easyui-textbox' value='"+item.accountName+"'/></td>";
						htmlC += "<th>贷款人开户行账号</th>";
						htmlC += "<td><input name='accountNums' class='easyui-textbox' value='"+item.accountNum+"'/></td>";
						htmlC += "</tr>";
					});
					
					htmlC += "<tr><td colspan='6' style='text-align: right;'>";
					htmlC += "<a href='javascript:void(0);' onclick='saveBankAccountInfo();'	class='easyui-linkbutton' iconCls='icon-save'>保存</a>	</td></tr>";
				
					$("#bankAccountInfoForm table").append(htmlC);
					$.parser.parse($("#bankAccountInfoForm"));
				}
			}
		});
	});
	
	function saveBankAccountInfo() {
		var type = $("#type").val();
		var id= $("#id").val();
		$("#bankAccountInfoForm").form('submit', {
			url : "accountInfo/accountInfoAction!updateAccountInfo.action",
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				if (isValid) {
					param.loanOrderId = $row.loanOrderId;
				}
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
				data = $.parseJSON(data);
				$.messager.show({
					title:"提示",
					msg:data.message,
					showType:"slide",
					timeout:1000
				});
			}
		});	
	}
</script>
<div>
	<form id="bankAccountInfoForm" method="post">
		<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
			
		</table>
	</form>
</div>
