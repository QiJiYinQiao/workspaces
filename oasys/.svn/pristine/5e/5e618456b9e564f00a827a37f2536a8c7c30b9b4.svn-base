<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">
	
	var $selRow=$row;
	
	//上传附件,明细存储taskId
	$("#upploadAttachment").click(function(){
		fileUploadsDlg($$row.appNo);
	});
	//查看附件
	$("#checkAttachment").click(function(){
		checkAttachementDetail($row.appNo);
	});
	
	$(function(){
		//归还人
		RenderName($selRow.userDept);
	})
	
	//归还人
	function RenderName(organizeId){
		//选中部门后下拉框
		
		$("#reverter").combobox({
			width:171,
			url:"PpeUsedConfirm/findUserList.do?organizeId="+organizeId,
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 		
		 	},
		}); 
	}
	
	//更改归还人
	
	
	//业务办理
	function microcreditOpinion(isSuccess){
		
		if(isSuccess!=1){
			if($("#taskComment").val()==""){
				
				$.messager.alert("提示","请填写审批意见后再进行提交!","warning");
				return false;
			}
		}
		//处理结果
		$("#result").val("1");
		
		$("#isSuccess").val(isSuccess);
		
			
		
		$.messager.confirm('确定','是否确定提交办理任务？',	function(flag) {
			if (flag) {
				$.ajax({
					url :"PpeUsedTask/saveTaskMgrResult.do?attPsaId="+$selRow.puaPsaId+"&reverter="+$("#reverter").combobox("getValue"),
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
<div style="margin-left: 5px;margin-top: 5px;" style="width: 880px;margin-left:5px;">
		<form id="taskForm" method="post">
			<input id="psaId" name="psaId" type="hidden"/><!-- 申请id -->
			<input id="applicantNo" name="applicantNo" type="hidden"/><!-- 申请人id -->
			 <input name="taskID" id="taskID"  type="hidden"/><!-- 任务id -->
			 
			 <input name="useNature" id="useNature"  type="hidden"/><!-- 固定资产使用性质-->
			 <input name="attPsaId" id="attPsaId"  type="hidden"/><!-- 字表中的id-->
			 
			 
			 <input id="result" name="result" type="hidden" /><!-- 申请状态编码 -->
			  <input name="isSuccess" id="isSuccess"  type="hidden"/><!-- 处理结果-->
			  <input name="definitionKey" id="definitionKey"  type="hidden"/><!-- 任务key值-->
			  <input name="businessID" id="businessID"  type="hidden"/><!-- 业务id-->
			  <input id="appNo" name="appNo" type="hidden"/>
			 <table class="table"  width="95%">
				 <tr>
				    <th align="right">申请编号:</th>
					<td colspan="1"><input id="appNoMain" name="appNo" readonly="readonly" class="easyui-textbox " disabled="disabled" data-options="editable:false" type="text"/></td>
					<th align="right">归还人:</th>
					<td colspan="2" ><input name="reverter" id="reverter" type="text" class="easyui-textbox  easyui-combobox easyui-validatebox"  style="width: 170px" />&nbsp;</td>
				</tr>
				
				<tr>
				 	<th >审批意见:</th>
					<td colspan="5" >
						<textarea id="taskComment" name="taskComment" class="easyui-validatebox easyui-textbox" data-options="validType:'length[0,200]'" style="width:700px;height:70px;resize:none;"></textarea>
					</td>
				</tr>
				<!-- 操作 -->
				<tr>
					<td colspan="1"></td>
				   <td colspan="5" align="right">
						<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton" >上传附件</a>	
						<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>						   
				    	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="microcreditOpinion(1);">申请通过</a>
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

