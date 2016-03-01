<%@page import="com.oasys.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
var $$row = $selRow;
var $tiaoZhengWindow;
function toSaveOrUpdateInvestProductOpenDlg(){
	var btaId = $('#businessID').val();
	$tiaoZhengWindow = $("<div></div>").dialog({
		width: $(window).width()*0.40,    
	    height: $(window).height()*0.63,  					
		title:'费用申请调整',
		href:'jsp/ad/expensesApp/expensesAppForm.jsp',
		modal:false,
		resizable:true,
		iconCls:'icon-edit',
		closed: false,
		onClose:function(){
			$(this).dialog('destroy');
		},
		buttons : [ {
	    	text:'保存',
	    	iconCls : 'icon-save',
			handler:function(){
				saveEditExpensesApp();
			}
	     },{
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$tiaoZhengWindow.dialog('close');
			}
		}]
	}); 
}
//执行编辑
function saveEditExpensesApp(){
	$.ajax({
		type:'POST',
		url:'expensesAppController/saveExpensesApp.do',
		data:$("#expensesAppForm").serialize(),
		dataType:'JSON',
		success:function(msg){
		   if(msg.status){
			   $tiaoZhengWindow.dialog('close');
		   }
		   //弹出提示信息
		   $.messager.alert(msg.title,msg.message,'info');
		}
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
		url:"expensesAppController/submitTask.do",
		data:data1,
	    success: function(msg) {
 	    	if(msg.status){
 	    		$banliWindow.dialog("close");//关闭弹窗
	    	}
 	    	$.messager.alert(msg.title,msg.message,'info');
	    }
	});
}
</script>
    <form id="taskForm"  method="post" style="width: 98%">
        <input id="applyStr" name="applyStr" type="hidden"  value="<%=Constants.APPLY_FOR_ADJUSTMENT %>" />
 		<table class="table">
			<tr>
				<th align="right">申请人:</th>
				<td>
					<input name="userName" class="easyui-textbox"  disabled="disabled" />
				</td>				
				<th align="right">申请部门:</th>						
				<td>
					<input name="fullName" class="easyui-textbox"  disabled="disabled"  />
				</td>
				<th align="right">申请时间:</th>
				<td>
					<input name="appDate" class="easyui-textbox" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th colspan="1" align="right">审批意见:</th>
				<td colspan="5"><textarea name="taskComment" id="taskComment" class="easyui-textbox" style="width: 95%; height: 90px;" data-options="required:true,validType:'length[0,200]'"></textarea></td>
			</tr>
			<tr>
			   <td colspan="6" align="right" style="padding-right: 23px;">
					 <jsp:include page="/jsp/ad/taskHiddenJsp.jsp" flush="true" />
			   </td>
			</tr>	
			<tr>
				<td colspan="6">
					<jsp:include page="/jsp/ad/optionsList.jsp" flush="true" />
				</td>
			</tr>					
	   </table>
    </form>
