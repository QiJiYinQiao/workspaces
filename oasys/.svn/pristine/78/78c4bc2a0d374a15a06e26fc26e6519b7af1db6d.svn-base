<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">
	
	//上传附件,明细存储taskID
	$("#upploadAttachment").click(function(){
		fileUploadsDlg($$row.appNo);
	});
	//查看附件
	$("#checkAttachment").click(function(){
		checkAttachementDetail($row.appNo,$$row.assistant,'');
	});
	
	//业务办理
	function microcreditOpinion(result,isSuccess){
		
		if(isSuccess!=1){
			if($("#taskComment").val()==""){
				
				$.messager.alert("提示","请填写审批意见后再进行提交!","warning");
				return false;
			}
		}
		//处理结果
		$("#isSuccess").val(isSuccess);
		if(isSuccess==1){
			if($("#planLeDays").val()>2){
				$("#result").val("1");
			}else if($("#planLeDays").val()<=2 ){
				$("#result").val("ZhiJieTongGuo");
			}
			
		}else{
			//申请状态
			$("#result").val(result);
			
		}
		
		$.messager.confirm('确定','是否确定提交办理任务？',	function(flag) {
			if (flag) {
				$.ajax({
					url :"LeaveTask/saveTaskLeaveCliApp.do",
					data : $("#taskForm").serialize(),
					async: false,
					type : "post",
					success : function(iJson) {
						if(iJson.status){
			 	    		$banliDialog.dialog("close");//关闭弹窗
				    		$("#dg").datagrid("reload");//刷新表格
				    	}
			 	    	$.messager.alert(iJson.title,iJson.message,'info');
					},
					error:function(){
						
					}
				});
			}
		});
	}
	
		
</script>
<!-- 受理任务 S -->
<div style="margin-left: 5px;margin-top: 5px;">
		<form id="taskForm" method="post">
			<input id="leaId" name="leaId" type="hidden"/><!-- 申请id -->
			<input id="planLeDays" name="planLeDays" type="hidden"/><!-- 计划请假天数 -->
			 <input name="taskID" id="taskID"  type="hidden"/><!-- 任务id -->
			 <input name="businessID" id="businessID"  type="hidden"/><!-- 业务id-->
			 <input id="result" name="result" type="hidden" /><!-- 申请状态编码 -->
			  <input name="isSuccess" id="isSuccess"  type="hidden"/><!-- 处理结果-->
			  <input name="definitionKey" id="definitionKey"  type="hidden"/><!-- 任务key值-->
			  <input id="appNo" name="appNo" type="hidden"/>
			 <table class="table"  width="95%">
				 <tr>
				    <th >申请编号:</th>
					<td ><input id="appNoMain" name="appNo" readonly="readonly" class="easyui-textbox " disabled="disabled" data-options="editable:false" type="text"/></td>
				</tr>
				
				<tr>
				 	<th >审批意见:</th>
					<td colspan="3" >
						<textarea id="taskComment" name="taskComment" class="easyui-validatebox easyui-textbox" data-options="validType:'length[0,200]'" style="width:730px;height:70px;"></textarea>
					</td>
				</tr>
				<!-- 操作 -->
				<tr>
				   <td colspan="3" align="right">
						<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton" >上传附件</a>	
						<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>						   
				    	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="microcreditOpinion('',1);">申请通过</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="microcreditOpinion('BoHui',0);">申请驳回</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="microcreditOpinion('JuJue',2);">申请拒绝</a>
				   </td>
				</tr>
				<!-- 审批意见 -->
				<tr>
					<td colspan="6">
						<jsp:include page="/jsp/ad/optionsList.jsp" />
					</td>
				</tr>
			 </table>
		</form>
	</div>

