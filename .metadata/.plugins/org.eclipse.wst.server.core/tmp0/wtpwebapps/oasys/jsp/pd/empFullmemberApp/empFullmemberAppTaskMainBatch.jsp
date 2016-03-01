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
var $selRow;
var $row;
var $$row;
var $banliWindow;
var $waitTaskGrid;
$(function(){
	$(window).resize(function(){
		$("#waitTaskGrid").datagrid('resize',{
			height : $(this).height()-90
		});
	});
	createWaitTaskGrid();
});
<%String definitionKey=request.getParameter("key");%>
//渲染grid
function createWaitTaskGrid(){
	$waitTaskGrid = $("#waitTaskGrid").datagrid({
		url:'empFullmemberAppController/findAllEmpFullmemberAppTaskList.do?processKey=<%=definitionKey%>',
		width: 'auto',
		height : $(this).height()-90,
		pagination:true,
		rownumbers:true,
		border:false,                                                            
		singleSelect:false,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		pageSize:30,
		pageList:[10,20,30,40],
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns:[[
                {field : 'ck',title:'ck',checkbox:true},
                {field : 'deptName',title : '所属部门',width : 170,align : 'center'},
                {field : 'appNo',title : '申请编号',width :120,align : 'center'},
                {field : 'userName',title : '申请人',width : 70,align : 'center'},
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
                {field : 'caozuo',title : '操作',width :220,align : 'center',formatter:function(value,row,index){
                	var result = ""; 
				    if (row.taskModel.assistant == null || row.taskModel.assistant == "") {
						result += "<a href='javascript:void(0);' onclick='holdWorkTask("+index+");'>签收任务</a>&nbsp;&nbsp;";
					}else{
						result += "<a href='javascript:void(0);' onclick='handleTaskDialog("+index+");'>办理任务</a>&nbsp;&nbsp;";
					} 
				    result += "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>&nbsp;&nbsp;";
				    result += "<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看流程图</a>";
					return result;
                }}
	   ]],
	   toolbar:'#tb',
	   onLoadSuccess:function(data){
		  var mergeMap = getMergeMap( $(this).datagrid("getRows"),'deptName','');
     	  mergeFunc($(this),mergeMap,'deptName');
          $(this).datagrid("doCellTip",{'max-width':'100px'});
	  }
	});
}
//根据index获取该行
function getRowData(index){
	if (!$.isNumeric(index) || index < 0) {
		return undefined;
	}
	var rows = $waitTaskGrid.datagrid("getRows");
	return rows[index];
}
//签收任务
function holdWorkTask(index){
	var rows = $waitTaskGrid.datagrid("getRows");
	var row = rows[index];
	$.ajax({
		type:"POST",
		url:"empFullmemberAppController/holdWorkTask.do",
		data:{"taskId" : row.taskId},
		dataType:"JSON",
		success:function(msg){
			if(msg.status){
				$waitTaskGrid.datagrid('reload');
			}
			$.messager.alert(msg.title,msg.message,'info');
		}
	});
}
//办理任务
function handleTaskDialog(index){
	var rows = $waitTaskGrid.datagrid("getRows");
	$selRow = rows[index];
	$row = $$row = $selRow;
	$.ajax({
		type:"POST",
		url:"workflowAction/findTaskFormKeyByTaskId.do",
		data:{"taskId":$selRow.taskId},
		success:function(jspName){
			 $banliWindow = $("<div></div>").dialog({
					title:'办理任务',
					width:850,
					height:$(window).height()*0.63,
					modal:true,
					href:jspName,
					onLoad:function(){
				    	var taskForm = $("#taskForm");
				    	taskForm.form("load",$selRow);
				    	$("#taskForm #businessID").val($selRow.efaId);
				    	$("#taskForm #formKey").val($selRow.taskModel.formKey);
				    	$("#taskForm #taskID").val($selRow.taskId);
				    },
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
//批量办理任务
function signTask(){
	var rows = $waitTaskGrid.datagrid("getSelections");
	var businessID = new Array();
	var formKey = "";
	var taskId = new Array();
	var appNo = new Array;
	if(rows==null || rows.length<=0){
		$.messager.alert("提示","请选择至少一条记录!","warning");
		return false;
	}
	for(var i=0;i<rows.length;i++){
		if($.inArray(rows[i].efaId,businessID)==-1){
			businessID.push(rows[i].efaId);
			formKey =  rows[i].formKey;
			taskId.push(rows[i].taskId);
			appNo.push(rows[i].appNo);
		}
	}
	var data1 = "appNo="+appNo.join(",")+"&businessID="+businessID.join(",")+"&taskID="+taskId.join(",")+"&formKey="+formKey;
 	  $.ajax({
		type: "POST",
		url:"empFullmemberAppController/submitTaskBatch.do",
		data:data1,
	    success: function(iJson) {
	    	$waitTaskGrid.datagrid("load",{});
 	    	$.messager.alert(iJson.title,iJson.message,'info');
	    }
	});  
}
//查看历史审批意见
function checkHistoryOpinions(index){
	var rows = $waitTaskGrid.datagrid("getRows");
	$$row = rows[index];//获取本条数据
	var $optionsWindow = $("<div></div>").dialog({
		title: '历史审批意见',    
		width: 770,    
	    height: $(window).height()*0.63,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/pd/optionsList.jsp',    
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
//高级搜索
function toSearch(){
	var data = $("#searchForm").serializeObject();
	$waitTaskGrid.datagrid("reload",data);
};
//查看流程图
function showImage(index){
	var rows = $waitTaskGrid.datagrid("getRows");
	var row = rows[index];
	var src = "empFullmemberAppController/showProcessImg.do?efaId="+row.efaId;
	$('#imageDialog').dialog("open");
	$("#image").attr("src", src);
}
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置： 任务管理  &gt; 待办任务</div>
<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
	<form id="searchForm" style="min-width:900px;">
	  <table>
	    <tr>
	      <td>申请编号:</td>
	      <td><input name="appNo" class="easyui-textbox"/></td>
	      <td>支付方式:</td>
	      <td><input name="payMode" class="easyui-textbox" editable="false" panelHeight="auto"/></td>
	      <td>申请日期:</td>
	      <td>
	         <input id="appDateMini" name="appDateMini" class="easyui-textbox easyui-datebox" editable="false"/>
	                      至
	         <input id="appDateMax" name="appDateMax" class="easyui-textbox easyui-datebox" editable="false"/>
	      </td>
	      <td>
	         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="toSearch();">搜索</a>
	         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#searchForm').form('clear')">重置</a>
	      </td>
	    </tr>
	  </table>
	</form>
</div>
<table id="waitTaskGrid"></table>
<div id="tb" style="padding:2px 0">
	<a id="id4ExportReports" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="signTask();">批量受理任务</a>
</div>
<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
	<img id="image" src="" >
</div>
</body>
</html>