<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/validate.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	$(function(){
		disableForm("leaveAppShowForm");
	});

	//关闭
	function toCloseBaseInfo(){
		$banliDialog.dialog('close');
	}
	
	//查看附件
	$("#checkAttachment").click(function(){
		checkAttachementDetail($("#appNo").val(),$("#applicantNo").val(),'');
	});
  	
	
</script>
<div id="tt">
	<div title="休假申请详情">
	  <div class="well well-small" style="margin:5px;">
	     	<form id="leaveAppShowForm">
	      	 <input id="leaId" name="leaId" type="hidden"/><!-- 申请id -->
	         <table class="table">
	         	<tr>
	         		<th align="right">申请编号:</th>
	         		<td>
	         			<input id="appNo" name="appNo" style="border: none;" readonly="readonly"/>
	         		</td>
	         	</tr>
	         	<tr>
	         		<th align="right">申请人姓名:</th>
	         		<td>
	         			<input id="applicantName" name="applicantName" style="border: none;" readonly="readonly"/>
	         		</td>
	         		<th align="right">所属部门:</th>
	         		<td>
	         			<input id="fullName" name="fullName" style="border: none;" readonly="readonly"/>
	         		</td>
	         		<th align="right">所属职位:</th>
	         		<td style="width: 140px;">
	         			<input id="position" name="position" style="border: none;width: 100px" readonly="readonly" />
	         		</td>
	         	</tr>
	         
				<tr>
					<th align="right">职务代理人:</th>
					<td>
						<input name="agentName" id="agentName" type="text" style="border: none;" readonly="readonly"  /></td>					
					</td>
				</tr>
				
				<tr>
					<th align="right">休假种类:</th>
					<td>
						<input id="leName" name="leName" style="border: none;" readonly="readonly"/>
					</td>
				</tr>
				
				<tr>
					<th style="width: 120px;" align="right">计划请假开始时间:</th>
					<td style="width: 170px;">
						<input id="planBgDtime" name="planBgDtime"  style="border: none;" readonly="readonly"/>
					</td>
					<th style="width: 120px;" align="right">计划请假结束时间:</th>
					<td style="width: 170px;">
						<input id="planEdDtime" name="planEdDtime" style="border: none;" readonly="readonly"/>
					</td>
					<th style="width: 120px;" align="right">计划请假天数:</th>
					<td style="width: 140px;">
						<input id="planLeDays" name="planLeDays" style="border: none;width: 100px;" readonly="readonly">天</input>
					</td>
				</tr>
				<tr>
					<th style="width: 120px;" align="right">实际请假开始时间:</th>
					<td style="width: 170px;">
						<input id="realBgDtime" name="realBgDtime" style="border: none;" readonly="readonly"/>
					</td>
					<th style="width: 120px;" align="right">实际请假结束时间:</th>
					<td style="width: 170px;">
						<input id="realEdDtime" name="realEdDtime" style="border: none;" readonly="readonly"/>
					</td>
					<th style="width: 120px;" align="right">实际请假天数:</th>
					<td style="width: 140px;">
						<input id="realLeDays" name="realLeDays" style="border: none;width: 100px;" readonly="readonly">天</input>
					</td>
				</tr>
				
				<tr>
					<th align="right">休假事由:</th>
					<td colspan="6">
						<textarea id="leReason" name="leReason" disabled="disabled" style="width: 732px;height:80px;border: none;resize:none;" readonly="readonly"></textarea>
					</td>
				</tr>
				
				
				 <tr>
					<th align="right">备注信息:</th>
					<td colspan="6">
					  <textarea id="remark" name="remark" disabled="disabled" style="width: 732px;height:80px; border: none;resize:none;" readonly="readonly"></textarea>
					</td>
				</tr>
				<tr>
				   <td colspan="6" style="text-align: right;">
					  <a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton" >查看附件</a>	
				   	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-no" onclick="toCloseBaseInfo();">关闭</a>
				   </td>
				</tr>
	         </table>
	       </form>
	  </div>
	</div>
	
</div>
