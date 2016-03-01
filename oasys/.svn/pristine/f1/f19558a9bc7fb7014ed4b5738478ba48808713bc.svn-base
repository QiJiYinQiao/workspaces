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
var $banliWindow;
var $waitTaskGrid;
var $selRow;
var $row;
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
		url:"purchaseAppController/findAllPurchaseAppTaskList.do?processKey=<%=definitionKey %>",
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
                {field : 'fullName',title : '申请部门',width : 100,align : 'center'},
		        {field : 'appNo',title : '申请编号',width :120,align : 'center'},
                {field : 'account',title : '申请人',width : 100,align : 'center'},
	            {field : 'myId',title : '所属业务端',width : 80,align : 'center',formatter:function(value,row,index){
	            	if("JK" == value){
	            		return "借款";
	            	}else if("CF" == value){
	            		return "财富";
	            	}else{
	            		return "职能";
	            	}
	            }},
                {field : 'totalAmt',title : '总计金额(元)',width : 80,align : 'center'},
                {field : 'appDate',title : '申请日期',width : 80,align : 'center'},
                {field : 'planRecDate',title : '计划到货日期',width : 80,align : 'center'},
                {field : 'procStatus',title : '流程状态',width : 80,align : 'center',formatter:function(value,row,index){
                	if(row.procStatus == "1"){
                		return "初始状态";
                	}else if(row.procStatus == "2"){
                		return "审批中";
                	}else if(row.procStatus == "3"){
                		return "已完成";
                	}else{
                		return "已撤销";
                	}
                }},
                {field : 'remark',title : '备注',width : 110,align : 'center'},
                {field : 'articleName',title : '物品名称',width : 80,align : 'center'},
                {field : 'model',title : '型号规格',width : 80,align : 'center'},
                {field : 'price',title : '单价',width : 80,align : 'center'},
                {field : 'qty',title : '数量',width : 80,align : 'center'},
                {field : 'ztotalAmt',title : '小计(元)',width : 80,align : 'center'},
                {field : 'heji',title : '合计(元)',width : 80,align : 'center'},
                {field : 'purpose',title : '用途',width : 80,align : 'center'},
               /*  {field : 'userName',title : '使用人',width : 80,align : 'center'},
                {field : 'depositaryName',title : '保管人',width : 80,align : 'center'}, */
                {field : 'zremark',title : '备注信息',width : 80,align : 'center'},
                {field : 'caozuo',title : '操作',width :180,align : 'center',formatter:function(value,row,index){
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
	 	   var paramObj = {rowStr:'fullName,appNo',//按照哪列进行分组
					rowGroup:'fullName,heji:ck,appNo,account,totalAmt,appDate,planRecDate,procStatus,remark,caozuo,myId',//对应rowStr的需要合并单元格的字段
					hejiColumn:'ztotalAmt',//需要计算的合计值的所在列
					hejiResult:'heji'};//计算后将合计值更新到该列中 */
		   dataGirdSumMergeFunc($(this),paramObj);
		  
           $(this).datagrid("doCellTip",{'max-width':'100px'});
           $waitTaskGrid.datagrid('hideColumn', 'paId');
           $waitTaskGrid.datagrid('hideColumn','taskId');
           $waitTaskGrid.datagrid('hideColumn','appNo');
         /*   $waitTaskGrid.datagrid('hideColumn','userName'); */
           $waitTaskGrid.datagrid('hideColumn','formKey');
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
	var row = getRowData(index);
	$.ajax({
		type:"POST",
		url:"purchaseAppController/holdWorkTask.do",
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
	$selRow = getRowData(index);
	$row = $selRow;
	$.ajax({
		type:"POST",
		url:"workflowAction/findTaskFormKeyByTaskId.do",
		data:{"taskId":$selRow.taskId},
		success:function(jspName){
		   $banliWindow = $("<div></div>").dialog({
					title:'办理任务',
					width: $(window).width()*0.49,    
				    height: $(window).height()*0.66,
					modal:true,
					href:jspName,
					onLoad:function(){
				    	var taskForm = $("#taskForm");
				    	taskForm.form("load",$selRow);
				    	$("#taskForm #businessID").val($selRow.paId);
				    	$("#taskForm #formKey").val($selRow.taskModel.formKey);
				    	$("#taskForm #taskID").val($selRow.taskId);
				    },
					onClose : function(){
						$waitTaskGrid.datagrid("reload");
						$(this).dialog('destroy');
						$selRow = null;
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
	var taskID = new Array();
	var appNo = new Array;
	if(rows==null || rows.length<=0){
		$.messager.alert("提示","请选择至少一条记录!","warning");
		return false;
	}
	for(var i=0;i<rows.length;i++){
		if($.inArray(rows[i].paId,businessID)==-1){
			businessID.push(rows[i].paId);
			formKey = rows[i].formKey;
			taskID.push(rows[i].taskId);
			appNo.push(rows[i].appNo);
		}
	}
	var data1 = "appNo="+appNo.join(",")+"&businessID="+businessID.join(",")+"&taskID="+taskID.join(",")+"&formKey="+formKey;
 	 $.ajax({
		type: "POST",
		url:"purchaseAppController/submitTaskBatch.do",
		data:data1,
	    success: function(iJson) {
	    	$waitTaskGrid.datagrid("load",{});
 	    	$.messager.alert(iJson.title,iJson.message,'info');
	    }
	});  
}
var $$row;
//查看历史审批意见
function checkHistoryOpinions(index){
	var rows = $("#waitTaskGrid").datagrid("getRows");
	$$row = rows[index];//获取本条数据
	var $optionsWindow = $("<div></div>").dialog({
		title: '历史审批意见',    
		width: $(window).width()*0.44,    
	    height: $(window).height()*0.63,    
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
//查看流程图
function showImage(index){
	var rowdata = getRowData(index);
	var src = "purchaseAppController/showProcessImg.do?paId="+rowdata.paId;
	$('#imageDialog').dialog("open");
	$("#image").attr("src", src);
}
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置： 任务管理  &gt; 任务办理</div>
<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
	<form id="searchForm" style="min-width:900px;">
	  <table>
	    <tr>
	      <td>申请编号:</td>
	      <td><input name="appNo" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,12]'"/></td>
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