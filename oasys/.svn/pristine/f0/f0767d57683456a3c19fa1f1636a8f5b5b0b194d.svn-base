<%@page import="com.oasys.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
//申请调整
function toSaveOrUpdateInvestProductOpenDlg(){
		$("#saveOrUpdateEmpSalDialog").dialog({
			title: '申请调整',    
		    width: 920,    
		    height: 400,    
		    closed: false,    
		    cache: false,    
		    href: 'jsp/pd/overtime/overtimeAdd.jsp?id=1',
		    modal: true,
	 	    onClose : function(){
	 	    	$("#saveOrUpdateEmpSalDialog").dialog('close');
				$$row=null;
	 	    },
		    onLoad:function(){
		    	createSelect();
		    	$("#investProductInputOrSaveForm").form("load",$selRow);
		        $("#investProductInputOrSaveForm #deptNo").combotree('setValue',$selRow.organizationId);
		        $("#investProductInputOrSaveForm #applicantNo").combogrid('setValue',$selRow.userId);
		        $("#investProductInputOrSaveForm #applicantNo").combogrid('setText',$selRow.applicantNo); 
		    },
		    buttons : [ {
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					$("#saveOrUpdateEmpSalDialog").dialog('close');
					$("#waitTaskGrid").datagrid('reload');
				}
			}]
		});
}


//受理任务
function saveTaskFunc(isSuccess){
	//校验理财产品输入的信息
	var remark = $("#taskComment").val();
	$("#result").val(isSuccess);
	$("#isSuccess").val(isSuccess);
	var data1 = $('#taskForm').serialize();
	if(data1.indexOf("&appNo")==-1){
		data1+=("&appNo="+$selRow.appNo);
	}
	if((isSuccess == 0 || isSuccess == 2)  && remark == ""){
		$.messager.alert("提示","请填写备注信息!","info");
		return false;
	}
	 $.ajax({
		type: "POST",
		url:"overtime/handleTask.do",
		data:data1,
	    success: function(iJson) {
 	    	if(iJson.status){
 	    		$dialog1.dialog("close");//关闭弹窗
	    		$("#waitTaskGrid").datagrid("reload");//刷新表格
	    	}
			$.messager.alert(iJson.title,iJson.message,'info');  
	    }
	});
}
</script>
    <form id="taskForm"  method="post" style="width: 880px;margin-left:5px;">
    <input id="applyStr" name="applyStr" type="hidden"  value="<%= Constants.APPLY_FOR_ADJUSTMENT %>" />
 		<table class="table"  width="95%">
 			<tr>
 				<td>编号:</td>
 				<td colspan="5"><input name="appNo" id="appNo" class="easyui-textbox" data-options="validType:'length[0,100]', required:true" disabled="disabled"/></td>
 			</tr>
			<!-- <tr>
					<th>部门:</th>
					<td>
						<input name="deptName" id="deptName" class="easyui-textbox" data-options="validType:'length[0,100]', required:true" disabled="disabled"/>
					</td>
					<th>姓名:</th>
					<td id="name">
						<input name="applicantNo" id="applicantNo" class="easyui-textbox" data-options="validType:'length[0,100]', required:true" disabled="disabled"/>
					</td>
				</tr> -->
				<!-- <tr>
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
				</tr> -->
				<tr>
					<th>备注:</th>
					<td colspan="3">
						<textarea id="taskComment" name="taskComment" class="easyui-textbox" data-options="validType:'length[0,400]', required:true" style="width:560px;"></textarea>
					</td>
				</tr>
				<tr>
				   <td colspan="1"></td>
				   <td colspan="5" align="right">
						 <jsp:include page="/jsp/pd/taskHiddenJsp.jsp" flush="true" />
				   </td>
				</tr>
				<tr>
					<td colspan="6">
						<jsp:include page="/jsp/pd/optionsList.jsp" flush="true" />
					</td>
				</tr>					
			</table>
			<div id="saveOrUpdateEmpSalDialog"></div>	
    </form>
