<%@page import="com.oasys.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">

	var $selRow=$row;

	function toSaveOrUpdateInvestProductOpenDlg(){
		$("#saveOrUpdateEmpSalDialog").dialog({
			title : '编辑用章申请',
			iconCls: 'icon-edit',
			width : 955,
			height : 646,
			modal:true,
			resizable:false,
			href : "jsp/ad/UseStampApp/useStampAddForm.jsp",
			onLoad:function(){
				if(index!=null){
					if($row.appDate!=null){
						$row.appDate=$row.appDate.split(" ")[0];
					}
					if($row.useBgDate!=null){
						$row.useBgDate=row.useBgDate.split(" ")[0];
					}
					if($row.useEdDate!=null){
						$row.useEdDate=row.useEdDate.split(" ")[0];
					}
					
					var f = $("#useStampForm");
					f.form("load", $row); 
					useStampGrid($row.appNo)
				}
			}, 
			onClose:function(){
				$grid.datagrid('reload');
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
			data1+=("&appNo="+$row.appNo);
		}
		if((isSuccess == 0 || isSuccess == 2)  && remark == ""){
			$.messager.alert("提示","请填写备注信息!","info");
			return false;
		}
		$.messager.confirm('确定','是否确定提交办理任务？',	function(flag) {
			if (flag) {
				 $.ajax({
					type: "POST",
					url:"UseStampTask/saveTaskMgrResult.do",
					data:data1,
					async:false,
				    success: function(iJson) {
			 	    	if(iJson.status){
			 	    		$banliDialog.dialog("close");//关闭弹窗
				    		$("#dg").datagrid("reload");//刷新表格
				    	}
			 	    	$.messager.alert(iJson.title,iJson.message,'info');
				    }
				});
			}
		});
	}
	
</script>
    <form id="taskForm"  method="post" style="width: 880px;margin-left:5px;">
	    <input id="applyStr" name="applyStr" type="hidden"  value="<%= Constants.APPLY_FOR_ADJUSTMENT %>" />
	    <input id="giveuseNature" name="useNature" type="hidden"><!-- 使用性质 -->
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
