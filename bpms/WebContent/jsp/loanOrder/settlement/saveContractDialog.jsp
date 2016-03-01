<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.easyui-textbox {
	height: 18px;
	width: 170px;
	line-height: 16px;
	/*border-radius: 3px 3px 3px 3px;*/
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
}
textarea:focus, input[type="text"]:focus {
	border-color: rgba(82, 168, 236, 0.8);
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px
		rgba(82, 168, 236, 0.6);
	outline: 0 none;
}
input, textarea {
	font-weight: normal;
}
.easyui-aa{
}
.table {
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
	max-width: 100%;
}

.table {
	text-align: left;
	padding: 6px 10px 6px 10px;
}

.table th {
	text-align: right;
	padding: 6px 10px 6px 10px;
}
.table td {
    text-align: left;
	padding: 6px 10px 6px 10px;
}
</style>
<script type="text/javascript">
$(function(){
	$("#lcId").val($lcId);
	$(".txtDate").datebox({
        required: "true",
        editable:false,
    });
});

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
					$saveContractDialog.dialog("close");
					submitTask(result);
				}else{
					$.messager.alert("提示",data.message,"info");
				}
			}
		});
	}});
}
</script>
<div id="saveContract">
	 <form id="contactForm" method="post">
		<input id="lcId" name="lcId" type="hidden" /><!-- 合同id -->
		<input id="contractNo" name="contractNo" type="hidden" /><!-- 合同编号 -->
		<table class="table">
			<tr>
				<th>合同签署地:</th>
				<td><input name="contractSignSite" class="easyui-validatebox easyui-textbox" type="text" data-options="required:true,validType:'length[0,128]'"/></td>
			    <th>合同签署日期:</th><!-- class="easyui-validatebox easyui-datebox" data-options="required:true" -->
				<td><input class="txtDate" name="contractSignDate" type="text" /></td>
				<th>共同贷款人签字日期:</th>
				<td><input name="ljSignDate"
					type="text" class="easyui-validatebox easyui-datebox" data-options="editable:false"/></td>
			</tr>
			<tr>
				<th>委托代理人姓名:</th>
				<td><input name="agent" type="text"
					class="easyui-validatebox easyui-textbox" data-options="validType:'length[0,20]'"/></td>
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
			<tr>
				<th>备注信息:</th>
				<td colspan="5"><textarea name="remark" type="text" style="width:100%;height:75px;"
					class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,100]'"/></textarea></td>
			</tr>
			<tr>
			   <td colspan="6" style="text-align: right;">
			      <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="saveContAndSinatoryThroughFun('QianYueRenThrough');">保存</a>
			   </td>
			</tr>
		</table>
	</form>
</div>		