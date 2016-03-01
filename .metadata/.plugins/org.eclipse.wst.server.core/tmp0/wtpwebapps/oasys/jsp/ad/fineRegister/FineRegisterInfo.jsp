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
					<th>罚款对象:</th>
					<td id="name">
						<input name="pt" id="pt" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
					<th>受罚对象类型:</th>
					<td>
						<input name="ptType" id="ptType" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>受罚日期:</th>
					<td>
						<input name="penaltyDate" id="penaltyDate" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
					<th>受罚原因:</th>
					<td>
						<input name="penaltyReson" id="penaltyReson" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>其他受罚原因:</th>
					<td>
						<input name="penaltyResonOther" id="penaltyResonOther" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
					<th>罚款金额:</th>
					<td>
						<input name="penaltyAMT" id="penaltyAMT" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>罚款单签发部门编号:</th>
					<td>
						<input id="signDept" name="signDept" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td><th>备注信息:</th>
					<td>
						<input id="remark" name="remark" type="text" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>			
			</table>
		</form>
	</div>	