<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.oasys.util.Constants"%>
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
<title>员工转正申请</title>
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
</style>
</head>
<script type="text/javascript">
var $datagrid;
var $row;
var $$row;
$(function(){
	createTaskGrid();
});
<%String definitionKey=request.getParameter("key");%>
function createTaskGrid(){
	$datagrid = $("#taskGrid").datagrid({
		url:'empFullmemberAppController/findAllEmpFullmemberAppTaskList.do?processKey=<%=definitionKey%>',
		title:'员工转正申请',
		width: 'auto',
		height : $(this).height()-40,
		pagination:true,
		rownumbers:true,
		border:false,
		singleSelect:true,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		pageSize:30,
		pageList:[10,20,30,40],
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns:[[
		        {field : 'appNo',title : '申请编号',width :120,align : 'center'},
                {field : 'userName',title : '申请人',width : 70,align : 'center'},
	            {field : 'deptName',title : '所属部门',width : 100,align : 'center'},
                {field : 'entryDate',title : '入职日期',width : 80,align : 'center'},
                {field : 'bgProbDate',title : '试用期开始日期',width : 100,align : 'center'},
                {field : 'edProbDate',title : '试用期结束日期',width : 100,align : 'center'},
                {field : 'regularSal',title : '转正薪资(元)',width : 80,align : 'center'},
                {field : 'workYears',title : '工作经验年限(年)',width : 100,align : 'center'},
                {field : 'position',title : '职位',width : 80,align : 'center'},
                {field : 'graduateSchool',title : '毕业院校',width : 80,align : 'center'},
                {field : 'major',title : '专业',width : 80,align : 'center'},
                {field : 'educationName',title : '学历',width : 80,align : 'center'},
                {field : 'gender',title : '年龄',width : 60,align : 'center'},
                {field : 'chooseModeName',title : '甄选方式',width : 80,align : 'center'},
                {field : 'appStatusName',title : '申请状态',width : 80,align : 'center',formatter:function(value,row,index){
                	if(value == null || value == ""){
                		return "初始状态";
                	}
                	return value;
                }},
                {field : 'appDate',title : '申请日期',width : 90,align : 'center'},
                {field : 'workExplain',title : '工作说明',width : 100,align : 'center'},
                {field : 'caozuo',title : '操作',width :180,align : 'center',formatter:function(value,row,index){
                	var result = ""; 
				    if (row.taskModel.assistant == null || row.taskModel.assistant == "") {
						result += "<a href='javascript:void(0);' onclick='holdWorkTask("+index+");'>签收任务</a>　　";
					}else{
						result += "<a href='javascript:void(0);' onclick='handleTaskDialog("+index+");'>办理任务</a>　　";
					} 
				    result += "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>　　";
					return result;
                }}
	   ]],
	   onLoadSuccess:function(data){
           $(this).datagrid("doCellTip",{'max-width':'100px'});
	   }
	});
}
//签收任务
function holdWorkTask(index){
	var rows = $("#taskGrid").datagrid("getRows");
	var row = rows[index];
	$.ajax({
		type:"POST",
		url:"empFullmemberAppController/holdWorkTask.do",
		data:{"taskId" : row.taskId},
		dataType:"JSON",
		success:function(msg){
			if(msg.status){
				$datagrid.datagrid('reload');
			}
			$.messager.alert(msg.title,msg.message,'info');
		}
	});
}
//办理任务
function handleTaskDialog(index){
	var rows = $datagrid.datagrid("getRows");
	$selRow = rows[index];
	$$row = rows[index];
	$.ajax({
		type:"POST",
		url:"workflowAction/findTaskFormKeyByTaskId.do",
		data:{"taskId":$selRow.taskId},
		success:function(jspName){
			 $banliWindow = $("<div></div>").dialog({
					title:'办理任务',
					width:870,
					height:530,
					modal:true,
					href:jspName,
					onLoad:function(){
						$("#taskForm #businessID").val($selRow.efaId);
				    	$("#taskForm #taskID").val($selRow.taskId);
				    	$("#taskForm #formKey").val($selRow.taskModel.formKey);
				    	$("#taskForm").form("load",$selRow);
				    },
					onClose:function(){
						$datagrid.datagrid('reload');
						$(this).dialog('destroy');
						$row = null;
					},
					buttons:[{
				    	text:'关闭',
				    	iconCls:'icon-cancel',
						handler:function(){
							$banliWindow.dialog('close');
						}
				    }]
			 }); 
		}
	});
}
//查看历史审批意见
function checkHistoryOpinions(index){
	var rows = $("#taskGrid").datagrid("getRows");
	$$row = rows[index];//获取本条数据
	$("<div></div>").dialog({
		title: '历史审批意见',    
	    width: 850,    
	    height: 500,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/pd/optionsList.jsp',    
	    modal: true,
	    onClose : function(){
	    	$(this).dialog('destroy');
	    	$$row = null;
        }
	});
}
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置：任务管理  &gt; 任务办理 </div>
<table id="taskGrid"></table>
<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
	<img id="image" src="" >
</div>
</body>
</html>