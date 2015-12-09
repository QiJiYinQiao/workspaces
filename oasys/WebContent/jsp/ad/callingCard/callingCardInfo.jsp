<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">	
</script>
	<div style="margin-left: 5px;margin-top: 5px;" data-options="iconCls:'icon-cstbase'">
	   <form id="investProductInputOrSaveFormInfo"  method="post">
<!--  	   		<input id="caId" name="caId" type="hidden"/>理财产品的ID -->
<!--  	   		<input id="appNo" name=appNo type="hidden"/>理财产品的ID -->
<!--  	   		<input id="unit" name=unit type="hidden"/>理财产品的ID -->
			<table class="table" width="100%">
				<tr>
					<th>姓名:</th>
					<td id="name">
						<input name="userName" id="userName" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
					<th>申请部门:</th>
					<td>
						<input name="deptName" id="deptName" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>职务:</th>
					<td>
						<input name="positionName" id="positionName" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
					<th>个人电话:</th>
					<td>
						<input name="personalTel" id="personalTel" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>办公电话:</th>
					<td>
						<input name="officeTel" id="officeTel" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
					<th>邮箱:</th>
					<td>
						<input name="email" id="email" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>	
			</table>
		</form>
	</div>	