<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../layout/script.jsp"></jsp:include>
<title>银行流水</title>
<style type="text/css">
	table {border-right: 1px solid black; border-bottom: 1px solid black;}
	table th{border-left: 1px solid black;border-top: 1px solid black;}
	table td{border-left: 1px solid black; border-top: 1px solid black;}
	textarea{resize: none;width:100%;height:80px;}
	form{height:570px;text-align: center;}
	.subDiv{
		width: 98%; height: 30px; text-align: right;position: absolute;margin-top:5px;
	}
	font{font-weight: bold;}
	.easyui-textbox{width:100%;}
	.subDiv{
		width: 98%; height: 30px; text-align: right;position: absolute;margin-top:5px;
	}
</style>
</head>
<body>
	<div style="overflow: auto;">
		<form action="post">
			<div style="width:100%:">
				<font size="3">银行往来账目汇总表</font>
			</div>
			
			<table cellpadding="5">
				<tr>
					<th>银行尾号(后四位)</th>
					<td>
						<input class="easyui-textbox">
					</td>
					<td colspan="4"></td>
				</tr>
				
				<tr>
					<th>年份</th>
					<th>月份</th>
					<th>存入金额</th>
					<th>支出金额</th>
					<th>月末余额</th>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
					<td>
						<input class="easyui-textbox">
					</td>
				</tr>
				
				<tr>
					<th colspan="2">合计数</th>
					<td>
						<input class="easyui-textbox" value="0">
					</td>
					<td>
						<input class="easyui-textbox" value="0">
					</td>
					<td>
						<input class="easyui-textbox" value="0">
					</td>
				</tr>
				
				<tr>
					<th colspan="2">平均数</th>
					<td>
						<input class="easyui-textbox" value="0">
					</td>
					<td>
						<input class="easyui-textbox" value="0">
					</td>
					<td>
						<input class="easyui-textbox" value="0">
					</td>
				</tr>
				
				<tr>
					<th>备注</th>
					<td colspan="5">
						<textarea rows="" cols=""></textarea>
					</td>
				</tr>
			</table>
		</form>
		<div class="subDiv">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="">保存</a>
		</div>
	</div>
</body>
</html>