<%@page import="com.bpms.util.Constants"%>
<%@page import="com.bpms.shiro.ShiroUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
 ShiroUser shiroUser = Constants.getCurrendUser();
%>
<style type="text/css">
	
</style>
<script type="text/javascript">
	$(function(){
		
	});
</script>

<!-- 申请报告 S -->
<div id="applicationReportTabs" class="easyui-tabs" style="fit:true;overflow: hidden;">
	<div title="贷款摘要">
		<div style="width:100%;height:605px;overflow: hidden;">
			<iframe src="jsp/loanOrder/IPC/ipcSurveyReportTabs.jsp" frameborder="0" style="width:100%; height:100%;overflow: hidden;"></iframe>
		</div>
	</div>
	
	<div title="资产负债表">
		<div style="width:100%;height:605px;overflow: hidden;">
			<iframe src="jsp/loanOrder/IPC/assetLiabilitiesTabs.jsp" frameborder="0" style="width:100%; height:100%;overflow: hidden;"></iframe>
		</div>
	</div>
	
	<div title="损益表">
		
	</div>
	
	<div title="存货及设备清单">
		<div style="width:100%;height:605px;overflow: hidden;">
			<iframe src="jsp/loanOrder/IPC/inventoryEquipmentList.jsp" frameborder="0" style="width:100%; height:100%;overflow: hidden;"></iframe>
		</div>
	</div>
	
	<div title="银行流水">
		<div style="width:100%;height:605px;overflow: hidden;">
			<iframe src="jsp/loanOrder/IPC/bankStatement.jsp" frameborder="0" style="width:100%; height:100%;overflow: hidden;"></iframe>
		</div>
	</div>
	
</div>
