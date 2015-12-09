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
	 $$row=$row;
	 $("#acceptTaskForm").form('load',$row);
})
/**
 * 营业部经理驳回/通过
 */
function submitTask(result,object){
	if(result=="zhongxinzongjianbohui"){
	// 验证备注信息是否已经填写
		if($("#comment").val()==""){
			$.messager.alert("提示","请填写备注信息后再进行提交!","warning")
			return false;
		}
	}
	// 确认是否提交
	//0驳回 1是通过
	$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
		if (r){
			var data = {
				"remark":$("#comment").val(),
				"result":result,
				"paId":$row.oveId,
				"appNo":$row.appNo,
				"taskId":$row.taskId,
				"handleResult":result=='zhongxinzongjiantongguo'?1:result=="zhongxinzongjianbohui"?0:2
			}
			$.ajax({
				type:"POST",
				url:"overtime/handleTask.do",
				data:data,
				dataType:"JSON",
				success : function(msg) {
					if(msg.status){
						$("#waitTaskGrid").datagrid('reload');
						$("#addWindow").dialog('close');
					}
				}
			});
		}
	});
}
$(function(){
	 $("#investProductInputOrSaveForm").form('load',$row);	
		//上传附件
		$("#upploadAttachment").click(function(){
			fileUploadsDlg($row.appNo);
		});
		//查看附件
		$("#checkAttachment").click(function(){
			checkAttachementDetail($row.appNo,'');
		});
	});
</script>
     
<div data-options="region:'north',title:'North Title',split:true">
	<div style="overflow: auto;margin-left: 3px;">
	<form id="acceptTaskForm" method="post">
		 <input name="oveId" id="caID"  type="hidden"/>
<!-- 		 <input name="auditId" type="hidden" value="noauditId"/>  --> 
		 <table class="table" cellpadding="5px;">
			<tr>
					<th>部门:</th>
					<td>
						<input name="deptName" id="deptName" class="easyui-textbox" data-options="validType:'length[0,100]', required:true" disabled="disabled"/>
					</td>
					<th>姓名:</th>
					<td id="name">
						<input name="applicantNo" id="applicantNo" class="easyui-textbox" data-options="validType:'length[0,100]', required:true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>职务:</th>
					<td>
						<input name="position" id="position" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
					<th>申请日期:</th>
					<td>
						<input name="appDate" id="appDate" class="easyui-textbox easyui-datebox"  data-options="validType:'length[0,100]',required:true" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>计划开始时间:</th>
					<td>
						<input name="planBgDtime" id="planBgDtime" class="easyui-textbox easyui-datetimebox" readonly="true"  data-options="validType:'length[0,100]',required:true" disabled="disabled"/>
					</td>
					<th>计划结束日期时间:</th>
					<td>
						<input name="planEdDtime" id="planEdDtime" class="easyui-textbox easyui-datetimebox" readonly="true"  data-options="validType:'length[0,100]',required:true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>实际开始日期时间:</th>
					<td>
						<input id="realBgDtime" name="realBgDtime" class="easyui-textbox easyui-datetimebox" data-options="validType:'length[0,100]', required:true" disabled="disabled"/>
					</td>
					<th>实际结束日期时间:</th>
					<td>
						<input id="realEdDtime" name="realEdDtime" type="text" class="easyui-textbox easyui-datetimebox" data-options="validType:'length[0,100]',required:true" maxlength="5" disabled="disabled"/>
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
					<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton" >上传附件</a>	
					<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('zhongxinzongjiantongguo',this);">申请通过</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('zhongxinzongjianbohui',this);">申请驳回</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('zongxinzongjianjujue',this);">申请拒绝</a>
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
		<!-- <div id="upload_form" style="height: 30px; margin-left: 700px;">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="fileUploads(this)">上传附件</a>
		</div>  -->
	</form>
	</div>
	
	<!-- <div style="margin-left: 25px;margin-bottom: 5px;">
		<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton" >上传附件</a>	
		<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('jiekuanzongjiantongguo',this);">借款总监通过</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('jiekuanzongjianbohui',this);">借款总监驳回</a>
	</div> -->
</div>   
