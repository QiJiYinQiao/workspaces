<%@page import="com.oasys.model.OvertimeApp"%>
<%@page import="com.oasys.util.Constants"%>
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
<title>待办任务主页面</title>
</head>
<script type="text/javascript">
var $$row;
var $dialog;
var $dialog1;
$(function(){
	createWaitTaskGrid();
});

//上传附件,明细存储taskId
$("#upploadAttachment").click(function(){
	fileUploadsDlg($row.appNo);
});
//查看附件
$("#checkAttachment").click(function(){
	checkAttachementDetail($row.appNo,$row.taskModel.assistant,'');
});

function lookAttachment(index){
	var row=getRowData(index)
	checkAttachementDetail($row.appNo,row.handler,'');
}

var $grid;
//渲染grid
function createWaitTaskGrid(){
	$(window).resize(function(){  
        $("#waitTaskGrid").datagrid("resize",{  
        	height: $(window).height()-100,
        	width : 'auto'
        });                
    });
	<%String definitionKey=request.getParameter("key");%>//流程变量
	$grid=$("#waitTaskGrid").datagrid({
<%-- 		url:'callingCard/QueryCardTask.do?processKey=<%=Constants.CALLINGCARD_HO%>', --%>
		url:'overtie/QueryOvertimeTask.do?processKey=<%=definitionKey%>',
		width: 'auto',
		height : $(this).height()-80,
		pagination:true,
		rownumbers:true,
		border:true,
		singleSelect:false,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		pageSize:30,
		pageList:[10,20,30,40],
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns:[[
			{field : 'ck',        title : 'ck',    width : $(this).width * 0.1, align:'center',checkbox:true},					             
			{field : 'oveId',        title : '编号',    width : $(this).width * 0.1, align:'center',
				        	 formatter: function(value,row,index){
				        		 return row.oveId;
				        	 }},
			{field : 'taskId',        title : 'TASK_ID',    width : $(this).width * 0.1, align:'center',
			      	 formatter: function(value,row,index){
			      		 return row.taskId;
							}},
			{field : 'formKey',        title : 'FORM_KEY',    width : 60, align:'center' ,
				        	 formatter: function(value,row,index){
				        		 return row.formKey;
			    				}},
			{field : 'appNo',        title : '申请编号',    width : parseInt($(this).width()*0.1), align:'center' },
		    {field : 'applicantNo',        title : '姓名',    width :parseInt($(this).width()*0.05), align:'center' },
			{field : 'position',    title : '职位',    width :parseInt($(this).width()*0.06), align:'center' },
			{field : 'deptName',    title : '部门名称',    width :parseInt($(this).width()*0.06), align:'center' },
		    {field : 'appDate',    title : '申请日期',    width :parseInt($(this).width()*0.06), align:'center' },
			{field : 'planBgDtime',    title : '计划开始日期时间',    width :parseInt($(this).width()*0.12), align:'center' },
			{field : 'planEdDtime',    title : '计划结束日期时间',    width :parseInt($(this).width()*0.12), align:'center' },
			{field : 'planOtCnt',    title : '计划加班合计(小时)',    width :parseInt($(this).width()*0.08), align:'center' },
			{field : 'realBgDtime',    title : '实际开始日期时间',    width :parseInt($(this).width()*0.12), align:'center' },
			{field : 'realEdDtime',    title : '实际结束日期时间',    width :parseInt($(this).width()*0.12), align:'center' },
			{field : 'realOtCnt',    title : '实际加班合计(小时)',    width :parseInt($(this).width()*0.08), align:'center' },
			{field : 'remark',    title : '备注信息',    width :parseInt($(this).width()*0.08), align:'center' },
		    {field : 'caozuo',    title : '操作',    width : $(this).width * 0.1, align:'center',
				formatter: function(value,row,index){
					var result = "";
				    if (row.taskModel.assistant == null || row.taskModel.assistant == "") {
						result += "<a href='javascript:void(0);' onclick='holdWorkTask("+index+");'>签收任务</a>　　";
					}else{
						result += "<a href='javascript:void(0);' onclick='handleTaskDialog("+index+");'>办理任务</a>　　";
					}
				    result += "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>　　";
					result += "<a href='javascript:void(0);' onclick='showImage("+index+");'>查看流程图</a>　　";
					return result;        		             		  					           		  
				}
			}
	   ]],
	   toolbar:'#tb',
	   onLoadSuccess:function(data){
              var paramObj = {rowStr:'deptName,appNo',//按照哪列进行分组
			rowGroup:'deptName:appNo,caozuo'};//对应rowStr的需要合并单元格的字段  
			dataGirdSumMergeFunc($(this),paramObj); 
	  }
	});
	$('#waitTaskGrid').datagrid("doCellTip",{'max-width':'100px'});
	$('#waitTaskGrid').datagrid('hideColumn', 'oveId');
	$('#waitTaskGrid').datagrid('hideColumn','taskId');
	$('#waitTaskGrid').datagrid('hideColumn','formKey');
}
//执行高级查询
function doSearch(){			
	$("#waitTaskGrid").datagrid("load",{
		appDateBefore:$('#queryDateBeginId').datebox('getValue'),
		appDateAfter:$('#queryDateEndId').datebox('getValue')
		});
}
//重置条件
function clearAdvancedQueryConditions(){
	//1、清空高级查询各组件内容
	$("#searchForm").form("clear");
	//2、datagrid重新加载
	$("#waitTaskGrid").datagrid("load",{});
}
//根据index获取该行
function getRowData(index){
	if (!$.isNumeric(index) || index < 0) {
		return undefined;
	}
	var rows = $("#waitTaskGrid").datagrid("getRows");
	return rows[index];
}
//查看流程图
function showImage(index){
	var row = getRowData(index);
	var src="workflowAction/showProcessImgByBusinessKey.do?processKey="+row.definitionKey+"&resName="+row.resourcesName+"&busID="+row.oveId;
	$('#imageDialog').dialog("open");
	$("#image").attr("src", src);
}
//签收任务
function holdWorkTask(index){
	var row = getRowData(index);
	$.ajax({
		type:"POST",
		url:"callingCard/SignTask.do",
		data:{"taskId" : row.taskId},
		dataType:"JSON",
		success:function(rsp){
			if(rsp.status){
				$.messager.alert("提示","签收成功","warning");
			}else{
				parent.$.messager.alert(rsp.title,rsp.message,'warning');
			}
			$("#waitTaskGrid").datagrid('reload');
		}
	});
}
//办理任务
function handleTaskDialog(index){
	var selectedRow = getRowData(index);
	$selRow = selectedRow;
	$$row = $selRow;
	$dialog1=$("<div></div>").dialog({
		width : 920,
		height : $(this).height()*0.7,					
		title : '受理任务',
		href :$selRow.formKey,
	    onLoad:function(){
	    	var taskForm = $("#taskForm");
	    	taskForm.form("load",selectedRow);
	    	$("#taskForm #businessID").val(selectedRow.oveId);
	    	$("#taskForm #taskID").val(selectedRow.taskId);
	    },
		modal:true,
		resizable:false,
		closed: false,
		iconCls:'icon-add',
	    buttons : [{
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$dialog1.dialog('close');
		}
		}],onClose:function(){
	    	//刷新列表
	    	$("#dg").datagrid("reload");
	    	//关闭弹窗
	    	$(this).dialog('destroy');
	    }
	}); 
}
//批量处理任务
function signTask(){
	var rows = $grid.datagrid("getSelections");
	var businessID = new Array();
	var formKey = "";
	var taskID = new Array();
	var appNo = new Array;
	if(rows==null || rows.length<=0){
		$.messager.alert("提示","请选择至少一条记录!","warning");
		return false;
	}
	for(var i=0;i<rows.length;i++){
		if($.inArray(rows[i].oveId,businessID)==-1){
			businessID.push(rows[i].oveId);
			formKey=rows[i].formKey;
			taskID.push(rows[i].taskId);
			appNo.push(rows[i].appNo);
		}
	}
	var data1 = "appNo="+appNo.join(",")+"&businessID="+businessID.join(",")+"&taskID="+taskID.join(",")+"&formKey="+formKey;
	ajaxLoading("正在处理 请稍后...");
 	  $.ajax({
		type: "POST",
		url:"overtime/saveTaskOvertimeAppBatch.do",
		data:data1,
	    success: function(iJson) {
			$("#dg").datagrid("reload");
	    	$("#waitTaskGrid").datagrid("load",{});
	    	ajaxLoadEnd();
 	    	$.messager.alert(iJson.title,iJson.message,'info');
	    }
	});  
}
//查看历史审批意见
function checkHistoryOpinions(index){
	$$row = getRowData(index);
	$dialog=$("<div></div>").dialog({
	/* 动态显示Dialog的标题	*/
	width : 800,
	height : 450,					
	title : "查看审批意见",
	href : "jsp/ad/optionsList.jsp",
	onClose:function(){
		$$row=null;
    	$(this).dialog('destroy');
	},
	modal:true,
	resizable:false,
	iconCls:'icon-add',
	closed: false
});
}
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置： 任务管理  &gt; 待办任务</div>
<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
<form id="searchForm" action="overtime/index.do" method="post">
	<table cellpadding="0" cellspacing="1" border="0">
		<tr>
			<td>加班时间(实际)：&nbsp;&nbsp;</td>
			<td><input name="queryDateBegin" id="queryDateBeginId" class="easyui-datetimebox" editable="false" style="width:174px;"/></td>
			<td>至</td>
			<td><input name="queryDateEnd" id="queryDateEndId" class="easyui-datetimebox" editable="false" style="width:174px;"/></td>	
			<td align="right" style="float:right;text-align: right;">
			    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td>  
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">条件重置</a>
			</td>
		</tr>					
	</table>
</form>
</div>
<div id="tb" style="padding:2px 0">
	<a id="id4ExportReports" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="signTask();">批量受理任务</a>
</div>
<table id="waitTaskGrid"></table>
<!-- 新增弹框 -->
<div id="addWindow"></div>
<div id="handleTask"></div>
<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
	<img id="image" src="" >
</div>
<div id="optionsDialog" class="easyui-dialog" title="历史审批意见" style="width:900px;height:400px;" closed="true" data-options="href:'jsp/ad/optionsList.jsp',resizable:true,modal:true"></div>
</body>
</html>