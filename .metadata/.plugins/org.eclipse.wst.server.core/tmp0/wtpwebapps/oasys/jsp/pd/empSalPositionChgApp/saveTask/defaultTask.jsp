<%@page import="com.oasys.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function toSaveOrUpdateInvestProductOpenDlg(){
	var efaId = $('#businessID').val();
	$dialog=$("<div></div>").dialog({
		width : 900,
		height : 650,					
		title : '员工调岗调薪变动调整',
		href : "empSalPositionChgAppController/toAddEmpSalPositionChgApp.do?efaId="+ efaId,
		modal:false,
		resizable:true,
		iconCls:'icon-add',
		onClose:function(){
			$$row=null;
	    	$(this).dialog('destroy');
		},
		buttons : [{
		    	text:'保存',
		    	iconCls:'icon-save',
				handler:saveEmpSal
		    },{
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$dialog.dialog('close');
			}
		}],
		closed: false
	}); 
}


/** 增加或者修改理财产品的数据**/
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
		url:"empSalPositionChgAppController/saveTaskEmpSalPositionChgApp.do",
		data:data1,
	    success: function(iJson) {
 	    	if(iJson.status){
 	    		$dialog.dialog("close");//关闭弹窗
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
					<th align="right">申请编号:</th>
					<td colspan="5">
						<input name="appNo" id="appNo"  class="easyui-textbox "  type="text"  style="width: 170px" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th colspan="1" align="right">*审批意见</th>
					<td colspan="5"></td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="5"><textarea name="taskComment" id="taskComment" class="easyui-textbox" 
						style="width: 95%; height: 90px;" data-options="required:true,validType:'length[0,200]'"></textarea></td>
				</tr>
				<tr>
					<td colspan="1"></td>
				   <td colspan="5" align="right">
						 <jsp:include page="/jsp/pd/taskHiddenJsp.jsp" flush="true" />
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
