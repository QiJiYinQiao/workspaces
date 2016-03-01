<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String taskId = request.getParameter("taskId");
%>
<script type="text/javascript">
	var $grid ;
	$(function(){
		var $taskId = $("#taskId").val();
		// 查看申请状态
		var row = parent.$.modalDialog.openner.datagrid('getSelected');
		$grid = $("#lookLoanOrderdg").datagrid({
			url : "investOrderHis/investOrderHisAction!findAllInvestOrderHisList.action",
			width : 'auto',
			height : 610,
			pagination:true,
			rownumbers:true,
			border:true,
			singleSelect:true,
			nowrap:true,
			queryParams:{"investOrderId":row.investOrderId},
			multiSort:false,
			columns : [ [ 
			              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),sortable:true,align:'center'},
			              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1),align:'center'},
			              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'center'},
			              {field : 'comment',title : '审批意见',width :parseInt($(this).width()*0.1),align : 'center'},
			              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.09),align : 'center',
			            	formatter:function(value,row,index){
			            		return "<a href='javascript:void(0);' onclick='lookAttachment("+index+");'>查看附件</a>　　" ;
			            	}  
			              }
			              ] ]
		});
		
		//是否显示查看附件
		$.ajax({
			url:"investApply/investApplyAction!isShowAttachment.action",
			type:"post",
			async:false,
			data:{"taskId":$taskId},
			success:function(data){
				if(data){
					$("#lookLoanOrderdg").datagrid("hideColumn","id");
				}
			}	
		});
	});
	
	// 根据行索引获取行信息
	function getRowData (index) {
        if (!$.isNumeric(index) || index < 0) { return undefined; }
        var rows = $grid.datagrid("getRows");
        return rows[index];
    }

	// 查看附件信息
	function lookAttachment(index){
			var row = getRowData(index);
			console.info(row);
			$('#attachmentList').dialog({
					title:"附件信息",
					width: 1000,    
				    height: 650,    
				    closed: false,    
				    cache: false,    
				    modal: true   
			});
			$("#lookAttachmentList").datagrid({
				url : "attachment/attachmentAction!findAllAttachmentList.action",
				/* url : "attachment/attachmentAction!findAttachmentListByUserIdAndOrderId.action", */
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
				            			result += "<a target='_blank' href='javascript:void(0);' onclick=\"downloadAttachment4InvestOrder('"+row.attId+"');\">下载</a>　　" ;
				            		return result;
				              }}
			    ] ]
			});
	}
</script>
<table id="lookLoanOrderdg" title="申请备注的信息"></table>
<div id="attachmentList">
	<input type="hidden" id="taskId" value="<%=taskId%>">
	<table id="lookAttachmentList" title="申请附件的信息"></table>
</div>
