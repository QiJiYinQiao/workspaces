<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript">
	var $grid ;
	$(function(){
		// 查看申请状态
		var row = parent.$.modalDialog.openner.datagrid('getSelected');
		$grid = $("#lookLoanOrderdg").datagrid({
			url : "loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action",
			rownumbers:true,
			border:true,
			singleSelect:true,
			fitColumns:true,
			nowrap:true,
			queryParams:{"loanOrderId":row.loanOrderId},
			multiSort:false,
			columns : [ [ 
			              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),align : 'center'},
			              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1),align : 'center'},
			              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'center'},
			              {field : 'title',title : '审批简述',width :parseInt($(this).width()*0.1),align : 'center'},
			              /* {field : 'comment',title : '审批详情',width :parseInt($(this).width()*0.1),align : 'left'}, */
			              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.09),align : 'center',
			            	formatter:function(value,row,index){
			            		return "<a href='javascript:void(0);' onclick='lookAttachment("+index+");'>查看附件</a>　　" ;
			            	}  
			              }
			              ] ]
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
			checkAttachementDetail('',row.loanOrderId,row.assignee,'1');
			/* $('#attachmentList').dialog({
					title:"附件信息",
					width: 1000,    
				    height: 650,    
				    closed: false,    
				    cache: false,    
				    modal: true   
			});
			$("#lookAttachmentList").datagrid({
				url : "attachment/attachmentAction!findAttachmentListByUserIdAndOrderId.action",
				width : 'auto',
				height : 610,
				pagination:true,
				rownumbers:true,
				border:true,
				singleSelect:true,
				nowrap:true,
				queryParams:{"loanOrderId":row.loanOrderId,"userId":row.assignee},
				multiSort:false,
				columns : [ [ 
				              {field : 'attName',title : '附件名称',width : parseInt($(this).width()*0.1),sortable:true},
				              {field : 'attTypeName',title : '附件类型',width : parseInt($(this).width()*0.1)},
				              {field : 'creatorName',title : '创建者',width : parseInt($(this).width()*0.1),align : 'left'},
				              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.1),align : 'left',
				            	formatter:function(value,row,index){
				            		var result = "<a target='_blank' href='jsp/openoffice/documentView.jsp?attId="+row.attId+"'>在线预览</a>　　" ;
				            			result += "<a target='_blank' href='javascript:void(0);' onclick=\"downloadAttachment('"+row.attId+"');\">下载</a>　　" ;
				            		return result;
				            	}  
				              }
				              ] ]
			}); */
	}
</script>
<table id="lookLoanOrderdg" title="申请备注的信息"></table>
<div id="attachmentList">
	<table id="lookAttachmentList" title="申请附件的信息"></table>
</div>
