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
<title>流程部署</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../../layout/script.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath %>js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=basePath %>jsp/workflow/proc_dep.js"></script>
</head>
<body>
    <!-- 提示信息区域 -->
	<div class="well well-small" style="margin-left: 5px; margin-top: 5px;">
		<span class="badge">提示</span>
		<p>
			在此你可以对<span class="label-info"><strong>流程</strong></span>进行更新、部署、删除、查询等操作!
		</p>
		
		<table>
			<tr>
			<form id="formId" action="${pageContext.request.contextPath }/workflowAction/deployProcess.do" method="post" 
	enctype="multipart/form-data">
				<td style="padding-left:2px">
			    	<span class="label-info"><strong>部署</strong></span>
					流程名称:<input type="text" id="fileName" class="easyui-textbox" name="fileName" style="width: 110px">
				</td>
				<td style="padding-left:2px">
					<input type="file" id="file" name="file"  size=100/>
				</td>
				<td style="padding-left:2px">
				    <!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'1" onclick="procrep_toolbar.upload();">上传文件</a> -->
				   	<!-- <input type="submit" value="上传文件"/> -->
				   	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="procrep_toolbar.upload2()";>上传文件</a>
				</td>
				</form>
			</tr>
			<tr>
				<td style="padding-left:2px">
			    	<span class="label-info"><strong>查询</strong></span>
					流程编号:<input type="text" id="deploymentId" class="easyui-textbox" style="width: 110px">
				</td>
				<td style="padding-left:2px">
					流程名称:<input type="text" id="deploymentName" class="easyui-textbox" style="width: 110px">
				</td>
				<td style="padding-left:2px">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="procrep_toolbar.search();">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo'"  onclick="procrep_toolbar.resetting();">重置</a>
				</td>
			</tr>
		</table>
		
	</div>
	<!-- 工具栏区域 -->
	<div id="procrep_toolbar" style="padding:2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="procrep_toolbar.remove();">删除</a>
					</td>
				</tr>
			</table>
	  </div>
	  <table id="process_deploy" title="流程部署" style="margin-bottom: 5px;"></table>
</body>
</html>