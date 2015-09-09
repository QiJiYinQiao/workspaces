<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史审批意见</title>
</head>
<script type="text/javascript">
$(function(){
	createOptionsGrid();
});
//审批意见列表
 function createOptionsGrid(){
	$("#optionsGrid").datagrid({
		url : "investOrderHis/investOrderHisAction!findAllInvestOrderHisList.action",
		width : 'auto',
		height : 463,
		pagination:false,
		rownumbers:true,
		border:false,
		singleSelect:true,
		nowrap:true,
		queryParams:{"investOrderId":row.investOrderId},
		multiSort:false,
		columns : [ [ 
		              {field : 'agentTime',title : '受理时间',width : 120,sortable:true,align:'center'},
		              {field : 'roleName',title : '受理角色',width : 120,align:'center'},
		              {field : 'assigneeName',title : '受理人',width :100,align:'center'},
		              {field : 'comment',title : '审批意见',width :300,align:'center'},
		              {field : 'id',title : '查看附件',width :100,align:'center',formatter:function(value,row,index){
		            		return "<a href='javascript:void(0);' onclick='lookAttachment("+index+");'>查看附件</a>" ;
		              }}
		] ]
	});
} 

// 根据行索引获取行信息
function getRowData (index) {
    if (!$.isNumeric(index) || index < 0) { return undefined; }
    var rows = $("#optionsGrid").datagrid("getRows");
    return rows[index];
}
//查看附件信息
function lookAttachment(index){
		var row = getRowData(index);
		$("#attachmentList").dialog("open");
		$("#lookAttachmentList").datagrid({
			url : "attachment/attachmentAction!findAllAttachmentList.action",
			width : 'auto',
			height : 430,
			pagination:false,
			rownumbers:true,
			border:false,
			singleSelect:true,
			nowrap:true,
			queryParams:{"orderId":row.investOrderId,"userId":row.assignee,"orderType":"attachment_type_invest"},
			multiSort:false,
			columns : [ [ 
			              {field : 'attName',title : '附件名称',width : 200,sortable:true,align:'center'},
			              {field : 'attTypeName',title : '附件类型',width : 160,align:'center'},
			              {field : 'creatorName',title : '创建者',width : 170,align:'center'},
			              {field : 'id',title : '查看附件',width :220,align:'center',formatter:function(value,row,index){
			            		var result = "<a target='_blank' href='jsp/openoffice/documentView.jsp?attId="+row.attId+"'>在线预览</a>　　" ;
			            			result += "<a target='_blank' href='javascript:void(0);' onclick=\"downloadAttachment('"+row.attId+"');\">下载</a>　　" ;
			            		return result;
			              }}
		    ] ]
		});
}
</script>
<table id="optionsGrid"></table>
<div id="attachmentList" class="easyui-dialog" title="附件信息" style="width:800px;height:500px;" data-options="resizable:true,modal:true" closed="true">
	<table id="lookAttachmentList"></table>
</div>
</html>