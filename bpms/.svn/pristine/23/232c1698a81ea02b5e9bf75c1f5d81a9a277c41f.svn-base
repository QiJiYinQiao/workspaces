<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>决绝决议表</title>
</head>
<body>
	<div id="microcreditOpinionRefuseDlg" class="easyui-dialog" title=" " data-options="border:false,closed:true,modal:true" style="width:800px;height:700px;">
	<form id="microcreditOpinionRefuseForm" method="post"> 
		<input name="mcbrId" type="hidden" />
		<div style="text-align:center;">
			<font size="4" style="font-weight: bold;">拒绝决议表</font>
		</div>
		<div>
			<table cellpadding="8px;">
				<tr>
					<th>
						客户姓名
					</th>
					<td>
						<input name="name" class="easyui-textbox" readonly="readonly" style="background-color: #EBEBE4" type="text" value="韩冰"/>
						<input name="loanOrderId" type="hidden" />
					</td>
					<th>
						身份证号
					</th>
					<td >
						<input readonly="readonly" style="background-color: #EBEBE4" style="background-color: #EBEBE4" class="easyui-validatebox easyui-textbox" name="idNo"  type="text" />
					</td>
				</tr>
				
				<tr>
					<th>
						申请金额(元)
					</th>
					<td>
						<input name="loanAmount" readonly="readonly" style="background-color: #EBEBE4" class="easyui-validatebox easyui-textbox" type="text" value=""/>
					</td>
					<th>
						调查日期
					</th>
					<td >
						<input id="surveyDate" name="surveyDate" class="easyui-textbox easyui-datebox" data-options="editable:false"/>
					</td>
				</tr>
				
				<tr>
					<th>
						贷款目的
					</th>
					<td colspan="3">
						<input name="purpose"  readonly="readonly" style="background-color: #EBEBE4" class="easyui-validatebox easyui-textbox"/>
					</td>
				</tr>
				
				<tr>
					<th>
						所在地区
					</th>
					<td><input readonly="readonly" style="background-color: #EBEBE4" class="easyui-validatebox easyui-textbox" name="loanCtiy"  type="text"/></td>
					<th>
						调查人员
					</th>
					<td>
						A:<input id="operatorAR" class="easyui-validatebox easyui-textbox easyui-combobox"  name="operatorA" />&nbsp;&nbsp;&nbsp;
						B:<input id="operatorBR" class="easyui-validatebox easyui-textbox easyui-combobox" name="operatorB"  /> 
					</td>
				</tr>
			</table>
			<div style="width:99.5%;height:400px;">
				<div style="height:30px;">
						<span style="font-weight:bold;padding-left:10px;">拒绝原因：</span>
				</div>
				<div style="padding:20px 0 20px 20px;height:330px;overflow:auto;">
					<textarea class="easyui-validatebox easyui-textbox" name="rejectCause" style="width:99%;height:320px;resize: none;"></textarea>
				</div>
			</div>
			<div style="height:40px;">
				<table cellpadding="8px;">
					<tr>
						<th>
							业务经办人
						</th>
						<td>
							<input readonly="readonly" style="background-color: #EBEBE4" type="text" name="operatorAR" class="easyui-validatebox easyui-textbox" />
						</td>
						<td>
							<input readonly="readonly" style="background-color: #EBEBE4" type="text" name="operatorBR" class="easyui-validatebox easyui-textbox" />
						</td>
						<th >
							部门负责人
						</th>
						<td >
							<input class="easyui-validatebox easyui-textbox" name="deptPrincipal" data-options="required:true"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>	
	<div id="upload_form" style="width: 100%; height: 30px; text-align: right;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="microcreditOpinionRefuseSubmit('microcreditOpinionRefuseForm','microcreditOpinionRefuseDlg');">提交</a>
	</div> 
 </div>
</body>
</html>