<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript">
	$(function(){
		$("#microcreditOpinionForm").form("load","microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionByOid.action?loanOrderId="+$row.loanOrderId);
		$("#microcreditOpinionForm input[name='name']").val($row.name);
		$("#microcreditOpinionForm input[name='idNo']").val($row.idNo);
		$("#microcreditOpinionForm input[name='purpose']").val($row.purpose);
		
		$("#microcreditOpinionForm input").attr("readonly","readonly").css("background-color","#EBEBE4");
		$("#microcreditOpinionForm textarea").attr("readonly","readonly").css("background-color","#F5F5F5");
	});
</script>
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
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="name"  type="text" />
						<input name="loanOrderId" type="hidden" />
					</td>
					<th>
						身份证号
					</th>
					<td colspan="3">
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="idNo"  type="text" />
					</td>
					<th>
						咨询服务费
					</th>
					<td>
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="counselingRate"  type="text" />%
					</td>
				</tr>
				
				<tr>
					<th>
						共同借款人
					</th>
					<td>
						<input class="easyui-validatebox easyui-textbox" name="coborrowerName"  type="text" />
					</td>
					<th>
						身份证号
					</th>
					<td colspan="3">
						<input class="easyui-validatebox easyui-textbox" name="coborrowerIdno"  type="text" />
					</td>
					<th>
						收取方式
					</th>
					<td colspan="3">
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="collectionMthd"  type="text" />
					</td>
				</tr>
				
				<tr>
					<th>
						建议金额(元)
					</th>
					<td>
						<input class="easyui-validatebox easyui-textbox" name="adviceLoanAmt"  type="text"/>
					</td>
					<th>
						期限(月)
					</th>
					<td>
						<input class="easyui-validatebox easyui-textbox" name="adviceLoanPeriod"  type="text" />
					</td>
					<th>
						放款方式
					</th>
					<td>
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="loanMthd"  type="text" />
					</td>
				</tr>
				
				<tr>
					<th>
						贷款用途
					</th>
					<td>
						<input class="easyui-validatebox easyui-textbox" name="purpose"  type="text" value=""/>
					</td>
					<th>
						利率(年)
					</th>
					<td colspan="3">
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="loanRate"  type="text" />%
					</td>
					<th>
						还款方式
					</th>
					<td>
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="adviceRepayMthd"  type="text" />
					</td>
				</tr>
				
				<tr>
					<th>
						经办机构/部门
					</th>
					<td>
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name=""  type="text" value="IPC项目组-保定"/>
					</td>
					<th>
						经办人
					</th>
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
						<textarea class="easyui-validatebox easyui-textbox" name="specificMeasures" style="width:99%;height:220px;resize: none;">
						
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
							<input class="easyui-validatebox easyui-textbox" name="operatorA" type="text" />
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
							<input class="easyui-validatebox easyui-textbox" name="deptPrincipal" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<input class="easyui-validatebox easyui-textbox" name="operatorB" type="text" />
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