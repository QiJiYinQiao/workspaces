<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
.textStyle{
  text-align: right;
}
</style>
<script type="text/javascript">
var $$row;
$(function(){
// 	 $$row=$row;
	 $("#acceptTaskForm").form('load',$$row);
})
/**
 * 营业部经理驳回/通过
 */
function confirmOvertime(){
	// 确认是否提交
	//0驳回 1是通过
	$.messager.confirm('提示', '您确认要提交到人事专员进行加班审批?', function(r){
		if (r){
			var data = {
				"remark":$("#comment").val(),
				"result":"Perposer1",
				"oveId":$$row.oveId,
				"appNo":$$row.appNo,
				"taskId":$$row.taskId,
				"handleResult":1,
				"status":"JiaBanQueRen"
			}
			$.ajax({
				type:"POST",
				url:"overtime/handleTaskConfirmApp.do",
				data:data,
				dataType:"JSON",
				success : function(msg) {
					if(msg.status){
						$.messager.alert(msg.title,msg.message,'info');  
			    		$("#handleTask").dialog("close");//关闭弹窗
			    		$("#waitTaskGrid").datagrid("reload");//刷新表格
					}
				}
			});
		}
	});
}
</script>
     
<div data-options="region:'north',title:'North Title',split:true">
	<div style="overflow: auto;margin-left: 3px;">
	<form id="acceptTaskForm" method="post">
		 <input name="oveId" id="caID"  type="hidden"/>
			<table>
				<tr>
					<th>计划开始时间:</th>
					<td>
						<input name="planBgDtime" id="planBgDtime" class="easyui-textbox easyui-datetimebox" readonly="true"  data-options="validType:'length[0,100]',required:true"/>
					</td>
					<th>计划结束日期时间:</th>
					<td>
						<input name="planEdDtime" id="planEdDtime" class="easyui-textbox easyui-datetimebox" readonly="true"  data-options="validType:'length[0,100]',required:true"/>
					</td>
				</tr>
				<tr>
					<th>实际开始日期时间:</th>
					<td>
						<input id="realBgDtime" name="realBgDtime" class="easyui-textbox easyui-datetimebox" data-options="validType:'length[0,100]', required:true"/>
					</td>
					<th>实际结束日期时间:</th>
					<td>
						<input id="realEdDtime" name="realEdDtime" type="text" class="easyui-textbox easyui-datetimebox" data-options="validType:'length[0,100]',required:true" maxlength="5"/>
					</td>
				</tr>
				<tr>
					<th>备注:</th>
					<td colspan="3">
						<textarea id="comment" name="comment" class="easyui-textbox" data-options="validType:'length[0,400]', required:true" style="width:560px;"></textarea>
					</td>
				</tr>
			<tr>
				<td colspan="8">
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="confirmOvertime()" name="saveButton" class="easyui-linkbutton" style="float:right;">确认</a>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<jsp:include page="/jsp/ad/optionsList.jsp" />
				</td>
			</tr>
		 </table>
		<div id="attachmentList" style="display:block;float:left;">
		</div>
	</form>
	</div>
</div>   
