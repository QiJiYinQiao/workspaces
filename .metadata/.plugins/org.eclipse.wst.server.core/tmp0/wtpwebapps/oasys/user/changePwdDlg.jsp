<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<form id="chgForm" method="post">
			<table>
			<tr>
				<th>请输入新密码：</th>
				<td><input name="password" id="password" type="password" data-options="required:true,validType:'length[0,64]'"/></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th>请确认新密码：</th>
				<td><input name="rpwd" id="rpwd" type="password" data-options=""required="required" validType="same['password']"/></td>
			</tr>
			</table>
		</form>
	</div>
</div>
