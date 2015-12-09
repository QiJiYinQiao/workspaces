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
<title>待办任务主页面</title>
</head>
<script type="text/javascript">
var $row;
var $$row;
var $banliWindow;
var $waitTaskGrid;
$(function(){
	createWaitTaskGrid();
});
<%String definitionKey=request.getParameter("key");%>
//渲染grid
function createWaitTaskGrid(){
	$waitTaskGrid = $("#waitTaskGrid").datagrid({
		url:'expensesAppController/findAllExpensesAppTaskList.do?processKey=<%=definitionKey%>',
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
                {field : 'account',title : '申请人',width : 100,align : 'center'},
	            {field : 'fullName',title : '所属部门',width : 100,align : 'center'},
                {field : 'appDate',title : '申请日期',width : 150,align : 'center'},
                {field : 'appAmt',title : '申请金额(元)',width : 80,align : 'center'},
                {field : 'payModeName',title : '支付方式',width : 80,align : 'center'},
                {field : 'intoAct',title : '转入账号',width : 180,align : 'center'},
                {field : 'bankName',title : '银行名称',width : 80,align : 'center'},
                {field : 'actName',title : '账户名称',width : 80,align : 'center'},
                {field : 'billTypeName',title : '票据类型',width : 80,align : 'center'},
                {field : 'billTypeOther',title : '其他票据类型',width : 80,align : 'center'},
                {field : 'appStatus',title : '申请状态',width : 90,align : 'center',formatter:function(value,row,index){
                	if(value == null || value == ""){
                		return "初始状态";
                	}
                	return value;
                }},
                {field : 'remark',title : '备注信息',width : 240,align : 'center'},
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
//根据index获取该行
function getRowData(index){
	if (!$.isNumeric(index) || index < 0) {
		return undefined;
	}
	var rows = $("#waitTaskGrid").datagrid("getRows");
	return rows[index];
}
//签收任务
function holdWorkTask(index){
	var rows = $("#waitTaskGrid").datagrid("getRows");
	var row = rows[index];
	$.ajax({
		type:"POST",
		url:"expensesAppController/holdWorkTask.do",
		data:{"taskId" : row.taskId},
		dataType:"JSON",
		success:function(msg){
			if(msg.status){
				$("#waitTaskGrid").datagrid('reload');
			}
			$.messager.alert(msg.title,msg.message,'info');
		}
	});
}
//办理任务
function handleTaskDialog(index){
	var rows = $("#waitTaskGrid").datagrid("getRows");
	$row = rows[index];
	$.ajax({
		type:"POST",
		url:"workflowAction/findTaskFormKeyByTaskId.do",
		data:{"taskId":$row.taskId},
		onClose:function(){
			$("#waitTaskGrid").datagrid('reload');
		},
		success:function(jspName){
			 $banliWindow = $("<div></div>").dialog({
					title:'办理任务',
					width:850,
					height:480,
					modal:true,
					href:jspName,
					onClose:function(){
						$waitTaskGrid.datagrid('reload');
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
//查看流程图
function showImage(index){
	var rows = $("#waitTaskGrid").datagrid("getRows");
	var row = rows[index];
	var src = "expensesAppController/showProcessImg.do?btaId="+row.btaId;
	$('#imageDialog').dialog("open");
	$("#image").attr("src", src);
}
//查看历史审批意见
function checkHistoryOpinions(index){
	var rows = $("#waitTaskGrid").datagrid("getRows");
	$$row = rows[index];//获取本条数据
	var $optionsWindow = $("<div></div>").dialog({
		title: '历史审批意见',    
	    width: 900,    
	    height: 500,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/ad/optionsList.jsp',    
	    modal: true,
	    onClose : function(){
	    	$(this).dialog('destroy');
	    	$$row = null;
        },
        buttons : [ {
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$optionsWindow.dialog('close');
			}
		}]
	});
}
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置： 任务管理  &gt; 待办任务</div>
<table id="waitTaskGrid"></table>
<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
	<img id="image" src="" >
</div>
</body>
</html>