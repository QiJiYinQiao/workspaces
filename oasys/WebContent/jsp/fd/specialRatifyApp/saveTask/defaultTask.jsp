<%@page import="com.oasys.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function toSaveOrUpdateInvestProductOpenDlg(){
	var btaId = $('#btaId').val();
	$tiaozheng=$("<div></div>").dialog({
		title : '编辑',
		width : 650,
		height : 300,
		modal:true,
		href : "jsp/fd/specialRatifyApp/specialRatifyAppForm.jsp",
		onLoad:function(){
			$("#speRatType").combobox({
				url:"commonController/findDicList.do?codeMyid=spe_rat_type",
				valueField: 'code',
				textField: 'text',
				required:true,
				onSelect:function(rec){
					if(rec.code=='1'){
						$("#hiddenOne").attr("class","");
						$("#hiddenTwo").attr("class","");
						$("#appRepaymentDate").validatebox({required:true});
					}else{
						$("#hiddenOne").attr("class","dis");
						$("#hiddenTwo").attr("class","dis");
						$("#appRepaymentDate").validatebox({required:false});
						$("#appRepaymentDate").datebox("setValue","");
					}
				}
			});
			var f = $("#baseForm");
			f.form("load", $selRow);
			var code=$("#speRatType").combobox('getValue');
			if(code=="1"){
				$("#hiddenOne").attr("class","");
				$("#hiddenTwo").attr("class","");
				$("#appRepaymentDate").validatebox({required:true});
			}
		},
		onClose:function(){
			$("#dg").datagrid("reload");
			$(this).dialog('destroy');
		},
		buttons : [ {
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$tiaozheng.dialog('close');
				$tiaozheng.dialog('destroy');
			}
		}]
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
		url:"specialRatifyApp/saveTask.do",
		data:data1,
	    success: function(iJson) {
 	    	if(iJson.status){
	    		$("#dg").datagrid("reload");//刷新表格
	    		$dialog.dialog("close");//关闭弹窗
	    	}
 	    	$.messager.alert(iJson.title,iJson.message,'info');
	    }
	}); 
}
</script>
    <form id="taskForm"  method="post" style="width: 780px;margin-left:5px;">
    <input id="applyStr" name="applyStr" type="hidden"  value="<%= Constants.APPLY_FOR_ADJUSTMENT %>" />
 		<table class="table"  width="95%">
				<tr>
					<th>申请编号:</th>
					<td>
						<input id="appNo" name="appNo" class="easyui-textbox" disabled="disabled"/>
					</td>
					<th>申请人:</th>
					<td>
						<input id="userName" name="userName" class="easyui-textbox"  disabled="disabled" />
					</td>
				</tr>
				<tr>
					<th>申请部门:</th>						
					<td>
						<input id="deptName" name="deptName" class="easyui-textbox"  disabled="disabled"  />
					</td>
					<th>申请时间:</th>
					<td>
						<input name="appDate" id="appDate" class="easyui-textbox easyui-datebox"  editable="false" style="width:174px;" disabled="disabled" />
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
						<jsp:include page="/jsp/pd/optionsList.jsp" flush="true" />
					</td>
				</tr>					
			</table>
			<div id="saveOrUpdateEmpSalDialog"></div>	
    </form>
