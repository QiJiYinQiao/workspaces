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
<%-- <script type="text/javascript" src="<%=basePath %>js/ajaxfileupload.js"></script> --%>
<script type="text/javascript" src="<%=basePath %>jsp/workflow/proc_dep.js"></script>
</head>

<script type="text/javascript">

function upload(){
	var fileNames = ["身份证","户口本","港澳通行证"];
	var fileElementIds = ["file1","file2","file3"];
	$.ajaxFileUpload({
		url:'uploadAction/uploadAction!upload.action',
		data:{"fileName":fileNames},
		fileElementId:fileElementIds,
		secureuri:false,
		dataType:'text',
		success:function(data,status){
			data = $.parseJSON(data);
			if(data.status){
				$.messager.show({
					title:data.title,
					msg:data.message,
					timeout:3000,
					showType:'slide'
				});	
			}else{
				$.messager.alert(data.title,data.message,'error');
			}
			$('input').val('');
		}
	});
}
</script>
<body>
    <!-- 提示信息区域 -->
	<div class="well well-small" style="margin-left: 5px; margin-top: 5px;">
		<table>
			<tr>
				<td style="padding-left:2px">
					流程名称:<input id="fileName1" class="easyui-textbox" name="fileName" type="text" style="width: 110px">
				</td>
				<td style="padding-left:2px">
					<input id="file1"   name="file"  type="file"size=100/>
				</td>
			</tr>
			<tr>
				<td style="padding-left:2px">
					流程名称:<input id="fileName2" class="easyui-textbox" name="fileName"  type="text" style="width: 110px">
				</td>
				<td style="padding-left:2px">
					<input id="file2" name="file"  type="file"size=100/>
				</td>
			</tr>
			<tr>
				<td style="padding-left:2px">
					流程名称:<input id="fileName3" class="easyui-textbox" name="fileName"   type="text"  style="width: 110px">
				</td>
				<td style="padding-left:2px">
					<input id="file3" name="file" type="file"size=100/>
				</td>
			</tr>	
			<tr>					
				<td style="padding-left:2px">
				    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width: 100px;" onclick="upload();">上传文件</a>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>