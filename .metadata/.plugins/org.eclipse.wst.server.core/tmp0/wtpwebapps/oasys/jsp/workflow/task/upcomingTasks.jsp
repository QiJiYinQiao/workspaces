<%@page import="com.oasys.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<jsp:include page="../../../layout/script.jsp"></jsp:include>
	<style type="text/css">
		a{
			text-decoration:none;
		}
	</style>
	<script type="text/javascript">
			var $dg;
			var $grid;
			var $key;
 		 	$(function() {
 		 		//加载待办任务下拉框列表
				$('#processKey').combobox({
 					url:'workFlowTaskAction/getProcNameList.do',
 					valueField:'listURL',
 					textField:'processName',
 					formatter: function (data) {
                        return data.processName;
                    },
 					onSelect: function(rec){
 						$key=rec.processKey;
 					}
 				});
			}); 
 		 	function doTaskFunc(){
 		 		var taskURL = $('#processKey').combobox('getValue');
 		 		if(taskURL == ''){
 		 			alert('请选择待办任务种类');
 		 		}else{
 		 			var effort="";
 		 			if(taskURL.indexOf("?")>-1){
 	 		 		 	effort = $('#processKey').combobox('getText')+ "任务||" + "" + "||" +taskURL+"&key="+$key ;
 		 			}else{
 		 				effort = $('#processKey').combobox('getText')+ "任务||" + "" + "||" +taskURL+"?key="+$key ;
 		 			}
 		 			// 加载Tab页卡
 					parent.addTab(effort);
 		 		}

 		 	}
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      	<div class="position" style="margin-top: 5px;">您当前所在位置： 任务管理  &gt; 待办任务</div>
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
  		<form  action="workFlowTaskAction/getAllUpComingTaskList.do" method="post">
  				<table cellpadding="0" cellspacing="1"  border="0">
					<tr>
						<td>[选择待办任务种类]</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						选择待办任务种类：<select id="processKey" class="easyui-combobox" name="processKey"  style="width: 170px;"></select>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doTaskFunc();">办理任务</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
  		</form>
		</div>
  	</div>	
  </body>
</html>
