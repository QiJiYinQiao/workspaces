<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$("#lcId").val($lcId);
		$("#contractNo").val($contractNo);
	})
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
						$loanContract.dialog("close");
						submitTask(result);
					}else{
						$.messager.alert("提示",data.message,"info");
					}
				}
			});
		}});
	}
</script>
<div id="saveContract" data-options="region:'north',title:'North Title',split:true">
	 <form id="contactForm" method="post">
		<input id="lcId" name="lcId" type="hidden" /><!-- 合同id -->
		<input id="contractNo" name="contractNo" type="hidden" /><!-- 合同编号 -->
		<table class="table">
			<tr>
				<th>合同签署地:</th>
				<td><input name="contractSignSite" class="easyui-validatebox easyui-textbox" type="text" data-options="required:true"/></td>
			    <th>合同签署日期:</th>
				<td><input  name="contractSignDate" type="text" class="easyui-validatebox easyui-datebox" data-options="editable:false"/></td>
				<th>共同贷款人签字日期:</th>
				<td><input name="ljSignDate"
					type="text" class="easyui-validatebox easyui-datebox" data-options="editable:false"/></td>
			</tr>
			<tr>
				<th>委托代理人姓名:</th>
				<td>
					<input name="agent" type="text" class="easyui-validatebox easyui-textbox"/>
				</td>
				<th>委托代理人身份证号:</th>
				<td>
					<input name="agentIdno" type="text" class="easyui-validatebox easyui-textbox" data-options="validType:'idcard'"/>
				</td>
				<th>委托代理人签字日期:</th>
				<td>
					<input name="agentSignDate" type="text"	class="easyui-validatebox easyui-datebox " data-options="editable:false"/>
				</td>
			</tr>
			<tr>
			    <th>还款开始日期:</th>
			    <td>
			       <input  name="repaymentBgDate" type="text" class="easyui-validatebox easyui-datebox" data-options="editable:false"/>
			    </td>
			    <th>还款结束日期:</th>
				<td>
				   <input name="repaymentEdDate" type="text" class="easyui-validatebox easyui-datebox" data-options="editable:false"/>
				</td>
				<th>贷款结束日期:</th>
			    <td>
			       <input name="loanEdDate" type="text" class="easyui-validatebox easyui-datebox" data-options="editable:false"/>
			    </td>
			</tr>
			<tr>
				<th>备注信息:</th>
				<td colspan="5"><textarea name="remark" type="text" style="width:100%;height:75px;"
					class="easyui-textbox easyui-validatebox" data-options="required:true"/></textarea></td>
			</tr>
			<tr>
			   <td colspan="6" style="text-align: right;">
			      <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="saveContAndSinatoryThroughFun('SignatoryThrough');">保存</a>
			   </td>
			</tr>
		</table>
	</form>
</div>