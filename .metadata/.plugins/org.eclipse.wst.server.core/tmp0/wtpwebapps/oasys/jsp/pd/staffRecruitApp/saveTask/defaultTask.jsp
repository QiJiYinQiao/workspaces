<%@page import="com.oasys.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	var $row = $selRow;
	var $tiaoZhengWindow;
	function toSaveOrUpdateInvestProductOpenDlg(){
		var efaId = $('#businessID').val();
		$tiaoZhengWindow = $("<div></div>").dialog({
			width:690,
			height:480,					
			title:'招聘员工申请调整',
			href:'jsp/pd/staffRecruitApp/staffRecruitAppForm.jsp',
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
				handler:saveAdjuststaffRecruitApp
		    },{
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					$tiaoZhengWindow.dialog('close');
				}
			}]
		}); 
	}
	
	
	//申请调整函数
	function saveAdjuststaffRecruitApp(){
		$.ajax({
			type:'POST',
			url:'staffRecruitAppController/saveStaffRecruitApp.do',
			data:$("#staffRecruitAppForm").serialize(),
			dataType:'JSON',
			success:function(msg){
			   if(msg.status){
				   $tiaoZhengWindow.dialog('close');//销毁弹窗
			   }
			   //弹出提示信息
			   $.messager.alert(msg.title,msg.message,'info');
			}
		});
	}
	
	/** 点击申请通过时，校验数据，提交申请 **/
	function saveTaskFunc(isSuccess){
		//校验输入的信息
		var remark = $("#taskComment").val();
		$("#result").val(isSuccess);
		$("#isSuccess").val(isSuccess);
		var data1 = $('#taskForm').serialize();
		if(data1.indexOf("&appNo")==-1){
			data1+=("&appNo="+$selRow.appNo);
		}
		if((isSuccess == 0 || isSuccess == 2)  && (remark == "" || remark == null)){
			$.messager.alert("提示","请填写备注信息!","info");
			return false;
		}
		$.ajax({
			type: "POST",
			url:"staffRecruitAppController/submitTask.do",
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
					<th align="right">申请编号:</th>
					<td>
						<input name="appNo" class="easyui-textbox " disabled="disabled"/>
					</td>
					<th align="right">申请人:</th>
					<td>
						<input name="curLoginUserName" class="easyui-textbox " disabled="disabled"/>
					</td>
					<th align="right">所属部门:</th>
					<td>
						<input name="deptName" class="easyui-textbox " disabled="disabled"/>
					</td>
				</tr>

				<tr>
					<th align="right">职位名称:</th>
					<td>
						<input name="jobTitle" class="easyui-textbox " disabled="disabled"/>
					</td>
					<th align="right">现有人员数量:</th>
					<td>
						<input name="existingStaff" class="easyui-textbox " disabled="disabled"/>
					</td>
					<th align="right">招聘数量:</th>
					<td>
						<input name="recruitQty" class="easyui-textbox " disabled="disabled"/>
					</td>
				</tr>
				
				<tr>
					<th align="right">申请日期:</th>
					<td>
						<input name="appDate" class="easyui-textbox " disabled="disabled"/>
					</td>
					<th align="right">上岗时间:</th>
					<td>
						<input name="jobDtime" class="easyui-textbox " disabled="disabled"/>
					</td>
					<th align="right">服务年限:</th>
					<td>
						<input name="serviceLife" class="easyui-textbox " disabled="disabled"/>
					</td>
				</tr>
				
				<tr>
					<th align="right">申请状态:</th>
					<td>
						<input name="appStatusName" class="easyui-textbox " disabled="disabled"/>
					</td>				
					<th align="right">建议薪资下限(元):</th>
					<td>
						<input name="adviceSalLower" class="easyui-textbox " disabled="disabled"/>
					</td>
					<th align="right">建议薪资上限(元):</th>
					<td>
						<input name="adviceSalUpper" class="easyui-textbox " disabled="disabled"/>
					</td>
				</tr>								

				<tr>
					<th align="right">备注</th>
					<td colspan="5">
					   <textarea name="taskComment" id="taskComment" class="easyui-textbox" style="width: 99%; height: 90px;" data-options="validType:'length[0,200]'"></textarea>
					</td>
				</tr>
				<tr>
				   <td colspan="6" align="right">
						 <jsp:include page="/jsp/pd/taskHiddenJsp.jsp" flush="true"/>
				   </td>
				</tr>	
				<tr>
					<td colspan="6">
						<jsp:include page="/jsp/ad/optionsList.jsp" flush="true" />
					</td>
				</tr>					
			</table>
    </form>
