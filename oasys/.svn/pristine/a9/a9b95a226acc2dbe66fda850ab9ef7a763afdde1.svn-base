<%@page import="com.oasys.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$row=null;
$isDisable=null;
//申请调整
function toSaveOrUpdateInvestProductOpenDlg(){
		$row=$$row;
		$isDisable=true;
		$("#saveOrUpdateEmpSalDialog").dialog({
			title: '申请调整',    
		    width: 920,    
		    height: 400,    
		    closed: false,    
		    cache: false,    
		    href: 'jsp/ad/carRegister/vehicleExpensesAppAdd.jsp',
		    modal: true,
		    onLoad : function(){
		    	var f = $("#investProductInputOrSaveForm");
				f.form("load",$row);
		    },
	 	    onClose : function(){
	 	    	$("#saveOrUpdateEmpSalDialog").dialog('close');
				$$row=null;
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
		url:"VehicleExpenses/saveTaskVehicleExpensesApp.do",
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

    <form id="taskForm" name="taskForm"  method="post" style="width: 800px;margin-left:5px;">
    <input id="applyStr" name="applyStr" type="hidden"  value="<%= Constants.APPLY_FOR_ADJUSTMENT %>" />
    <table class="table"  width="95%">
			<tr>
			    <td class="textStyle">申请编号:</td>
				<td colspan="5"><input name="appNo" class="easyui-textbox" readonly="readonly" type="text" disabled="disabled"/></td>
			</tr>
			<tr>
			 	<td class="textStyle">备注:</td>
				<td colspan="5">
					<textarea id="taskComment" name="taskComment" class="easyui-validatebox easyui-textbox" style="width:688px;height:70px;"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="3"></td>
			   <td colspan="3" align="right">
					 <jsp:include page="/jsp/ad/taskHiddenJsp.jsp" flush="true" />
			   </td>
			</tr>	
			<tr>
				<td colspan="6">
					<jsp:include page="/jsp/ad/optionsList.jsp" flush="true" />
				</td>
			</tr>									
		</table>
		<div id="saveOrUpdateEmpSalDialog"></div>
    </form>
