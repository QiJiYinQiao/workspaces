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
					<th>使用人:</th>
					<td id="name">
						<input name="user" id="user" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
					<th>部门:</th>
					<td>
						<input name="dept" id="dept" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>车牌号码:</th>
					<td>
						<input name="carNo" id="carNo" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
					<th>使用事由:</th>
					<td>
						<input name="usesReson" id="usesReson" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>用车时间:</th>
					<td>
						<input name="usesDateTime" id="usesDateTime" class="easyui-datebox" readonly="true" disabled="disabled"/>
					</td>
					<th>始发地:</th>
					<td>
						<input name="origin" id="origin" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>目的地:</th>
					<td>
						<input id="destination" name="destination" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td><th>启程公里数:</th>
					<td>
						<input id="bgKilometer" name="bgKilometer" type="text" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>结束公里数:</th>
					<td>
						<input id="edKilometer" name="edKilometer" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td><th>归还时间:</th>
					<td>
						<input id="gvDateTime" name="gvDateTime" type="text" class="easyui-datebox" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>加油费:</th>
					<td>
						<input id="fuelCharge" name="fuelCharge" class="easyui-textbox" value="0" readonly="true" disabled="disabled"/>
					</td><th>路桥费:</th>
					<td>
						<input id="roadToll" name="roadToll" type="text" class="easyui-textbox" value="0" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>停车费:</th>
					<td>
						<input id="parkingFee" name="parkingFee" class="easyui-textbox" value="0" readonly="true" disabled="disabled"/>
					</td><th>合计金额:</th>
					<td>
						<input id="totalAMT" name="totalAMT" type="text" class="easyui-textbox " value="0" readonly="true" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th>备注信息:</th>
					<td colspan="2">
						<input id="remark" name="remark" class="easyui-textbox" readonly="true" disabled="disabled"/>
					</td>
				</tr>				
			</table>
		</form>
	</div>	