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
<title>财富客户信息查阅</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../../layout/script.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath %>jsp/investor/investorMain.js"></script>
<style type="text/css">
.textStyle{
	text-align: right;
	color: blue;
}
</style>
</head>
<body>
<!-- 提示信息区域 -->
<div class="well well-small" style="margin-left: 5px; margin-top: 5px">
	<span class="badge">提示</span>
	<p>
		在此你可以对<span class="label-info"><strong>财富客户信息</strong></span>进行查看!<span class="label-info"><strong>双击</strong></span>可以查看该客户的详细信息。
	</p>
</div>
<!-- 工具栏区域 -->
<div id="tb" style="padding:2px 0">
   <form id="searchForm">
      <table cellpadding="0" cellspacing="0">
		<tr>
			<td style="padding-left:11px">
				<span class="badge">搜索</span>姓名:
			</td>
			<td style="padding-left:2px">
				<input id="name" name="name" class="easyui-textbox" style="width: 100px;"/>
			</td>
			<td style="padding-left:3px">
				职业:
			</td>
			<td style="padding-left:2px">
				<input id="jobType" name="jobType" class="easyui-textbox" data-options="panelHeight:'auto'" style="width: 100px;"/>
			</td>
			<td style="padding-left:3px">
				证件号码:
			</td>
			<td style="padding-left:2px">
				<input id="idNo" name="idNo" class="easyui-textbox" style="width: 150px;"/>
			</td>
			<td style="padding-left:3px">
				手机号码:
			</td>
			<td style="padding-left:2px">
				<input id="mobileTel" name="mobileTel" class="easyui-textbox" style="width: 100px;"/>
			</td>
			<td style="padding-left:3px">
				电子邮箱:
			</td>
			<td style="padding-left:2px">
				<input id="email" name="email" class="easyui-textbox" style="width: 140px;"/>
			</td>
			<td style="padding-left:3px">
				描述:
			</td>
			<td style="padding-left:2px">
				<input id="description" name="description" class="easyui-textbox" style="width: 200px;"/>
			</td>
			<td>&nbsp;&nbsp;
			    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">搜索</a>
			    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="javascript:$('#searchForm').form('clear');">清空</a>
			</td>
		</tr>
	  </table>
   </form>
</div>
<!-- 财富客户信息表 -->
<table id="dg" title="财富客户信息列表"></table>
<!-- 详情弹框 -->
<div id="dl"></div>
<!-- 紧急联系人弹框 -->
<div id="dlLinkman">
  <div id="linkmanDatagrid"></div>
</div>
</body>
</html>