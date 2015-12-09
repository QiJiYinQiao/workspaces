<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../layout/script.jsp"></jsp:include>
<title>已办理任务主页面</title>
<style type="text/css">
.easyui-textbox {
	height: 18px;
	width: 170px;
	line-height: 16px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
}

textarea:focus, input[type="text"]:focus {
	border-color: rgba(82, 168, 236, 0.8);
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px
		rgba(82, 168, 236, 0.6);
	outline: 0 none;
}
.table {
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
	max-width: 100%;
}
fieldset {
	    border-width: 1px;
	    margin-top: 5px;
	    border-color:#F5F5F5;
	    -moz-border-radius:8px;
}
input, textarea {
	font-weight: normal;
}

.table td {
	padding: 6px;
}
.table th{
    text-align: right;
	padding: 6px;
}
.textStyle{
  text-align: right;
}
.dis{
	display: none;
}
a{
text-decoration:none;
}
</style>
</head>
<script type="text/javascript">
$(function(){
	createOptionsGrid();
}); 
//审批意见列表
 function createOptionsGrid(){
	$("#optionsGrid").datagrid({
		url : "workFlowTaskAction/getAppTypeListByPre.do?preAppNo=<%=request.getParameter("appNoPre")%>",
		title:'已办理任务列表',
		width :'auto',
		height :'auto',
		pagination:true,
		pageList:[10,20,30,50],
		rownumbers:true,
		border:true,
		singleSelect:true,
		nowrap:true,
		multiSort:false,
		fitColumns:false,
		columns : [ [ 
		              {field : 'appNo',title : '申请编号',width : parseInt($(this).width() * 0.15),align:'center'},
		              {field : 'appStatus',title : '申请状态',width : parseInt($(this).width() * 0.2),align:'center',
		            	  formatter:function(value,row,index){
		            		  return getStatusNameById(value);
		            	  }},
		              {field : 'handleDate',title : '受理时间',width :parseInt($(this).width() * 0.2),align:'center'},
		              {field : 'handleResult',title : '处理结果',width :parseInt($(this).width() * 0.2),align:'center'},
		              {field : 'remark',title : '备注信息',width :parseInt($(this).width() * 0.2),align:'center'},
		] ],
		onLoadSuccess:function(data){
			$(this).datagrid("doCellTip",{'max-width':'100px'});
		}
	});
} 

/**根据申请状态id查询申请状态*/
function getStatusNameById(id){
	var statusName="";
	$.ajax({
		url:'auditProcHisController/getStatusNameByID.do',
		type:'post',
		data:"id="+id,
		async:false,
		success:function(data){
			if(data){
				if(data.refId==id){
					statusName=data.appStatusName;
				}
			}
		},
		error:function(data){}
	})
	return statusName;
}
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置： 任务管理 -> 查看已办理任务</div>
	<table id="optionsGrid"></table>
</body>
</html>