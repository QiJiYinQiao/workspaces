<%@page import="com.bpms.util.Constants"%>
<%@page import="com.bpms.shiro.ShiroUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
 ShiroUser shiroUser = Constants.getCurrendUser();
%>
<script type="text/javascript">
		$(function(){
			$("#finaDate").datetimebox({   
			    required: true,   
				editable:false,
			});  
			
			// 渲染贷款期限
		   $("input[name='loanPeriodType']").combobox({
				url : "common/commonAction!findTextArr.action?codeMyid=loan_period_type",
				valueField : 'code',
				textField : 'text',
				disabled : true,
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
				disabled : true,
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
			
			// 信贷方式
		   $("input[name='auditWay']").combobox({
				width:171,
				url:"common/commonAction!findTextArr.action?codeMyid=audit_way",
				valueField: 'code',
				textField: 'text',
				editable: false,
				required:true,
				disabled : true,
				onLoadSuccess : function(){
		            var val = $(this).combobox("getData");
		            for (var item in val[0]) {
		                if (item == "code") {
		                    $(this).combobox("select", val[0][item]);
		                }
		            }
					},
			});
			
		 	//查询初审建议金额
			$.ajax({
				url:"creditAuditReport/creditAuditReportAction!findCreditAuditReportByLoanOrderId.action",
				type:"post",
				data:{"loanOrderId":$row.loanOrderId},
				async:false,
				success:function(data){
					if(data.firstAuditReport) {
						var organizationId = $row.organizationId;
						//初审建议金额为万元
						var suggestAmt = parseInt(data.firstAuditReport.suggestAmt)*10000;
						$("#firstAuditSugAmt").val(suggestAmt);
		
						if($row.organizationId==79 && suggestAmt <= 150000){
							$("#auditWay").combobox("select","1").combobox("disable");
						}else if(suggestAmt <= 100000){
							$("#auditWay").combobox("select","1").combobox("disable");
						}
					}
				}
			});
			
			// 获取终审报告的信息
			$.ajax({
				url:"creditAuditReport/creditAuditReportAction!findCreditAuditReportByLoanOrderId.action",
				type:"post",
				data:{"loanOrderId":$row.loanOrderId},
				async:false,
				success:function(data){
					if(undefined!=data.finalAuditReport) {
						$("#finalAuditReport-form").form("load",data.finalAuditReport);
					}
				}
			});
			
		});
		
		// 保存资质分析的信息
		function saveFinalAuditReport(result,object){
			result = $result;
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
								// 确认是否提交
								submitTask(result);
							}
					});
				}
			});
		}
</script>
<!-- 终审资质分析 -->	
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
						<td><input name="contractLoanAmount" class="easyui-validatebox" value="0" readonly="readonly"/>元</td>
						<th>贷款期限:</th>
						<td><input id="loanPeriodType" name="loanPeriodType"  class="easyui-validatebox easyui-textbox easyui-combobox" />月</td>
						<th>月服务汇率:</th>
						<td><input id="monthServiceFeeRate" name="monthServiceFeeRate" class="easyui-validatebox easyui-combobox" /></td>
					</tr>
					<tr>
						<th>利息:</th>
						<td><input id="loanInterestRate" name="loanInterestRate"  class="easyui-validatebox easyui-numberbox" data-options="min:0,precision:2,required:true"/></td>
						<th>信访费用:</th>
						<td ><input name="visitFee"  class="easyui-validatebox" value="0" readonly="readonly" />元</td>
						<th>信贷方式:</th>
						<td>
							<input id="auditWay" name="auditWay" class="easyui-combobox"/>
						</td>
					</tr>
					<tr>
						<th>实放金额:</th>
						<td><input name="actualLoanAmount"  class="easyui-textbox" value="0" readonly="readonly" />元</td>
						<th>月还款额:</th>
						<td><input name="monthRepay"  class="easyui-textbox" value="0" readonly="readonly" /></td>
						<th>初审建议金额</th>
						<td><input id="firstAuditSugAmt" class="easyui-textbox" disabled="disabled"></td>
					</tr>
					<tr>
						<th>审核专员:</th>
						<td>
							<input class="easyui-validatebox easyui-textbox" value="<%=shiroUser.getUser().getName() %>" disabled="disabled"/>
							<input id="finaPersonnel" name="finaPersonnel" hidden="true" value="<%=shiroUser.getUserId() %>" class="easyui-validatebox easyui-textbox"/>
						</td>
						<th>审核日期</th>
						<td><input id="finaDate" name="finaDate"/></td>
					</tr>
					<tr>
						<th>审贷部门意见:</th>
						<td colspan="6"><textarea id="finaPersSugg" name="finaPersSugg" style="width:95%;height:70px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
					</tr>
					<tr>
						<td colspan="6" style="text-align: right;">
							<a href="javascript:void(0);" onclick="saveFinalAuditReport('',this);"	class="easyui-linkbutton" iconCls="icon-save">保存</a>
						</td>
					</tr>
				</table>
			</form>
		</div>	
	</div>	
