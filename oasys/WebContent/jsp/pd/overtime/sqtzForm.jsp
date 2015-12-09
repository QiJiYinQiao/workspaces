<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
.easyui-textbox {
	height: 18px;
	width: 170px;
	line-height: 16px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
}

textarea:focus, input[type="text"]:focus {
	border-color: rgba(82, 168, 236, 0.8);
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px
		rgba(82, 168, 236, 0.6);
	outline: 0 none;
}
.table {
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
	max-width: 100%;
}
fieldset {
	    border-width: 1px;
	    margin-top: 5px;
	    border-color:#F5F5F5;
	    -moz-border-radius:8px;
}
input, textarea {
	font-weight: normal;
}

.table td {
	padding: 6px;
}
.table th{
    text-align: right;
	padding: 6px;
}
.textStyle{
  text-align: right;
}
</style>
<script type="text/javascript">
var $$row;
$(function(){
	 $$row=$row;
	 $("#acceptTaskForm").form('load',$row);
	 $("#deptNo").val($row.deptName);
})
/**
 * 营业部经理驳回/通过
 */
function submitTask(result,object){
	if($("#comment").val()==""){
		$.messager.alert("提示","请填写备注信息后再进行提交!","warning")
		return false;
	}
	//0驳回 1是通过
	$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
		if (r){
			var data = {
				"remark":$("#comment").val(),
				"result":result,
				"paId":$row.oveId,
				"appNo":$row.appNo,
				"taskId":$row.taskId,
				"handleResult":result=='shenqingchongti'?1:0
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
//申请调整
function tiaoZhengDialog(){
	$("#tiaozhengDialog").dialog({
		title: '申请调整',    
	    width: 920,    
	    height: 400,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/pd/overtime/overtimeAdd.jsp',
	    modal: true,
// 	    onClose : function(){
// 	    	$(this).dialog('close');
// 	    },
	    onLoad:function(){
	    	createSelect();
	    	$("#investProductInputOrSaveForm").form("load",$row);
	        $("#deptNo").combotree('setValue',$row.organizationId);
	        $("#applicantNo").combogrid('setValue',$row.userId);
	        $("#applicantNo").combogrid('setText',$row.applicantNo); 
	    },
	    buttons : [ {
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$("#tiaozhengDialog").dialog('close');
				$("#waitTaskGrid").datagrid('reload');
			}
		}]
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
			 <input name="caID" id="caID"  type="hidden"/>
<!-- 		 <input name="auditId" type="hidden" value="noauditId"/>  -->
		<table class="table" cellpadding="5px;">
			<tr>
					<th>部门:</th>
					<td>
						<input name="deptName" class="easyui-textbox" disabled="disabled"/>
					</td>
					<th>姓名:</th>
					<td id="name">
						<input name="applicantNo" class="easyui-textbox" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>职务:</th>
					<td>
						<input name="position" id="position" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
					<th>申请日期:</th>
					<td>
						<input name="appDate" id="appDate" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>计划开始时间:</th>
					<td>
						<input name="planBgDtime" id="planBgDtime" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
					<th>计划结束日期时间:</th>
					<td>
						<input name="planEdDtime" id="planEdDtime" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>实际开始日期时间:</th>
					<td>
						<input id="realBgDtime" name="realBgDtime" class="easyui-textbox" disabled="disabled"/>
					</td>
					<th>实际结束日期时间:</th>
					<td>
						<input id="realEdDtime" name="realEdDtime" type="text" class="easyui-textbox" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>备注:</th>
					<td colspan="3">
						<textarea id="comment" name="comment" class="easyui-textbox" style="width:560px;height:50px;"></textarea>
					</td>
				</tr>
			<tr>
				<td colspan="8">
					<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton" >上传附件</a>	
					<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>
				    <a href="javascript:void(0);" class="easyui-linkbutton" onclick="tiaoZhengDialog();">申请调整</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('shenqingchongti',this);">申请重提</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('shenqingchexiao',this);">申请撤销</a>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<jsp:include page="/jsp/pd/optionsList.jsp" />
				</td>
			</tr>
		 </table>
		<div id="attachmentList" style="display:block;float:left;">
		</div>
	</form>
	</div>
	
	<!-- <div style="margin-left: 25px;margin-bottom: 5px;">
		<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton" >上传附件</a>	
		<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>
	    <a href="javascript:void(0);" class="easyui-linkbutton" onclick="tiaoZhengDialog();">申请调整</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('shenqingchongtibo',this);">申请重提</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('shenqingchexiaobo',this);">申请撤销</a>
	</div>
 -->	
</div>   
<!-- 新增弹框 -->
<div id="tiaozhengDialog"></div>