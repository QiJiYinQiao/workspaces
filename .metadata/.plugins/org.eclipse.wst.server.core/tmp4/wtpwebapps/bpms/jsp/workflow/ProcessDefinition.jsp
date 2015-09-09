<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
<title>流程定义</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../../layout/script.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath %>js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=basePath %>jsp/workflow/proc_def.js"></script>
</head>
<body>
    <!-- 提示信息区域 -->
	<div class="well well-small" style="margin-left: 5px; margin-top: 5px">
		<span class="badge">提示</span>
		<p>
			在此你可以对<span class="label-info"><strong>流程定义</strong></span>进行查询等操作!
		</p>
		<table>
			<tr>
				<td style="padding-left:2px">
			    	<span class="label-info"><strong>查询</strong></span>
					名称:<input type="text" id="name" class="easyui-textbox" style="width: 110px">
				</td>
				<td style="padding-left:2px">
					KEY:<input type="text" id="key" class="easyui-textbox" style="width: 110px">
				</td>
				<td style="padding-left:2px">
					版本:<input type="text" id="version" class="easyui-textbox" style="width: 110px">
				</td>
				<td style="padding-left:2px">
					部署ID:<input type="text" id="deploymentId" class="easyui-textbox" style="width: 110px">
				</td>
				<td style="padding-left:2px">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="procrep_toolbar.search();">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo'"  onclick="procrep_toolbar.resetting();">重置</a>
				</td>
			</tr>
		</table>
	</div>
    <table id="process_definition" title="流程部署"></table>
    
    <div id="imageDialog" class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<!-- 1.获取到规则流程图 -->
			<img id="image">
	</div>
</body>
</html>